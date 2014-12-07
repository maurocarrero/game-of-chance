package bingo.servidor.persistencia;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;

/**
 * Interfaz Persistente
 * @author maurocarrero/fernandogonzalez
 */
public interface Persistente extends Remote {

    public abstract String getInsertSQL() throws RemoteException;
    
    public abstract String getUpdateSQL() throws RemoteException;
    
    public abstract String getDeleteSQL() throws RemoteException;
    
    public abstract String getSelectSQL() throws RemoteException;
    
    public abstract void leerDesdeResultSet(ResultSet rs) throws RemoteException;
    
    public abstract Persistente getNuevo() throws RemoteException;
    
    public abstract Object getObjeto() throws RemoteException;

}
