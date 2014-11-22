package bingo.interfaces;

public interface IFigura {
    
    String getNombre();
    boolean isActiva();
    void setActiva(boolean activa);
    boolean condicional(ICarton c);
    
}
