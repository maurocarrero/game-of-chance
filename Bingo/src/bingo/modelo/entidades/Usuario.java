/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.modelo.entidades;

/**
 *
 * @author maurocarrero
 */
public abstract class Usuario {
    private String usuario;
    private String password;
    private Boolean logeado = false;  

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }
    
    public Boolean estaLogeado(){
        return this.logeado;
    }
    
    public void setLogeado(Boolean logeado){
        this.logeado = logeado;
    }
    
    public String getUsuario() {
        return usuario;
    }
    
    public boolean validate(char[] password) {
        String passStr = String.valueOf(password);
        return this.password.equals(passStr);
    }
}
