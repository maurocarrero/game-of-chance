package bingo.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz IBolilla
 * @author maurocarrero/fernandogonzalez
 */
public interface IBolilla extends Remote{
     
    public int getValor() throws RemoteException;
    
}
