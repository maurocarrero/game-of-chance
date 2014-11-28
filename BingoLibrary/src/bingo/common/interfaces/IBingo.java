/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.common.interfaces;

import bingo.common.exceptions.AccesoDenegadoException;
import bingo.common.exceptions.CantidadCartonesInvalidaException;
import bingo.common.exceptions.ConfiguracionNoValidaException;
import bingo.common.exceptions.DemasiadosCartonesException;
import bingo.common.exceptions.EstaLogeadoException;
import bingo.common.exceptions.JuegoEnCursoException;
import bingo.common.exceptions.SaldoInsuficienteException;
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