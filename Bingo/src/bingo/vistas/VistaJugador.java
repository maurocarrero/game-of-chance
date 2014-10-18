package bingo.vistas;

import bingo.controladores.ControlJugador;
import bingo.controladores.Controlador;
import bingo.interfaces.IJugador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
        btnIngresar.setActionCommand("INGRESAR");
        btnNO.setActionCommand("NO_CONTINUAR");
        btnSI.setActionCommand("SI_CONTINUAR");
        ocultarPaneles();
        pack();        
    }
    
    public String getUsuario(){
        return this.txtUsuario.getText();
    }
    
    public void setSaldo(String saldo){
        this.lblSaldo.setText(saldo);
    }
    
    public void setBolilla(int bolilla) {
        lblBolilla.setText(bolilla + "");
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
    
    public void ocultarPaneles() {
        panelEspera.setVisible(false);
        panelLoginJugador.setVisible(false);
        panelContinuarJugando.setVisible(false);
        panelCartones.setVisible(false);
        panelInfo.setVisible(false);
    }
    
   
    public void dibujarContenedorCartones(int tamanio, int cantFilas, int cantColumnas) {
        ocultarPaneles();
        panelInfo.setVisible(true);
        panelContinuarJugando.setVisible(true);
        panelCartones.setLayout(new BoxLayout(panelCartones, BoxLayout.PAGE_AXIS));
    }  
    
    public List<JCasillero> dibujarCarton(int[][] numeros, int cantFilas, int cantColumnas) {
        JCarton carton = new JCarton(numeros, cantFilas, cantColumnas);
        panelCartones.add(carton);
        return carton.getCasilleros();
    }
    
    public void mostrarCartones() {
        panelCartones.setVisible(true);
        pack();
    }
    
    public void mostrarInfo(String nombreJugador, double pozo, double saldoActual, List<IJugador> jugadores) {
        setTitle(nombreJugador);
        lblPozo.setText(pozo + "");
        lblSaldoActual.setText(saldoActual + "");
        listJugadores.setListData(jugadores.toArray());
    }
    
    public void actualizarPozo(double pozo){
        lblPozo.setText(pozo + "");
    }
    
    public void abandonarPartida() {
        ocultarPaneles();
        panelLoginJugador.setVisible(true);
        pack();
    }
    
    public void mostrarMensaje(String msg) {
        lblMensaje.setText(msg);
        pack();
    }
    
    public void finalizarJuego(String usuario, double saldo, double pozo){
        mostrarMensaje("Fin del juego, Ganador: " + usuario);
        actualizarPozo(pozo);
        lblSaldoActual.setText(saldo + "");        
        panelContinuarJugando.setVisible(false);
        listJugadores.removeAll();
        listJugadores.setVisible(false);
    }

    @Override
    public void setControlador(Controlador c) {
        // Login
        btnIngresar.addActionListener(c);
        txtUsuario.addActionListener(c);
        txtPassword.addActionListener(c);
        txtCantCartones.addActionListener(c);
        btnNO.addActionListener(c);
        btnSI.addActionListener(c);
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
        lblSaldo = new javax.swing.JLabel();
        panelCartones = new javax.swing.JPanel();
        panelInfo = new javax.swing.JPanel();
        lblPozoName = new javax.swing.JLabel();
        lblSaldoName = new javax.swing.JLabel();
        lblSaldoActual = new javax.swing.JLabel();
        lblPozo = new javax.swing.JLabel();
        lblJugadoresName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listJugadores = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        lblBolilla = new javax.swing.JLabel();
        lblMensaje = new javax.swing.JLabel();

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(23, Short.MAX_VALUE))
        );
        panelEsperaLayout.setVerticalGroup(
            panelEsperaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEsperaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblContinuarJugando.setText("¿Desea continuar jugando? ");

        btnSI.setText("SI");

        btnNO.setText("NO");

        javax.swing.GroupLayout panelContinuarJugandoLayout = new javax.swing.GroupLayout(panelContinuarJugando);
        panelContinuarJugando.setLayout(panelContinuarJugandoLayout);
        panelContinuarJugandoLayout.setHorizontalGroup(
            panelContinuarJugandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContinuarJugandoLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(lblSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelContinuarJugandoLayout.createSequentialGroup()
                .addGroup(panelContinuarJugandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContinuarJugandoLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(btnSI, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btnNO, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelContinuarJugandoLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lblContinuarJugando)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        panelContinuarJugandoLayout.setVerticalGroup(
            panelContinuarJugandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContinuarJugandoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblContinuarJugando)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelContinuarJugandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNO)
                    .addComponent(btnSI))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelCartonesLayout = new javax.swing.GroupLayout(panelCartones);
        panelCartones.setLayout(panelCartonesLayout);
        panelCartonesLayout.setHorizontalGroup(
            panelCartonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 253, Short.MAX_VALUE)
        );
        panelCartonesLayout.setVerticalGroup(
            panelCartonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 194, Short.MAX_VALUE)
        );

        lblPozoName.setText("Pozo:");

        lblSaldoName.setText("Saldo actual:");

        lblJugadoresName.setText("Jugadores en el juego:");

        listJugadores.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listJugadores);

        jLabel4.setText("Bolilla sorteada:");

        lblBolilla.setBackground(new java.awt.Color(204, 255, 204));
        lblBolilla.setOpaque(true);

        javax.swing.GroupLayout panelInfoLayout = new javax.swing.GroupLayout(panelInfo);
        panelInfo.setLayout(panelInfoLayout);
        panelInfoLayout.setHorizontalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addComponent(lblJugadoresName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(lblPozoName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblPozo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addComponent(lblSaldoName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblSaldoActual, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(104, 104, 104)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblBolilla, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addGap(66, 66, 66)
                .addComponent(lblMensaje)
                .addContainerGap(136, Short.MAX_VALUE))
        );
        panelInfoLayout.setVerticalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPozoName)
                            .addComponent(lblPozo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSaldoName)
                            .addComponent(lblSaldoActual, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBolilla, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addGap(18, 18, 18)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblJugadoresName)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMensaje))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelLoginJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelContinuarJugando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelEspera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelCartones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelLoginJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelContinuarJugando, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelEspera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCartones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBolilla;
    private javax.swing.JLabel lblCantCartones;
    private javax.swing.JLabel lblContinuarJugando;
    private javax.swing.JLabel lblJugadoresName;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblPozo;
    private javax.swing.JLabel lblPozoName;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JLabel lblSaldoActual;
    private javax.swing.JLabel lblSaldoName;
    private javax.swing.JList listJugadores;
    private javax.swing.JPanel panelCartones;
    private javax.swing.JPanel panelContinuarJugando;
    private javax.swing.JPanel panelEspera;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JPanel panelLoginJugador;
    private javax.swing.JTextField txtCantCartones;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

}
