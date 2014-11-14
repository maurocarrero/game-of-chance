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
public class CartonLleno extends Figura {

    private static CartonLleno instance;
    
    public static CartonLleno getInstance() {
        if (instance == null) {
            instance = new CartonLleno("cartonLleno");
        }
        return instance;
    }
    
    public CartonLleno(String nombre) {
        super(nombre);
        super.setActiva(true);
    }
    
}
