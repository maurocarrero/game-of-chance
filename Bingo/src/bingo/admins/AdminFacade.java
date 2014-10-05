package bingo.admins;

import bingo.SistemaFacade;
import bingo.modelo.Bolilla;
import bingo.modelo.exceptions.ConfiguracionNoValidaException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;

/**
 *
 * @author maurocarrero
 */
public class AdminFacade extends Observable implements Observer {
    
    private static AdminFacade instance;
    private JFrame frame;
    
    private SistemaFacade sistema;
    
    private AdminFacade(SistemaFacade sistema) {
        this.sistema = sistema; 
        frame = new InterfazAdminFrame(this);
        frame.setVisible(true);
    }

    public static AdminFacade getInstance(SistemaFacade sistema) {
        if (instance == null) {
            instance = new AdminFacade(sistema);
        }
        return instance;
    }
    
    public boolean login(String usuario, char[] password) {
        return sistema.loginAdmin(usuario, password);
    }
    
    public void guardarConfiguracion(int cF, int cCols, int cCart, 
            int cJ, double vC) throws ConfiguracionNoValidaException {
        SistemaFacade.guardarConfiguracion(cF, cCols, cCart, cJ, vC);
    }
    
    public int getCantFilas() {
        return SistemaFacade.getCantFilas();
    }

    public int getCantColumnas() {
        return SistemaFacade.getCantColumnas();
    }

    public int getCantMaxCartones() {
       return SistemaFacade.getCantMaxCartones();
    }

    public int getCantJugadores() {
        return SistemaFacade.getCantJugadores();
    }

    public double getValorCarton() {
        return SistemaFacade.getValorCarton();
    }
    
    public void lanzarNuevaInterfazJugador() {
        sistema.lanzarNuevaInterfazJugador();
    }
    
    public boolean hayJuegoActivo() {
        return sistema.hayJuegoActivo();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        Bolilla bolilla = (Bolilla) arg;
        System.out.println("[Administración] El juego empezó: " + bolilla.getValor());
    }
    
}
