/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.modelo;

import java.util.List;

/**
 *
 * @author maurocarrero
 */
public class Carton {
    
    private int[][] numeros;
    private Bolilla[][] bolillas;
    
    private int cantFilas;
    private int cantColumnas;

    public Carton(int cantFilas, int cantColumnas) {
        this.numeros = new int[cantFilas][cantColumnas];
        this.cantFilas = cantFilas;
        this.cantColumnas = cantColumnas;
    }
    
    public void poblar(List<Bolilla> bolillas) {
        for (int x = 0; x < this.cantFilas; x++) {
            for (int y = 0; y < cantColumnas; y++) {
                numeros[x][y] = bolillas.remove(0).getValor();
            }
        }
    }
    
    public void dibujar() {
        System.out.println("\nCARTON");
        for (int x = 0; x < this.cantFilas; x++) {
            for (int y = 0; y < cantColumnas; y++) {
                System.out.print(numeros[x][y] + " | ");
            }
            System.out.print("\n");
        }
    }
    
}
