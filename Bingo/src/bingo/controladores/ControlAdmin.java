package bingo.controladores;

import bingo.interfaces.IBingo;
import bingo.interfaces.IPartida;
import bingo.modelo.Bingo;
import bingo.modelo.exceptions.ConfiguracionNoValidaException;
import bingo.vistas.VistaAdmin;
import bingo.vistas.VistaJugador;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public class ControlAdmin extends Controlador implements ActionListener {
    
    private static ControlAdmin instance;
    
    private IBingo modelo;
    private VistaAdmin vista;
    
    private ControlAdmin(VistaAdmin vista, IBingo modelo) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public static ControlAdmin getInstance(VistaAdmin vista, IBingo modelo) {
        if (instance == null) {
            instance = new ControlAdmin(vista, modelo);
        }
        return instance;
    }
    
    private void ingresar() {
        String usuario = vista.getUsuario();
        char[] password = vista.getPassword();
        if (modelo.loginAdmin(usuario, password)) {
            vista.ocultarPaneles();
            vista.mostrarMenu();
            JOptionPane.showMessageDialog(null, "Bienvenido " + usuario + "!", "Exito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Acceso denegado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void poblarCamposConfiguracion() {
        List<String> figuras = modelo.getPartida().getFigurasString();
        vista.poblarCamposConfiguracion(modelo.getPartida().getCantFilas(), 
                modelo.getPartida().getCantColumnas(),
                modelo.getPartida().getCantMaxCartones(),
                modelo.getPartida().getCantJugadores(),
                modelo.getPartida().getValorCarton(),
                figuras);
    }
    
    private void configurar() {
        if (!modelo.getPartida().isJuegoActivo()) {
            vista.mostrarPanelConfiguracion();          
            poblarCamposConfiguracion();
        } else {
            vista.mostrarError("Hay un juego activo, no se puede modificar la configuración.");
        }
        
    }
    
    private void lanzarNuevaInterfazJugador() {
        IPartida partida = modelo.getPartida();
        if (!partida.isEnCurso()) {
            try {
                VistaJugador nuevaVista = new VistaJugador();
                ControlJugador control = new ControlJugador(nuevaVista, modelo);
                nuevaVista.setControlador(control);
                partida.addObserver(control);
                nuevaVista.ejecutar();
            } catch (RemoteException ex) {
                Logger.getLogger(ControlAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
   private void crearInterfaces() {
        vista.mostrarCrearInterfaces();
        lanzarNuevaInterfazJugador();
    }
    
    private void guardarConfiguracion() {
        try {
            int cantFilas = Integer.parseInt(vista.getCantFilas());
            int cantColumnas = Integer.parseInt(vista.getCantColumnas());
            int cantMaxCartones = Integer.parseInt(vista.getCantMaxCartones());
            int cantJugadores = Integer.parseInt(vista.getCantJugadores());
            double valorCarton = Double.parseDouble(vista.getValorCarton());
            boolean figuraLinea = vista.getChkLinea();
            boolean figuraDiagonal = vista.getChkDiagonal();
            boolean figuraCentro = vista.getChkCentro();
            
            modelo.guardarConfiguracion(cantFilas, cantColumnas, cantMaxCartones, 
                    cantJugadores, valorCarton, figuraLinea, figuraDiagonal, figuraCentro);
            
            vista.ocultarPaneles();
            vista.mostrarInfo("Configuración guardada", "Exito");
            
        } catch (NumberFormatException | ConfiguracionNoValidaException | HeadlessException ex) {
            
            vista.mostrarError("Configuración no válida");

        }
    }

    private void finalizarAplicacion() {
        vista.dispose();
        modelo.finalizarAplicacion();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("INGRESAR")) {
            ingresar();
        }
        if (e.getActionCommand().equals("CONFIGURAR")) {
            configurar();
        }
        if (e.getActionCommand().equals("GUARDAR_CONFIGURACION")) {
            guardarConfiguracion();
        }
        if (e.getActionCommand().equals("CREAR_INTERFACES")) {
            crearInterfaces();
        }
        if (e.getActionCommand().equals("FINALIZAR_APLICACION")) {
            finalizarAplicacion();
        }
    }
}
