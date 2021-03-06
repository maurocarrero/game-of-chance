package bingoclientejugador;

import bingo.common.Controlador;
import bingo.common.exceptions.AccesoDenegadoException;
import bingo.common.exceptions.CantidadCartonesInvalidaException;
import bingo.common.exceptions.DemasiadosCartonesException;
import bingo.common.exceptions.EstaLogueadoException;
import bingo.common.exceptions.JuegoEnCursoException;
import bingo.common.exceptions.SaldoInsuficienteException;
import bingo.common.interfaces.IBingo;
import bingo.common.interfaces.IBolilla;
import bingo.common.interfaces.ICarton;
import bingo.common.interfaces.IFigura;
import bingo.common.interfaces.IJugador;
import bingo.common.interfaces.IPartida;
import bingo.common.interfaces.IRemoteObservable;
import bingo.common.interfaces.IRemoteObserver;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Clase ControlJugador
 * @author maurocarrero/fernandogonzalez
 */
public class ControlJugador extends Controlador implements ActionListener, 
        IRemoteObserver, Serializable {

    private static ControlJugador instance;
    private static VistaJugador vista;

    private String nombreServidor; 
    private IBingo bingo;
    private IJugador jugador;
    private IRemoteObservable observable;
    private ControlJugadorObservable controladorObservable;
    
    private boolean nuevaBolilla = false;
  
    private WindowAdapter windowAdapter = new WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent e) {
            try {
                if (bingo.getPartida() != null) {
                    if (bingo.getPartida().getContador() != null) {
                        bingo.getPartida().getContador().deleteObserver(instance);
                    }
                    bingo.getPartida().deleteObserver(instance);
                    if (jugador != null) {
                        bingo.getPartida().salidaForzosa(jugador);
                    }
                }
            } catch(RemoteException ex) {
                System.out.println(ex.getMessage());
            }            
            System.exit(0);
        }
    };
    
    private ControlJugador(String nombreServidor, VistaJugador vista) 
            throws RemoteException {
        this.nombreServidor = nombreServidor;
        this.controladorObservable = new ControlJugadorObservable();
        vista.addWindowListener(windowAdapter);
    }
    
    public static ControlJugador getInstance(VistaJugador vista, String nombreServidor) 
            throws RemoteException {
        if (instance == null) {
            instance = new ControlJugador(nombreServidor, vista);
            setVista(vista);
        }
        return instance;
    }
    
    public static void setVista(VistaJugador pVista) {
        vista = pVista;
    }
    
    private void ingresar() throws RemoteException {
        try {
            registrar();
            
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
            
            IJugador j = bingo.loginJugador(usuario, password, cantCartones);
            this.jugador = j;
          
            vista.esperarComienzoJuego();

            bingo.inicioCondicional();
            
            JOptionPane.showMessageDialog(null, "Bienvenido " + usuario + "!", 
                    "Exito", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (AccesoDenegadoException | JuegoEnCursoException | EstaLogueadoException | CantidadCartonesInvalidaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 
                    JOptionPane.ERROR_MESSAGE);
        } catch (DemasiadosCartonesException ex) {
            String msg = "No puede participar con más de " + 
                    bingo.getPartida().getCantMaxCartones() + " cartones";
            JOptionPane.showMessageDialog(null, msg, "Error", 
                    JOptionPane.ERROR_MESSAGE);
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "This is headless!", "Error", 
                    JOptionPane.ERROR_MESSAGE);
            vista.limpiarCampos();
        } catch (SaldoInsuficienteException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", 
                    JOptionPane.ERROR_MESSAGE);
            vista.limpiarCampos();
        } catch (ClassCastException ex) {
            JOptionPane.showMessageDialog(null, "Debe crear un perfil de Jugador para jugar.", "Error", 
                    JOptionPane.ERROR_MESSAGE);
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(null, "No se ha podido establecer la conexión con el servidor.", "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
        
    } 
    
    public int getCantMaxCartones() throws RemoteException {
        return bingo.getPartida().getCantMaxCartones();
    }


    public IJugador getJugador() throws RemoteException {
        return jugador;
    }
    
    public void inicioJuego() throws RemoteException{
        if (this.jugador == null) {
            try {
                bingo.getPartida().deleteObserver(this);
            } catch (RemoteException ex) {
                Logger.getLogger(ControlJugador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            dibujarCartones();
            mostrarInfo();
        }
    }
    
    private void finJuego(IJugador ganador, double pozo) throws RemoteException{
       vista.finalizarJuego(ganador.getUsuario(), getJugador().getSaldo(), pozo);
    }
    
    private void dibujarCartones() throws RemoteException {
        List<ICarton> cartones = this.getJugador().getCartones();
        vista.dibujarContenedorCartones(cartones.size(), 
                bingo.getPartida().getCantFilas(), 
                bingo.getPartida().getCantColumnas());
        
        for (ICarton c : cartones) {
            int[][] numeros = c.getNumeros();
            List<JCasillero> casilleros = vista.dibujarCarton(numeros, c.getCantFilas(), c.getCantColumnas());
            for (JCasillero casillero : casilleros) {
                this.controladorObservable.addObserver(casillero);
            }
        }
        
        vista.mostrarCartones();    
    }
    
    
    public void mostrarInfo() throws RemoteException {
        List<IJugador> restoJugadoresEnJuego = new ArrayList<>(bingo.getPartida().getJugadores());
        restoJugadoresEnJuego.remove(jugador);
        
        List<IFigura> figuras = bingo.getPartida().getFiguras();
        
        vista.mostrarInfo(jugador.getUsuario(), bingo.getPartida().getPozo(),
            jugador.getSaldoPreview(bingo.getPartida().getValorCarton()),
            restoJugadoresEnJuego, figuras);
    }  
    
    
    
    public void marcarCasillero(IBolilla bolilla) throws RemoteException {
        this.controladorObservable.notificar(bolilla.getValor());
        vista.setBolilla(bolilla.getValor());
        if (jugador.tieneBolilla(bolilla)) {
            vista.mostrarMensaje("¡Anotó!");
        } else {
            vista.mostrarMensaje("");
        }
        vista.mostrarPanelContinuar();
    }


    
    public void continuarParticipando(boolean continuar, boolean perdieronTodos) throws RemoteException {
       IPartida partida = bingo.getPartida();
       setNuevaBolilla(false);
       
       if (perdieronTodos) {
           partida.perdieronTodos();
       } else {
            try {
                bingo.getPartida().getContador().deleteObserver(this);
            } catch (RemoteException ex) {
                Logger.getLogger(ControlJugador.class.getName()).log(Level.SEVERE, null, ex);
            }
           partida.continuarParticipando(continuar, jugador);
       }
       
       
       if (!continuar) {
           vista.abandonarPartida();
       } else {
           if (!isNuevaBolilla()) {
               vista.ocultarPanelContinuar();
               vista.mostrarMensaje("Esperando ...");
           }
       }
    }
    
    public void actualizarTimer(int timer){
        vista.actualizarTimer(timer);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("INGRESAR")) {
                ingresar();
            }
            if (e.getActionCommand().equals("NO_CONTINUAR")) {
                continuarParticipando(false, false);
            }
             if (e.getActionCommand().equals("SI_CONTINUAR")) {
                continuarParticipando(true, false);
            }
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setNuevaBolilla(boolean nuevaBolilla) {
        this.nuevaBolilla = nuevaBolilla;
    }

    public boolean isNuevaBolilla() {
        return nuevaBolilla;
    }
    
    public boolean registrar(){
        if(System.getSecurityManager() == null){
               System.setSecurityManager(new RMISecurityManager());
        }
        System.out.println("Registrando jugador...");
        try {
            IRemoteObservable modelo = (IRemoteObservable) Naming.lookup(nombreServidor);
            this.bingo = (IBingo) modelo;
            bingo.getPartida().addObserver(this);
            System.out.println(nombreServidor + " is up.");
        }catch (NotBoundException | MalformedURLException | RemoteException ex) {
            vista.mostrarMensaje("No se ha podido establecer la conexión con el servidor.");
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void update(IRemoteObservable origen, Serializable param) throws RemoteException {
        HashMap<String, Object> evento = (HashMap) param;
        
        try {
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
                try {
                    bingo.getPartida().getContador().addObserver(this);
                } catch (RemoteException ex) {
                    Logger.getLogger(ControlJugador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (evento.containsKey("timer")) {
                int timer = (int)(evento.get("timer"));

                if (timer == 0) {
                    int cantJugadoresPendientes = bingo.getPartida().getJugadoresPendientes().size();
                    int cantJugadores = bingo.getPartida().getJugadores().size();
                    boolean perdieronTodos = false;

                    if (cantJugadoresPendientes == cantJugadores) {
                        perdieronTodos = true;
                    } else {
                        try {
                            bingo.getPartida().getContador().deleteObserver(this);
                        } catch (RemoteException ex) {
                            Logger.getLogger(ControlJugador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    continuarParticipando(false, perdieronTodos);
                } else {
                    actualizarTimer(timer);
                }
            }        
            if (evento.containsKey("ganador")) {
                IJugador ganador = (IJugador) evento.get("ganador");
                finJuego(ganador, 0);
            }
            if (evento.containsKey("finalizar_aplicacion")) {
                exit();
            }
            if (evento.containsKey("perdieron_todos")) {
                exit();
            }
        } catch (RemoteException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void exit() throws RemoteException {   
        System.exit(0);
    }
}
