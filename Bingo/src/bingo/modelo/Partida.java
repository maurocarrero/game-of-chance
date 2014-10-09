/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.modelo;

import bingo.modelo.entidades.Bolillero;
import bingo.modelo.entidades.Carton;
import bingo.modelo.entidades.Bolilla;
import bingo.controladores.JugadorController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author maurocarrero
 */
public class Partida {

    private int cantFilas;
    private int cantColumnas;
    private int cantMaxCartones;
    private int cantJugadores;
    private double valorCarton;
    
    private int cantCartonesRequeridos;
    private Bolillero bolillero;
    private List<Carton> cartones;
    private List<JugadorController> jugadores = null;


    public Partida(int cantFilas, int cantColumnas, int cantMaxCartones, int cantJugadores, double valorCarton) {
        this.cantFilas = cantFilas;
        this.cantColumnas = cantColumnas;
        this.cantMaxCartones = cantMaxCartones;
        this.cantJugadores = cantJugadores;
        this.valorCarton = valorCarton;
        this.jugadores = new ArrayList();
    }

    
    
    public List<JugadorController> getJugadores() {
        return jugadores;
    }

    
    
    public int getCantCartonesRequeridos() {
        return cantCartonesRequeridos;
    }
    
    
    public boolean addJugador(JugadorController e) {
        return jugadores.add(e);
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
        for (JugadorController interfaz : this.jugadores) {
            for (int i = 0; i < interfaz.getJugador().getCantCartones(); i++) {
                Carton carton = this.cartones.remove(0);
                interfaz.addCarton(carton);
            }
        }
    }
    
    
    public void iniciarJuego() {
        distribuirCartones();
        Bolilla bolilla = this.bolillero.sacarBolilla();
    }
}
