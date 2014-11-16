/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bingo.modelo.entidades;

import bingo.modelo.Partida;
import java.util.HashMap;
import java.util.Observable;

/**
 *
 * @author zorro
 */
public class Timer extends Observable implements Runnable {

    private int tiempo = 0;
    private static Timer instance;
    private Thread thread = null;
    
    public Timer(int segundos){
        this.tiempo = segundos;
    }
    
    public static Timer getInstance() {
        if (instance == null) {
            instance = new Timer();
        }
        return instance;
    }
    
    public Timer(){}
       
    @Override
    public void run() {
        System.out.println("Observers: " + this.countObservers());
        int cont = tiempo;
        try {
            while (!Thread.currentThread().isInterrupted() && cont >= 0){
                setChanged();
                notifyObservers(crearHash("timer", cont));
                System.out.println("Timer " + cont);
                Thread.sleep(1000);
                cont--;
            }
        } catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void start() {
        thread = new Thread(this);
        thread.start();
    }
    
    private HashMap crearHash(String clave, Object valor){
        HashMap<String, Object> evento = new HashMap();
        evento.put(clave, valor);
        return evento;
    }
    
    public int getTiempo(){
        return tiempo;
    }
    
    public void setTiempo(int segundos){
        tiempo = segundos;
    }
    
}
