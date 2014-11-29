package bingo.servidor.modelo.entidades;

import bingo.common.interfaces.IContador;
import bingo.common.interfaces.IRemoteObserver;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Mauro.Carrero
 */
public class Contador extends UnicastRemoteObject implements IContador {

    private int tiempo = 0;
    private int cont = 0;
    private Timer timer;
    private ArrayList<IRemoteObserver> observers;
    
    public Contador(int segundos) throws RemoteException {
        this.tiempo = (segundos > 120) ? segundos : 20;
        this.cont = this.tiempo;
        this.observers = new ArrayList<>();
        this.timer = new Timer(this);
    }

    @Override
    public int getTiempo() throws RemoteException {
        return tiempo;
    }

    @Override
    public int getCont() throws RemoteException {
        return cont;
    }

    @Override
    public void setTiempo(int tiempo) throws RemoteException {
        this.tiempo = tiempo;
    }

    @Override
    public void setCont(int cont) throws RemoteException {
        this.cont = cont;
    }
    
    @Override
    public void decrementar() throws RemoteException {
        this.cont--;
    }
    
    @Override
    public void addObserver(IRemoteObserver observer) throws RemoteException {
        if(!observers.contains(observer)){
            observers.add(observer);
        }
    }

    @Override
    public void deleteObserver(IRemoteObserver observer) throws RemoteException {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Serializable param) {
        ArrayList<IRemoteObserver> aux = new ArrayList(observers);
        for (IRemoteObserver observer : aux){
            try {
                observer.update(this, param);
            } catch (RemoteException ex) {
                System.out.println(ex.getMessage());
                observers.remove(observer);
            }
        }
    }

    @Override
    public void notificar() throws RemoteException {
        System.out.println("Cont: " + cont);
        this.notifyObservers(crearHash("timer", cont));
    }
    
    @Override
    public void cancelar() throws RemoteException {
        this.cont = 0;
    }
    
    @Override
    public void start() throws RemoteException {
        this.timer.start();
    }
    
    
    private HashMap crearHash(String clave, Object valor) {
        HashMap<String, Object> evento = new HashMap();
        evento.put(clave, valor);
        return evento;
    }

}
