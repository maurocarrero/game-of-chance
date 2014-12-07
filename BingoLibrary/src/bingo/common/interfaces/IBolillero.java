package bingo.common.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interfaz IBolillero
 * @author maurocarrero/fernandogonzalez
 */
public interface IBolillero extends Remote, Serializable {

    IBolilla sacarBolilla() throws RemoteException;
    int getSize() throws RemoteException;    
    List<IBolilla> getListaBolillas() throws RemoteException;
    void borrarBolillas(ICarton carton) throws RemoteException;

}
