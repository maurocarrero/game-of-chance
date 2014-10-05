package bingo.jugadores;

import bingo.SistemaFacade;
import bingo.modelo.exceptions.AccesoDenegadoException;
import bingo.modelo.exceptions.DemasiadosCartonesException;
import bingo.modelo.exceptions.JuegoEnCursoException;
import bingo.modelo.exceptions.SaldoInsuficienteException;
import javax.swing.JFrame;

/**
 * Fachada para módulo de jugadores
 * @author maurocarrero
 */
public class JugadorFacade {
    private static JugadorFacade instance;
    private JFrame frame;
    
    private SistemaFacade sistema;
    
    private JugadorFacade(SistemaFacade sistema) {
        this.sistema = sistema;
    }
    
    public void lanzarInterfazJugador() {
        frame = new InterfazJugadorFrame(this);
        frame.setVisible(true);
    }

    public static JugadorFacade getInstance(SistemaFacade sistema) {
        if (instance == null) {
            instance = new JugadorFacade(sistema);
        }
        return instance;
    }
    
    public void login(String usuario, char[] password, int cantCartones) 
            throws JuegoEnCursoException, AccesoDenegadoException,
                DemasiadosCartonesException, SaldoInsuficienteException {
        sistema.loginJugador(usuario, password, cantCartones);
    }
}
