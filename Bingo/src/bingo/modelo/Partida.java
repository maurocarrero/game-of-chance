/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author maurocarrero
 */
public class Partida extends Observable {

    private static Partida instance;
    
    private int cantFilas;
    private int cantColumnas;
    private int cantMaxCartones;
    private int cantJugadores;
    private double valorCarton;
    
    private int cantCartonesRequeridos;
    private Bolillero bolillero;
    
    private List<ICarton> cartones;
    private List<IJugador> jugadores = null;
    private List<IJugador> jugadoresPendientes;
    private boolean enCurso = false;
    private double pozo = 0d;
    private boolean juegoActivo = false;

    private Partida(int cantFilas, int cantColumnas, int cantMaxCartones, int cantJugadores, double valorCarton) {
        this.cantFilas = cantFilas;
        this.cantColumnas = cantColumnas;
        this.cantMaxCartones = cantMaxCartones;
        this.cantJugadores = cantJugadores;
        this.valorCarton = valorCarton;
        this.jugadores = new ArrayList();
    }

    public double getPozo() {
        return pozo;
    }

    public boolean isEnCurso() {
        return enCurso;
    }

    public void setEnCurso(boolean enCurso) {
        this.enCurso = enCurso;
    }

    public double getValorCarton() {
        return valorCarton;
    }

    public boolean isJuegoActivo() {
        return juegoActivo;
    }

    public void setJuegoActivo(boolean juegoActivo) {
        this.juegoActivo = juegoActivo;
    }

    public static Partida getInstance(int cantFilas, int cantColumnas, 
            int cantMaxCartones, int cantJugadores, double valorCarton) {
        if (instance == null) {
            instance = new Partida(cantFilas, cantColumnas, cantMaxCartones, 
                    cantJugadores, valorCarton);
        }
        return instance;
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
            finalizar(ganador);
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
             if(jugadores.size() > 1){
                setChanged();
                notifyObservers(crearHash("abandono", getPozo()));
             } else {
                 finalizar(jugadores.get(0));
             }
        } else {
            if (!hayJugadoresPendientes()) {
                siguienteTurno();
            }
        }
    }
    
    public boolean hayJugadoresPendientes() {
        return !jugadoresPendientes.isEmpty();
    }
    
    public void recalcularPozo(double monto){
        this.pozo -= monto;
    }
    
    private void finalizar(IJugador ganador) {
        //double restoApuestaAGanador = ganador.getCantCartones() * getValorCarton() *2;
        int cantCartonesEnJuego = 0;
        for (IJugador jugador : jugadores) {
            cantCartonesEnJuego += jugador.getCantCartones();
            if (!jugador.equals(ganador)) {
                pozo += jugador.debitarDoble(valorCarton);
            }
        }
        ganador.acreditar(pozo);
        setChanged();
        notifyObservers(crearHash("ganador", ganador));
    }
}
