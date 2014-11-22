/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author maurocarrero
 */
public interface IRemoteObserver extends Remote {
    
    public void update(IRemoteObservable origen, Serializable param) throws RemoteException;

}
