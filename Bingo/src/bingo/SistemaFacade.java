package bingo;

import bingo.admins.InterfazAdmin;
import bingo.modelo.Administrador;
import bingo.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maurocarrero
 */
public class SistemaFacade {

    private static SistemaFacade instance;
    private List<Usuario> usuarios = null;
    
    private SistemaFacade() {
        usuarios = new ArrayList<Usuario>();
        usuarios.add(new Administrador("mcarrero", "mcarrero"));
        usuarios.add(new Administrador("fgonzalez", "fgonzalez"));
        usuarios.add(new Jugador("jugador1", "jugador1"));
        usuarios.add(new Jugador("", ""));
        usuarios.add(new Jugador("", ""));
    }
    
    public static SistemaFacade getInstance() {
        if (instance == null) {
            instance = new SistemaFacade();
        }
        return instance;
    }
    
    public static void run() {
        InterfazAdmin admins = new InterfazAdmin();
        admins.setVisible(true);
    }
    
}
