package bingo.common.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interfaz IPartida
 * @author maurocarrero/fernandogonzalez
 */
public interface IPartida extends Remote, IRemoteObservable, Serializable {

    void setCantFilas(int cantFilas) throws RemoteException;
    
    void setCantColumnas(int cantColumnas) throws RemoteException;
    
    void setCantMaxCartones(int cantMaxCartones) throws RemoteException;
    
    void setCantJugadores(int cantJugadores) throws RemoteException;
    
    void setValorCarton(double valorCarton) throws RemoteException;
    
    void setPozo(double pozo) throws RemoteException;
    
    public void setTiempo(int tiempo) throws RemoteException;
    
    public int getTiempo() throws RemoteException;
    
    int getCantFilas() throws RemoteException;
    
    int getCantColumnas() throws RemoteException;
    
    int getCantMaxCartones() throws RemoteException;
    
    int getCantJugadores() throws RemoteException;
    
    IContador getContador() throws RemoteException;
    
    double getValorCarton() throws RemoteException;
    
    IBolillero getBolillero() throws RemoteException;
    
    List<ICarton> getCartones() throws RemoteException;
    
    List<IJugador> getJugadoresPendientes() throws RemoteException;
    
    List<IFigura> getFiguras() throws RemoteException;
    
    double getPozo() throws RemoteException;
    
    boolean isEnCurso() throws RemoteException;
    
    void setCantCartonesRequeridos(int cantCartonesRequeridos) throws RemoteException;
    
    void setEnCurso(boolean enCurso) throws RemoteException;
    
    boolean isJuegoActivo() throws RemoteException;
    
    void setJuegoActivo(boolean juegoActivo) throws RemoteException;
    
    void guardarConfiguracion(int cF, int cC, int cMC, 
            int cJ, double vC, int tiempo, List<String> figuras)
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

    void quitarJugadorDeLaPartida(IJugador jugador) throws RemoteException;
        
    void perdieronTodos() throws RemoteException;
    
    void continuarParticipando(Boolean continua, IJugador jugador) throws RemoteException;
    
    boolean hayJugadoresPendientes() throws RemoteException;
    
    void recalcularPozo(double monto) throws RemoteException;
    
    void resetear() throws RemoteException;
    
    void finalizarAplicacion() throws RemoteException;
    
    public void salidaForzosa(IJugador jugador) throws RemoteException;
    
    @Override
    void deleteObserver(IRemoteObserver obs) throws RemoteException;

    @Override
    void addObserver(IRemoteObserver obs) throws RemoteException;
    
}
