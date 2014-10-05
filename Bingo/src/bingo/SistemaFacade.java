package bingo;

import bingo.admins.AdminFacade;
import bingo.jugadores.InterfazJugador;
import bingo.modelo.Administrador;
import bingo.modelo.Bolilla;
import bingo.modelo.Bolillero;
import bingo.modelo.Carton;
import bingo.modelo.Jugador;
import bingo.modelo.Usuario;
import bingo.modelo.exceptions.AccesoDenegadoException;
import bingo.modelo.exceptions.CantidadCartonesInvalidaException;
import bingo.modelo.exceptions.ConfiguracionNoValidaException;
import bingo.modelo.exceptions.DemasiadosCartonesException;
import bingo.modelo.exceptions.JuegoEnCursoException;
import bingo.modelo.exceptions.SaldoInsuficienteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author maurocarrero
 */
public class SistemaFacade extends Observable implements Observer {

    private static SistemaFacade instance;
    
    private static int cantFilas = 3;
    private static int cantColumnas = 2;
    private static int cantMaxCartones = 2;
    private static int cantJugadores = 3;
    private static double valorCarton = 10;
    private static boolean juegoActivo = false;
    
    private AdminFacade adminsFacade;

    private List<Usuario> usuariosTest = null;
    private List<InterfazJugador> jugadores = null;
    private Administrador admin;
    
    // QUITAR TODO ESTO PARA UNA CLASE PARTIDA!!!
    private int cantCartonesRequeridos;
    private Bolillero bolillero;
    private List<Carton> cartones;

    private SistemaFacade() {
        usuariosTest = new ArrayList();
        usuariosTest.add(new Administrador("mcarrero", "mcarrero"));
        usuariosTest.add(new Administrador("fgonzalez", "fgonzalez"));
        usuariosTest.add(new Jugador("j1", "j1", 0, 1200));
        usuariosTest.add(new Jugador("j2", "j2", 0, 2700));
        usuariosTest.add(new Jugador("j3", "j3", 0, 4200));
        
        jugadores = new ArrayList();
    }
    
    
    
    public static SistemaFacade getInstance() {
        if (instance == null) {
            instance = new SistemaFacade();
        }
        return instance;
    }
    
    
    
    public void run() {
        this.adminsFacade = AdminFacade.getInstance(instance);
        this.addObserver(this.adminsFacade);
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

    
    
    private boolean listoParaEmpezar() {
        return this.jugadores.size() == cantJugadores;
    }
    
    
    
    private void construirBolillero() {
        int cantNumerosPorCarton = cantFilas * cantColumnas;
        int cantTotalNumeros = cantNumerosPorCarton * cantCartonesRequeridos;
        this.bolillero = new Bolillero(cantTotalNumeros);
    }
    
    
    
    private void construirCartones() {
        this.cartones = new ArrayList();
        List<Bolilla> bolillas = bolillero.getListaBolillas();

        for (int i = 0; i < cantCartonesRequeridos; i++) {
            Collections.shuffle(bolillas);
            Carton carton = new Carton(getCantFilas(), getCantColumnas());
            carton.poblar(bolillas);
            this.cartones.add(carton);
        }
        
    }
    
    private void distribuirCartones() {
        construirBolillero();
        construirCartones();
        for (InterfazJugador interfaz : this.jugadores) {
            for (int i = 0; i < interfaz.getJugador().getCantCartones(); i++) {
                Carton carton = this.cartones.remove(0);
                interfaz.addCarton(carton);
            }
        }
    }
    
    
    private void iniciarJuego() {
        distribuirCartones();
        this.setChanged();
        notifyObservers(7);
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
    
    
    
    public void loginJugador(String usuario, char[] password, int cantCartones, InterfazJugador interfazJugador) 
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
        
        jugador.setCantCartones(cantCartones);
        interfazJugador.setJugador(jugador);
        this.jugadores.add(interfazJugador);
        this.cantCartonesRequeridos += jugador.getCantCartones();
        
        if (listoParaEmpezar()) {
            iniciarJuego();
        }
    }
    
    
    
    public void lanzarNuevaInterfazJugador() {
        InterfazJugador nuevaInterfaz = new InterfazJugador(this);
        nuevaInterfaz.lanzar();
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

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
