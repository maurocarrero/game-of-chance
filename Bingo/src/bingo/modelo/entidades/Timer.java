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
    private static Timer instance;
    
    public Timer(int segundos){
        this.tiempo = segundos;
    }
    
    public static Timer getInstance() {
        if (instance == null) {
            instance = new Timer();
        }
        return instance;
    }
    
    public Timer(){
        
    }
       
    @Override
    public void run() {
        try {
            while(tiempo >= 0){
                setChanged();
                notifyObservers(crearHash("timer", tiempo));
                Thread.sleep(1000);
                tiempo = tiempo -1;
            }
            
        }catch(InterruptedException e){
            e.printStackTrace();
        }
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
