package bingo.common.interfaces;

import java.util.List;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public interface ICarton {
    
    void poblar(List<IBolilla> bolillas);
    
    int[][] getNumeros();

    int getCantFilas();

    int getCantColumnas();    
    
    void buscarBolilla(IBolilla bolilla);
    
    boolean tieneBolilla(IBolilla bolilla);

    boolean tieneFiguras(List<IFigura> figuras);
    
    int getCantCasilleros();

    int getCantAciertos();
    
    boolean[][] getPintados();
    
}
