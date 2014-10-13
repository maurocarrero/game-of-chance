package bingo.modelo.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author maurocarrero
 */
public class Bolillero {
    private List<Bolilla> bolillas;

    public Bolillero(int cantBolillas) {
        bolillas = new ArrayList();
        for (int i = 1; i <= cantBolillas; i++) {
            this.bolillas.add(new Bolilla(i));
        }
        Collections.shuffle(this.bolillas);
    }
    
    public Bolilla sacarBolilla() {
        if (getSize() > 0) {
            return this.bolillas.remove(0);
        }
        return null;
    }
    
    public int getSize() {
        return this.bolillas.size();
    }
    
    public List<Bolilla> getListaBolillas() {
        return new ArrayList<>(this.bolillas);
    }
    
    public void borrarBolillas(Carton carton){
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
