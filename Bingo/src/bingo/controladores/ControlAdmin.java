package bingo.controladores;

import bingo.modelo.Bingo;
import bingo.modelo.entidades.Bolilla;
import bingo.modelo.exceptions.ConfiguracionNoValidaException;
import bingo.vistas.VistaAdmin;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author maurocarrero
 */
public class ControlAdmin extends Controlador implements Observer {
    
    private static ControlAdmin instance;
    private JFrame frame;
    
    private Bingo modelo;
    private VistaAdmin vista;
    
    private ControlAdmin(VistaAdmin vista, Bingo modelo) {
        this.modelo = modelo;
        this.vista = vista;
        frame = new VistaAdmin();
        frame.setVisible(true);
    }

    public static ControlAdmin getInstance(VistaAdmin vista, Bingo modelo) {
        if (instance == null) {
            instance = new ControlAdmin(vista, modelo);
        }
        return instance;
    }
    
    public int getCantFilas() {
        return Bingo.getCantFilas();
    }

    public int getCantColumnas() {
        return Bingo.getCantColumnas();
    }

    public int getCantMaxCartones() {
       return Bingo.getCantMaxCartones();
    }

    public int getCantJugadores() {
        return Bingo.getCantJugadores();
    }

    public double getValorCarton() {
        return Bingo.getValorCarton();
    }
    
    
    @Override
    public void update(Observable o, Object arg) {
        Bolilla bolilla = (Bolilla) arg;
        System.out.println("[Administración] El juego empezó: " + bolilla.getValor());
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
        vista.poblarCamposConfiguracion(Bingo.getCantFilas(), 
                Bingo.getCantColumnas(),
                Bingo.getCantMaxCartones(),
                Bingo.getCantJugadores(),
                Bingo.getValorCarton());
    }
    
    private void configurar() {
        if (!modelo.hayJuegoActivo()) {
            vista.mostrarPanelConfiguracion();          
            poblarCamposConfiguracion();
        } else {
            vista.mostrarError("Hay un juego activo, no se puede modificar la configuración.");
        }
        
    }
    
    private void lanzarNuevaInterfazJugador() {
        modelo.lanzarNuevaInterfazJugador();
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
            
            Bingo.guardarConfiguracion(cantFilas, cantColumnas, cantMaxCartones, 
                    cantJugadores, valorCarton);
            
            vista.mostrarInfo("Configuración guardada", "Exito");
            
        } catch (NumberFormatException | ConfiguracionNoValidaException | HeadlessException ex) {
            
            vista.mostrarError("Configuración no válida");

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "INGRESAR") {
            ingresar();
        }
        if (e.getActionCommand() == "CONFIGURAR") {
            configurar();
        }
    }
}
