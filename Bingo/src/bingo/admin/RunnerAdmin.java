package bingo.admin;

/**
 * Bingo
 * @author maurocarrero/fernandogonzalez
 */
public class RunnerAdmin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        VistaAdmin vistaAdmin = new VistaAdmin();
        ControlAdmin controlAdmin = ControlAdmin.getInstance(vistaAdmin, "BingoServidor");
        if (controlAdmin.registrar()) {
            vistaAdmin.setControlador(controlAdmin);
            vistaAdmin.ejecutar();
        } else {
            System.out.println("No se pudo conectar al servidor.");
        };
        
    }
}
