package bingo.modelo.exceptions;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public class JuegoEnCursoException extends Exception {
    
    public JuegoEnCursoException() {
        super("El juego ya comenzó");
    }
    
}
