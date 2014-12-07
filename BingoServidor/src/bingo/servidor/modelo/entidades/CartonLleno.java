package bingo.servidor.modelo.entidades;

import bingo.common.interfaces.ICarton;
import bingo.common.interfaces.IFigura;
import java.rmi.RemoteException;

/**
 * Clase CartonLleno
 * @author maurocarrero/fernandogonzalez
 */
public class CartonLleno extends Figura implements IFigura {

    private static CartonLleno instance;
    
    public static CartonLleno getInstance() throws RemoteException {
        if (instance == null) {
            instance = new CartonLleno("carton lleno");
        }
        return instance;
    }
    
    public CartonLleno(String nombre) throws RemoteException {
        super(nombre);
    }
    
    @Override
    public boolean condicional(ICarton c) throws RemoteException{
        return c.getCantAciertos() == c.getCantCasilleros();
    }
}
