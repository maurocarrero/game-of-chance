package bingo.controladores;

import bingo.interfaces.IBolilla;
import bingo.modelo.Bingo;
import bingo.modelo.entidades.Carton;
import bingo.modelo.entidades.Jugador;
import bingo.modelo.exceptions.AccesoDenegadoException;
import bingo.modelo.exceptions.CantidadCartonesInvalidaException;
import bingo.modelo.exceptions.DemasiadosCartonesException;
import bingo.modelo.exceptions.JuegoEnCursoException;
import bingo.modelo.exceptions.SaldoInsuficienteException;
import bingo.vistas.JCasillero;
import bingo.vistas.VistaJugador;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;

/**
 * @author maurocarrero
 */
public class ControlJugador extends Controlador implements ActionListener, Observer {

    private Bingo modelo;
    private Jugador jugador;
    private VistaJugador vista;
    
    public ControlJugador(VistaJugador vista, Bingo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
    
    private void ingresar() {
        try {
            
            String usuario = vista.getUsuario();
            char[] password = vista.getPassword();
        
            if (usuario.length() == 0 || password.length == 0) {
                throw new AccesoDenegadoException();
            }

            int cantCartones = -1;
            try {
              cantCartones = Integer.parseInt(vista.getCantCartones());
            } catch (NumberFormatException ex) {
                throw new CantidadCartonesInvalidaException();
            }
            
            Jugador j = modelo.loginJugador(usuario, password, cantCartones);
            this.jugador = j;
            
            vista.esperarComienzoJuego();

            modelo.inicioCondicional();
            
            JOptionPane.showMessageDialog(null, "Bienvenido " + usuario + "!", 
                    "Exito", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (AccesoDenegadoException | JuegoEnCursoException | 
                SaldoInsuficienteException | CantidadCartonesInvalidaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (DemasiadosCartonesException ex) {
            String msg = "No puede participar con más de " + modelo.getCantMaxCartones() + " cartones";
            JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "This is headless!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public int getCantMaxCartones() {
        return Bingo.getCantMaxCartones();
    }


    public Jugador getJugador() {
        return jugador;
    }
    
    
    private void dibujarCartones() {
        System.out.println("Dibujando cartones");

        List<Carton> cartones = this.getJugador().getCartones();

        vista.dibujarContenedorCartones(cartones.size(), Bingo.getCantFilas(), Bingo.getCantColumnas());
        
        for (Carton c : cartones) {
            int[][] numeros = c.getNumeros();
            List<JCasillero> casilleros = vista.dibujarCarton(numeros, c.getCantFilas(), c.getCantColumnas());
            for (JCasillero casillero : casilleros) {
                addObserver(casillero);
            }
        }
    }
    
    public void marcarCasillero(IBolilla bolilla) {
        setChanged();
        notifyObservers(bolilla.getValor());
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("INGRESAR")) {
            ingresar();
        }        
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            IBolilla bolilla = (IBolilla) arg;
            marcarCasillero(bolilla);
            System.out.println("Bolilla " + bolilla.getValor());
        } else {
            System.out.println("Inicio del juego desde el controlador del jugador " + this.jugador.getUsuario());
            dibujarCartones();
        }
    }
}
