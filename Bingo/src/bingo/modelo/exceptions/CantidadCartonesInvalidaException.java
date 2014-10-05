package bingo.modelo.exceptions;

/**
 *
 * @author maurocarrero
 */
public class CantidadCartonesInvalidaException extends Exception {
    
    public CantidadCartonesInvalidaException() {
        super("Indique con cuantos cartones desea jugar");
    }
    
}
