/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.modelo.entidades;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public abstract class Usuario {
    private String usuario;
    private String password;
    private Boolean logueado = false;  

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
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
}
