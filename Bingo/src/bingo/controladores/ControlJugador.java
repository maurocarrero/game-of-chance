package bingo.controladores;

import bingo.interfaces.IBolilla;
import bingo.interfaces.ICarton;
import bingo.interfaces.IJugador;
import bingo.modelo.Bingo;
import bingo.modelo.entidades.Jugador;
import bingo.modelo.exceptions.AccesoDenegadoException;
import bingo.modelo.exceptions.CantidadCartonesInvalidaException;
import bingo.modelo.exceptions.DemasiadosCartonesException;
import bingo.modelo.exceptions.EstaLogeadoException;
import bingo.modelo.exceptions.JuegoEnCursoException;
import bingo.modelo.exceptions.SaldoInsuficienteException;
import bingo.vistas.JCasillero;
import bingo.vistas.VistaJugador;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;

/**
 * @author maurocarrero
 */
public class ControlJugador extends Controlador implements ActionListener, Observer {

    private Bingo modelo;
    private IJugador jugador;
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
            
        } catch (AccesoDenegadoException | JuegoEnCursoException | EstaLogeadoException | 
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


    public IJugador getJugador() {
        return jugador;
    }
    
    public void inicioJuego() {
       dibujarCartones();
       mostrarInfo();
    }
    
    private void finJuego(String ganador) {
       vista.mostrarMensaje("Fin del juego, Ganador: " + ganador);
       vista.finalizarJuego();
    }
    
    private void dibujarCartones() {
        List<ICarton> cartones = this.getJugador().getCartones();
        vista.dibujarContenedorCartones(cartones.size(), Bingo.getCantFilas(), Bingo.getCantColumnas());
        
        for (ICarton c : cartones) {
            int[][] numeros = c.getNumeros();
            List<JCasillero> casilleros = vista.dibujarCarton(numeros, c.getCantFilas(), c.getCantColumnas());
            for (JCasillero casillero : casilleros) {
                addObserver(casillero);
            }
        }
        
        vista.mostrarCartones();    
    }
    
    
    public void mostrarInfo() {
        List<IJugador> restoJugadoresEnJuego = new ArrayList<>(modelo.getPartida().getJugadores());
        restoJugadoresEnJuego.remove(jugador);
        vista.mostrarInfo(jugador.toString(), modelo.getPartida().getPozo(),
            jugador.getSaldoPreview(modelo.getPartida().getValorCarton()),
            restoJugadoresEnJuego);
    }  
    
    public void marcarCasillero(IBolilla bolilla) {
        setChanged();
        notifyObservers(bolilla.getValor());
        vista.setBolilla(bolilla.getValor());
        if (jugador.tieneBolilla(bolilla)) {
            vista.mostrarMensaje("¡Anotó!");
        } else {
            vista.mostrarMensaje("");
        }
    }
    
     private void finalizarPartida(){        
        if (modelo.getPartida().getJugadores().size() == 1 && modelo.getPartida().isEnCurso()){
            finJuego(modelo.getPartida().getJugadores().get(0).getUsuario());
        } 
    }
    
    //HAY QUE VER QUE HACER ACA CUANDO UN USUARIO DEJA DE JUGAR!!
    public void continuarParticipando(boolean continuar){
       this.modelo.getPartida().continuarParticipando(continuar, jugador);
       if (!continuar) {
           vista.abandonarPartida();
       }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("INGRESAR")) {
            ingresar();
        }
        if (e.getActionCommand().equals("NO_CONTINUAR")) {
            continuarParticipando(false);
        }
         if (e.getActionCommand().equals("SI_CONTINUAR")) {
             continuarParticipando(true);
        }        
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg == "INICIO") {
            inicioJuego();
            return;
        }
        if (arg == "ABANDONO") {
            vista.actualizarPozo(modelo.getPartida().getPozo());
            finalizarPartida();
            return;
        }
        else {
            try {
                IBolilla bolilla = (IBolilla) arg;
                marcarCasillero(bolilla);
            } catch (ClassCastException ex) {
                finJuego(arg.toString());
            }
        }
    }
}
