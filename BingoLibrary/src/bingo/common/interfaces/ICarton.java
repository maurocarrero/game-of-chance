package bingo.common.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interfaz ICarton
 * @author maurocarrero/fernandogonzalez
 */
public interface ICarton extends Remote, Serializable{
    
    void poblar(List<IBolilla> bolillas) throws RemoteException;
    
    int[][] getNumeros() throws RemoteException;

    int getCantFilas() throws RemoteException;

    int getCantColumnas() throws RemoteException;    
    
    void anotarBolilla(IBolilla bolilla) throws RemoteException;
    
    boolean tieneBolilla(IBolilla bolilla) throws RemoteException;

    boolean tieneFiguras(List<IFigura> figuras) throws RemoteException;
    
    int getCantCasilleros() throws RemoteException;

    int getCantAciertos() throws RemoteException;
    
    boolean[][] getPintados() throws RemoteException;
    
}
