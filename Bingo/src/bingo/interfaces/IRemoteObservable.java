package bingo.interfaces;


import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author DocenteFI
 */
public interface IRemoteObservable extends Remote {
    
    public void addObserver(IRemoteObserver observer) throws RemoteException;
    
    public void deleteObserver(IRemoteObserver observer) throws RemoteException;
    
    public void notifyObservers(Serializable param);
    
}
