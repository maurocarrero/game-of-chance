package bingo.modelo.entidades;

import bingo.interfaces.IBolilla;
import bingo.interfaces.ICarton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author maurocarrero
 */
public class Bolillero {

    private List<IBolilla> bolillas;

    public Bolillero(int cantBolillas) {
        bolillas = new ArrayList();
        for (int i = 1; i <= cantBolillas; i++) {
            this.bolillas.add(new Bolilla(i));
        }
        Collections.shuffle(this.bolillas);
    }
    
    public IBolilla sacarBolilla() {
        if (getSize() > 0) {
            return this.bolillas.remove(0);
        }
        return null;
    }
    
    public int getSize() {
        return this.bolillas.size();
    }
    
    public List<IBolilla> getListaBolillas() {
        return new ArrayList<>(this.bolillas);
    }
    
    public void borrarBolillas(ICarton carton){
        for (int x = 0; x < carton.getCantFilas(); x++) {
            for (int y = 0; y < carton.getCantColumnas(); y++) {
                for (int i = 0; i < this.bolillas.size(); i++) {
                    if(this.bolillas.get(i).getValor() == carton.getNumeros()[x][y]){
                        this.bolillas.remove(i);
                    }
                }
            }
        }
    }
    
}
