package bingo.modelo.entidades;

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
    }
    
    public int getCantCartones() {
        return cantCartones;
    }

    public double getSaldo() {
        return saldo;
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
    
    public void buscarBolilla(Bolilla bolilla) {
        for (Carton c : this.cartones) {
            c.buscarBolilla(bolilla);
            if (c.estaCompleto()) {
                System.out.println("Bingo!!!");
            }
        }
    }
    
}
