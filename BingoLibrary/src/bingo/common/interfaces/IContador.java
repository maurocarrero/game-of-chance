package bingo.common.interfaces;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Interfaz IContador
 * @author Mauro.Carrero
 */
public interface IContador extends IRemoteObservable, Serializable {

    public int getTiempo() throws RemoteException;

    public int getCont() throws RemoteException;

    public void setTiempo(int tiempo) throws RemoteException;

    public void setCont(int cont) throws RemoteException;
    
    public void decrementar() throws RemoteException;
    
    public void notificar() throws RemoteException;
    
    public void cancelar() throws RemoteException;
    
    public void start() throws RemoteException;

    public void resetear() throws RemoteException;
    
}
