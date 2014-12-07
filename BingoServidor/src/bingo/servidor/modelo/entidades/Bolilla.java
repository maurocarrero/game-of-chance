package bingo.servidor.modelo.entidades;

import bingo.common.interfaces.IBolilla;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Clase Bolilla
 * @author maurocarrero/fernandogonzalez
 */
public class Bolilla extends UnicastRemoteObject implements IBolilla, Serializable {
    private int valor;

    public Bolilla(int valor) throws RemoteException{
        this.valor = valor;
    }
    
    @Override
    public int getValor() throws RemoteException{
        return valor;
    }
}
