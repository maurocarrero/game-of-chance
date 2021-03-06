package bingo.servidor.modelo.entidades;

import bingo.common.interfaces.IBolilla;
import bingo.common.interfaces.ICarton;
import bingo.common.interfaces.IFigura;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Clase Carton
 * @author maurocarrero/fernandogonzalez
 */
public class Carton extends UnicastRemoteObject implements ICarton {
    
    private int[][] numeros;
    private boolean[][] pintados;
    private int cantCasilleros;
    private int cantAciertos;
    
    private int cantFilas;
    private int cantColumnas;

    public Carton(int cantFilas, int cantColumnas) throws RemoteException{
        this.numeros = new int[cantFilas][cantColumnas];
        this.pintados = new boolean[cantFilas][cantColumnas];
        this.cantFilas = cantFilas;
        this.cantColumnas = cantColumnas;
        this.cantCasilleros = cantFilas * cantColumnas;
        this.cantAciertos = 0;
    }
    
    @Override
    public void poblar(List<IBolilla> bolillas) throws RemoteException{
        for (int x = 0; x < this.cantFilas; x++) {
            for (int y = 0; y < cantColumnas; y++) {
                numeros[x][y] = bolillas.remove(0).getValor();
            }
        }
    }

    public boolean[][] getPintados() {
        return pintados;
    }
    
    
    @Override
    public int[][] getNumeros() throws RemoteException{
        return numeros;
    }

    @Override
    public int getCantFilas() throws RemoteException{
        return cantFilas;
    }

    @Override
    public int getCantColumnas() throws RemoteException{
        return cantColumnas;
    }

    @Override
    public int getCantCasilleros() {
        return cantCasilleros;
    }


    @Override
    public int getCantAciertos() {
        return cantAciertos;
    }
    
    
    @Override
    public void anotarBolilla(IBolilla bolilla) throws RemoteException{
        int valor = bolilla.getValor();
        for (int x = 0; x < this.cantFilas; x++) {
            for (int y = 0; y < this.cantColumnas; y++) {
                if (numeros[x][y] == valor) {
                    this.cantAciertos++;
                    pintados[x][y] = true;
                }
            }
        }
    }
    
    @Override
    public boolean tieneBolilla(IBolilla bolilla) throws RemoteException {
        int valor = bolilla.getValor();
        for (int x = 0; x < this.cantFilas; x++) {
            for (int y = 0; y < this.cantColumnas; y++) {
                if (numeros[x][y] == valor) {
                    return true;
                }
            }
        }
        return false;
    }
    
    
    
    
    @Override
    //Condicional para tener Figuras
    public boolean tieneFiguras(List<IFigura> figuras) throws RemoteException {
        for(IFigura fig : figuras){
            if(fig.condicional(this)) {
                return true;
            }
        }           
        return false;
    }
    
}
