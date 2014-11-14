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
public class Linea extends Figura {

     private static Linea instance;
    
    public static Linea getInstance() {
        if (instance == null) {
            instance = new Linea("linea");
        }
        return instance;
    }
    
    public Linea(String nombre) {
        super(nombre);
    }
    
}
