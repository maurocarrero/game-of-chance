package bingo.modelo.exceptions;

/**
 *
 * @author maurocarrero
 */
public class DemasiadosCartonesException extends Exception {
    
    public DemasiadosCartonesException() {
        super("No puede participar con más de %s cartones");
    }
    
}
