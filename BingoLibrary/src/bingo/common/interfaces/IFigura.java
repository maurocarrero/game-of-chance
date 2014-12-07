package bingo.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz IFigura
 * @author maurocarrero/fernandogonzalez
 */
public interface IFigura extends Remote {
    
    String getNombre() throws RemoteException;
    boolean condicional(ICarton c) throws RemoteException;
    
}
