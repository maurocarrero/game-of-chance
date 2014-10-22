/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.vistas;

import bingo.controladores.Controlador;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public interface InterfazVista {
    
    void setControlador(Controlador c);
    void ejecutar();
}
