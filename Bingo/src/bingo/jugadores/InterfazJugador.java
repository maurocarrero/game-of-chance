package bingo.jugadores;

import bingo.SistemaFacade;
import bingo.modelo.Carton;
import bingo.modelo.Jugador;
import bingo.modelo.exceptions.AccesoDenegadoException;
import bingo.modelo.exceptions.CantidadCartonesInvalidaException;
import bingo.modelo.exceptions.DemasiadosCartonesException;
import bingo.modelo.exceptions.JuegoEnCursoException;
import bingo.modelo.exceptions.SaldoInsuficienteException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;

/**
 * Fachada para módulo de jugadores
 * @author maurocarrero
 */
public class InterfazJugador extends Observable implements Observer {

    private JFrame frame;
    private SistemaFacade sistema;
    private Jugador jugador;
    
    public InterfazJugador(SistemaFacade sistema) {
        this.sistema = sistema;
    }
    
    public void lanzar() {
        frame = new InterfazJugadorFrame(this);
        frame.setVisible(true);
    }
    
    public void login(String usuario, char[] password, int cantCartones) 
            throws JuegoEnCursoException, AccesoDenegadoException,
                CantidadCartonesInvalidaException, DemasiadosCartonesException, 
                SaldoInsuficienteException {
        sistema.loginJugador(usuario, password, cantCartones, this);
    }
    
    public int getCantMaxCartones() {
        return SistemaFacade.getCantMaxCartones();
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Jugador getJugador() {
        return jugador;
    }
    
    public void addCarton(Carton carton) {
        carton.dibujar();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("[Jugadores] El juego empezó: " + Integer.parseInt(arg.toString()));
    }
}
