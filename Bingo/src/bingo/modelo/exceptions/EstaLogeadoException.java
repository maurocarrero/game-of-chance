/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bingo.modelo.exceptions;

/**
 *
 * @author zorro
 */
public class EstaLogeadoException extends Exception{
    
    public EstaLogeadoException(String usuario){
        super("El usuario " + usuario + " ya esta logeado");
    }    
}
