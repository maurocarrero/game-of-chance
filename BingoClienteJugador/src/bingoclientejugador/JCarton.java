/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoclientejugador;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author maurocarrero/fernandogonzalez
 */
public class JCarton extends JPanel {
    
    List<JCasillero> casilleros = new ArrayList<>();

    public JCarton(int[][] numeros, int cantFilas, int cantColumnas) {
        setLayout(new GridLayout(cantFilas, cantColumnas));
        setSize(cantFilas * 100, cantColumnas * 100);
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10, true));
        
        casilleros = new ArrayList<>();
        
        for (int x = 0; x < numeros.length; x++) {
            for (int y = 0; y < numeros[x].length; y++) {
                JCasillero casillero = new JCasillero(numeros[x][y]);
                add(casillero);
                casilleros.add(casillero);
            }
        }
    }

    public List<JCasillero> getCasilleros() {
        return casilleros;
    }
}
