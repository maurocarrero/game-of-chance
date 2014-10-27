package bingo.modelo;

import bingo.interfaces.IBolilla;
import bingo.interfaces.ICarton;
import bingo.interfaces.IJugador;
import bingo.modelo.entidades.Bolillero;
import bingo.modelo.entidades.Carton;
import bingo.modelo.entidades.Jugador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public class Partida extends Observable {

    private static Partida instance;
    
    // VALORES POR DEFECTO
    private static int cantFilas = 3;
    private static int cantColumnas = 2;
    private static int cantMaxCartones = 2;
    private static int cantJugadores = 3;    
    private static double valorCarton = 10;
    
    private int cantCartonesRequeridos;
    private Bolillero bolillero;
    
    private List<ICarton> cartones;
    private List<IJugador> jugadores = null;
    private List<IJugador> jugadoresPendientes;
    private boolean enCurso = false;
    private double pozo = 0d;
    private boolean juegoActivo = false;

    private Partida() {
        jugadores = new ArrayList();
    }

    public static void setCantFilas(int cantFilas) {
        Partida.cantFilas = cantFilas;
    }

    public static void setCantColumnas(int cantColumnas) {
        Partida.cantColumnas = cantColumnas;
    }

    public static void setCantMaxCartones(int cantMaxCartones) {
        Partida.cantMaxCartones = cantMaxCartones;
    }

    public static void setCantJugadores(int cantJugadores) {
        Partida.cantJugadores = cantJugadores;
    }

    public static void setValorCarton(double valorCarton) {
        Partida.valorCarton = valorCarton;
    }

    public void setPozo(double pozo) {
        this.pozo = pozo;
    }

    
    
    public static int getCantFilas() {
        return cantFilas;
    }

    public static int getCantColumnas() {
        return cantColumnas;
    }

    public static int getCantMaxCartones() {
        return cantMaxCartones;
    }

    public static int getCantJugadores() {
        return cantJugadores;
    }

    public static double getValorCarton() {
        return valorCarton;
    }

    
    
    public Bolillero getBolillero() {
        return bolillero;
    }

    public List<ICarton> getCartones() {
        return cartones;
    }

    public List<IJugador> getJugadoresPendientes() {
        return jugadoresPendientes;
    }

    public double getPozo() {
        return pozo;
    }

    public boolean isEnCurso() {
        return enCurso;
    }

    public void setCantCartonesRequeridos(int cantCartonesRequeridos) {
        this.cantCartonesRequeridos = cantCartonesRequeridos;
    }

    public void setEnCurso(boolean enCurso) {
        this.enCurso = enCurso;
    }

    public boolean isJuegoActivo() {
        return juegoActivo;
    }

    public void setJuegoActivo(boolean juegoActivo) {
        this.juegoActivo = juegoActivo;
    }

    public static Partida getInstance() {
        if (instance == null) {
            instance = new Partida();
        }
        return instance;
    }
    
    public static void guardarConfiguracion(int cF, int cC, int cMC, 
            int cJ, double vC) {
        setCantFilas(cF);
        setCantColumnas(cC);
        setCantMaxCartones(cMC);
        setCantJugadores(cJ);
        setValorCarton(vC);
    }
    
    public List<IJugador> getJugadores() {
        return jugadores;
    }

    /**
     * VER COMO HACERLO MAS PERFORMANTE
     * @param jugador
     * @return 
     */
    public double borrarJugador(IJugador jugador){
        for (ICarton c : jugador.getCartones()) {
            bolillero.borrarBolillas(c);
        }
        jugadores.remove(jugador);
        jugador.setLogueado(false);
        cantCartonesRequeridos -= jugador.getCantCartones();
        return jugador.debitarSimple(valorCarton);
    }
    
    public int getCantCartonesRequeridos() {
        return cantCartonesRequeridos;
    }
    
    
    public void addJugador(Jugador jugador, int cantCartones) {
        jugador.setCantCartones(cantCartones);
        this.updateCantCartones(cantCartones);
        this.jugadores.add(jugador);

    }
    
    
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
            ICarton carton = new Carton(this.cantFilas, this.cantColumnas);
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
    
    
    public void iniciar() {        
        setEnCurso(true);
        calcularPozo(cantCartonesRequeridos);
        distribuirCartones();
        setChanged();
        notifyObservers(crearHash("inicio", "INICIO"));
        siguienteTurno();
    }
    
    private HashMap crearHash(String clave, Object valor){
        HashMap<String, Object> evento = new HashMap();
        evento.put(clave, valor);
        return evento;
    }
    
    public void siguienteTurno() {
        IBolilla bolilla = this.bolillero.sacarBolilla();
        anunciarBolilla(bolilla);
        jugadoresPendientes = new ArrayList<>(jugadores);
    }
    
    
    public void anunciarBolilla(IBolilla bolilla) {        
        IJugador ganador = null;
        for (IJugador jugador : this.jugadores) {
            if (jugador.buscarBolilla(bolilla)) {
                ganador = jugador;
            }
        }
        setChanged();
        notifyObservers(crearHash("bolilla", bolilla));
        if (ganador != null) {
            finalizar(ganador, false);
        }        
    }
    
    public void eliminarJugadorPendiente(IJugador jugador) {
        for (int i = 0; i < jugadoresPendientes.size(); i++) {
            if (jugadoresPendientes.get(i).getUsuario().equals(jugador.getUsuario())) {
                jugadoresPendientes.remove(i);
            }
        }
    }
    
    public void continuarParticipando(Boolean continua, IJugador jugador){
        eliminarJugadorPendiente(jugador);
        if (!continua) {
            recalcularPozo(borrarJugador(jugador));
            jugador.mostrar();
            if(jugadores.size() > 1){
               setChanged();
               notifyObservers(crearHash("abandono", getPozo()));
            } else {
                finalizar(jugadores.get(0), true);
            }
        }
        if (!hayJugadoresPendientes() && jugadores.size() > 1) {
            siguienteTurno();
        }
    }
    
    public boolean hayJugadoresPendientes() {
        return !jugadoresPendientes.isEmpty();
    }
    
    public void recalcularPozo(double monto){
        this.pozo -= monto;
    }
    
    private void finalizar(IJugador ganador, boolean porAbandono) {
        for (IJugador jugador : jugadores) {
            jugador.debitarDoble(valorCarton);
            jugador.resetearCartones();
        }
        ganador.acreditar(pozo);
        mostrarEstadoJugadores();
        resetearPozo();
        setChanged();
        notifyObservers(crearHash("ganador", ganador));
    }
    
    
    private void mostrarEstadoJugadores() {
        for (IJugador jugador : jugadores) {
            jugador.mostrar();
        }
    }
    
    public void resetearPozo() {
        this.pozo = 0;
    }
    
    public void finalizarAplicacion() {
        setChanged();
        notifyObservers(crearHash("finalizar_aplicacion", null));
    }
}
