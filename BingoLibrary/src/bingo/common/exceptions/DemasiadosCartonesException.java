package bingo.common.exceptions;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public class DemasiadosCartonesException extends Exception {
    
    public DemasiadosCartonesException() {
        super("No puede participar con más de %s cartones");
    }
    
}
