package bingo.servidor.modelo.entidades;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public class Administrador extends Usuario {

    public Administrador(String usuario, String password) throws RemoteException{
        super(usuario, password);
    }
    
}
