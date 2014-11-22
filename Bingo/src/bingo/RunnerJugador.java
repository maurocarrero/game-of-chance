/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bingo;

import bingo.controladores.ControlJugador;
import bingo.interfaces.IBingo;
import bingo.interfaces.IPartida;
import bingo.modelo.Bingo;
import bingo.modelo.Partida;
import bingo.vistas.VistaJugador;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zorro
 */
public class RunnerJugador {
    
     public static void main(String[] args) {
         IBingo modelo = Bingo.getInstance();
         IPartida partida = modelo.getPartida();
         if (!partida.isEnCurso()) {
             try {
                 VistaJugador nuevaVista = new VistaJugador();
                 ControlJugador control = new ControlJugador(nuevaVista, modelo);
                 nuevaVista.setControlador(control);
                 partida.addObserver(control);
                 nuevaVista.ejecutar();
             } catch (RemoteException ex) {
                 Logger.getLogger(RunnerJugador.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
     }
    
}
