package bingo.modelo;

/**
 *
 * @author maurocarrero
 */
public class Jugador extends Usuario {

    private int cantCartones;
    private double saldo;
    
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
    
}
