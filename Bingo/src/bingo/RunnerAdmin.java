package bingo;

import bingo.controladores.ControlAdmin;
import bingo.interfaces.IBingo;
import bingo.modelo.Bingo;
import bingo.vistas.VistaAdmin;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Bingo
 * @author maurocarrero/fernandogonzalez
 */
public class RunnerAdmin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IBingo modelo = Bingo.getInstance();
        VistaAdmin vistaAdmin = new VistaAdmin();
        ControlAdmin controlAdmin = ControlAdmin.getInstance(vistaAdmin, modelo);
        vistaAdmin.setControlador(controlAdmin);
        vistaAdmin.ejecutar();
        
        try{
            if(System.getSecurityManager()==null){
                System.setSecurityManager(new RMISecurityManager());
            }
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            Naming.rebind("Bingo", modelo);
            System.out.println("OK!!!");
            
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
