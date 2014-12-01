/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bingo.servidor.modelo.entidades;

import bingo.common.interfaces.IFigura;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author zorro
 */
public abstract class Figura extends UnicastRemoteObject implements IFigura {
    
    private final String nombre;

    public Figura(String nombre) throws RemoteException {
        this.nombre = nombre;        
    }

    @Override
    public String getNombre() throws RemoteException {
        return nombre;
    }  
    
}
