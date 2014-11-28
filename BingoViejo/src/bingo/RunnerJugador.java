/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bingo;

import bingo.controladores.ControlJugador;
import bingo.modelo.Bingo;
import bingo.modelo.Partida;
import bingo.vistas.VistaJugador;

/**
 *
 * @author zorro
 */
public class RunnerJugador {
    
     public static void main(String[] args) {
         Bingo modelo = Bingo.getInstance();
         Partida partida = modelo.getPartidaInstance();
         if (!partida.isEnCurso()) {
            VistaJugador nuevaVista = new VistaJugador();
            ControlJugador control = new ControlJugador(nuevaVista, modelo);
            nuevaVista.setControlador(control);
            partida.addObserver(control);
            nuevaVista.ejecutar();
        }
     }
    
}
