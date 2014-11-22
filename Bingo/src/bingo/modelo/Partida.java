package bingo.modelo;

import bingo.common.RObservable;
import bingo.interfaces.IBolilla;
import bingo.interfaces.IBolillero;
import bingo.interfaces.ICarton;
import bingo.interfaces.IFigura;
import bingo.interfaces.IJugador;
import bingo.interfaces.IPartida;
import bingo.interfaces.ITimer;
import bingo.modelo.entidades.Bolillero;
import bingo.modelo.entidades.Carton;
import bingo.modelo.entidades.CartonLleno;
import bingo.modelo.entidades.Centro;
import bingo.modelo.entidades.Diagonal;
import bingo.modelo.entidades.Linea;
import bingo.modelo.entidades.Timer;
import java.rmi.RemoteException;
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
public class Partida extends RObservable implements IPartida {

    private static Partida instance;
    
    // VALORES POR DEFECTO
    private static int cantFilas = 2;
    private static int cantColumnas = 2;
    private static int cantMaxCartones = 2;
    private static int cantJugadores = 2;
    private static double valorCarton = 10;
    
    private static ITimer timer = null;
    
    private int cantCartonesRequeridos;
    private Bolillero bolillero;
    
    private List<ICarton> cartones;
    private List<IJugador> jugadores = null;
    private List<IJugador> jugadoresPendientes;
    private boolean enCurso = false;
    private double pozo = 0d;
    private boolean juegoActivo = false;
        
    //Figuras
    private static List<IFigura> figuras = new ArrayList();

    private Partida() throws RemoteException {
        jugadores = new ArrayList();
        figuras = new ArrayList<>();
        figuras.add(CartonLleno.getInstance());
        figuras.add(Linea.getInstance());
        figuras.add(Diagonal.getInstance());
        figuras.add(Centro.getInstance());     
    }

    @Override
    public void setCantFilas(int cantFilas) {
        Partida.cantFilas = cantFilas;
    }

    @Override
    public void setCantColumnas(int cantColumnas) {
        Partida.cantColumnas = cantColumnas;
    }

    @Override
    public void setCantMaxCartones(int cantMaxCartones) {
        Partida.cantMaxCartones = cantMaxCartones;
    }

    @Override
    public void setCantJugadores(int cantJugadores) {
        Partida.cantJugadores = cantJugadores;
    }

    @Override
    public void setValorCarton(double valorCarton) {
        Partida.valorCarton = valorCarton;
    }

    @Override
    public void setPozo(double pozo) {
        this.pozo = pozo;
    }    
    
    @Override
    public int getCantFilas() {
        return cantFilas;
    }

    @Override
    public int getCantColumnas() {
        return cantColumnas;
    }

    @Override
    public int getCantMaxCartones() {
        return cantMaxCartones;
    }

    @Override
    public int getCantJugadores() {
        return cantJugadores;
    }

    @Override
    public double getValorCarton() {
        return valorCarton;
    }  
    
    @Override
    public IBolillero getBolillero() {
        return bolillero;
    }

    @Override
    public List<ICarton> getCartones() {
        return cartones;
    }

    @Override
    public List<IJugador> getJugadoresPendientes() {
        return jugadoresPendientes;
    }

    @Override
    public List<IFigura> getFiguras() {
        return figuras;
    }

    @Override
    public List<String> getFigurasString(){
        List<String> listaString = new ArrayList<>();
        for(IFigura fig : figuras){
            listaString.add(fig.getNombre() + "#" + fig.isActiva() + "");
        }
        return listaString;
    }
    
    @Override
    public double getPozo() {
        return pozo;
    }

    @Override
    public boolean isEnCurso() {
        return enCurso;
    }

    @Override
    public void setCantCartonesRequeridos(int cantCartonesRequeridos) {
        this.cantCartonesRequeridos = cantCartonesRequeridos;
    }

    @Override
    public void setEnCurso(boolean enCurso) {
        this.enCurso = enCurso;
    }

    @Override
    public boolean isJuegoActivo() {
        return juegoActivo;
    }

    @Override
    public void setJuegoActivo(boolean juegoActivo) {
        this.juegoActivo = juegoActivo;
    }

    
    public static IPartida getInstance() throws RemoteException {
        if (instance == null) {
            instance = new Partida();
        }
        return instance;
    }
    
    @Override
    public void guardarConfiguracion(int cF, int cC, int cMC, 
            int cJ, double vC, boolean linea, boolean diagonal, boolean centro) {
        setCantFilas(cF);
        setCantColumnas(cC);
        setCantMaxCartones(cMC);
        setCantJugadores(cJ);
        setValorCarton(vC);
        Linea.getInstance().setActiva(linea);
        Diagonal.getInstance().setActiva(diagonal);
        Centro.getInstance().setActiva(centro);
    }
    
    @Override
    public List<IJugador> getJugadores() {
        return jugadores;
    }

    @Override
    public double borrarJugador(IJugador jugador){
        System.out.println("Cartones de" + jugador + ": " + jugador.getCartones());
        if (jugador.getCartones() != null) {
            for (ICarton c : jugador.getCartones()) {
                bolillero.borrarBolillas(c);
            }
        }
        jugadores.remove(jugador);
        jugador.setLogueado(false);
        cantCartonesRequeridos -= jugador.getCantCartones();
        return jugador.debitarSimple(valorCarton);
    }    
    
    @Override
    public int getCantCartonesRequeridos() {
        return cantCartonesRequeridos;
    }
    
    @Override
    public void addJugador(IJugador jugador, int cantCartones) {
        jugador.setCantCartones(cantCartones);
        this.updateCantCartones(cantCartones);
        this.jugadores.add(jugador);
    }
    
    @Override
    public void updateCantCartones(int cant) {
        this.cantCartonesRequeridos += cant;
    }
    
    
    private void construirBolillero() {
        int cantNumerosPorCarton = cantFilas * cantColumnas;
        int cantTotalNumeros = cantNumerosPorCarton * cantCartonesRequeridos;
        this.bolillero = new Bolillero(cantTotalNumeros);
    }

    
    private void construirCartones() {
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
    
    private void distribuirCartones() {
        construirBolillero();
        construirCartones();
        
        for (IJugador jugador : this.jugadores) {
            for (int i = 0; i < jugador.getCantCartones(); i++) {
                jugador.addCarton(this.cartones.remove(0));
            }
        }
    }
    
    private HashMap crearHash(String clave, Object valor){
        HashMap<String, Object> evento = new HashMap();
        evento.put(clave, valor);
        return evento;
    }
    
    @Override
    public void iniciar() {        
        setEnCurso(true);
        calcularPozo(cantCartonesRequeridos);
        distribuirCartones();
        //setChanged();
        notifyObservers(crearHash("inicio", "INICIO"));
        siguienteTurno();
    }
    
    @Override
    public void siguienteTurno() {
        System.out.println("Siguiente turno: ");
        IBolilla bolilla = this.bolillero.sacarBolilla();
        anunciarBolilla(bolilla);
        jugadoresPendientes = new ArrayList<>(jugadores);
    }
    
    @Override
    public void anunciarBolilla(IBolilla bolilla) {
        IJugador ganador = null;
        for (IJugador jugador : this.jugadores) {
            if (jugador.buscarBolilla(bolilla, figuras)) {
                ganador = jugador;
            }
        }
        if (ganador != null) {
            finalizar(ganador, false, bolilla);
        } else {
            if (timer != null) {
                timer.abandonar();
            }
            try {
                timer = new Timer(10);
                timer.start();
            } catch (RemoteException ex) {
                Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
            }
            notifyObservers(crearHash("bolilla", bolilla));
        }
    }
    
    
    public ITimer getTimer() {
        return timer;
    }
    
    
    @Override
    public void eliminarJugadorPendiente(IJugador jugador) {
        for (int i = 0; i < jugadoresPendientes.size(); i++) {
            if (jugadoresPendientes.get(i).getUsuario().equals(jugador.getUsuario())) {
                jugadoresPendientes.remove(i);
            }
        }
    }
    
    @Override
    public void perdieronTodos() {
        finalizarAplicacion();
    }
    
    @Override
    public void continuarParticipando(Boolean continua, IJugador jugador){
        eliminarJugadorPendiente(jugador);
        System.out.println("jugador: " + jugador);
        if (!continua) {
            recalcularPozo(borrarJugador(jugador));
            jugador.mostrar();
            if (jugadores.size() > 1) {
              // setChanged();
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
    public boolean hayJugadoresPendientes() {
        return !jugadoresPendientes.isEmpty();
    }
    
    @Override
    public void recalcularPozo(double monto){
        this.pozo -= monto;
    }
    
    private void finalizar(IJugador ganador, boolean porAbandono, IBolilla ultimaBolilla) {
        if (ultimaBolilla != null) {
           //  setChanged();
            notifyObservers(crearHash("bolilla", ultimaBolilla));
        }
        for (IJugador jugador : jugadores) {
            jugador.debitarDoble(valorCarton);
            jugador.resetearCartones();
        }
        ganador.acreditar(pozo);
        mostrarEstadoJugadores();
        resetearPozo();
        getTimer().abandonar();
        // setChanged();
        notifyObservers(crearHash("ganador", ganador));
    }
    
    
    private void mostrarEstadoJugadores() {
        for (IJugador jugador : jugadores) {
            jugador.mostrar();
        }
    }
    
    @Override
    public void resetearPozo() {
        this.pozo = 0;
    }
    
    
    @Override
    public void finalizarAplicacion() {
        // setChanged();
        notifyObservers(crearHash("finalizar_aplicacion", null));
    }

}
