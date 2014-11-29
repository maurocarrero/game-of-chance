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
    private boolean activa;

    public Figura(String nombre) throws RemoteException {
        this.nombre = nombre;
        this.activa = false;
    }

    @Override
    public String getNombre() throws RemoteException {
        return nombre;
    }

    @Override
    public boolean isActiva() throws RemoteException {
        return activa;
    }

    @Override
    public void setActiva(boolean activa) throws RemoteException {
        this.activa = activa;
    }
    
}
