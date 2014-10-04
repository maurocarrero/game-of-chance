/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.modelo;

/**
 *
 * @author maurocarrero
 */
public abstract class Usuario {
    private String usuario;
    private String password;

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }
    
    public String getUsuario() {
        return usuario;
    }
    
}
