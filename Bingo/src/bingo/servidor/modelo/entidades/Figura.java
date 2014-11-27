/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bingo.servidor.modelo.entidades;

import bingo.common.interfaces.IFigura;

/**
 *
 * @author zorro
 */
public abstract class Figura implements IFigura {
    
    private final String nombre;
    private boolean activa;

    public Figura(String nombre) {
        this.nombre = nombre;
        this.activa = false;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean isActiva() {
        return activa;
    }

    @Override
    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
}
