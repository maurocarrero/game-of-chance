package bingo.common.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz IRemoteObserver
 * @author maurocarrero/fernandogonzalez
 */
public interface IRemoteObserver extends Remote {
    
    public void update(IRemoteObservable origen, Serializable param) throws RemoteException;

}
