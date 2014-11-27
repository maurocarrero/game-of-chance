package bingo.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Observer;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public interface IPartida extends Remote {

    void setCantFilas(int cantFilas) throws RemoteException;
    
    void setCantColumnas(int cantColumnas) throws RemoteException;
    
    void setCantMaxCartones(int cantMaxCartones) throws RemoteException;
    
    void setCantJugadores(int cantJugadores) throws RemoteException;
    
    void setValorCarton(double valorCarton) throws RemoteException;
    
    void setPozo(double pozo) throws RemoteException;
    
    int getCantFilas() throws RemoteException;
    
    int getCantColumnas() throws RemoteException;
    
    int getCantMaxCartones() throws RemoteException;
    
    int getCantJugadores() throws RemoteException;
    
    ITimer getTimer() throws RemoteException;
    
    double getValorCarton() throws RemoteException;
    
    IBolillero getBolillero() throws RemoteException;
    
    List<ICarton> getCartones() throws RemoteException;
    
    List<IJugador> getJugadoresPendientes() throws RemoteException;
    
    List<IFigura> getFiguras() throws RemoteException;
    
    List<String> getFigurasString() throws RemoteException;
    
    double getPozo() throws RemoteException;
    
    boolean isEnCurso() throws RemoteException;
    
    void setCantCartonesRequeridos(int cantCartonesRequeridos) throws RemoteException;
    
    void setEnCurso(boolean enCurso) throws RemoteException;
    
    boolean isJuegoActivo() throws RemoteException;
    
    void setJuegoActivo(boolean juegoActivo) throws RemoteException;
    
    void guardarConfiguracion(int cF, int cC, int cMC, 
            int cJ, double vC, boolean linea, boolean diagonal, boolean centro)
             throws RemoteException;
    
    List<IJugador> getJugadores() throws RemoteException;
    
    double borrarJugador(IJugador jugador) throws RemoteException;
    
    int getCantCartonesRequeridos() throws RemoteException;
    
    void addJugador(IJugador jugador, int cantCartones) throws RemoteException;
    
    void updateCantCartones(int cant) throws RemoteException;
    
    void iniciar() throws RemoteException;
    
    void siguienteTurno() throws RemoteException;
    
    void anunciarBolilla(IBolilla bolilla) throws RemoteException;
    
    void eliminarJugadorPendiente(IJugador jugador) throws RemoteException;
    
    void perdieronTodos() throws RemoteException;
    
    void continuarParticipando(Boolean continua, IJugador jugador) throws RemoteException;
    
    boolean hayJugadoresPendientes() throws RemoteException;
    
    void recalcularPozo(double monto) throws RemoteException;
    
    void resetearPozo() throws RemoteException;
    
    void finalizarAplicacion() throws RemoteException;
    
    void deleteObserver(Observer obs) throws RemoteException;

    void addObserver(Observer obs) throws RemoteException;
    
}
