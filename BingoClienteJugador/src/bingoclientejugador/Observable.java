/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bingoclientejugador;

import java.util.ArrayList;

/**
 *
 * @author zorro
 */
public class Observable extends java.util.Observable {
    
    
    public void avisar(Object p){
       setChanged();
       notifyObservers(p);
    }
     
    public ArrayList<Object> observadores = new ArrayList();
    
    public Observable(){}
    
    public void agregar(Object o){
        if(!observadores.contains(o)){
            observadores.add(o);
        }
    }

    public void eliminar(Object o) {
        observadores.remove(o);
    }
    
    public void notificar(Object param){
        ArrayList<Object> tmp = new ArrayList(observadores);
        for(Object obs:tmp){        
                obs.actualizar(this, param);
                observadores.remove(obs);
            }
        }    
}
