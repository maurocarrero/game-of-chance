package bingoservidor;

import bingo.common.exceptions.AccesoDenegadoException;
import bingo.common.exceptions.CantidadCartonesInvalidaException;
import bingo.common.exceptions.ConfiguracionNoValidaException;
import bingo.common.exceptions.DemasiadosCartonesException;
import bingo.common.exceptions.EstaLogueadoException;
import bingo.common.exceptions.JuegoEnCursoException;
import bingo.common.exceptions.SaldoInsuficienteException;
import bingo.common.interfaces.IBingo;
import bingo.common.interfaces.IJugador;
import bingo.common.interfaces.IPartida;
import bingo.common.interfaces.IRemoteObservable;
import bingo.common.interfaces.IRemoteObserver;
import bingo.servidor.modelo.entidades.Administrador;
import bingo.servidor.modelo.entidades.Usuario;
import bingo.servidor.persistencia.AdminPersistente;
import bingo.servidor.persistencia.JugadorPersistente;
import bingo.servidor.persistencia.ManejadorBD;
import bingo.servidor.persistencia.PartidaPersistente;
import bingo.servidor.persistencia.Persistente;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public class Bingo extends UnicastRemoteObject implements IBingo, IRemoteObservable, Serializable {

    private List<Usuario> usuariosTest = null;

    private IPartida partida;
    private Administrador admin;
    
    private ArrayList<IRemoteObserver> observers = null;
    
    private ManejadorBD db = null;
    
    public Bingo() throws RemoteException {

        String url = "jdbc:mysql://localhost/bingo?user=bingo&password=bingo";
        
        this.db = ManejadorBD.getInstancia();
        this.db.conectar(url);
        this.partida = new Partida();
        this.observers = new ArrayList<>();
        this.usuariosTest = new ArrayList<>();
        
        obtenerTodosLosUsuarios();
        obtenerConfiguracion();
    }
    
    private void obtenerTodosLosUsuarios() throws RemoteException {        
        agregarUsuarios(this.db.obtenerTodos((Persistente) new JugadorPersistente()));
        agregarUsuarios(this.db.obtenerTodos((Persistente) new AdminPersistente()));
    }
    
    private void obtenerConfiguracion() throws RemoteException {
        this.db.obtenerTodos((Persistente) new PartidaPersistente((Partida) getPartida()));
        notifyObservers(crearHash("BINGO", this));
    }  
    
    private void agregarUsuarios(ArrayList usuarios) {
        for (Object p : usuarios) {
            usuariosTest.add((Usuario) p);
        }
    }
        
    @Override
    public IPartida getPartida() throws RemoteException {
        return this.partida;
    }

    
    @Override
    public void ejecutar() throws RemoteException {
        partida.iniciar();
    }
    
    
    
    @Override
    public void guardarConfiguracion(int cantFilas, int cantColumnas, 
            int cantMaxCartones, int cantJugadores, double valorCarton, 
            int tiempo, List<String> figuras) 
                throws ConfiguracionNoValidaException, RemoteException {
        
        // VALIDACIONES
        if (cantFilas < 1 || cantFilas > 10 || cantColumnas < 2 || 
                cantColumnas > 10 || cantMaxCartones < 2 || cantJugadores < 2 || 
                valorCarton < 1 || tiempo < 10) {
            throw new ConfiguracionNoValidaException();
        }       
        
        this.partida.guardarConfiguracion(cantFilas, cantColumnas, cantMaxCartones, 
                cantJugadores, valorCarton, tiempo, figuras);

        notifyObservers(crearHash("BINGO", this));
    }
    
    private HashMap crearHash(String clave, Object valor){
        HashMap<String, Object> evento = new HashMap();
        evento.put(clave, valor);
        return evento;
    }
    
    private Usuario login(String usuario) throws RemoteException {
        for (Usuario u : this.usuariosTest) {
            if (u.getUsuario().equals(usuario)) {
                return u;
            }
        }
        return null;
    }

    
    
    @Override
    public void inicioCondicional() throws RemoteException {
        if (this.partida.getJugadores().size() == this.partida.getCantJugadores()) {
            this.partida.iniciar();
        }
    }
    
    
    
    private boolean demasiadosCartones(int cC) throws RemoteException {
        return cC > this.partida.getCantMaxCartones();
    }
    
    
    
    private double getPrecioCartones(int cantCartones) throws RemoteException {
        return cantCartones * this.partida.getValorCarton() * 2;
    }
    
    
    
    @Override
    public boolean loginAdmin(String usuario, char[] password) throws RemoteException {
        Administrador aux;
        try {
             aux = (Administrador) login(usuario);
             if (aux == null) {
                 throw new AccesoDenegadoException();
             }
             if (!aux.validate(password)) {
                 throw new AccesoDenegadoException();
             }

             this.admin = aux;
             return true;
             
        } catch (Exception ex) {
            return false;
        }    
    }
    
    
    
    @Override
    public IJugador loginJugador(String usuario, char[] password, int cantCartones) 
            throws AccesoDenegadoException, JuegoEnCursoException,
                CantidadCartonesInvalidaException, DemasiadosCartonesException, 
                SaldoInsuficienteException, EstaLogueadoException, RemoteException {
        
        IJugador jugador = (IJugador) login (usuario);
        
        // ACCESO DENEGADO
        if (jugador == null) {
            throw new AccesoDenegadoException();
        }
        if (!jugador.validate(password)) {
            throw new AccesoDenegadoException();
        }

        // JUEGO ACTIVO
        if (this.partida.isEnCurso()) {
            throw new JuegoEnCursoException();
        }

        // CARTONES
        if (cantCartones <= 0) {
            throw new CantidadCartonesInvalidaException();
        }
        if (demasiadosCartones(cantCartones)) {
            throw new DemasiadosCartonesException();
        }             
        if (jugador.estaLogueado()){
            throw new EstaLogueadoException("" + jugador.getUsuario());
        }
        // SALDO INSUFICIENTE
        double precioCartones = getPrecioCartones(cantCartones);
        if (!jugador.puedeCostear(precioCartones)) {
            throw new SaldoInsuficienteException();
        }
        
        this.partida.addJugador(jugador, cantCartones);
        jugador.setLogueado(true);
        this.partida.setJuegoActivo(true);
                
        return jugador;
        
    }
    
    @Override
    public void finalizarAplicacion() throws RemoteException {
        try {
            if (partida != null) {
                partida.finalizarAplicacion();
            }
            this.db.desconectar();
        } catch (RemoteException ex) {
            System.out.println("Bingo - RemoteException: " + ex.getMessage());
        }
        
        System.exit(0);
    }

    
    @Override
    public void addObserver(IRemoteObserver observer) throws RemoteException {
        if(!observers.contains(observer)){
            observers.add(observer);
        }
    }

    @Override
    public void deleteObserver(IRemoteObserver observer) throws RemoteException {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Serializable param) {
        ArrayList<IRemoteObserver> aux = new ArrayList(observers);
        for (IRemoteObserver observer : aux){
            try {
                observer.update(this, param);
            } catch (RemoteException ex) {
                observers.remove(observer);
            }
        }
    }
    
    
    
}
