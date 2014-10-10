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
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Fachada para módulo de jugadores
 * @author maurocarrero
 */
public class ControlJugador extends Controlador implements Observer {

    private JFrame frame;
    private Bingo modelo;
    private Jugador jugador;
    private VistaJugador vista;
    
    public ControlJugador(Bingo modelo) {
        this.modelo = modelo;
        this.vista = new VistaJugador();
    }
    
    /*public void lanzar() {
        frame = new VistaJugador(this);
        frame.setVisible(true);
    }*/
    
    /*public void login(String usuario, char[] password, int cantCartones) 
            throws JuegoEnCursoException, AccesoDenegadoException,
                CantidadCartonesInvalidaException, DemasiadosCartonesException, 
                SaldoInsuficienteException {
        sistema.loginJugador(usuario, password, cantCartones, this);
    }*/
    
   /*private void ingresar() {
        String usuario = vista.getUsuario();
        char[] password = vista.getPassword();
        int cantCartones = vista.getCantCartones();
        
        if (modelo.loginJugador(usuario, password, )) {
            vista.ocultarPaneles();
            vista.mostrarMenu();
            JOptionPane.showMessageDialog(null, "Bienvenido " + usuario + "!", "Exito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Acceso denegado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }*/
    
    private void ingresar() {
        String usuario = vista.getUsuario();
        char[] password = vista.getPassword();
        //int cantCartones = vista.getCantCartones();
        try {
            
            if (usuario.length() == 0 || password.length == 0) {
                throw new AccesoDenegadoException();
            }

            int cantCartones = -1;
            try {
              cantCartones = Integer.parseInt(vista.getCantCartones());
            } catch (NumberFormatException ex) {
                throw new CantidadCartonesInvalidaException();
            }
            
            modelo.loginJugador(usuario, password, cantCartones, this);
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
    public void update(Observable o, Object arg) {
        Bolilla bolilla = (Bolilla) arg;
        
        System.out.println(modelo);
        System.out.println("[Jugador " + this.getJugador().getUsuario() + "] " + bolilla.getValor());
    }

   @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "INGRESAR") {
            ingresar();
        }        
    }
}
