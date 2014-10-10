package bingo.controladores;

import bingo.vistas.VistaJugador;
import bingo.modelo.Bingo;
import bingo.modelo.entidades.Bolilla;
import bingo.modelo.entidades.Carton;
import bingo.modelo.entidades.Jugador;
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
public class ControlJugador extends Observable implements Observer {

    private JFrame frame;
    private Bingo sistema;
    private Jugador jugador;
    
    public ControlJugador(Bingo sistema) {
        this.sistema = sistema;
    }
    
    public void lanzar() {
        frame = new VistaJugador(this);
        frame.setVisible(true);
    }
    
    public void login(String usuario, char[] password, int cantCartones) 
            throws JuegoEnCursoException, AccesoDenegadoException,
                CantidadCartonesInvalidaException, DemasiadosCartonesException, 
                SaldoInsuficienteException {
        sistema.loginJugador(usuario, password, cantCartones, this);
    }
    
    public int getCantMaxCartones() {
        return Bingo.getCantMaxCartones();
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
        Bolilla bolilla = (Bolilla) arg;
        
        System.out.println(sistema);
        System.out.println("[Jugador " + this.getJugador().getUsuario() + "] " + bolilla.getValor());
    }
}
