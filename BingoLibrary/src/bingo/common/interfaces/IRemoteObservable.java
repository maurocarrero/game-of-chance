package bingo.common.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz IRemoteObservable
 * @author maurocarrero/fernandogonzalez
 */
public interface IRemoteObservable extends Remote {
    
    public void addObserver(IRemoteObserver observer) throws RemoteException;
    
    public void deleteObserver(IRemoteObserver observer) throws RemoteException;
    
    public void notifyObservers(Serializable param) throws RemoteException;;
    
}
