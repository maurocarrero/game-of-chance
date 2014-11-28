package bingo;

import bingo.controladores.ControlAdmin;
import bingo.modelo.Bingo;
import bingo.vistas.VistaAdmin;

/**
 * Bingo
 * @author maurocarrero/fernandogonzalez
 */
public class RunnerAdmin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Bingo modelo = Bingo.getInstance();
        VistaAdmin vistaAdmin = new VistaAdmin();
        ControlAdmin controlAdmin = ControlAdmin.getInstance(vistaAdmin, modelo);
        vistaAdmin.setControlador(controlAdmin);
        vistaAdmin.ejecutar();
    }
}
