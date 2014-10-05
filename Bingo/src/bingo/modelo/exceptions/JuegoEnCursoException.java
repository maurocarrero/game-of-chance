package bingo.modelo.exceptions;

/**
 *
 * @author maurocarrero
 */
public class JuegoEnCursoException extends Exception {
    
    public JuegoEnCursoException() {
        super("El juego ya comenzó");
    }
    
}
