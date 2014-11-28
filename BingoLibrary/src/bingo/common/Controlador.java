/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.common;

import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public abstract class Controlador extends UnicastRemoteObject implements ActionListener {
    
    public Controlador() throws RemoteException {} 
    
}
