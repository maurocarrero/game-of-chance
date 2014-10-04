package bingo;

import bingo.admins.InterfazAdmin;
import bingo.modelo.AccesoDenegadoException;
import bingo.modelo.Administrador;
import bingo.modelo.Jugador;
import bingo.modelo.Usuario;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maurocarrero
 */
public class SistemaFacade {

    private static SistemaFacade instance;
    
    private static int cantFilas = 3;
    private static int cantColumnas = 2;
    private static int cantCartones = 2;
    private static int cantJugadores = 3;
    private static double valorCarton = 10;
            
    private List<Usuario> usuarios = null;
    private Administrador admin;
    
    private SistemaFacade() {
        usuarios = new ArrayList();
        usuarios.add(new Administrador("mcarrero", "mcarrero"));
        usuarios.add(new Administrador("fgonzalez", "fgonzalez"));
        usuarios.add(new Jugador("jugador1", "jugador1"));
        usuarios.add(new Jugador("jugador2", "jugador2"));
        usuarios.add(new Jugador("jugador3", "jugador3"));
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
    
    private Usuario login(String usuario) {
        for (Usuario u : this.usuarios) {
            if (u.getUsuario().equals(usuario)) {
                return u;
            }
        }
        return null;
    }
    
    public boolean loginAdmin(String usuario, char[] password) {
        Administrador aux;
        try {
             aux = (Administrador) login(usuario);
             if (aux == null) {
                 throw new AccesoDenegadoException();
             }
             if (!aux.validate(password)) {
                 throw new AccesoDenegadoException();
             }

             this.admin = aux;
             return true;
             
        } catch (AccesoDenegadoException | HeadlessException | ClassCastException ex) {
            System.out.println(ex.getMessage());
            return false;
        }    
    }
    
    public boolean loginJugador(String usuario, char[] password) {
        Jugador jugador;
        try {
             jugador = (Jugador) login(usuario);
             if (jugador == null) {
                 throw new AccesoDenegadoException();
             }
             if (!jugador.validate(password)) {
                 throw new AccesoDenegadoException();
             }

             return true;
             
        } catch (AccesoDenegadoException | HeadlessException | ClassCastException ex) {
            System.out.println(ex.getMessage());
            return false;
        }    
    }

}
