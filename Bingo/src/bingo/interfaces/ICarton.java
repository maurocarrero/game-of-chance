/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.interfaces;

import java.util.List;

/**
 *
 * @author maurocarrero
 */
public interface ICarton {
    
    public void poblar(List<IBolilla> bolillas);
    
    public int[][] getNumeros();

    public int getCantFilas();

    public int getCantColumnas();    
    
    public void buscarBolilla(IBolilla bolilla);
    
    public boolean tieneBolilla(IBolilla bolilla);
    
    public boolean estaCompleto();
    
}
