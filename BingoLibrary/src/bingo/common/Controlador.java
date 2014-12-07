package bingo.common;

import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Class Controlador
 * @author maurocarrero/fernandogonzalez
 */
public abstract class Controlador extends UnicastRemoteObject implements ActionListener {
    
    public Controlador() throws RemoteException {} 
    
}
