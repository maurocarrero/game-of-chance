/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.common.interfaces;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 *
 * @author maurocarrero
 */
public interface ITimer extends Runnable {
    
    void start();
    
    void abandonar();
    
    void addObserver(IRemoteObserver observer) throws RemoteException;

    void deleteObserver(IRemoteObserver observer) throws RemoteException;
    
    void notifyObservers(Serializable param) throws RemoteException;
    
}

