/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.modelo;

import bingo.modelo.entidades.Bolillero;
import bingo.modelo.entidades.Carton;
import bingo.modelo.entidades.Bolilla;
import bingo.controladores.ControlJugador;
import bingo.modelo.entidades.Jugador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author maurocarrero
 */
public class Partida {

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

    
    
    public int getCantCartonesRequeridos() {
        return cantCartonesRequeridos;
    }
    
    
    public void addJugador(Jugador jugador, int cantCartones) {
        this.jugadores.add(jugador);
        this.updateCantCartones(jugador.getCantCartones());
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
        for (Jugador jugador : this.jugadores) {
            for (int i = 0; i < jugador.getCantCartones(); i++) {
                jugador.addCarton(this.cartones.remove(0));
            }
        }
    }
    
    
    public void iniciarJuego() {
        distribuirCartones();
        Bolilla bolilla = this.bolillero.sacarBolilla();
        anunciarBolilla(bolilla);
    }
    
    public void anunciarBolilla(Bolilla bolilla) {
        for (Jugador j : this.jugadores) {
            j.buscarBolilla(bolilla);
        }
    }
}
