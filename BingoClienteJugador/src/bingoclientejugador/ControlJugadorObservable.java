package bingoclientejugador;

import java.util.Observable;

/**
 * Class ControlJugadorObservable
 * @author maurocarrero/fernandogonzalez
 */
public class ControlJugadorObservable extends Observable {
    
    public ControlJugadorObservable() {
        super();
    }

    public void notificar(int bolilla) {
        setChanged();
        notifyObservers(bolilla);
    }    
}
