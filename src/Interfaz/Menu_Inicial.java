package Interfaz;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import com.formdev.flatlaf.ui.FlatEmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

/**
 *
 * @author mesoi
 */
public class Menu_Inicial extends javax.swing.JFrame {

    public static int tiempoI = 0, tiempoP = 0, tiempoE = 0, tiempoS = 0, costoI = 0, costoP = 0, costoE = 0, costoS = 0;

    /**
     * Creates new form Menu_Inicial
     */
    public Menu_Inicial() {
        initComponents();
        this.setTitle("Menú Inicial");
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        Simulacion.bloqueoBTN = false;
        FlatSVGIcon svgBtn2 = new FlatSVGIcon("img/iniciar.svg", 8, 8);
        svgBtn2.setColorFilter(new FlatSVGIcon.ColorFilter(new Function<Color, Color>() {
            @Override
            public Color apply(Color t) {
                return new Color(255, 255, 255);
            }

        }));

        tiempoI = 0;
        tiempoP = 0;
        tiempoE = 0;
        tiempoS = 0;
        costoI = 0;
        costoP = 0;
        costoE = 0;
        costoS = 0;
        btnIniciar.setIcon(svgBtn2);

        //txtTiempoI.putClientProperty("JTextField.leadingIconGap", 20);
    }

    public void iniciarSimulacion() {
        boolean verificar = false;
        int contador = 0;
        if (txtCostoE.getText().trim().isEmpty() || txtCostoI.getText().trim().isEmpty() || txtCostoP.getText().trim().isEmpty() || txtCostoS.getText().trim().isEmpty()
                || txtTiempoE.getText().trim().isEmpty() || txtTiempoI.getText().trim().isEmpty() || txtTiempoP.getText().trim().isEmpty() || txtTiempoS.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Llene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String[] valores = {txtTiempoI.getText(), txtTiempoP.getText(), txtTiempoE.getText(), txtTiempoS.getText(), txtCostoI.getText(), txtCostoP.getText(), txtCostoE.getText(), txtCostoS.getText()};
            //String[] nombre = {"Tiempo del Inventario","Tiempo de Producción", "Tiempo del Empaquetado","Tiempo de Salida","Costo del Invernario", "Costo de Producción","Costo del Empaquetado","Costo de Salida"};
            for (int i = 0; i < valores.length; i++) {
                if (valores[i].matches("[0-9]*")) {
                    contador++;
                }
            }
            if (contador == 8) {
                tiempoI = Integer.parseInt(txtTiempoI.getText());
                tiempoP = Integer.parseInt(txtTiempoP.getText());
                tiempoE = Integer.parseInt(txtTiempoE.getText());
                tiempoS = Integer.parseInt(txtTiempoS.getText());

                costoI = Integer.parseInt(txtCostoI.getText());
                costoP = Integer.parseInt(txtCostoP.getText());
                costoE = Integer.parseInt(txtCostoE.getText());
                costoS = Integer.parseInt(txtCostoS.getText());
                if (tiempoE > 0 && tiempoI > 0 && tiempoP > 0 && tiempoS > 0 && costoE > 0 && costoI > 0 && costoP > 0 && costoS > 0) {
                    Simulacion s = new Simulacion();
                    s.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "No se aceptan valores iguales a 0", "Alerta", JOptionPane.WARNING_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Algunos datos ingresados no son correctos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void soloNum(JTextField txt, java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            txt.setEditable(false);

            txt.putClientProperty("Component.outlineWidth", 1);
            txt.putClientProperty("JComponent.outline", "error");
        } else {
            txt.putClientProperty("Component.outlineWidth", 1);
            txt.putClientProperty("JComponent.outline", "correct");
            txt.setEditable(true);
        }
    }

    public void verificarNum(JTextField txt) {
        if (txt.isEditable()) {
            if (!txt.getText().matches("[0-9]*")) {
                txt.putClientProperty("Component.outlineWidth", 1);
                txt.putClientProperty("JComponent.outline", "error");

            } else {
                txt.putClientProperty("Component.outlineWidth", 1);
                txt.putClientProperty("JComponent.outline", "correct");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelMenu = new Elementos.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTiempoI = new javax.swing.JTextField();
        txtCostoI = new javax.swing.JTextField();
        txtTiempoP = new javax.swing.JTextField();
        txtCostoP = new javax.swing.JTextField();
        txtTiempoE = new javax.swing.JTextField();
        txtCostoE = new javax.swing.JTextField();
        txtCostoS = new javax.swing.JTextField();
        txtTiempoS = new javax.swing.JTextField();
        btnIniciar = new Elementos.ButtonRound();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(23, 24, 31));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bienvenido a Monkey");

        panelMenu.setBackground(new java.awt.Color(32, 33, 44));
        panelMenu.setRoundBottomLeft(20);
        panelMenu.setRoundBottomRight(20);
        panelMenu.setRoundTopLeft(20);
        panelMenu.setRoundTopRight(20);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Inventario:");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tiempo (s)");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Costo(Q/s)");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Producción:");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Empaquetado:");

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Salida:");

        txtTiempoI.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtTiempoI.setForeground(new java.awt.Color(216, 226, 252));
        txtTiempoI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTiempoIKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTiempoIKeyReleased(evt);
            }
        });

        txtCostoI.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtCostoI.setForeground(new java.awt.Color(216, 226, 252));
        txtCostoI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCostoIKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCostoIKeyReleased(evt);
            }
        });

        txtTiempoP.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtTiempoP.setForeground(new java.awt.Color(216, 226, 252));
        txtTiempoP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTiempoPKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTiempoPKeyReleased(evt);
            }
        });

        txtCostoP.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtCostoP.setForeground(new java.awt.Color(216, 226, 252));
        txtCostoP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCostoPKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCostoPKeyReleased(evt);
            }
        });

        txtTiempoE.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtTiempoE.setForeground(new java.awt.Color(216, 226, 252));
        txtTiempoE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTiempoEKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTiempoEKeyReleased(evt);
            }
        });

        txtCostoE.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtCostoE.setForeground(new java.awt.Color(216, 226, 252));
        txtCostoE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCostoEKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCostoEKeyReleased(evt);
            }
        });

        txtCostoS.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtCostoS.setForeground(new java.awt.Color(216, 226, 252));
        txtCostoS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCostoSKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCostoSKeyReleased(evt);
            }
        });

        txtTiempoS.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtTiempoS.setForeground(new java.awt.Color(216, 226, 252));
        txtTiempoS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTiempoSKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTiempoSKeyReleased(evt);
            }
        });

        btnIniciar.setBorder(null);
        btnIniciar.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciar.setText(" Iniciar Simulación");
        btnIniciar.setBorderColor(new java.awt.Color(110, 125, 255));
        btnIniciar.setColor(new java.awt.Color(110, 125, 255));
        btnIniciar.setColorClick(new java.awt.Color(100, 112, 222));
        btnIniciar.setColorOver(new java.awt.Color(105, 119, 240));
        btnIniciar.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        btnIniciar.setRadius(20);
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelMenuLayout.createSequentialGroup()
                                .addComponent(txtTiempoS, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                .addComponent(txtCostoS, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                                .addComponent(txtTiempoE, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCostoE, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTiempoP, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelMenuLayout.createSequentialGroup()
                                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                    .addComponent(txtTiempoI))
                                .addGap(24, 24, 24)
                                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCostoP, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCostoI, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTiempoI, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtCostoI, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTiempoP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCostoP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTiempoE, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCostoE, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTiempoS, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCostoS, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(21, 21, 21)
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTiempoIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTiempoIKeyPressed
        // TODO add your handling code here:
        soloNum(txtTiempoI, evt);
    }//GEN-LAST:event_txtTiempoIKeyPressed

    private void txtCostoIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoIKeyPressed
        // TODO add your handling code here:
        soloNum(txtCostoI, evt);
    }//GEN-LAST:event_txtCostoIKeyPressed

    private void txtTiempoPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTiempoPKeyPressed
        // TODO add your handling code here:
        soloNum(txtTiempoP, evt);
    }//GEN-LAST:event_txtTiempoPKeyPressed

    private void txtCostoPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoPKeyPressed
        // TODO add your handling code here:
        soloNum(txtCostoP, evt);
    }//GEN-LAST:event_txtCostoPKeyPressed

    private void txtTiempoEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTiempoEKeyPressed
        // TODO add your handling code here:
        soloNum(txtTiempoE, evt);
    }//GEN-LAST:event_txtTiempoEKeyPressed

    private void txtCostoEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoEKeyPressed
        // TODO add your handling code here:
        soloNum(txtCostoE, evt);
    }//GEN-LAST:event_txtCostoEKeyPressed

    private void txtTiempoSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTiempoSKeyPressed
        // TODO add your handling code here:
        soloNum(txtTiempoS, evt);
    }//GEN-LAST:event_txtTiempoSKeyPressed

    private void txtCostoSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoSKeyPressed
        // TODO add your handling code here:
        soloNum(txtCostoS, evt);
    }//GEN-LAST:event_txtCostoSKeyPressed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        // TODO add your handling code here:
        iniciarSimulacion();
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void txtTiempoIKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTiempoIKeyReleased
        // TODO add your handling code here:
        verificarNum(txtTiempoI);
    }//GEN-LAST:event_txtTiempoIKeyReleased

    private void txtCostoIKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoIKeyReleased
        // TODO add your handling code here:
        verificarNum(txtCostoI);
    }//GEN-LAST:event_txtCostoIKeyReleased

    private void txtTiempoPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTiempoPKeyReleased
        // TODO add your handling code here:
        verificarNum(txtTiempoP);
    }//GEN-LAST:event_txtTiempoPKeyReleased

    private void txtCostoPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoPKeyReleased
        // TODO add your handling code here:
        verificarNum(txtCostoP);
    }//GEN-LAST:event_txtCostoPKeyReleased

    private void txtTiempoEKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTiempoEKeyReleased
        // TODO add your handling code here:
        verificarNum(txtTiempoE);
    }//GEN-LAST:event_txtTiempoEKeyReleased

    private void txtCostoEKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoEKeyReleased
        // TODO add your handling code here:
        verificarNum(txtCostoE);
    }//GEN-LAST:event_txtCostoEKeyReleased

    private void txtTiempoSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTiempoSKeyReleased
        // TODO add your handling code here:
        verificarNum(txtTiempoS);
    }//GEN-LAST:event_txtTiempoSKeyReleased

    private void txtCostoSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCostoSKeyReleased
        // TODO add your handling code here:
        verificarNum(txtCostoS);
    }//GEN-LAST:event_txtCostoSKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(new FlatOneDarkIJTheme());
            //UIManager.put("TextComponent.arc", 999);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Menu_Inicial.class.getName()).log(Level.SEVERE, null, ex);
        }
        //UIManager.put("defaultFont", new Font("Montserrat", 0, 13));
        UIManager.put("Component.arrowType", "chevron");
        //85/89/146

        UIManager.put("Component.focusedBorderColor", new Color(129, 135, 222));
        UIManager.put("Component.borderColor", new Color(32, 33, 44));
        UIManager.put("TextComponent.arc", 15);
        UIManager.put("Component.arc", 10);
        UIManager.put("TextField.margin", new Insets(0, 10, 0, 10));
        UIManager.put("PasswordField.margin", new Insets(0, 10, 0, 10));
        UIManager.put("ComboBox.selectionArc", 10);
        UIManager.put("TextField.background", new Color(41, 42, 56));
        //</editor-fold> 28, 29, 33

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Inicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Elementos.ButtonRound btnIniciar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private Elementos.PanelRound panelMenu;
    private javax.swing.JTextField txtCostoE;
    private javax.swing.JTextField txtCostoI;
    private javax.swing.JTextField txtCostoP;
    private javax.swing.JTextField txtCostoS;
    private javax.swing.JTextField txtTiempoE;
    private javax.swing.JTextField txtTiempoI;
    private javax.swing.JTextField txtTiempoP;
    private javax.swing.JTextField txtTiempoS;
    // End of variables declaration//GEN-END:variables
}
