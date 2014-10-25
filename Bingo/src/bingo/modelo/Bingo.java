package bingo.modelo;

import bingo.modelo.entidades.Administrador;
import bingo.modelo.entidades.Jugador;
import bingo.modelo.entidades.Usuario;
import bingo.modelo.exceptions.AccesoDenegadoException;
import bingo.modelo.exceptions.CantidadCartonesInvalidaException;
import bingo.modelo.exceptions.ConfiguracionNoValidaException;
import bingo.modelo.exceptions.DemasiadosCartonesException;
import bingo.modelo.exceptions.EstaLogeadoException;
import bingo.modelo.exceptions.JuegoEnCursoException;
import bingo.modelo.exceptions.SaldoInsuficienteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public class Bingo extends Observable {

    private static Bingo instance;
   
    
    
    private List<Usuario> usuariosTest = null;

    private Partida partida;
    private Administrador admin;
    
    private Bingo() {
        usuariosTest = new ArrayList();

        usuariosTest.add(new Administrador("mcarrero", "mcarrero"));
        usuariosTest.add(new Administrador("fgonzalez", "fgonzalez"));

        usuariosTest.add(new Jugador("j1", "j1", 0, 1200));
        usuariosTest.add(new Jugador("j2", "j2", 0, 2700));
        usuariosTest.add(new Jugador("j3", "j3", 0, 4200));
        usuariosTest.add(new Jugador("j4", "j4", 0, 7000));
    }
    
    
    
    public static Bingo getInstance() {
        if (instance == null) {
            instance = new Bingo();
        }
        return instance;
    }
    
    
    public Partida getPartidaInstance() {
        partida = Partida.getInstance();
        return partida;
    }
    
    public Partida getPartida(){
        return this.partida;
    }
    
    public void ejecutar() {
        partida.iniciar();
    }
    
    
    
    public static void guardarConfiguracion(int cantFilas, int cantColumnas, 
            int cantMaxCartones, int cantJugadores, double valorCarton) 
            throws ConfiguracionNoValidaException {
        
        // VALIDACIONES
        if (cantFilas < 1 || cantFilas > 10 || cantColumnas < 2 || 
                cantColumnas > 10 || cantMaxCartones < 2 || cantJugadores < 2 || 
                valorCarton < 1) {
            throw new ConfiguracionNoValidaException();
        }
        
        Partida.guardarConfiguracion(cantFilas, cantColumnas, cantMaxCartones, 
                cantJugadores, valorCarton);
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
        if (getPartidaInstance().getJugadores().size() == partida.getCantJugadores()) {
            getPartidaInstance().iniciar();
        }
    }
    
    
    
    private boolean demasiadosCartones(int cC) {
        return cC > Partida.getCantMaxCartones();
    }
    
    
    
    private double getPrecioCartones(int cantCartones) {
        return cantCartones * Partida.getValorCarton() * 2;
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
                SaldoInsuficienteException, EstaLogeadoException {
        
        Jugador jugador = (Jugador) login (usuario);
        
        // ACCESO DENEGADO
        if (jugador == null) {
            throw new AccesoDenegadoException();
        }
        if (!jugador.validate(password)) {
            throw new AccesoDenegadoException();
        }

        // JUEGO ACTIVO
        if (getPartidaInstance().isEnCurso()) {
            throw new JuegoEnCursoException();
        }

        // CARTONES
        if (cantCartones <= 0) {
            throw new CantidadCartonesInvalidaException();
        }
        if (demasiadosCartones(cantCartones)) {
            throw new DemasiadosCartonesException();
        }             
        if (jugador.estaLogueado()){
            throw new EstaLogeadoException("" + jugador.getUsuario());
        }
        // SALDO INSUFICIENTE
        double precioCartones = getPrecioCartones(cantCartones);
        if (!jugador.puedeCostear(precioCartones)) {
            throw new SaldoInsuficienteException();
        }
        
        getPartidaInstance().addJugador(jugador, cantCartones);
        jugador.setLogueado(true);
        getPartidaInstance().setJuegoActivo(true);
                
        return jugador;
        
    }
    
    public void finalizarAplicacion() {
        if (partida != null) {
            partida.finalizarAplicacion();
        }        
        System.exit(0);
    }
}
