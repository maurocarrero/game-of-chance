package bingoclientejugador;

import bingo.common.Controlador;
import bingo.common.interfaces.IFigura;
import bingo.common.interfaces.IJugador;
import java.awt.GridLayout;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 * Interfaz para Jugadores
 * @author maurocarrero/fernandogonzalez
 */
public final class VistaJugador extends JFrame {

    public VistaJugador() {
        initComponents();
        
        btnIngresar.setActionCommand("INGRESAR");
        txtUsuario.setActionCommand("INGRESAR");
        txtPassword.setActionCommand("INGRESAR");
        txtCantCartones.setActionCommand("INGRESAR");
        
        btnNO.setActionCommand("NO_CONTINUAR");
        btnSI.setActionCommand("SI_CONTINUAR");
        ocultarPaneles();
        pack();
    }
    
    public String getUsuario(){
        return this.txtUsuario.getText();
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
        panelMensaje.setVisible(true);
        pack();
    }
    
    public void ocultarPaneles() {
        panelLoginJugador.setVisible(false);
        panelContinuarJugando.setVisible(false);
        panelCartones.setVisible(false);
        panelInfo.setVisible(false);
        panelMensaje.setVisible(false);
        panelTimerFiguras.setVisible(false);
    }
    
    
    public void ocultarPanelContinuar() {
        panelContinuarJugando.setVisible(false);
    }
    
    public void mostrarPanelContinuar() {
        panelContinuarJugando.setVisible(true);
        panelTimerFiguras.setVisible(true);
        pack();
    }
   
    public void dibujarContenedorCartones(int tamanio, int cantFilas, int cantColumnas) {
        ocultarPaneles();
        panelInfo.setVisible(true);
        panelMensaje.setVisible(true);
        panelContinuarJugando.setVisible(true);
        panelCartones.setLayout(new GridLayout(0, 5));
        pack();
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
    
    public void mostrarInfo(String nombreJugador, double pozo, double saldoActual, 
            List<IJugador> jugadores, List<IFigura> figuras) throws RemoteException {
        setTitle(nombreJugador);
        lblPozo.setText(pozo + "");
        lblSaldoActual.setText(saldoActual + "");
 
        // JUGADORES
        List<String> jugadoresArray = new ArrayList(); 
        for(IJugador j : jugadores) {
            jugadoresArray.add(j.getUsuario());
        }
        listJugadores.setListData(jugadoresArray.toArray());
        
        // FIGURAS
        List<String> figurasArray = new ArrayList();
        for(IFigura f :figuras) {
            figurasArray.add(f.getNombre());
        }
        listFiguras.setListData(figurasArray.toArray());
        
    }
    
    public void actualizarTimer(int timer){
        StringBuilder buff = new StringBuilder();
        buff.append("<html>");
        buff.append("<h1 style=\"font-family: Yuanti SC; font-size: 48px;");
        buff.append(String.format(" font-weight: bold;\">%s</h1>", timer));
        buff.append("</html>");
        lblTimer.setText(buff.toString());
    }
    
    public void actualizarPozo(double pozo){
        lblPozo.setText(pozo + "");
        
    }

    public void limpiarCampos() {
        txtUsuario.setText("");
        txtPassword.setText("");
        txtCantCartones.setText("");
        txtUsuario.requestFocus();
    }
    
    
    public void abandonarPartida() {
        dispose();
        System.exit(0);
    }
    
    public void mostrarMensaje(String msg) {
        panelMensaje.setVisible(true);
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

    public void setControlador(Controlador c) {
        // Login
        btnIngresar.addActionListener(c);
        txtUsuario.addActionListener(c);
        txtPassword.addActionListener(c);
        txtCantCartones.addActionListener(c);
        btnNO.addActionListener(c);
        btnSI.addActionListener(c);
    }

    public void ejecutar() {
        ocultarPaneles();
        panelLoginJugador.setVisible(true);
        setTitle("Interfaz de Jugador");
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContinuarJugando = new javax.swing.JPanel();
        lblContinuarJugando = new javax.swing.JLabel();
        btnSI = new javax.swing.JButton();
        btnNO = new javax.swing.JButton();
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
        panelMensaje = new javax.swing.JPanel();
        lblMensaje = new javax.swing.JLabel();
        panelLoginJugador = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();
        lblCantCartones = new javax.swing.JLabel();
        txtCantCartones = new javax.swing.JTextField();
        panelTimerFiguras = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listFiguras = new javax.swing.JList();
        lblJugadoresName1 = new javax.swing.JLabel();
        lblTimer = new javax.swing.JLabel();
        lblJugadoresName2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblContinuarJugando.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        lblContinuarJugando.setText("¿Desea continuar jugando? ");

        btnSI.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        btnSI.setText("SI");

        btnNO.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        btnNO.setText("NO");

        javax.swing.GroupLayout panelContinuarJugandoLayout = new javax.swing.GroupLayout(panelContinuarJugando);
        panelContinuarJugando.setLayout(panelContinuarJugandoLayout);
        panelContinuarJugandoLayout.setHorizontalGroup(
            panelContinuarJugandoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContinuarJugandoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblContinuarJugando)
                .addGap(37, 37, 37))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContinuarJugandoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnSI, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNO, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelCartonesLayout = new javax.swing.GroupLayout(panelCartones);
        panelCartones.setLayout(panelCartonesLayout);
        panelCartonesLayout.setHorizontalGroup(
            panelCartonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelCartonesLayout.setVerticalGroup(
            panelCartonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        lblPozoName.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        lblPozoName.setText("Pozo:");

        lblSaldoName.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        lblSaldoName.setText("Saldo actual:");

        lblSaldoActual.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        lblSaldoActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblPozo.setFont(new java.awt.Font("Yuanti SC", 1, 24)); // NOI18N
        lblPozo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblJugadoresName.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        lblJugadoresName.setText("Jugadores en el juego:");

        listJugadores.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        listJugadores.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listJugadores);
        listJugadores.getAccessibleContext().setAccessibleName("listjugadores");

        jLabel4.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        jLabel4.setText("Bolilla sorteada:");

        lblBolilla.setBackground(new java.awt.Color(0, 0, 0));
        lblBolilla.setFont(new java.awt.Font("Yuanti SC", 1, 36)); // NOI18N
        lblBolilla.setForeground(new java.awt.Color(204, 255, 204));
        lblBolilla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBolilla.setOpaque(true);

        javax.swing.GroupLayout panelInfoLayout = new javax.swing.GroupLayout(panelInfo);
        panelInfo.setLayout(panelInfoLayout);
        panelInfoLayout.setHorizontalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addComponent(lblJugadoresName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPozoName)
                            .addComponent(lblSaldoName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPozo, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(lblSaldoActual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(lblBolilla, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))))
        );
        panelInfoLayout.setVerticalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblPozo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSaldoName)
                            .addComponent(lblSaldoActual, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(lblPozoName)
                                .addGap(5, 5, 5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(lblBolilla, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblJugadoresName)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblMensaje.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        lblMensaje.setText("Esperando inicio del juego...");

        javax.swing.GroupLayout panelMensajeLayout = new javax.swing.GroupLayout(panelMensaje);
        panelMensaje.setLayout(panelMensajeLayout);
        panelMensajeLayout.setHorizontalGroup(
            panelMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMensajeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMensajeLayout.setVerticalGroup(
            panelMensajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMensajeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        jLabel1.setText("Usuario");

        jLabel2.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        jLabel2.setText("Contrasenia");

        txtUsuario.setFont(new java.awt.Font("Yuanti SC", 0, 18)); // NOI18N

        txtPassword.setFont(new java.awt.Font("Yuanti SC", 0, 18)); // NOI18N

        btnIngresar.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        btnIngresar.setText("Ingresar");

        lblCantCartones.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        lblCantCartones.setText("Cantidad de cartones");

        txtCantCartones.setFont(new java.awt.Font("Yuanti SC", 0, 18)); // NOI18N

        javax.swing.GroupLayout panelLoginJugadorLayout = new javax.swing.GroupLayout(panelLoginJugador);
        panelLoginJugador.setLayout(panelLoginJugadorLayout);
        panelLoginJugadorLayout.setHorizontalGroup(
            panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginJugadorLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLoginJugadorLayout.createSequentialGroup()
                        .addComponent(lblCantCartones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantCartones, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelLoginJugadorLayout.createSequentialGroup()
                            .addGroup(panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        panelLoginJugadorLayout.setVerticalGroup(
            panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginJugadorLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(2, 2, 2)
                .addGroup(panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(panelLoginJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantCartones, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantCartones))
                .addGap(18, 18, 18)
                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        listFiguras.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        listFiguras.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(listFiguras);
        listFiguras.getAccessibleContext().setAccessibleName("listFiguras");

        lblJugadoresName1.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        lblJugadoresName1.setText("Tiempo restante:");

        lblTimer.setFont(new java.awt.Font("Al Nile", 1, 18)); // NOI18N
        lblTimer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTimer.setText("Timer");

        lblJugadoresName2.setFont(new java.awt.Font("Yuanti SC", 1, 18)); // NOI18N
        lblJugadoresName2.setText("Figuras habilitadas:");

        javax.swing.GroupLayout panelTimerFigurasLayout = new javax.swing.GroupLayout(panelTimerFiguras);
        panelTimerFiguras.setLayout(panelTimerFigurasLayout);
        panelTimerFigurasLayout.setHorizontalGroup(
            panelTimerFigurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTimerFigurasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTimerFigurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(panelTimerFigurasLayout.createSequentialGroup()
                        .addGroup(panelTimerFigurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblJugadoresName2)
                            .addGroup(panelTimerFigurasLayout.createSequentialGroup()
                                .addComponent(lblJugadoresName1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelTimerFigurasLayout.setVerticalGroup(
            panelTimerFigurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTimerFigurasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTimerFigurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblJugadoresName1)
                    .addComponent(lblTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblJugadoresName2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCartones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(panelTimerFiguras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panelContinuarJugando, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(panelLoginJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelTimerFiguras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelContinuarJugando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCartones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelLoginJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnNO;
    private javax.swing.JButton btnSI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblBolilla;
    private javax.swing.JLabel lblCantCartones;
    private javax.swing.JLabel lblContinuarJugando;
    private javax.swing.JLabel lblJugadoresName;
    private javax.swing.JLabel lblJugadoresName1;
    private javax.swing.JLabel lblJugadoresName2;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblPozo;
    private javax.swing.JLabel lblPozoName;
    private javax.swing.JLabel lblSaldoActual;
    private javax.swing.JLabel lblSaldoName;
    private javax.swing.JLabel lblTimer;
    private javax.swing.JList listFiguras;
    private javax.swing.JList listJugadores;
    private javax.swing.JPanel panelCartones;
    private javax.swing.JPanel panelContinuarJugando;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JPanel panelLoginJugador;
    private javax.swing.JPanel panelMensaje;
    private javax.swing.JPanel panelTimerFiguras;
    private javax.swing.JTextField txtCantCartones;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

}
