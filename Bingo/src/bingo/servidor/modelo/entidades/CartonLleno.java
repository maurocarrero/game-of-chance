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
public class CartonLleno extends Figura implements IFigura {

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
    
    @Override
    public boolean condicional(ICarton c){
        return c.getCantAciertos() == c.getCantCasilleros();
    }
}
