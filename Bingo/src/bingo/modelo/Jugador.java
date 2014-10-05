package bingo.modelo;

/**
 *
 * @author maurocarrero
 */
public class Jugador extends Usuario {

    private int cantCartones;
    private double saldo;
    
    public Jugador(String usuario, String password, int cantCartones, int monto) {
        super(usuario, password);
        this.cantCartones = cantCartones;
        this.saldo = monto;
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
}
