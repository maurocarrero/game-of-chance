package bingo.servidor.modelo.entidades;

import bingo.common.interfaces.IFigura;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Clase Figura
 * @author maurocarrero/fernandogonzalez
 */
public abstract class Figura extends UnicastRemoteObject implements IFigura, Serializable {
    
    private final String nombre;

    public Figura(String nombre) throws RemoteException {
        this.nombre = nombre;        
    }

    @Override
    public String getNombre() throws RemoteException {
        return nombre;
    }  
    
}
