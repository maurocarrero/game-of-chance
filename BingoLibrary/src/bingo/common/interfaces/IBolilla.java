/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public interface IBolilla extends Remote{
     
    public int getValor() throws RemoteException;
    
}
