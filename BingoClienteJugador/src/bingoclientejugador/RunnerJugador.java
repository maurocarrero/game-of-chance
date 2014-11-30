/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bingoclientejugador;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zorro
 */
public class RunnerJugador {
    
     public static void main(String[] args) {
        
        VistaJugador vistaAdmin = new VistaJugador();
        ControlJugador controlJugador;

         try {
             controlJugador = ControlJugador.getInstance(vistaAdmin, "BingoServidor");
             if (controlJugador.registrar()) {
                vistaAdmin.setControlador(controlJugador);
                vistaAdmin.ejecutar();
            } else {
                System.out.println("No se pudo conectar al servidor.");
                System.exit(0);
            };
         } catch (RemoteException ex) {
             Logger.getLogger(RunnerJugador.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
    
}
