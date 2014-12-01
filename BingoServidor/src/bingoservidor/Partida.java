package bingoservidor;

import bingo.common.interfaces.IBolilla;
import bingo.common.interfaces.IBolillero;
import bingo.common.interfaces.ICarton;
import bingo.common.interfaces.IContador;
import bingo.common.interfaces.IFigura;
import bingo.common.interfaces.IJugador;
import bingo.common.interfaces.IPartida;
import bingo.common.interfaces.IRemoteObserver;
import bingo.servidor.modelo.entidades.Bolillero;
import bingo.servidor.modelo.entidades.Carton;
import bingo.servidor.modelo.entidades.CartonLleno;
import bingo.servidor.modelo.entidades.Centro;
import bingo.servidor.modelo.entidades.Contador;
import bingo.servidor.modelo.entidades.Diagonal;
import bingo.servidor.modelo.entidades.Linea;
import bingo.servidor.persistencia.PartidaPersistente;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public class Partida extends UnicastRemoteObject implements IPartida {

    // VALORES POR DEFECTO
    private static int cantFilas = 2;
    private static int cantColumnas = 2;
    private static int cantMaxCartones = 2;
    private static int cantJugadores = 2;
    private static double valorCarton = 10;
    
    private static IContador contador = null;
    
    private int cantCartonesRequeridos;
    private Bolillero bolillero;
    
    private List<ICarton> cartones;
    private List<IJugador> jugadores = null;
    private List<IJugador> jugadoresPendientes;
    private ArrayList<IRemoteObserver> observers;   
    
    private boolean enCurso = false;
    private double pozo = 0d;
    private boolean juegoActivo = false;
        
    //Figuras
    private static List<IFigura> figuras = new ArrayList();
    
    public Partida() throws RemoteException {
        jugadores = new ArrayList<>();
        observers = new ArrayList<>();
        figuras = new ArrayList<>();
        figuras.add(CartonLleno.getInstance());
    }

    @Override
    public void setCantFilas(int cantFilas) throws RemoteException  {
        Partida.cantFilas = cantFilas;
    }

    @Override
    public void setCantColumnas(int cantColumnas) throws RemoteException  {
        Partida.cantColumnas = cantColumnas;
    }

    @Override
    public void setCantMaxCartones(int cantMaxCartones) throws RemoteException  {
        Partida.cantMaxCartones = cantMaxCartones;
    }

    @Override
    public void setCantJugadores(int cantJugadores) throws RemoteException  {
        Partida.cantJugadores = cantJugadores;
    }

    @Override
    public void setValorCarton(double valorCarton) throws RemoteException  {
        Partida.valorCarton = valorCarton;
    }

    @Override
    public void setPozo(double pozo) throws RemoteException  {
        this.pozo = pozo;
    }    
    
    @Override
    public int getCantFilas() throws RemoteException  {
        return cantFilas;
    }

    @Override
    public int getCantColumnas() throws RemoteException  {
        return cantColumnas;
    }

    @Override
    public int getCantMaxCartones() throws RemoteException  {
        return cantMaxCartones;
    }

    @Override
    public int getCantJugadores() throws RemoteException  {
        return cantJugadores;
    }

    @Override
    public double getValorCarton() throws RemoteException  {
        return valorCarton;
    }  
    
    @Override
    public IBolillero getBolillero() throws RemoteException  {
        return bolillero;
    }

    @Override
    public List<ICarton> getCartones() throws RemoteException  {
        return cartones;
    }

    @Override
    public List<IJugador> getJugadoresPendientes() throws RemoteException {
        return jugadoresPendientes;
    }

    @Override
    public List<IFigura> getFiguras() throws RemoteException {
        return figuras;
    }   
    
    @Override
    public double getPozo() throws RemoteException {
        return pozo;
    }

    @Override
    public boolean isEnCurso() throws RemoteException {
        return enCurso;
    }

    @Override
    public void setCantCartonesRequeridos(int cantCartonesRequeridos)
     throws RemoteException {
        this.cantCartonesRequeridos = cantCartonesRequeridos;
    }

    @Override
    public void setEnCurso(boolean enCurso) throws RemoteException {
        this.enCurso = enCurso;
    }

    @Override
    public boolean isJuegoActivo() throws RemoteException {
        return juegoActivo;
    }

    @Override
    public void setJuegoActivo(boolean juegoActivo) throws RemoteException {
        this.juegoActivo = juegoActivo;
    }

    
    @Override
    public void guardarConfiguracion(int cF, int cC, int cMC, 
            int cJ, double vC, boolean linea, boolean diagonal, boolean centro)
                throws RemoteException {
        setCantFilas(cF);
        setCantColumnas(cC);
        setCantMaxCartones(cMC);
        setCantJugadores(cJ);
        setValorCarton(vC);
        if(linea && !getFiguras().contains(Linea.getInstance())) getFiguras().add(Linea.getInstance());
        if(centro && !getFiguras().contains(Centro.getInstance())) getFiguras().add(Centro.getInstance());
        if(diagonal && !getFiguras().contains(Diagonal.getInstance())) getFiguras().add(Diagonal.getInstance());
    }
    
    @Override
    public List<IJugador> getJugadores() throws RemoteException {
        return jugadores;
    }

    @Override
    public double borrarJugador(IJugador jugador) throws RemoteException {
        System.out.println("Cartones de" + jugador + ": " + jugador.getCartones());
        if (jugador.getCartones() != null) {
            for (ICarton c : jugador.getCartones()) {
                bolillero.borrarBolillas(c);
            }
        }
        quitarJugadorDeLaPartida(jugador);
        jugador.setLogueado(false);
        cantCartonesRequeridos -= jugador.getCantCartones();
        return jugador.debitarSimple(valorCarton);
    }    
    
    @Override
    public int getCantCartonesRequeridos() throws RemoteException  {
        return cantCartonesRequeridos;
    }
    
    @Override
    public void addJugador(IJugador jugador, int cantCartones) throws RemoteException {
        jugador.setCantCartones(cantCartones);
        this.updateCantCartones(cantCartones);
        this.jugadores.add(jugador);
    }
    
    @Override
    public void updateCantCartones(int cant) throws RemoteException {
        this.cantCartonesRequeridos += cant;
    }
    
    
    private void construirBolillero() throws RemoteException{
        int cantNumerosPorCarton = cantFilas * cantColumnas;
        int cantTotalNumeros = cantNumerosPorCarton * cantCartonesRequeridos;
        this.bolillero = new Bolillero(cantTotalNumeros);
    }

    
    private void construirCartones() throws RemoteException {
        this.cartones = new ArrayList();
        List<IBolilla> bolillas = bolillero.getListaBolillas();

        for (int i = 0; i < cantCartonesRequeridos; i++) {
            Collections.shuffle(bolillas);
            ICarton carton = new Carton(cantFilas, cantColumnas);
            carton.poblar(bolillas);
            this.cartones.add(carton);
        }
    }
    
    
    private double calcularPozo(int cartonesEnJuego) {
        this.pozo = cantCartonesRequeridos * valorCarton +
                cartonesEnJuego * valorCarton;
        return this.pozo;
        
    }
    
    private void distribuirCartones() throws RemoteException {
        construirBolillero();
        construirCartones();
        
        for (IJugador jugador : this.jugadores) {
            for (int i = 0; i < jugador.getCantCartones(); i++) {
                jugador.addCarton(this.cartones.remove(0));
            }
        }
    }
    
    private HashMap crearHash(String clave, Object valor) {
        HashMap<String, Object> evento = new HashMap();
        evento.put(clave, valor);
        return evento;
    }
    
    @Override
    public void iniciar() throws RemoteException {
        setEnCurso(true);
        calcularPozo(cantCartonesRequeridos);
        distribuirCartones();
        notifyObservers(crearHash("inicio", "INICIO"));
        siguienteTurno();
    }
    
    @Override
    public void siguienteTurno() throws RemoteException  {
        System.out.println("Siguiente turno: ");
        IBolilla bolilla = this.bolillero.sacarBolilla();
        anunciarBolilla(bolilla);
        jugadoresPendientes = new ArrayList<>(jugadores);
    }
    
    @Override
    public void anunciarBolilla(IBolilla bolilla) throws RemoteException {
        IJugador ganador = null;
        for (IJugador jugador : this.jugadores) {
            if (jugador.buscarBolilla(bolilla, figuras)) {
                ganador = jugador;
            }
        }
        if (ganador != null) {
            finalizar(ganador, false, bolilla);
        } else {
            if (contador != null) {
                contador.resetear();
            }else{
                try {
                    contador = new Contador(10);
                    contador.start();
                } catch (RemoteException ex) {
                    Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            notifyObservers(crearHash("bolilla", bolilla));
        }
    }
    
    
    @Override
    public IContador getContador() {
        return contador;
    }
    
    
    @Override
    public void eliminarJugadorPendiente(IJugador jugador) throws RemoteException {
        eliminarJugadorDeColeccion(jugador, this.jugadoresPendientes);
    }
    
    @Override
    public void quitarJugadorDeLaPartida(IJugador jugador) throws RemoteException {
        eliminarJugadorDeColeccion(jugador, this.jugadores);
    }
    
    
    private void eliminarJugadorDeColeccion(IJugador jugador, List<IJugador> lista)
            throws RemoteException {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getUsuario().equals(jugador.getUsuario())) {
                lista.remove(i);
            }
        }
    }
    
    @Override
    public void perdieronTodos() throws RemoteException  {
        notifyObservers(crearHash("perdieron_todos", ""));
        resetear();
    }
    
    @Override
    public void continuarParticipando(Boolean continua, IJugador jugador) 
            throws RemoteException {
        eliminarJugadorPendiente(jugador);
        System.out.println("jugador: " + jugador);
        if (!continua) {
            recalcularPozo(borrarJugador(jugador));
            jugador.mostrar();
            if (jugadores.size() > 1) {
               notifyObservers(crearHash("abandono", getPozo()));
            } else {
                if (jugadores.size() > 0) {
                    IJugador ganador = jugadores.get(0);
                    if (ganador != null) {
                        finalizar(jugadores.get(0), true, null);
                    }
                }
            }
        }
        if (!hayJugadoresPendientes() && jugadores.size() > 1) {
            siguienteTurno();
        }
    }

    @Override
    public boolean hayJugadoresPendientes() throws RemoteException  {
        return !jugadoresPendientes.isEmpty();
    }
    
    @Override
    public void recalcularPozo(double monto) throws RemoteException {
        this.pozo -= monto;
    }
    
    private void finalizar(IJugador ganador, boolean porAbandono, 
            IBolilla ultimaBolilla) throws RemoteException {
        if (ultimaBolilla != null) {
           notifyObservers(crearHash("bolilla", ultimaBolilla));
        }
        for (IJugador jugador : jugadores) {
            jugador.debitarDoble(valorCarton);
            jugador.resetearCartones();
            jugador.desloguear();
        }
        ganador.acreditar(pozo);
        ganador.desloguear();
        mostrarEstadoJugadores();
        getContador().cancelar();
        notifyObservers(crearHash("ganador", ganador));
        resetear();
    }
    
    
    private void mostrarEstadoJugadores() throws RemoteException {
        for (IJugador jugador : jugadores) {
            jugador.mostrar();
        }
    }
    
    @Override
    public void resetear() throws RemoteException {
        cantCartonesRequeridos = 0;
        pozo = 0;
        jugadores = new ArrayList<>();
        jugadoresPendientes = new ArrayList<>();
        cartones = new ArrayList<>();
        observers = new ArrayList<>();
        setJuegoActivo(false);
        setEnCurso(false);
    }

    @Override
    public void finalizarAplicacion() throws RemoteException {
        notifyObservers(crearHash("finalizar_aplicacion", pozo));
        resetear();
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
                System.out.println(ex.getMessage());
                observers.remove(observer);
            }
        }
    }
}
