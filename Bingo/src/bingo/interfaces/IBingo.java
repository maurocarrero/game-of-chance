/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.interfaces;

import bingo.modelo.exceptions.AccesoDenegadoException;
import bingo.modelo.exceptions.CantidadCartonesInvalidaException;
import bingo.modelo.exceptions.ConfiguracionNoValidaException;
import bingo.modelo.exceptions.DemasiadosCartonesException;
import bingo.modelo.exceptions.EstaLogeadoException;
import bingo.modelo.exceptions.JuegoEnCursoException;
import bingo.modelo.exceptions.SaldoInsuficienteException;

/**
 *
 * @author maurocarrero
 */
public interface IBingo extends IRemoteObservable {
    
    IPartida getPartida();
    
    void ejecutar();
    
    void guardarConfiguracion(int cantFilas, int cantColumnas, 
            int cantMaxCartones, int cantJugadores, double valorCarton,
            boolean linea, boolean diagonal, boolean centro) 
            throws ConfiguracionNoValidaException;
    
    void inicioCondicional();
    
    boolean loginAdmin(String usuario, char[] password);    
    
    
    IJugador loginJugador(String usuario, char[] password, int cantCartones) 
            throws AccesoDenegadoException, JuegoEnCursoException,
                CantidadCartonesInvalidaException, DemasiadosCartonesException, 
                SaldoInsuficienteException, EstaLogeadoException;

    void finalizarAplicacion();
}
