package bingo.servidor.modelo.entidades;

import bingo.common.interfaces.ICarton;
import bingo.common.interfaces.IFigura;
import java.rmi.RemoteException;

/**
 *
 * @author zorro
 */
public class Centro extends Figura implements IFigura {

     private static Centro instance;
    
    public static Centro getInstance() throws RemoteException {
        if (instance == null) {
            instance = new Centro("centro");
        }
        return instance;
    }
    
    public Centro(String nombre) throws RemoteException{
        super(nombre);
        super.setActiva(false);
    }
    
    @Override
    public boolean condicional(ICarton c) throws RemoteException{
        int centro = (c.getCantFilas() -1) / 2;
        
        /**   ELIMINAR OUTPUT  **/
        if (c.getCantAciertos() == 1 && c.getPintados()[centro][centro]) {
            System.out.println("Centro!");
        }
        /*************/
        
        return c.getCantAciertos() == 1 && c.getPintados()[centro][centro];
    }
    
}
