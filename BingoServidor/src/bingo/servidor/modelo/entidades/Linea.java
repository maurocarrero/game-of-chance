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
public class Linea extends Figura implements IFigura {

     private static Linea instance;
    
    public static Linea getInstance() {
        if (instance == null) {
            instance = new Linea("linea");
        }
        return instance;
    }
    
    public Linea(String nombre) {
        super(nombre);
        super.setActiva(false);
    }
    
    @Override
    public boolean condicional(ICarton c){
        boolean acierto = false; 
        boolean verFilas = c.getCantAciertos() == c.getCantFilas();
        boolean verColumnas = c.getCantAciertos() == c.getCantColumnas();
        if(verFilas || verColumnas){
            if(verFilas){
               acierto = recorrerFilas(c);
            }
            if(!acierto && verColumnas){
                acierto = recorrerColumnas(c);
            }
        }
        return acierto;
    }
    
    //Lineal por Fila
    private boolean recorrerFilas(ICarton c) {
        int cantPintados = 0;
         for (int x = 0; x < c.getCantFilas(); x++) {
            for (int y = 0; y < c.getCantColumnas(); y++) {
                if (c.getPintados()[x][y]) {
                    cantPintados ++;
                }
            }
            //if(cantPintados == cantColumnas && cantPintados == cantAciertos) return true;
            if(cantPintados == c.getCantAciertos()) return true;
            else cantPintados = 0;
        }
        return false;
    }
    
    //Lineal por Columna
    private boolean recorrerColumnas(ICarton c) {
        int cantPintados = 0;
         for (int x = 0; x < c.getCantColumnas(); x++) {
            for (int y = 0; y < c.getCantFilas(); y++) {
                if (c.getPintados()[y][x]) {
                    cantPintados ++;
                }
            }
            //if(cantPintados == cantFilas && cantPintados == cantAciertos) return true;
            if(cantPintados == c.getCantFilas()) return true;
            else{
                cantPintados = 0;
            }
        }
        return false;
    }
    
}
