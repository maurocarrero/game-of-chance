/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bingo.modelo.entidades;

/**
 *
 * @author zorro
 */
public class Diagonal extends Figura {

     private static Diagonal instance;
    
    public static Diagonal getInstance() {
        if (instance == null) {
            instance = new Diagonal("diagonal");
        }
        return instance;
    }
    
    public Diagonal(String nombre) {
        super(nombre);
    }
    
}
