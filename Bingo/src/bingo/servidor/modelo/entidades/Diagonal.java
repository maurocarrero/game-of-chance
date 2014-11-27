/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bingo.servidor.modelo.entidades;

import bingo.common.interfaces.ICarton;
import bingo.common.interfaces.IFigura;

/**
 *
 * @author zorro
 */
public class Diagonal extends Figura implements IFigura {

     private static Diagonal instance;
    
    public static Diagonal getInstance() {
        if (instance == null) {
            instance = new Diagonal("diagonal");
        }
        return instance;
    }
    
    public Diagonal(String nombre) {
        super(nombre);
        super.setActiva(false);
    }
    
    @Override
    public boolean condicional(ICarton c) {
        if (c.getCantAciertos() == c.getCantColumnas()) {
            if (diagonalHaciaDerecha(c)) {
                System.out.println("diagonal derecha");
            }
            if (diagonalHaciaIzquierda(c)) {
                System.out.println("diagonal izquierda");
            }
            return diagonalHaciaDerecha(c) || diagonalHaciaIzquierda(c);
        }
        return false;
    }
    
    //Figura Diagonal Hacia la derecha "\"
    public boolean diagonalHaciaDerecha(ICarton c) {
        int cantPintados = 0;
        for (int x = 0; x < c.getCantColumnas(); x++) {
            if (c.getPintados()[x][x]) {
                cantPintados ++;
            }
        }
        return cantPintados == c.getCantAciertos();  
    }
    
    //Figura Diagonal Hacia la izquierda "/"
    public boolean diagonalHaciaIzquierda(ICarton c) {       
        int cantPintados = 0;
        for (int x = c.getCantColumnas()-1 ; x > 0; x--) {
            if (c.getPintados()[x][x]) {
                cantPintados ++;
            }
        }
        return cantPintados == c.getCantAciertos();        
    }
    
}
