/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo.vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author maurocarrero
 */
public class JCasillero extends JLabel implements Observer {

    private static Font font = new Font(Font.SANS_SERIF, Font.BOLD, 24);
   
    
    public JCasillero(int valor) {
        super(valor + "");
        setVerticalAlignment(SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.CENTER);
        setPreferredSize(new Dimension(50, 50));
        setBackground(Color.BLACK);
        setForeground(Color.GREEN);
        setOpaque(true);
        setFont(font);
    }
    
    
    @Override
    public void update(Observable o, Object arg) {
        try {
            int valorBolilla = (int) arg;
            int valorCasillero = Integer.parseInt(this.getText());
            if (valorBolilla == valorCasillero) {
                setBackground(Color.GREEN);
                setForeground(Color.DARK_GRAY);
            }
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
