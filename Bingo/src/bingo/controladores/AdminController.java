package bingo.controladores;

import bingo.vistas.AdminView;
import bingo.modelo.Bingo;
import bingo.modelo.entidades.Bolilla;
import bingo.modelo.exceptions.ConfiguracionNoValidaException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;

/**
 *
 * @author maurocarrero
 */
public class AdminController extends Observable implements Observer {
    
    private static AdminController instance;
    private JFrame frame;
    
    private Bingo sistema;
    
    private AdminController(Bingo sistema) {
        this.sistema = sistema; 
        frame = new AdminView(this);
        frame.setVisible(true);
    }

    public static AdminController getInstance(Bingo sistema) {
        if (instance == null) {
            instance = new AdminController(sistema);
        }
        return instance;
    }
    
    public boolean login(String usuario, char[] password) {
        return sistema.loginAdmin(usuario, password);
    }
    
    public void guardarConfiguracion(int cF, int cCols, int cCart, 
            int cJ, double vC) throws ConfiguracionNoValidaException {
        Bingo.guardarConfiguracion(cF, cCols, cCart, cJ, vC);
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
