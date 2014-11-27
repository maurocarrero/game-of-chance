/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bingo.jugador;

import bingo.jugador.ControlJugador;

/**
 *
 * @author zorro
 */
public class RunnerJugador {
    
     public static void main(String[] args) {
        
        // IBingo modelo = new Bingo();
        // IPartida partida = modelo.getPartida();
        // if (!partida.isEnCurso()) {
         
        VistaJugador nuevaVista = new VistaJugador();
        ControlJugador control = new ControlJugador("BingoServer");
        control.registrar();
        nuevaVista.setControlador(control);
        
        // partida.addObserver(control);
        nuevaVista.ejecutar();
        
        
        // }

     }
    
}
