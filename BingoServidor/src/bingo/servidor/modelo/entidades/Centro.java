package bingo.servidor.modelo.entidades;

import bingo.common.interfaces.ICarton;
import bingo.common.interfaces.IFigura;
import java.rmi.RemoteException;

/**
 * Clase Centro
 * @author maurocarrero/fernandogonzalez
 */
public class Centro extends Figura implements IFigura {

     private static Centro instance;
    
    public static Centro getInstance() throws RemoteException {
        if (instance == null) {
            instance = new Centro("centro");
        }
        return instance;
    }
    
    public Centro(String nombre) throws RemoteException {
        super(nombre);
    }
    
    @Override
    public boolean condicional(ICarton c) throws RemoteException{
        int centro = (c.getCantFilas() -1) / 2;
        return c.getCantAciertos() == 1 && c.getPintados()[centro][centro];
    }
    
}
