/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bingo.modelo.entidades;

import bingo.common.RObservable;
import bingo.interfaces.ITimer;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zorro
 */
public class Timer extends RObservable implements ITimer {

    private int tiempo = 0;
    private int cont = 0;
    
    
    
    public Timer(int segundos) throws RemoteException {
        this.tiempo = (segundos > 120) ? segundos : 20;
    }
    
    
    
    @Override
    public void run() {
        cont = tiempo;
        try {
            while (!Thread.currentThread().isInterrupted() && cont >= 0) {
                // setChanged();
                notifyObservers(crearHash("timer", cont));
                Thread.sleep(1000);
                cont--;
            }
        } catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    @Override
    public void start() {
        (new Thread(this)).start();
    }
    
    
    
    private HashMap crearHash(String clave, Object valor){
        HashMap<String, Object> evento = new HashMap();
        evento.put(clave, valor);
        return evento;
    }
    
    
    
    public void abandonar() {
        cont = 0;
    }

}
