/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.modelo.entidades;

import java.util.List;

/**
 *
 * @author maurocarrero
 */
public class Carton {
    
    private int[][] numeros;
    private List<Bolilla> bolillas;
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
    
    public void buscarBolilla(Bolilla bolilla) {
        int valor = bolilla.getValor();
        for (int x = 0; x < this.cantFilas; x++) {
            for (int y = 0; y < this.cantColumnas; y++) {
                if (numeros[x][y] == valor) {
                    this.cantAciertos++;
                    bolillas.add(bolilla);
                }
            }
        }
    }
    
    public boolean estaCompleto() {
        return this.cantAciertos == this.cantCasilleros;
    }
    
}
