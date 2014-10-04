package bingo;

import bingo.admins.InterfazAdmin;

/**
 *
 * @author maurocarrero
 */
public class SistemaFacade {

    private static SistemaFacade instance;
    
    private SistemaFacade() {}
    
    public static SistemaFacade getInstance() {
        if (instance == null) {
            instance = new SistemaFacade();
        }
        return instance;
    }
    
    public static void runAdmins() {
        InterfazAdmin admins = new InterfazAdmin();
        admins.setVisible(true);
    }
    
}
