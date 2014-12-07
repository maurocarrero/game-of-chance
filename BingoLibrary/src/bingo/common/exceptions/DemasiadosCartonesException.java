package bingo.common.exceptions;

/**
 * Exception Class DemasiadosCartonesException
 * @author maurocarrero/fernandogonzalez
 */
public class DemasiadosCartonesException extends Exception {
    
    public DemasiadosCartonesException() {
        super("No puede participar con más de %s cartones");
    }
    
}
