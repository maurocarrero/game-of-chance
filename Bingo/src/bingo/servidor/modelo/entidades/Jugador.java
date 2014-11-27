package bingo.servidor.modelo.entidades;

import bingo.common.interfaces.IBolilla;
import bingo.common.interfaces.ICarton;
import bingo.common.interfaces.IFigura;
import bingo.common.interfaces.IJugador;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public class Jugador extends Usuario implements IJugador, Serializable {

    private int cantCartones;
    private double saldo;
    
    private List<ICarton> cartones;
    
    public Jugador(String usuario, String password, int cantCartones, int saldo) {
        super(usuario, password);
        this.cantCartones = cantCartones;
        this.saldo = saldo;
        this.cartones = new ArrayList<>();
    }
    
    @Override
    public int getCantCartones() {
        return cantCartones;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }
    
    @Override
    public double getSaldoPreview(double valorCarton) {
        return saldo - cantCartones * valorCarton * 2;
    }

    @Override
    public List<ICarton> getCartones() {
        return cartones;
    }
    
    @Override
    public boolean puedeCostear(double valorCartones) {
        return this.saldo >= valorCartones * 2;
    }

    @Override
    public void setCantCartones(int cantCartones) {
        this.cantCartones = cantCartones;
    }

    @Override
    public boolean addCarton(ICarton e) {
        return cartones.add(e);
    }
    
    @Override
    public boolean buscarBolilla(IBolilla bolilla, List<IFigura> figuras) {
        for (ICarton c : this.cartones) {
            c.buscarBolilla(bolilla);
            if (c.tieneFiguras(figuras)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean tieneBolilla(IBolilla bolilla) {
        boolean tiene = false;
        for (ICarton c : this.cartones) {
            if (c.tieneBolilla(bolilla)) {
                tiene = true;
            }
        }
        return tiene;
    }

    @Override
    public void acreditar(double credito){
        saldo += credito;
    }
    
    @Override
    public double debitarDoble(double valorCarton) {
        double monto = 0d;
        for (int i = 0; i < cantCartones; i++) {
            monto += valorCarton * 2;
        }
        saldo -= monto;
        return monto;
    }
    
    @Override
    public double debitarSimple(double valorCarton){
        double monto = cantCartones * valorCarton;
        saldo -= monto;
        return monto;
    }   
    
    @Override
    public double calcularSaldo(double valorCarton){
        return getSaldo() - getCantCartones() * valorCarton * 2;        
    }
    
    @Override
    public String toString() {
        return this.getUsuario();
    }
    
    @Override
    public void resetearCartones() {
        this.cantCartones = 0;
        this.cartones = null;
    }
    
    @Override
    public void mostrar() {
        System.out.println("Jugador: " + getUsuario());
        System.out.println("Saldo: " + getSaldo() + "\n");
    }
   
}