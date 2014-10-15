/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.modelo;

import bingo.modelo.entidades.Bolilla;
import bingo.modelo.entidades.Bolillero;
import bingo.modelo.entidades.Carton;
import bingo.modelo.entidades.Jugador;
import java.util.ArrayList;
import java.util.Collections;
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
    
    private List<Carton> cartones;
    private List<Jugador> jugadores = null;
    private List<Jugador> jugadoresPendientes;
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

    public boolean isEnCurso() {
        return enCurso;
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

    public static Partida getInstance(int cantFilas, int cantColumnas, 
            int cantMaxCartones, int cantJugadores, double valorCarton) {
        if (instance == null) {
            instance = new Partida(cantFilas, cantColumnas, cantMaxCartones, 
                    cantJugadores, valorCarton);
        }
        return instance;
    }
    
    public List<Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * VER COMO HACERLO MAS PERFORMANTE
     * @param jugador
     */
    public void borrarJugador(Jugador jugador){
        for (Carton c : jugador.getCartones()) {
            bolillero.borrarBolillas(c);
        }
        jugadores.remove(jugador);
        jugador.setLogueado(false);
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
        System.out.println("Construir bolillero");
        int cantNumerosPorCarton = cantFilas * cantColumnas;
        int cantTotalNumeros = cantNumerosPorCarton * cantCartonesRequeridos;
        this.bolillero = new Bolillero(cantTotalNumeros);
        System.out.println("Bolillero: " + this.bolillero.getListaBolillas().size());
    }
    
    
    
    private void construirCartones() {
        System.out.println("Construir cartones");
        this.cartones = new ArrayList();
        List<Bolilla> bolillas = bolillero.getListaBolillas();

        for (int i = 0; i < cantCartonesRequeridos; i++) {
            Collections.shuffle(bolillas);
            Carton carton = new Carton(this.cantFilas, this.cantColumnas);
            carton.poblar(bolillas);
            this.cartones.add(carton);
        }
    }
    
    
    private double calcularPozo(int cartonesEnJuego) {
        return cantCartonesRequeridos * valorCarton +
                cartonesEnJuego * valorCarton;
        
    }
    
    private void distribuirCartones() {
        construirBolillero();
        construirCartones();
        
        System.out.println("Distribuir cartones");
        System.out.println("Cantidad de cartones: " + this.cartones.size());

        for (Jugador jugador : this.jugadores) {
            for (int i = 0; i < jugador.getCantCartones(); i++) {
                jugador.addCarton(this.cartones.remove(0));
            }
            System.out.println("Jugador: " + jugador.getUsuario());
            System.out.println("Cantidad de cartones: " + jugador.getCartones().size());
        }
        setChanged();
        notifyObservers(null);
    }
    
    
    public void iniciar() {
        System.out.println("Inicia el juego");
        setEnCurso(true);
        distribuirCartones();
        siguienteTurno();
    }
    
    
    public void siguienteTurno() {
        Bolilla bolilla = this.bolillero.sacarBolilla();
        anunciarBolilla(bolilla);
        jugadoresPendientes = new ArrayList<>(jugadores);
    }
    
    
    public void anunciarBolilla(Bolilla bolilla) {
        for (Jugador jugador : this.jugadores) {
            if (jugador.buscarBolilla(bolilla)) {
                System.out.println("Bingo!!!");
                finalizar(jugador);
            }
        }
        setChanged();
        notifyObservers(bolilla);
    }
    
    public void eliminarJugadorPendiente(Jugador jugador) {
        for (int i = 0; i < jugadoresPendientes.size(); i++) {
            if (jugadoresPendientes.get(i).getUsuario().equals(jugador.getUsuario())) {
                jugadoresPendientes.remove(i);
            }
        }
    }
    
    public void continuarParticipando(Boolean continua, Jugador jugador){
        eliminarJugadorPendiente(jugador);
        if (!continua) {
            this.borrarJugador(jugador);
        } else {
            if (jugadoresPendientes.isEmpty()) {
                siguienteTurno();
            }
        }
    }
    
    private void finalizar(Jugador ganador) {
        System.out.println("Bingo!!!");
        int cantCartonesEnJuego = 0;
        for (Jugador jugador : jugadores) {
            cantCartonesEnJuego += jugador.getCantCartones();
            if (!jugador.equals(ganador)) {
                pozo += jugador.debitar(valorCarton);
            }            
        }
        
        // this.pozo = calcularPozo(cantCartonesEnJuego);
        
    }
}
