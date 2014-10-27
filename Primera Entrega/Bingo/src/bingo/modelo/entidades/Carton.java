/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.modelo.entidades;

import bingo.interfaces.IBolilla;
import bingo.interfaces.ICarton;
import java.util.List;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public class Carton implements ICarton {
    
    private int[][] numeros;
    private int cantCasilleros;
    private int cantAciertos;
    
    private int cantFilas;
    private int cantColumnas;

    public Carton(int cantFilas, int cantColumnas) {
        this.numeros = new int[cantFilas][cantColumnas];
        this.cantFilas = cantFilas;
        this.cantColumnas = cantColumnas;
        this.cantCasilleros = cantFilas * cantColumnas;
        this.cantAciertos = 0;
    }
    
    @Override
    public void poblar(List<IBolilla> bolillas) {
        for (int x = 0; x < this.cantFilas; x++) {
            for (int y = 0; y < cantColumnas; y++) {
                numeros[x][y] = bolillas.remove(0).getValor();
            }
        }
    }

    
    @Override
    public int[][] getNumeros() {
        return numeros;
    }

    @Override
    public int getCantFilas() {
        return cantFilas;
    }

    @Override
    public int getCantColumnas() {
        return cantColumnas;
    }
    
    
    @Override
    public void buscarBolilla(IBolilla bolilla) {
        int valor = bolilla.getValor();
        for (int x = 0; x < this.cantFilas; x++) {
            for (int y = 0; y < this.cantColumnas; y++) {
                if (numeros[x][y] == valor) {
                    this.cantAciertos++;
                }
            }
        }
    }
    
    @Override
    public boolean tieneBolilla(IBolilla bolilla) {
        int valor = bolilla.getValor();
        for (int x = 0; x < this.cantFilas; x++) {
            for (int y = 0; y < this.cantColumnas; y++) {
                if (numeros[x][y] == valor) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public boolean estaCompleto() {
        return this.cantAciertos == this.cantCasilleros;
    }
    
}
