package bingo.common.interfaces;

import java.util.List;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public interface IBolillero {

    IBolilla sacarBolilla();
    int getSize();    
    List<IBolilla> getListaBolillas();
    void borrarBolillas(ICarton carton);

}
