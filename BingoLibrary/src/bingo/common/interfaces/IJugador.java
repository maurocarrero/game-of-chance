/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.common.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public interface IJugador extends Remote, Serializable {
    
    public int getCantCartones() throws RemoteException;

    public double getSaldo() throws RemoteException;

    public List<ICarton> getCartones() throws RemoteException;;
    
    public boolean puedeCostear(double valorCartones) throws RemoteException;;
    
    public void setCantCartones(int cantCartones) throws RemoteException;;

    public boolean addCarton(ICarton e) throws RemoteException;;

    public boolean tieneBolilla(IBolilla bolilla) throws RemoteException;;
    
    public double debitarDoble(double valorCarton) throws RemoteException;;
    
    double calcularSaldo(double valorCarton) throws RemoteException;;
    
    public double debitarSimple(double valorCarton) throws RemoteException;;

    public double getSaldoPreview(double valorCarton) throws RemoteException;;
    
    public void acreditar(double credito) throws RemoteException;;
            
    // Usuario
    public String getUsuario() throws RemoteException;;
    
    public void setLogueado(Boolean logueado) throws RemoteException;;

    public void resetearCartones() throws RemoteException;;
    
    public void mostrar() throws RemoteException;;

    public boolean buscarBolilla(IBolilla bolilla, List<IFigura> figuras)
            throws RemoteException;

    public boolean validate(char[] password) throws RemoteException;

    public boolean estaLogueado() throws RemoteException;
    
    public void desloguear() throws RemoteException;
    
}
