package bingoclienteadmin;

import bingo.common.Controlador;
import bingo.common.exceptions.ConfiguracionNoValidaException;
import bingo.common.interfaces.IBingo;
import bingo.common.interfaces.IRemoteObservable;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public class ControlAdmin extends Controlador implements ActionListener, Serializable {
    
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
            
            bingo.guardarConfiguracion(cantFilas, cantColumnas, cantMaxCartones, 
                    cantJugadores, valorCarton, figuraLinea, figuraDiagonal, figuraCentro);
           
            vista.ocultarPaneles();
            vista.mostrarInfo("Configuración guardada", "Exito");
            
        } catch (NumberFormatException | ConfiguracionNoValidaException | HeadlessException ex) {
            
            vista.mostrarError("Configuración no válida");

        }
    }

    private void finalizarAplicacion() throws RemoteException  {
        vista.dispose();
        new Thread() {

            @Override
            public void run() {
                try {
                    bingo.finalizarAplicacion();
                } catch (RemoteException ex) {
                    Logger.getLogger(ControlAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }.start();
        
        exit();
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
            System.out.println(nombreServidor + " is up.");
            
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    
    public void exit() throws RemoteException {
        try{
            Naming.unbind(nombreServidor);
            UnicastRemoteObject.unexportObject(this, true);
            System.out.println("Desconectando de Bingo server.");
        }
        catch(RemoteException | NotBoundException | MalformedURLException ex){
            System.out.println("Algo salí mal con la desconexión RMI");
        }
        System.exit(0);
    }
    
}
