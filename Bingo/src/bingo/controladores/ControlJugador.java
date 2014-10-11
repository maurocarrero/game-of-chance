package bingo.controladores;

import bingo.modelo.Bingo;
import bingo.modelo.entidades.Carton;
import bingo.modelo.entidades.Jugador;
import bingo.modelo.exceptions.AccesoDenegadoException;
import bingo.modelo.exceptions.CantidadCartonesInvalidaException;
import bingo.modelo.exceptions.DemasiadosCartonesException;
import bingo.modelo.exceptions.JuegoEnCursoException;
import bingo.modelo.exceptions.SaldoInsuficienteException;
import bingo.vistas.VistaJugador;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author maurocarrero
 */
public class ControlJugador extends Controlador implements ActionListener {

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
            
            modelo.loginJugador(usuario, password, cantCartones);
            vista.esperarComienzoJuego();
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
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("INGRESAR")) {
            ingresar();
        }        
    }
}
