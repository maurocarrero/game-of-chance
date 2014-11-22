package bingo.interfaces;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public interface IPartida {

    void setCantFilas(int cantFilas);
    
    void setCantColumnas(int cantColumnas);
    
    void setCantMaxCartones(int cantMaxCartones);
    
    void setCantJugadores(int cantJugadores);
    
    void setValorCarton(double valorCarton);
    
    void setPozo(double pozo);
    
    int getCantFilas();
    
    int getCantColumnas();
    
    int getCantMaxCartones();
    
    int getCantJugadores();
    
    ITimer getTimer();
    
    double getValorCarton();
    
    IBolillero getBolillero();
    
    List<ICarton> getCartones();
    
    List<IJugador> getJugadoresPendientes();
    
    List<IFigura> getFiguras();
    
    List<String> getFigurasString();
    
    double getPozo();
    
    boolean isEnCurso();
    
    void setCantCartonesRequeridos(int cantCartonesRequeridos);
    
    void setEnCurso(boolean enCurso);
    
    boolean isJuegoActivo();
    
    void setJuegoActivo(boolean juegoActivo);
    
    void guardarConfiguracion(int cF, int cC, int cMC, 
            int cJ, double vC, boolean linea, boolean diagonal, boolean centro);
    
    List<IJugador> getJugadores();
    
    double borrarJugador(IJugador jugador);
    
    int getCantCartonesRequeridos();    
    
    void addJugador(IJugador jugador, int cantCartones);
    
    void updateCantCartones(int cant);
    
    void iniciar();
    
    void siguienteTurno();
    
    void anunciarBolilla(IBolilla bolilla);
    
    void eliminarJugadorPendiente(IJugador jugador);
    
    void perdieronTodos();
    
    void continuarParticipando(Boolean continua, IJugador jugador);
    
    boolean hayJugadoresPendientes();
    
    void recalcularPozo(double monto);
    
    void resetearPozo();
    
    void finalizarAplicacion();

    void addObserver(IRemoteObserver observer) throws RemoteException;

    void deleteObserver(IRemoteObserver observer) throws RemoteException;
    
    void notifyObservers(Serializable param) throws RemoteException;
    
}
