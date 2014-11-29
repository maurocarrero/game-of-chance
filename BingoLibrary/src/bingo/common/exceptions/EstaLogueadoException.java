/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bingo.common.exceptions;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public class EstaLogueadoException extends Exception{
    
    public EstaLogueadoException(String usuario){
        super("El usuario " + usuario + " ya esta logueado");
    }    
}
