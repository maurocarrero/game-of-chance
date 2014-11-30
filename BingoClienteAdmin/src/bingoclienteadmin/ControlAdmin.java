package bingoclienteadmin;

import bingo.common.Controlador;
import bingo.common.exceptions.ConfiguracionNoValidaException;
import bingo.common.interfaces.IBingo;
import bingo.common.interfaces.IRemoteObservable;
import bingo.common.interfaces.IRemoteObserver;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public class ControlAdmin extends Controlador implements ActionListener, IRemoteObserver, Serializable {
    
    private static ControlAdmin instance;
    
    private String nombreServidor = "";
        
    private IBingo bingo;
    private static VistaAdmin vista;

    public static void setVista(VistaAdmin pVista) {
        vista = pVista;
    }
    
    private ControlAdmin(String nombreServidor) throws RemoteException {
        this.nombreServidor = nombreServidor;
    }

    public static ControlAdmin getInstance(VistaAdmin vista, String nombreServidor) 
            throws RemoteException {
        if (instance == null) {
            instance = new ControlAdmin(nombreServidor);
            setVista(vista);
        }
        return instance;
    }
    
    private void ingresar() throws RemoteException {
        String usuario = vista.getUsuario();
        char[] password = vista.getPassword();
        if (bingo.loginAdmin(usuario, password)) {
            vista.ocultarPaneles();
            vista.mostrarMenu();
            JOptionPane.showMessageDialog(null, "Bienvenido " + usuario + "!", "Exito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Acceso denegado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void poblarCamposConfiguracion() throws RemoteException {
        List<String> figuras = bingo.getPartida().getFigurasString();
        vista.poblarCamposConfiguracion(bingo.getPartida().getCantFilas(), 
                bingo.getPartida().getCantColumnas(),
                bingo.getPartida().getCantMaxCartones(),
                bingo.getPartida().getCantJugadores(),
                bingo.getPartida().getValorCarton(),
                figuras);
    }
    
    private void configurar() throws RemoteException {
        System.out.println(bingo.getPartida().toString());
        if (!bingo.getPartida().isJuegoActivo()) {
            vista.mostrarPanelConfiguracion();          
            poblarCamposConfiguracion();
        } else {
            vista.mostrarError("Hay un juego activo, no se puede modificar la configuración.");
        }
        
    }
    
   /* private void lanzarNuevaInterfazJugador() throws RemoteException {
        IPartida partida = bingo.getPartida();
        if (!partida.isEnCurso()) {
            try {
                VistaJugador nuevaVista = new VistaJugador();
                ControlJugador control = new ControlJugador(nuevaVista, bingo);
                nuevaVista.setControlador(control);
                partida.addObserver(control);
                nuevaVista.ejecutar();
            } catch (RemoteException ex) {
                Logger.getLogger(ControlAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }*/
    
   /*private void crearInterfaces() throws RemoteException  {
        vista.mostrarCrearInterfaces();
        lanzarNuevaInterfazJugador();
    }*/
    
    private void guardarConfiguracion() throws RemoteException  {
        try {
            int cantFilas = Integer.parseInt(vista.getCantFilas());
            int cantColumnas = Integer.parseInt(vista.getCantColumnas());
            int cantMaxCartones = Integer.parseInt(vista.getCantMaxCartones());
            int cantJugadores = Integer.parseInt(vista.getCantJugadores());
            double valorCarton = Double.parseDouble(vista.getValorCarton());
            boolean figuraLinea = vista.getChkLinea();
            boolean figuraDiagonal = vista.getChkDiagonal();
            boolean figuraCentro = vista.getChkCentro();
            
            System.out.println("ControlAdmin getCantJugadores(): " + bingo.getPartida().getCantJugadores());

            
            bingo.guardarConfiguracion(cantFilas, cantColumnas, cantMaxCartones, 
                    cantJugadores, valorCarton, figuraLinea, figuraDiagonal, figuraCentro);
                        
            System.out.println("ControlAdmin getCantJugadores(): " + bingo.getPartida().getCantJugadores());
            
            vista.ocultarPaneles();
            vista.mostrarInfo("Configuración guardada", "Exito");
            
        } catch (NumberFormatException | ConfiguracionNoValidaException | HeadlessException ex) {
            
            vista.mostrarError("Configuración no válida");

        }
    }

    private void finalizarAplicacion() throws RemoteException  {
        vista.dispose();
        bingo.finalizarAplicacion();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            if (e.getActionCommand().equals("INGRESAR")) {
                ingresar();
            }
            if (e.getActionCommand().equals("CONFIGURAR")) {
                configurar();
            }
            if (e.getActionCommand().equals("GUARDAR_CONFIGURACION")) {
                guardarConfiguracion();
            }            
            if (e.getActionCommand().equals("FINALIZAR_APLICACION")) {
                finalizarAplicacion();
            }
        } catch(RemoteException ex) {
            System.out.println(ex.getMessage());        
        }
    }
    
    
    public boolean registrar(){
        if(System.getSecurityManager() == null) {
               System.setSecurityManager(new RMISecurityManager());
        }
        System.out.println("Registrando administrador...");
        try {
            IRemoteObservable modelo = (IRemoteObservable) Naming.lookup(nombreServidor);
            this.bingo = (IBingo) modelo;
            modelo.addObserver(this);
            System.out.println(nombreServidor + " is up.");
            
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void update(IRemoteObservable origen, Serializable param) throws RemoteException {
        this.bingo = (IBingo) origen;
        System.out.println("Configuración nueva: " + this.bingo.getPartida().getCantJugadores());
    }
    
}
