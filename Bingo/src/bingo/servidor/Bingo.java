package bingo.servidor;

import bingo.common.interfaces.IBingo;
import bingo.common.interfaces.IPartida;
import bingo.common.interfaces.IRemoteObservable;
import bingo.common.interfaces.IRemoteObserver;
import bingo.modelo.exceptions.AccesoDenegadoException;
import bingo.modelo.exceptions.CantidadCartonesInvalidaException;
import bingo.modelo.exceptions.ConfiguracionNoValidaException;
import bingo.modelo.exceptions.DemasiadosCartonesException;
import bingo.modelo.exceptions.EstaLogeadoException;
import bingo.modelo.exceptions.JuegoEnCursoException;
import bingo.modelo.exceptions.SaldoInsuficienteException;
import bingo.servidor.modelo.entidades.Administrador;
import bingo.servidor.modelo.entidades.Jugador;
import bingo.servidor.modelo.entidades.Usuario;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
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
    
    public Bingo() throws RemoteException {
        usuariosTest = new ArrayList();

        usuariosTest.add(new Administrador("mcarrero", "mcarrero"));
        usuariosTest.add(new Administrador("fgonzalez", "fgonzalez"));

        usuariosTest.add(new Jugador("j1", "j1", 0, 1200));
        usuariosTest.add(new Jugador("j2", "j2", 0, 2700));
        usuariosTest.add(new Jugador("j3", "j3", 0, 4200));
        usuariosTest.add(new Jugador("j4", "j4", 0, 7000));
        usuariosTest.add(new Jugador("j5", "j5", 0, 30));
        
        this.partida = new Partida();
        this.observers = new ArrayList<>();
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
            boolean linea, boolean diagonal, boolean centro) 
            throws ConfiguracionNoValidaException, RemoteException {
        
        // VALIDACIONES
        if (cantFilas < 1 || cantFilas > 10 || cantColumnas < 2 || 
                cantColumnas > 10 || cantMaxCartones < 2 || cantJugadores < 2 || 
                valorCarton < 1) {
            throw new ConfiguracionNoValidaException();
        }
        // VALIDACIONES FIGURAS
        if (cantFilas != cantColumnas) {
            if (diagonal == true || centro == true) {
                throw new ConfiguracionNoValidaException();
            }
        } else {
            if (centro) {
                if (cantColumnas %2 == 0 || cantFilas %2 == 0) {
                    throw new ConfiguracionNoValidaException();
                }
            }            
        }      
        
        this.partida.guardarConfiguracion(cantFilas, cantColumnas, cantMaxCartones, 
                cantJugadores, valorCarton, linea, diagonal, centro);
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
            System.out.println(ex.getMessage());
            return false;
        }    
    }
    
    
    
    @Override
    public Jugador loginJugador(String usuario, char[] password, int cantCartones) 
            throws AccesoDenegadoException, JuegoEnCursoException,
                CantidadCartonesInvalidaException, DemasiadosCartonesException, 
                SaldoInsuficienteException, EstaLogeadoException, RemoteException {
        
        Jugador jugador = (Jugador) login (usuario);
        
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
            throw new EstaLogeadoException("" + jugador.getUsuario());
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
        if (partida != null) {
            partida.finalizarAplicacion();
        }        
        System.exit(0);
    }

    @Override
    public void addObserver(IRemoteObserver observer) throws RemoteException {
        System.out.println("Agregando un observer");

        if(!observers.contains(observer)){
            observers.add(observer);
        }
        notifyObservers("Hola a todos, I am the Controller.");
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