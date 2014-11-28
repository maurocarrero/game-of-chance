package bingo.common.exceptions;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public class CantidadCartonesInvalidaException extends Exception {
    
    public CantidadCartonesInvalidaException() {
        super("Indique con cuantos cartones desea jugar");
    }
    
}
