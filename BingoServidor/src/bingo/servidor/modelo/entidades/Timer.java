/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bingo.servidor.modelo.entidades;

import bingo.common.interfaces.IContador;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zorro
 */
public class Timer implements Runnable {

    private IContador contador;
    
    public Timer(Contador contador) throws RemoteException {
        this.contador = contador;
    }
        
    @Override
    public void run() {
        try {
            int cont = this.contador.getCont();
            while (!Thread.currentThread().isInterrupted() && 
                    this.contador.getCont() >= 0) {
                    this.contador.notificar();
                    Thread.sleep(1000);
                    this.contador.decrementar();
            }
        } catch(InterruptedException e) {
            System.out.println(e.getMessage());
        } catch (RemoteException ex) {
            Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void start() {
       (new Thread(this)).start();
    }

}
