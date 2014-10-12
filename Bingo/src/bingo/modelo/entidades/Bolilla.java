/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.modelo.entidades;

import bingo.interfaces.IBolilla;

/**
 *
 * @author maurocarrero
 */
public class Bolilla implements IBolilla {
    private int valor;

    public Bolilla(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
