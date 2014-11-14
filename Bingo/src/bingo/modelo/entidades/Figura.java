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
public class Figura {
    
    private String nombre;
    private boolean activa;

    public Figura(String nombre) {
        this.nombre = nombre;
        this.activa = false;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
    public boolean condicional(Carton c){
        return false;
    }
    
}
