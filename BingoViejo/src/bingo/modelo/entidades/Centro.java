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
public class Centro extends Figura {

     private static Centro instance;
    
    public static Centro getInstance() {
        if (instance == null) {
            instance = new Centro("centro");
        }
        return instance;
    }
    
    public Centro(String nombre) {
        super(nombre);
        super.setActiva(false);
    }
    
    @Override
    public boolean condicional(Carton c){
        int centro = (c.getCantFilas() -1) / 2;
        
        /**   ELIMINAR OUTPUT  **/
        if (c.getCantAciertos() == 1 && c.getPintados()[centro][centro]) {
            System.out.println("Centro!");
        }
        /*************/
        
        return c.getCantAciertos() == 1 && c.getPintados()[centro][centro];
    }
    
}
