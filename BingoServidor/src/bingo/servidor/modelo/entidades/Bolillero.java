package bingo.servidor.modelo.entidades;

import bingo.common.interfaces.IBolilla;
import bingo.common.interfaces.IBolillero;
import bingo.common.interfaces.ICarton;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase Bolillero
 * @author maurocarrero/fernandogonzalez
 */
public class Bolillero extends UnicastRemoteObject implements IBolillero {

    private List<IBolilla> bolillas;

    public Bolillero(int cantBolillas) throws RemoteException {
        bolillas = new ArrayList();
        for (int i = 1; i <= cantBolillas; i++) {
            this.bolillas.add(new Bolilla(i));
        }
        Collections.shuffle(this.bolillas);
    }
    
    @Override
    public IBolilla sacarBolilla() throws RemoteException{
        if (getSize() > 0) {
            return this.bolillas.remove(0);
        }
        return null;
    }
    
    @Override
    public int getSize() throws RemoteException {
        return this.bolillas.size();
    }
    
    @Override
    public List<IBolilla> getListaBolillas() throws RemoteException {
        return new ArrayList<>(this.bolillas);
    }
    
    @Override
    public void borrarBolillas(ICarton carton) throws RemoteException{
        for (int x = 0; x < carton.getCantFilas(); x++) {
            for (int y = 0; y < carton.getCantColumnas(); y++) {
                for (int i = 0; i < this.bolillas.size(); i++) {
                    if(this.bolillas.get(i).getValor() == carton.getNumeros()[x][y]){
                        this.bolillas.remove(i);
                    }
                }
            }
        }
    }
    
}
