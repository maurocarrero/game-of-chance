/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.common.interfaces;

import bingo.modelo.exceptions.AccesoDenegadoException;
import bingo.modelo.exceptions.CantidadCartonesInvalidaException;
import bingo.modelo.exceptions.ConfiguracionNoValidaException;
import bingo.modelo.exceptions.DemasiadosCartonesException;
import bingo.modelo.exceptions.EstaLogeadoException;
import bingo.modelo.exceptions.JuegoEnCursoException;
import bingo.modelo.exceptions.SaldoInsuficienteException;
import java.io.Serializable;
import java.rmi.RemoteException;

/**
 *
 * @author maurocarrero
 */
public interface IBingo extends IRemoteObservable {
    
    IPartida getPartida() throws RemoteException;
    
    void ejecutar() throws RemoteException;
       
    void guardarConfiguracion(int cantFilas, int cantColumnas, 
            int cantMaxCartones, int cantJugadores, double valorCarton,
            boolean linea, boolean diagonal, boolean centro) 
            throws ConfiguracionNoValidaException, RemoteException ;
    
    void inicioCondicional() throws RemoteException;
    
    boolean loginAdmin(String usuario, char[] password) throws RemoteException;
    
    
    IJugador loginJugador(String usuario, char[] password, int cantCartones) 
            throws AccesoDenegadoException, JuegoEnCursoException,
                CantidadCartonesInvalidaException, DemasiadosCartonesException, 
                SaldoInsuficienteException, EstaLogeadoException, RemoteException;

    void finalizarAplicacion() throws RemoteException;
    
    @Override
    void addObserver(IRemoteObserver observer) throws RemoteException;

     @Override
    public void deleteObserver(IRemoteObserver observer) throws RemoteException;

    @Override
    public void notifyObservers(Serializable param) throws RemoteException;

}