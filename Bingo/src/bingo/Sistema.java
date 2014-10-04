package bingo;

/**
 * Bingo
 * @author maurocarrero
 */
public class Sistema {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SistemaFacade facade = SistemaFacade.getInstance();  
        facade.run();
        
    }
}
