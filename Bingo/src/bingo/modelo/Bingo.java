package bingo.modelo;

import bingo.modelo.entidades.Administrador;
import bingo.modelo.entidades.Jugador;
import bingo.modelo.entidades.Usuario;
import bingo.modelo.exceptions.AccesoDenegadoException;
import bingo.modelo.exceptions.CantidadCartonesInvalidaException;
import bingo.modelo.exceptions.ConfiguracionNoValidaException;
import bingo.modelo.exceptions.DemasiadosCartonesException;
import bingo.modelo.exceptions.JuegoEnCursoException;
import bingo.modelo.exceptions.SaldoInsuficienteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author maurocarrero
 */
public class Bingo extends Observable {

    private static Bingo instance;
    
    private static int cantFilas = 3;
    private static int cantColumnas = 2;
    private static int cantMaxCartones = 2;
    

    //CAMBIAR POR 3 JUGADORES!!!
    //TODO
    private static int cantJugadores = 2;
    
    private static double valorCarton = 10;
    private static boolean juegoActivo = false;
    
    private List<Usuario> usuariosTest = null;

    private static Partida partida;
    private Administrador admin;
    
    private Bingo() {
        usuariosTest = new ArrayList();
        usuariosTest.add(new Administrador("mcarrero", "mcarrero"));
        usuariosTest.add(new Administrador("fgonzalez", "fgonzalez"));
        
        Jugador j = new Jugador("j1", "j1", 0, 1200);
        
        usuariosTest.add(j);
        usuariosTest.add(new Jugador("j2", "j2", 0, 2700));
        usuariosTest.add(new Jugador("j3", "j3", 0, 4200));
    }
    
    
    
    public static Bingo getInstance() {
        if (instance == null) {
            instance = new Bingo();
        }
        return instance;
    }
    
    
    public static Partida getPartida() {
        return Partida.getInstance(cantFilas, cantColumnas, cantMaxCartones, 
                cantJugadores, valorCarton);
    }
    
    
    public void ejecutar() {
        partida.iniciarJuego();
    }
    
    
    
    public static void guardarConfiguracion(int cF, int cCols, int cCart, 
            int cJ, double vC) throws ConfiguracionNoValidaException {
        
        // VALIDACIONES
        if (cF < 1 || cF > 10 || cCols < 2 || cCols > 10 || cCart < 2 || 
                cJ < 2 || vC < 1) {
            throw new ConfiguracionNoValidaException();
        }
        
        cantFilas = cF;
        cantColumnas = cCols;
        cantMaxCartones = cCart;
        cantJugadores = cJ;
        valorCarton = vC;
    }
    
    
    
    private Usuario login(String usuario) {
        for (Usuario u : this.usuariosTest) {
            if (u.getUsuario().equals(usuario)) {
                return u;
            }
        }
        return null;
    }

    
    
    public void inicioCondicional() {
        if (getPartida().getJugadores().size() == cantJugadores) {
            getPartida().iniciarJuego();
        }
    }
    
    
    
    private boolean demasiadosCartones(int cC) {
        return cC > cantMaxCartones;
    }
    
    
    
    private double getPrecioCartones(int cantCartones) {
        return cantCartones * valorCarton * 2;
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
             
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }    
    }
    
    
    
    public Jugador loginJugador(String usuario, char[] password, int cantCartones) 
            throws AccesoDenegadoException, JuegoEnCursoException,
                CantidadCartonesInvalidaException, DemasiadosCartonesException, 
                SaldoInsuficienteException {
        
        Jugador jugador = (Jugador) login(usuario);
        
        // ACCESO DENEGADO
        if (jugador == null) {
            throw new AccesoDenegadoException();
        }
        if (!jugador.validate(password)) {
            throw new AccesoDenegadoException();
        }

        // JUEGO ACTIVO
        if (hayJuegoActivo()) {
            throw new JuegoEnCursoException();
        }

        // CARTONES
        if (cantCartones <= 0) {
            throw new CantidadCartonesInvalidaException();
        }
        if (demasiadosCartones(cantCartones)) {
            throw new DemasiadosCartonesException();
        }             
        
        // SALDO INSUFICIENTE
        double precioCartones = getPrecioCartones(cantCartones);
        if (!jugador.puedeCostear(precioCartones)) {
            throw new SaldoInsuficienteException();
        }
        
        getPartida().addJugador(jugador, cantCartones);
                
        return jugador;
    }

    
    
    public boolean hayJuegoActivo() {
        return juegoActivo;
    }
    
    
    
    public static int getCantFilas() {
        return cantFilas;
    }

    
    
    public static int getCantColumnas() {
        return cantColumnas;
    }

    
    
    public static int getCantMaxCartones() {
        return cantMaxCartones;
    }

    
    
    public static int getCantJugadores() {
        return cantJugadores;
    }

    
    
    public static double getValorCarton() {
        return valorCarton;
    }

}
