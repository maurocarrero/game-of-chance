package bingoclientejugador;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class RunnerJugador
 * @author maurocarrero/fernandogonzalez
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
