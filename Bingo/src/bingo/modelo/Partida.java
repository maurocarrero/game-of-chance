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


    private Partida(int cantFilas, int cantColumnas, int cantMaxCartones, int cantJugadores, double valorCarton) {
        this.cantFilas = cantFilas;
        this.cantColumnas = cantColumnas;
        this.cantMaxCartones = cantMaxCartones;
        this.cantJugadores = cantJugadores;
        this.valorCarton = valorCarton;
        this.jugadores = new ArrayList();
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

    public void borrarJugador(Jugador jugador){
        List <Carton> cartones = jugador.getCartones();
        for (Carton c : cartones){
            c.borrarBolillas(this.bolillero);
        }
        for (Jugador j : jugadores){
            if(jugador.equals(j)){
                j = null;
                jugador = null;
            }
        }
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
    
    
    public void iniciarJuego() {
        System.out.println("Inicia el juego");
        distribuirCartones();
        Bolilla bolilla = this.bolillero.sacarBolilla();
        anunciarBolilla(bolilla);
    }
    
    public void anunciarBolilla(Bolilla bolilla) {
        for (Jugador j : this.jugadores) {
            j.buscarBolilla(bolilla);
        }
        setChanged();
        notifyObservers(bolilla);
    }
}
