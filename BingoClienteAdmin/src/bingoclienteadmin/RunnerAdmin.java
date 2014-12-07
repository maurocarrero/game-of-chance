package bingoclienteadmin;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase RunnerAdmin
 * @author maurocarrero/fernandogonzalez
 */
public class RunnerAdmin {

    public static void main(String[] args) {
        
        VistaAdmin vistaAdmin = new VistaAdmin();
        ControlAdmin controlAdmin;
        try {
            controlAdmin = ControlAdmin.getInstance(vistaAdmin, "BingoServidor");
            if (controlAdmin.registrar()) {
                vistaAdmin.setControlador(controlAdmin);
                vistaAdmin.ejecutar();
            } else {
                System.out.println("No se pudo conectar al servidor.");
            };
        } catch (RemoteException ex) {
            Logger.getLogger(RunnerAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
