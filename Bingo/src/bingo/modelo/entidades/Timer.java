/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bingo.modelo.entidades;

import java.util.HashMap;
import java.util.Observable;

/**
 *
 * @author zorro
 */
public class Timer extends Observable implements Runnable {

    private int tiempo = 0;
    private int cont = 0;
    
    
    
    public Timer(int segundos){
        this.tiempo = (segundos > 120) ? segundos : 20;
    }
    
    
    
    @Override
    public void run() {
        cont = tiempo;
        try {
            while (!Thread.currentThread().isInterrupted() && cont >= 0) {
                setChanged();
                notifyObservers(crearHash("timer", cont));
                Thread.sleep(1000);
                cont--;
            }
        } catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    public static Timer start(int segundos) {
        Timer timer = new Timer(segundos);
        (new Thread(timer)).start();
        return timer;
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
