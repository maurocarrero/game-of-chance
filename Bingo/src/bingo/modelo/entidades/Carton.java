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
    private boolean[][] pintados;
    private int cantCasilleros;
    private int cantAciertos;
    
    private int cantFilas;
    private int cantColumnas;

    public Carton(int cantFilas, int cantColumnas) {
        this.numeros = new int[cantFilas][cantColumnas];
        this.pintados = new boolean[cantFilas][cantColumnas];
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
                    pintados[x][y] = true;
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
    
    //Lineal por Fila
    public boolean recorrerFilas() {
        int cantPintados = 0;
         for (int x = 0; x < this.cantFilas; x++) {
            for (int y = 0; y < this.cantColumnas; y++) {
                if (pintados[x][y]) {
                    cantPintados ++;
                }
            }
            //if(cantPintados == cantColumnas && cantPintados == cantAciertos) return true;
            if(cantPintados == cantColumnas) return true;
            else{
                cantPintados = 0;
            }
        }
        return false;
    }
    
    //Lineal por Columna
    public boolean recorrerColumnas() {
        int cantPintados = 0;
         for (int x = 0; x < this.cantColumnas; x++) {
            for (int y = 0; y < this.cantFilas; y++) {
                if (pintados[y][x]) {
                    cantPintados ++;
                }
            }
            //if(cantPintados == cantFilas && cantPintados == cantAciertos) return true;
            if(cantPintados == cantFilas) return true;
            else{
                cantPintados = 0;
            }
        }
        return false;
    }
    
    //Figura Centro
    public boolean devolverCentro() {
        int centro = (cantFilas -1) / 2;
        //if(pintados[centro][centro] && cantAciertos == 1) return true;
        if(pintados[centro][centro]) return true;
        return false;
    }
    
    //Figura Diagonal Hacia la derecha "\"
    public boolean diagonalHaciaDerecha(){
        int cantPintados = 0;
        for (int x = 0; x < this.cantColumnas; x++) {
            if (pintados[x][x]) {
                cantPintados ++;
            }
        }
        //if(cantPintados == cantColumnas && cantPintados == cantAciertos) return true;
        if(cantPintados == cantColumnas) return true;
        else{
            cantPintados = 0;
        }       
        return false;
    }
    
    //Figura Diagonal Hacia la izquierda "/"
    public boolean diagonalHaciaIzquierda(){
        int cantPintados = 0;
        for (int x =cantColumnas-1 ; x > 0; x--) {
            if (pintados[x][x]) {
                cantPintados ++;
            }
        }
        //if(cantPintados == cantColumnas && cantPintados == cantAciertos) return true;
        if(cantPintados == cantColumnas) return true;
        else{
            cantPintados = 0;
        }       
        return false;
    }
    
    //Condicional para tener Figuras
    public boolean tieneFiguras(List<Figura> figuras) {
        int casilleroCentro = (cantFilas -1) / 2;
        
        for(Figura fig : figuras){
            
        }
        if(linea){
            if(recorrerFilas()) return true;
            if(recorrerColumnas()) return true;            
        }            
        if(cantColumnas == cantFilas) {
            if(centro){
                if(cantColumnas %2 != 0 && cantFilas %2 != 0) {
                    if(devolverCentro()) return true;
                }   
            }
           if(diagonal){
               if(pintados[casilleroCentro][casilleroCentro]) {
                   if(diagonalHaciaDerecha()) return true;
                   if(diagonalHaciaIzquierda()) return true;
               }
           } 
        }
        return false;
    }
    
}
