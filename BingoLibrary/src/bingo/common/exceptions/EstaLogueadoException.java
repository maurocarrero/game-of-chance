package bingo.common.exceptions;

/**
 * Exception Class EstaLogueadoException
 * @author maurocarrero/fernandogonzalez
 */
public class EstaLogueadoException extends Exception{
    
    public EstaLogueadoException(String usuario){
        super("El usuario " + usuario + " ya esta logueado");
    }    
}
