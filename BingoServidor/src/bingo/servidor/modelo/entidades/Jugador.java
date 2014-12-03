package bingo.servidor.modelo.entidades;

import bingo.common.interfaces.IBolilla;
import bingo.common.interfaces.ICarton;
import bingo.common.interfaces.IFigura;
import bingo.common.interfaces.IJugador;
import bingo.servidor.persistencia.JugadorPersistente;
import bingo.servidor.persistencia.ManejadorBD;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public class Jugador extends Usuario implements IJugador {

    private int cantCartones;
    private double saldo;
    
    private List<ICarton> cartones;
    
    public Jugador() throws RemoteException {
        this.cartones = new ArrayList<>();
    }
    
    public Jugador(String usuario, String password, int cantCartones, int saldo) 
            throws RemoteException {
        super(usuario, password);
        this.cantCartones = cantCartones;
        this.saldo = saldo;
        this.cartones = new ArrayList<>();
    }
        
    @Override
    public int getCantCartones() throws RemoteException {
        return cantCartones;
    }

    @Override
    public double getSaldo() throws RemoteException {
        return saldo;
    }
    
    @Override
    public double getSaldoPreview(double valorCarton) throws RemoteException {
        return saldo - cantCartones * valorCarton * 2;
    }
    
    @Override
    public List<ICarton> getCartones() throws RemoteException {
        return cartones;
    }
    
    @Override
    public boolean puedeCostear(double valorCartones) throws RemoteException {
        return this.saldo >= valorCartones * 2;
    }

    @Override
    public void setCantCartones(int cantCartones) throws RemoteException {
        this.cantCartones = cantCartones;
    }

    @Override
    public boolean addCarton(ICarton e) throws RemoteException {
        return cartones.add(e);
    }
    
    @Override
    public boolean buscarBolilla(IBolilla bolilla, List<IFigura> figuras) 
            throws RemoteException {
        for (ICarton c : this.cartones) {
            c.buscarBolilla(bolilla);
            if (c.tieneFiguras(figuras)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean tieneBolilla(IBolilla bolilla) throws RemoteException {
        boolean tiene = false;
        for (ICarton c : this.cartones) {
            if (c.tieneBolilla(bolilla)) {
                tiene = true;
            }
        }
        return tiene;
    }

    @Override
    public void acreditar(double credito) throws RemoteException {
        saldo += credito;
    }
    
    @Override
    public double debitarDoble(double valorCarton) throws RemoteException {
        double monto = 0d;
        for (int i = 0; i < cantCartones; i++) {
            monto += valorCarton * 2;
        }
        saldo -= monto;
        return monto;
    }
    
    @Override
    public double debitarSimple(double valorCarton) throws RemoteException {
        double monto = cantCartones * valorCarton;
        saldo -= monto;
        return monto;
    }   
    
    @Override
    public double calcularSaldo(double valorCarton) throws RemoteException {
        return getSaldo() - getCantCartones() * valorCarton * 2;        
    }
    
    @Override
    public String toString() {
        return this.getUsuario();
    }
    
    @Override
    public void resetearCartones() throws RemoteException {
        this.cantCartones = 0;
        this.cartones = new ArrayList<>();
    }
    
    @Override
    public void mostrar() throws RemoteException {
        System.out.println("Jugador: " + getUsuario());
        System.out.println("Saldo: " + getSaldo() + "\n");
    }

    @Override
    public boolean estaLogueado() throws RemoteException {
        return super.estaLogueado();
    }       

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
 
    @Override
    public void actualizarSaldoBD() throws RemoteException {
        ManejadorBD db = ManejadorBD.getInstancia();
        JugadorPersistente jugadorPersistente = new JugadorPersistente(this);
        db.modificar(jugadorPersistente);
    }
    
}
