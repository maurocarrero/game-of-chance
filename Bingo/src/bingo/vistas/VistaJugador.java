package bingo.vistas;

import bingo.controladores.ControlJugador;
import bingo.controladores.Controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Interfaz para Jugadores
 * @author maurocarrero
 */
public final class VistaJugador extends javax.swing.JFrame implements InterfazVista {

    private ControlJugador jugador;
    private JPanel contenedor;
    
    /**
     * Creates new form InterfazJugador
     */
    public VistaJugador() {
        initComponents();
        this.setTitle("Bingo - Administrador");
        btnIngresar.setActionCommand("INGRESAR");
        btnNO.setActionCommand("NO_CONTINUAR");
        btnSI.setActionCommand("SI_CONTINUAR");
        panelContinuarJugando.setVisible(false);       
    }
    
    public String getUsuario(){
        return this.txtUsuario.getText();
    }
    
    public char[] getPassword(){
        return this.txtPassword.getPassword();
    }
    
    public String getCantCartones(){
        return this.txtCantCartones.getText();
    }
    
    public void esperarComienzoJuego() {
        panelLoginJugador.setVisible(false);
        panelEspera.setVisible(true);
    }
    
    private void ocultarPaneles() {
        panelEspera.setVisible(false);
        panelLoginJugador.setVisible(false);
    }
    
    public void dibujarContenedorCartones(int tamanio, int cantFilas, int cantColumnas) {
        contenedor = new JPanel();
        contenedor.setName("panelCasilleros");
        contenedor.setLayout(new GridLayout(tamanio, 1));
        contenedor.setSize(cantFilas * 100, cantColumnas * 100 * tamanio);
        add(contenedor, BorderLayout.CENTER);
        contenedor.add(panelContinuarJugando).setVisible(true);
    }  
    
    public List<JCasillero> dibujarCarton(int[][] numeros, int cantFilas, int cantColumnas) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(cantFilas, cantColumnas));
        panel.setSize(cantFilas * 100, cantColumnas * 100);
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10, true));
        
        List<JCasillero> casilleros = new ArrayList<>();
        
        for (int x = 0; x < numeros.length; x++) {
            for (int y = 0; y < numeros[x].length; y++) {
                JCasillero casillero = new JCasillero(numeros[x][y]);
                panel.add(casillero);
                casilleros.add(casillero);
            }
        }
        ocultarPaneles();
        contenedor.add(panel);
        return casilleros;
    }
        
    
    @Override
    public void setControlador(Controlador c) {
        // Login
        btnIngresar.addActionListener(c);
        txtUsuario.addActionListener(c);
        txtPassword.addActionListener(c);
        txtCantCartones.addActionListener(c);
    }

    @Override
    public void ejecutar() {
        
        
        ocultarPaneles();
        panelLoginJugador.setVisible(true);
        setTitle("Interfaz de Jugador");
        
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLoginJugador = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();
        lblCantCartones = new javax.swing.JLabel();
        txtCantCartones = new javax.swing.JTextField();
        panelEspera = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        panelContinuarJugando = new javax.swing.JPanel();
        lblContinuarJugando = new javax.swing.JLabel();
        btnSI = new javax.swing.JButton();
        btnNO = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Usuario");

        jLabel2.setText("Contraseña");

        btnIngresar.setText("Ingresar");

        lblCantCartones.setText("Cantidad de cartones");

        javax.swing.GroupLayout panelLoginJugadorLayout = new javax.swing.GroupLayout(panelLoginJugador);
        panelLoginJugador.setLayout(panelLoginJugadorLayout);
        panelLoginJugadorLayout.setHorizontalGroup(
            panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginJugadorLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLoginJugadorLayout.createSequentialGroup()
                        .addGroup(panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(lblCantCartones))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUsuario)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantCartones, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        panelLoginJugadorLayout.setVerticalGroup(
            panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginJugadorLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantCartones, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantCartones))
                .addGap(23, 23, 23)
                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jLabel3.setText("Esperando inicio del juego...");

        javax.swing.GroupLayout panelEsperaLayout = new javax.swing.GroupLayout(panelEspera);
        panelEspera.setLayout(panelEsperaLayout);
        panelEsperaLayout.setHorizontalGroup(
            panelEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEsperaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        panelEsperaLayout.setVerticalGroup(
            panelEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEsperaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        lblContinuarJugando.setText("¿Desea continuar jugando? ");

        btnSI.setText("SI");

        btnNO.setText("NO");

        javax.swing.GroupLayout panelContinuarJugandoLayout = new javax.swing.GroupLayout(panelContinuarJugando);
        panelContinuarJugando.setLayout(panelContinuarJugandoLayout);
        panelContinuarJugandoLayout.setHorizontalGroup(
            panelContinuarJugandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContinuarJugandoLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(panelContinuarJugandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContinuarJugandoLayout.createSequentialGroup()
                        .addComponent(btnSI)
                        .addGap(36, 36, 36)
                        .addComponent(btnNO))
                    .addComponent(lblContinuarJugando, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelContinuarJugandoLayout.setVerticalGroup(
            panelContinuarJugandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContinuarJugandoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblContinuarJugando)
                .addGap(39, 39, 39)
                .addGroup(panelContinuarJugandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSI)
                    .addComponent(btnNO))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelLoginJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelEspera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(panelContinuarJugando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(267, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelLoginJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelEspera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelContinuarJugando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnNO;
    private javax.swing.JButton btnSI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblCantCartones;
    private javax.swing.JLabel lblContinuarJugando;
    private javax.swing.JPanel panelContinuarJugando;
    private javax.swing.JPanel panelEspera;
    private javax.swing.JPanel panelLoginJugador;
    private javax.swing.JTextField txtCantCartones;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

}
