package Interfaz;

import Hilos.SpawnLabels;
import Hilos.Tiempo;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author mesoi
 */
public class Simulacion extends javax.swing.JFrame {

    private FlatSVGIcon svgIcono;
    public static Tiempo t;
    public static Simulacion simio;
    private int contadorInicio = 30;
    private FlatSVGIcon.ColorFilter fl;
    public static boolean bloqueoBTN = false;

    /**
     * Creates new form Simulacion
     */
    public Simulacion() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        conector1.setVisible(false);
        Cronometro.setVisible(false);
        InventarioE.setLayout(new FlowLayout(FlowLayout.LEFT));
        Produccion1.setLayout(new FlowLayout(FlowLayout.LEFT));
        Empaquetado.setLayout(new FlowLayout(FlowLayout.LEFT));
        Salida1.setLayout(new FlowLayout(FlowLayout.LEFT));

        t = new Tiempo(lblMin, lblSeg, false);
        fl = new FlatSVGIcon.ColorFilter(new Function<Color, Color>() {
            @Override
            public Color apply(Color t) {
                return Color.white;
            }

        });
        FlatSVGIcon.ColorFilter flechas = new FlatSVGIcon.ColorFilter(new Function<Color, Color>() {
            @Override
            public Color apply(Color t) {
                return new Color(136, 143, 184);
            }
        });//47, 71, 102
        
        FlatSVGIcon svgBtn2 = new FlatSVGIcon("img/download.svg");
        svgBtn2.setColorFilter(new FlatSVGIcon.ColorFilter(new Function<Color, Color>() {
            @Override
            public Color apply(Color t) {
                return new Color(255, 255, 255);
            }

        }));
        btnReporte.setIcon(svgBtn2);
        FlatSVGIcon arriba = new FlatSVGIcon("img/flechaA2.svg", 40, 40);
        FlatSVGIcon izquierda = new FlatSVGIcon("img/flechaI.svg", 40, 40);
        FlatSVGIcon abajo = new FlatSVGIcon("img/flechaAbajo.svg", 40, 40);
        svgIcono = new FlatSVGIcon("img/house.svg", 40, 40);
        svgIcono.setColorFilter(fl);
        arriba.setColorFilter(flechas);
        izquierda.setColorFilter(flechas);
        abajo.setColorFilter(flechas);
        FlatSVGIcon svgIcono1 = new FlatSVGIcon("img/salida.svg", 40, 40);
        svgIcono1.setColorFilter(fl);
        Inicio.setIcon(svgIcono);
        salidaimagen.setIcon(svgIcono1);
        flecha1.setIcon(arriba);
        lblIzq.setIcon(izquierda);
        lblAbajo.setIcon(abajo);
        t.start();
        t.activar();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 15; i++) {
                    contadorInicio--;
                    lblContadorI.setText(contadorInicio + "");
                    SpawnLabels sp = new SpawnLabels(panelPrincipal, Cronometro, 1);
                    sp.start();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }

        });

        t1.start();
    }

    public JLabel getCronometro() {
        return Cronometro;
    }

    private void guardarReporte() {
        JFileChooser guardarComo = new JFileChooser();
        int userSelection = 0;
        try {
            guardarComo.setDialogTitle("Guardar Reporte");
            userSelection = guardarComo.showSaveDialog(null);
        } catch (Exception e) {
            System.err.print(e);
        }
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File directorio = new File(guardarComo.getSelectedFile().toString() + ".html");
            if (!directorio.isFile() && !directorio.isDirectory()) {
                String htmFilePath = "/htmls/reporte.html";
                InputStream inputStream = getClass().getResourceAsStream(htmFilePath);
                //File input = new File("src\\htmls\\reporte.html");

                String htmlContent = "";
                try {
                    htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
                } catch (IOException ex) {
                    Logger.getLogger(Simulacion.class.getName()).log(Level.SEVERE, null, ex);
                }

                Document doc = Jsoup.parse(htmlContent);
                Element dInventario = doc.getElementById("dInventario");
                Element dProduccion = doc.getElementById("dProduccion");
                Element dEmpaquetado = doc.getElementById("dEmpaquetado");
                Element dSalida = doc.getElementById("dSalida");
                Element dTotal = doc.getElementById("dTotal");

                int costoInventario = Menu_Inicial.costoI * Menu_Inicial.tiempoI * 30;
                int costoProduccion = Menu_Inicial.costoP * Menu_Inicial.tiempoP * 30;
                int costoEmpaquetado = Menu_Inicial.costoE * Menu_Inicial.tiempoE * 30;
                int costoSalida = Menu_Inicial.costoS * Menu_Inicial.tiempoS * 30;

                dInventario.text(costoInventario + "");
                dProduccion.text(costoProduccion + "");
                dEmpaquetado.text(costoEmpaquetado + "");
                dSalida.text(costoSalida + "");
                int total = costoInventario + costoEmpaquetado + costoProduccion + costoSalida;
                //dTotal.text(factura.getTotal() + "");
                dTotal.text(total + "");
                String htmlModificado = doc.outerHtml();
                FileWriter writer;

                try {
                    writer = new FileWriter(guardarComo.getSelectedFile() + ".html");
                    writer.write(htmlModificado);
                    JOptionPane.showMessageDialog(null, "Archivo Guardado Correctamente");
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(Simulacion.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Ese nombre ya existe en la carpeta", "Error", JOptionPane.ERROR_MESSAGE);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Cronometro = new javax.swing.JLabel();
        panelRound2 = new Elementos.PanelRound();
        lblMin = new javax.swing.JLabel();
        panelRound7 = new Elementos.PanelRound();
        lblSeg = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        buttonRound1 = new Elementos.ButtonRound();
        btnReporte = new Elementos.ButtonRound();
        panelRound1 = new Elementos.PanelRound();
        panelPrincipal = new javax.swing.JLayeredPane();
        Inventario = new Elementos.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        numInventario = new javax.swing.JLabel();
        InventarioE = new javax.swing.JPanel();
        panelRound3 = new Elementos.PanelRound();
        jLabel3 = new javax.swing.JLabel();
        numProd = new javax.swing.JLabel();
        Produccion1 = new javax.swing.JPanel();
        panelRound4 = new Elementos.PanelRound();
        jLabel4 = new javax.swing.JLabel();
        numEmpa = new javax.swing.JLabel();
        Empaquetado = new javax.swing.JPanel();
        panelRound5 = new Elementos.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        numSalida = new javax.swing.JLabel();
        Salida1 = new javax.swing.JPanel();
        Inicio = new javax.swing.JLabel();
        conector1 = new javax.swing.JPanel();
        salidaimagen = new javax.swing.JLabel();
        Final1 = new javax.swing.JLayeredPane();
        lblContadorI = new javax.swing.JLabel();
        panelTransicion = new javax.swing.JLayeredPane();
        lblContadorF = new javax.swing.JLabel();
        flecha1 = new javax.swing.JLabel();
        lblIzq = new javax.swing.JLabel();
        lblAbajo = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(23, 24, 31));

        jPanel2.setBackground(new java.awt.Color(32, 33, 44));
        jPanel2.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel1.setText("Tiempo Transcurrido");

        Cronometro.setText("00:00");

        panelRound2.setBackground(new java.awt.Color(79, 83, 107));
        panelRound2.setRoundBottomLeft(15);
        panelRound2.setRoundBottomRight(15);
        panelRound2.setRoundTopLeft(15);
        panelRound2.setRoundTopRight(15);

        lblMin.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        lblMin.setForeground(new java.awt.Color(255, 255, 255));
        lblMin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMin.setText("00");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblMin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelRound7.setBackground(new java.awt.Color(79, 83, 107));
        panelRound7.setRoundBottomLeft(15);
        panelRound7.setRoundBottomRight(15);
        panelRound7.setRoundTopLeft(15);
        panelRound7.setRoundTopRight(15);

        lblSeg.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        lblSeg.setForeground(new java.awt.Color(255, 255, 255));
        lblSeg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSeg.setText("00");

        javax.swing.GroupLayout panelRound7Layout = new javax.swing.GroupLayout(panelRound7);
        panelRound7.setLayout(panelRound7Layout);
        panelRound7Layout.setHorizontalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRound7Layout.setVerticalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSeg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jLabel7.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText(":");

        buttonRound1.setBorder(null);
        buttonRound1.setForeground(new java.awt.Color(255, 255, 255));
        buttonRound1.setText("Regresar");
        buttonRound1.setBorderColor(new java.awt.Color(31, 118, 254));
        buttonRound1.setColor(new java.awt.Color(31, 118, 254));
        buttonRound1.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        buttonRound1.setRadius(20);
        buttonRound1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRound1ActionPerformed(evt);
            }
        });

        btnReporte.setBorder(null);
        btnReporte.setForeground(new java.awt.Color(255, 255, 255));
        btnReporte.setText("Reporte");
        btnReporte.setBorderColor(new java.awt.Color(31, 118, 254));
        btnReporte.setColor(new java.awt.Color(31, 118, 254));
        btnReporte.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        btnReporte.setRadius(20);
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Cronometro)
                .addGap(68, 68, 68)
                .addComponent(jLabel1)
                .addGap(343, 343, 343))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(buttonRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(panelRound7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(175, 175, 175)
                .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonRound1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(Cronometro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(panelRound2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelRound7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        panelRound1.setBackground(new java.awt.Color(32, 33, 44));
        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);

        panelPrincipal.setBackground(new java.awt.Color(32, 33, 44));

        Inventario.setBackground(new java.awt.Color(51, 169, 254));
        Inventario.setRoundBottomLeft(20);
        Inventario.setRoundBottomRight(20);
        Inventario.setRoundTopLeft(20);
        Inventario.setRoundTopRight(20);

        jLabel2.setBackground(new java.awt.Color(38, 57, 80));
        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(38, 57, 80));
        jLabel2.setText("Inventario:");

        numInventario.setBackground(new java.awt.Color(38, 57, 80));
        numInventario.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        numInventario.setForeground(new java.awt.Color(38, 57, 80));
        numInventario.setText("0");

        InventarioE.setBackground(new java.awt.Color(51, 169, 254));

        javax.swing.GroupLayout InventarioLayout = new javax.swing.GroupLayout(Inventario);
        Inventario.setLayout(InventarioLayout);
        InventarioLayout.setHorizontalGroup(
            InventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InventarioLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(numInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InventarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(InventarioE, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        InventarioLayout.setVerticalGroup(
            InventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numInventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InventarioE, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelPrincipal.add(Inventario);
        Inventario.setBounds(430, 260, 300, 180);

        panelRound3.setBackground(new java.awt.Color(53, 223, 145));
        panelRound3.setRoundBottomLeft(20);
        panelRound3.setRoundBottomRight(20);
        panelRound3.setRoundTopLeft(20);
        panelRound3.setRoundTopRight(20);

        jLabel3.setBackground(new java.awt.Color(38, 57, 80));
        jLabel3.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(18, 104, 63));
        jLabel3.setText("Producción: ");

        numProd.setBackground(new java.awt.Color(38, 57, 80));
        numProd.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        numProd.setForeground(new java.awt.Color(18, 104, 63));
        numProd.setText("0");

        Produccion1.setBackground(new java.awt.Color(53, 223, 145));

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(numProd, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Produccion1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Produccion1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelPrincipal.add(panelRound3);
        panelRound3.setBounds(430, 20, 300, 180);

        panelRound4.setBackground(new java.awt.Color(250, 199, 98));
        panelRound4.setRoundBottomLeft(20);
        panelRound4.setRoundBottomRight(20);
        panelRound4.setRoundTopLeft(20);
        panelRound4.setRoundTopRight(20);

        jLabel4.setBackground(new java.awt.Color(38, 57, 80));
        jLabel4.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(149, 103, 10));
        jLabel4.setText("Empaquetado:");

        numEmpa.setBackground(new java.awt.Color(38, 57, 80));
        numEmpa.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        numEmpa.setForeground(new java.awt.Color(149, 103, 10));
        numEmpa.setText("0");

        Empaquetado.setBackground(new java.awt.Color(250, 199, 98));

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(numEmpa, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Empaquetado, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound4Layout.createSequentialGroup()
                .addGap(0, 9, Short.MAX_VALUE)
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numEmpa, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Empaquetado, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelPrincipal.add(panelRound4);
        panelRound4.setBounds(70, 20, 300, 180);

        panelRound5.setBackground(new java.awt.Color(244, 121, 96));
        panelRound5.setRoundBottomLeft(20);
        panelRound5.setRoundBottomRight(20);
        panelRound5.setRoundTopLeft(20);
        panelRound5.setRoundTopRight(20);

        jLabel5.setBackground(new java.awt.Color(38, 57, 80));
        jLabel5.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(135, 40, 22));
        jLabel5.setText("Salida:");

        numSalida.setBackground(new java.awt.Color(38, 57, 80));
        numSalida.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        numSalida.setForeground(new java.awt.Color(135, 40, 22));
        numSalida.setText("0");

        Salida1.setBackground(new java.awt.Color(244, 121, 96));

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(numSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Salida1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numSalida))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Salida1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelPrincipal.add(panelRound5);
        panelRound5.setBounds(70, 260, 300, 180);
        panelPrincipal.add(Inicio);
        Inicio.setBounds(750, 210, 40, 40);

        conector1.setAlignmentY(50.0F);
        conector1.setOpaque(false);

        javax.swing.GroupLayout conector1Layout = new javax.swing.GroupLayout(conector1);
        conector1.setLayout(conector1Layout);
        conector1Layout.setHorizontalGroup(
            conector1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        conector1Layout.setVerticalGroup(
            conector1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        panelPrincipal.add(conector1);
        conector1.setBounds(370, 200, 60, 60);
        panelPrincipal.add(salidaimagen);
        salidaimagen.setBounds(10, 210, 40, 40);

        javax.swing.GroupLayout Final1Layout = new javax.swing.GroupLayout(Final1);
        Final1.setLayout(Final1Layout);
        Final1Layout.setHorizontalGroup(
            Final1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );
        Final1Layout.setVerticalGroup(
            Final1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        panelPrincipal.add(Final1);
        Final1.setBounds(20, 10, 38, 100);

        lblContadorI.setFont(new java.awt.Font("Montserrat", 1, 15)); // NOI18N
        lblContadorI.setForeground(new java.awt.Color(255, 255, 255));
        lblContadorI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContadorI.setText("30");
        panelPrincipal.add(lblContadorI);
        lblContadorI.setBounds(740, 180, 60, 30);

        panelTransicion.setBackground(new java.awt.Color(153, 153, 153));
        panelTransicion.setForeground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout panelTransicionLayout = new javax.swing.GroupLayout(panelTransicion);
        panelTransicion.setLayout(panelTransicionLayout);
        panelTransicionLayout.setHorizontalGroup(
            panelTransicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );
        panelTransicionLayout.setVerticalGroup(
            panelTransicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );

        panelPrincipal.add(panelTransicion);
        panelTransicion.setBounds(0, 250, 70, 130);

        lblContadorF.setFont(new java.awt.Font("Montserrat", 1, 15)); // NOI18N
        lblContadorF.setForeground(new java.awt.Color(255, 255, 255));
        lblContadorF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContadorF.setText("0");
        panelPrincipal.add(lblContadorF);
        lblContadorF.setBounds(0, 170, 60, 30);

        flecha1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelPrincipal.add(flecha1);
        flecha1.setBounds(560, 200, 40, 60);

        lblIzq.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelPrincipal.add(lblIzq);
        lblIzq.setBounds(370, 90, 60, 30);

        lblAbajo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelPrincipal.add(lblAbajo);
        lblAbajo.setBounds(180, 200, 50, 60);

        jLabel6.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Final");
        panelPrincipal.add(jLabel6);
        jLabel6.setBounds(0, 140, 70, 30);

        jLabel9.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Inicio");
        panelPrincipal.add(jLabel9);
        jLabel9.setBounds(740, 146, 60, 30);

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
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

    private void buttonRound1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRound1ActionPerformed
        // TODO add your handling code here:
        Menu_Inicial m = new Menu_Inicial();
        m.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_buttonRound1ActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        // TODO add your handling code here:
        guardarReporte();
    }//GEN-LAST:event_btnReporteActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Simulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                simio = new Simulacion();
                simio.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cronometro;
    public static javax.swing.JPanel Empaquetado;
    public static javax.swing.JLayeredPane Final1;
    private javax.swing.JLabel Inicio;
    private Elementos.PanelRound Inventario;
    public static javax.swing.JPanel InventarioE;
    public static javax.swing.JPanel Produccion1;
    public static javax.swing.JPanel Salida1;
    private Elementos.ButtonRound btnReporte;
    private Elementos.ButtonRound buttonRound1;
    public static javax.swing.JPanel conector1;
    private javax.swing.JLabel flecha1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblAbajo;
    public static javax.swing.JLabel lblContadorF;
    private javax.swing.JLabel lblContadorI;
    private javax.swing.JLabel lblIzq;
    private javax.swing.JLabel lblMin;
    private javax.swing.JLabel lblSeg;
    public static javax.swing.JLabel numEmpa;
    public static javax.swing.JLabel numInventario;
    public static javax.swing.JLabel numProd;
    public static javax.swing.JLabel numSalida;
    private javax.swing.JLayeredPane panelPrincipal;
    private Elementos.PanelRound panelRound1;
    private Elementos.PanelRound panelRound2;
    private Elementos.PanelRound panelRound3;
    private Elementos.PanelRound panelRound4;
    private Elementos.PanelRound panelRound5;
    private Elementos.PanelRound panelRound7;
    public static javax.swing.JLayeredPane panelTransicion;
    private javax.swing.JLabel salidaimagen;
    // End of variables declaration//GEN-END:variables
}
