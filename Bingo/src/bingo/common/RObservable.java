/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.common;

import bingo.common.interfaces.IRemoteObservable;
import bingo.common.interfaces.IRemoteObserver;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author maurocarrero
 */
public class RObservable extends UnicastRemoteObject implements IRemoteObservable {

    public ArrayList<IRemoteObserver> observers = new ArrayList();
    
    public RObservable() throws RemoteException {}

    @Override
    public void addObserver(IRemoteObserver observer) throws RemoteException {
        if(!observers.contains(observer)){
            observers.add(observer);
        }
    }

    @Override
    public void deleteObserver(IRemoteObserver obs) throws RemoteException {
        observers.remove(obs);
    }
    
    @Override
    public void notifyObservers(Serializable param){
        ArrayList<IRemoteObserver> observersClone = new ArrayList(observers);
        for(IRemoteObserver observer : observersClone){
            try {
                observer.update(this, param);
            } catch (RemoteException ex) {
                observers.remove(observer);
            }
        }
    }
}
