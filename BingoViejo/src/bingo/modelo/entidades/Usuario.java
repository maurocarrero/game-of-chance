/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.modelo.entidades;

import bingo.persistencia.UsuarioPersistente;
import java.util.ArrayList;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public abstract class Usuario {
    private int id;
    private String usuario;
    private String password;
    private Boolean logueado = false;
    
    private UsuarioPersistente persistente = new UsuarioPersistente();

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    
    public Boolean estaLogueado(){
        return this.logueado;
    }
    
    public void setLogueado(Boolean logueado){
        this.logueado = logueado;
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
    
    public void guardar(Usuario u){
        persistente.setUsuario(u);
        persistente.guardar();        
    }
   /* public ArrayList getUsuarioBase(){
        persistente.setUsuario(null);
        return persistente.obtenerTodos);
    }*/
    
    public void eliminar(Usuario u){
        persistente.setUsuario(u);
        persistente.eliminar();
    }
    
}
