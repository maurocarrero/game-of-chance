package bingo.servidor.modelo.entidades;

import bingo.common.interfaces.ICarton;
import bingo.common.interfaces.IFigura;

/**
 *
 * @author zorro
 */
public class Centro extends Figura implements IFigura {

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
    public boolean condicional(ICarton c){
        int centro = (c.getCantFilas() -1) / 2;
        
        /**   ELIMINAR OUTPUT  **/
        if (c.getCantAciertos() == 1 && c.getPintados()[centro][centro]) {
            System.out.println("Centro!");
        }
        /*************/
        
        return c.getCantAciertos() == 1 && c.getPintados()[centro][centro];
    }
    
}
