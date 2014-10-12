package bingo.vistas;

import bingo.controladores.ControlJugador;
import bingo.controladores.Controlador;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Interfaz para Jugadores
 * @author maurocarrero
 */
public final class VistaJugador extends javax.swing.JFrame implements InterfazVista {

    private ControlJugador jugador;
    private Font font;
    
    /**
     * Creates new form InterfazJugador
     */
    public VistaJugador() {
        initComponents();
        this.setTitle("Bingo - Administrador");
        
        this.font = new Font(Font.SANS_SERIF, Font.PLAIN, 24);
        btnIngresar.setActionCommand("INGRESAR");
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
    
    public void dibujarCarton(int[][] numeros, int cantFilas, int cantColumnas) {
        System.out.println("\nDibujando cartón");
        JInternalFrame frame = new JInternalFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(cantFilas, cantColumnas));
        for (int x = 0; x < numeros.length; x++) {
            for (int y = 0; y < numeros[x].length; y++) {
                JLabel label = new JLabel("" + numeros[x][y]);
                label.setAlignmentX(CENTER_ALIGNMENT);
                label.setAlignmentY(CENTER_ALIGNMENT);
                label.setBounds(10, 10, 10, 10);
                label.setFont(this.font);
                panel.add(label);
            }
        }
        ocultarPaneles();
        this.add(frame);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        panel.setVisible(true);
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
                .addContainerGap(279, Short.MAX_VALUE))
        );
        panelEsperaLayout.setVerticalGroup(
            panelEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEsperaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(292, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelLoginJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panelEspera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelLoginJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelEspera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblCantCartones;
    private javax.swing.JPanel panelEspera;
    private javax.swing.JPanel panelLoginJugador;
    private javax.swing.JTextField txtCantCartones;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

}
