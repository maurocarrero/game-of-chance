package bingo;

import bingo.modelo.Bingo;

/**
 * Bingo
 * @author maurocarrero
 */
public class Runner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Bingo facade = Bingo.getInstance();  
        facade.run();
    }
}
