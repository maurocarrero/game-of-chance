/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.interfaces;

import bingo.modelo.entidades.Timer;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

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
