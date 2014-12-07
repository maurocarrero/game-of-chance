package bingo.servidor.modelo.entidades;

import java.rmi.RemoteException;

/**
 * Clase Administrador
 * @author maurocarrero/fernandogonzalez
 */
public class Administrador extends Usuario {

    public Administrador() throws RemoteException {}
    
    public Administrador(String usuario, String password) 
            throws RemoteException {
        super(usuario, password);
    }
    
}
