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
public class Diagonal extends Figura {

     private static Diagonal instance;
    
    public static Diagonal getInstance() {
        if (instance == null) {
            instance = new Diagonal("diagonal");
        }
        return instance;
    }
    
    public Diagonal(String nombre) {
        super(nombre);
    }
    
    public boolean condicional(Carton c){
        if(diagonalHaciaDerecha(c)){
           return true; 
        }else{
            return diagonalHaciaIzquierda(c);
        }
    }
    
    //Figura Diagonal Hacia la derecha "\"
    public boolean diagonalHaciaDerecha(Carton c){
        int cantPintados = 0;
        for (int x = 0; x < c.getCantColumnas(); x++) {
            if (c.getPintados()[x][x]) {
                cantPintados ++;
            }
        }
        //if(cantPintados == cantColumnas && cantPintados == cantAciertos) return true;
        if(cantPintados == c.getCantColumnas()) return true;
        else{
            cantPintados = 0;
        }       
        return false;
    }
    
    //Figura Diagonal Hacia la izquierda "/"
    public boolean diagonalHaciaIzquierda(Carton c){
        int cantPintados = 0;
        for (int x =c.getCantColumnas()-1 ; x > 0; x--) {
            if (c.getPintados()[x][x]) {
                cantPintados ++;
            }
        }
        //if(cantPintados == cantColumnas && cantPintados == cantAciertos) return true;
        if(cantPintados == c.getCantColumnas()) return true;
        else{
            cantPintados = 0;
        }       
        return false;
    }
}
