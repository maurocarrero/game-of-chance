/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.interfaces;

import bingo.modelo.entidades.Bolilla;
import bingo.modelo.entidades.Carton;
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
    
    public double debitar(double valorCarton);
    
    double calcularSaldo(double valorCarton);

    // Usuario
    public String getUsuario();
    
    public void setLogueado(Boolean logueado);

}
