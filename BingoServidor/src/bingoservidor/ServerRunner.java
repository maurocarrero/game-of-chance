/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoservidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author maurocarrero
 */
public class ServerRunner {
    
    public static void main(String[] args) {
       
        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new RMISecurityManager());
            }
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            Naming.rebind("BingoServidor", new Bingo());
            
            System.out.println("OK!!!");
            
        } catch (RemoteException | MalformedURLException e) {
            System.out.println("Server Runner Exception: " + e.getMessage());
        }
    }
    
}
