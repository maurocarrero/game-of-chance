package bingo.common.exceptions;

/**
 * Exception Class JuegoEnCursoException
 * @author maurocarrero/fernandogonzalez
 */
public class JuegoEnCursoException extends Exception {
    
    public JuegoEnCursoException() {
        super("El juego ya comenzó");
    }
    
}
