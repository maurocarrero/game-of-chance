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
public interface IBolillero {
    
    public IBolilla sacarBolilla();
    
    public int getSize();
    
    public List<IBolilla> getListaBolillas();
    
    public void borrarBolillas(ICarton carton);
}
