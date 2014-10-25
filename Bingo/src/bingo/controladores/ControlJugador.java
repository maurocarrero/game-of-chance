package bingo.controladores;

import bingo.interfaces.IBolilla;
import bingo.interfaces.ICarton;
import bingo.interfaces.IJugador;
import bingo.modelo.Bingo;
import bingo.modelo.Partida;
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
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;

/**
 * @author maurocarrero/fernandogonzalez
 */
public class ControlJugador extends Controlador implements ActionListener, Observer {

    private Bingo modelo;
    private IJugador jugador;
    private VistaJugador vista;
    
    private boolean nuevaBolilla = false;
    
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
                EstaLogeadoException | SaldoInsuficienteException | 
                CantidadCartonesInvalidaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 
                    JOptionPane.ERROR_MESSAGE);
        } catch (DemasiadosCartonesException ex) {
            String msg = "No puede participar con más de " + 
                    Partida.getCantMaxCartones() + " cartones";
            JOptionPane.showMessageDialog(null, msg, "Error", 
                    JOptionPane.ERROR_MESSAGE);
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "This is headless!", "Error", 
                    JOptionPane.ERROR_MESSAGE);
        } catch (ClassCastException ex) {
            JOptionPane.showMessageDialog(null, "Debe crear un perfil de Jugador para jugar.", "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
        
    } 
    
    
    public int getCantMaxCartones() {
        return Partida.getCantMaxCartones();
    }


    public IJugador getJugador() {
        return jugador;
    }
    
    public void inicioJuego() {
        if (this.jugador == null) {
            vista.dispose();
            modelo.getPartida().deleteObserver(this);
        } else {
            dibujarCartones();
            mostrarInfo();
        }
    }
    
    private void finJuego(IJugador ganador, double pozo) {
       vista.finalizarJuego(ganador.getUsuario(), getJugador().getSaldo(), pozo);
    }
    
    private void dibujarCartones() {
        List<ICarton> cartones = this.getJugador().getCartones();
        vista.dibujarContenedorCartones(cartones.size(), Partida.getCantFilas(), Partida.getCantColumnas());
        
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
        List<IJugador> restoJugadoresEnJuego = new ArrayList<>(modelo.getPartidaInstance().getJugadores());
        restoJugadoresEnJuego.remove(jugador);
        vista.mostrarInfo(jugador.toString(), modelo.getPartidaInstance().getPozo(),
            jugador.getSaldoPreview(Partida.getValorCarton()),
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
        vista.mostrarPanelContinuar();
    }
    
    
    
    public void continuarParticipando(boolean continuar){
       Partida partida = modelo.getPartidaInstance();
       setNuevaBolilla(false);       
       partida.continuarParticipando(continuar, jugador);
       
       if (!continuar) {
           vista.abandonarPartida();
       } else {
           if (!isNuevaBolilla()) {
               vista.ocultarPanelContinuar();
               vista.mostrarMensaje("Esperando a los demás jugadores...");
           }           
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
        HashMap<String, Object> evento = (HashMap)arg;
        
        if (evento.containsKey("inicio")) {
            inicioJuego();
        }
        if (evento.containsKey("abandono")) {
            mostrarInfo();
        }
        if (evento.containsKey("bolilla")) {
            IBolilla bolilla = (IBolilla)evento.get("bolilla");
            setNuevaBolilla(true);
            marcarCasillero(bolilla);
        }
        if (evento.containsKey("ganador")) {
            IJugador ganador = (IJugador)evento.get("ganador");
            finJuego(ganador, 0);
        }
        if (evento.containsKey("finalizar_aplicacion")) {
            vista.dispose();
            System.exit(0);
        }
    }

    public void setNuevaBolilla(boolean nuevaBolilla) {
        this.nuevaBolilla = nuevaBolilla;
    }

    public boolean isNuevaBolilla() {
        return nuevaBolilla;
    }
    
    
}
