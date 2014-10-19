/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.interfaces;

import java.util.List;

/**
 *
 * @author Mauro.Carrero
 */
public interface IJugador {
    
    public int getCantCartones();

    public double getSaldo();

    public List<ICarton> getCartones();
    
    public boolean puedeCostear(double valorCartones);
    
    public void setCantCartones(int cantCartones);

    public boolean addCarton(ICarton e);
    
    public boolean buscarBolilla(IBolilla bolilla);

    public boolean tieneBolilla(IBolilla bolilla);
    
    public double debitarDoble(double valorCarton);
    
    double calcularSaldo(double valorCarton);
    
    public double debitarSimple(double valorCarton);

    public double getSaldoPreview(double valorCarton);
    
    public void acreditar(double credito);
            
    // Usuario
    public String getUsuario();
    
    public void setLogueado(Boolean logueado);

    public void resetearCartones();
    
}
