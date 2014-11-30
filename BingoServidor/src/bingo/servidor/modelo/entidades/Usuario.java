/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.servidor.modelo.entidades;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public abstract class Usuario extends UnicastRemoteObject implements Serializable, Remote {
    private int id;
    private String usuario;
    private String password;
    private Boolean logueado = false;
    
    public Usuario() throws RemoteException {}
    
    public Usuario(String usuario, String password) throws RemoteException {
        this.usuario = usuario;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    
    public boolean estaLogueado() throws RemoteException {
        return this.logueado;
    }
    
    public void setLogueado(Boolean logueado){
        this.logueado = logueado;
    }
    
    public void desloguear() throws RemoteException {
        this.logueado = false;
    }
    
    public String getUsuario() {
        return usuario;
    }
    
    public boolean validate(char[] password) {
        String passStr = String.valueOf(password);
        return this.password.equals(passStr);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!this.usuario.equals(other.usuario)) {
            return false;
        }
        return true;
    }
    
    
}
