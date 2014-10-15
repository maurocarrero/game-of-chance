package bingo.modelo.entidades;

import bingo.modelo.Bingo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maurocarrero
 */
public class Jugador extends Usuario {

    private int cantCartones;
    private double saldo;
    
    private List<Carton> cartones;
    
    public Jugador(String usuario, String password, int cantCartones, int saldo) {
        super(usuario, password);
        this.cantCartones = cantCartones;
        this.saldo = saldo;
        this.cartones = new ArrayList<>();
    }
    
    public int getCantCartones() {
        return cantCartones;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Carton> getCartones() {
        return cartones;
    }
    
    public boolean puedeCostear(double valorCartones) {
        return this.saldo >= valorCartones;
    }

    public void setCantCartones(int cantCartones) {
        this.cantCartones = cantCartones;
    }

    public boolean addCarton(Carton e) {
        return cartones.add(e);
    }
    
    public boolean buscarBolilla(Bolilla bolilla) {
        for (Carton c : this.cartones) {
            c.buscarBolilla(bolilla);
            if (c.estaCompleto()) {
                return true;
            }
        }
        return false;
    }
    
    public double debitar(double valorCarton) {
        double monto = 0d;
        for (int i = 0; i < cantCartones; i++) {
            monto += valorCarton * 2;
        }
        saldo -= monto;
        return monto;
    }
    
    public double calcularSaldo(double valorCarton){
        return getSaldo() - getCantCartones() * valorCarton * 2;        
    }
   
}
