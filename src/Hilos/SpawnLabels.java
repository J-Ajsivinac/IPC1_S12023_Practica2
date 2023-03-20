package Hilos;

import Elementos.SVGImages;
import Interfaz.Simulacion;
import static Interfaz.Simulacion.InventarioE;
import static Interfaz.Simulacion.numInventario;
import java.awt.Component;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class SpawnLabels extends Thread {

    private JLayeredPane panelP;
    private int LIMITE_INV;
    private int LIMITE_PROD;
    private int LIMITE_EMPAQUETADO;
    private int LIMITE_SALIDA;
    private SVGImages label;
    private boolean primero = true;
    private boolean lateral = true;
    private boolean cambio1 = false;
    private boolean cambio2 = false;
    private boolean cambio3 = false;
    private boolean cambio4 = false;
    int contador;
    public Tiempo t;
    private JLabel crono;
    private boolean tiempoC = false;
    int varInventario;
    int contador_final = 1;

    public SpawnLabels(JLayeredPane panelP, JLabel crono, int i) {
        this.panelP = panelP;
        label = new SVGImages();
        label.setSvgImages("img/pelota.svg", 25, 25);
        panelP.add(label, JLayeredPane.PALETTE_LAYER);
        label.setBounds(730, 210, 50, 50);
        label.setVisible(true);
        this.crono = crono;
        this.varInventario = i;
    }

    public void run() {
        while (label.isVisible()) {
            
            Component[] components = Simulacion.InventarioE.getComponents();
            Simulacion.numInventario.setText(components.length + "");
            // Obtiene la posición actual del JLabel.
            int currentY = label.getY();
            if (label.getY() <= 340 && primero) {
                
                label.setLocation(label.getX(), currentY + 10);
            }

            if (label.getY() == 340 && lateral) {
                label.setLocation(label.getX() - 10, currentY);
                if (label.getX() <= 710 && contador < 15) {
                    casos(1);
                    lateral = false;
                }
            }
            //Simulacion.numInventario.setText(varInventario+"");
            if (cambio1) {
                components = Simulacion.InventarioE.getComponents();
                for (Component component : components) {
                    if (component.equals(label)) {
                        movArriba(label);
                        Simulacion.InventarioE.remove(label);
                        Simulacion.InventarioE.revalidate();
                        Simulacion.InventarioE.repaint();
                        panelP.add(label);
                        Component[] components1 = InventarioE.getComponents();
                        numInventario.setText(components1.length + "");
                        label.setBounds(430, 250, 50, 50);
                        label.repaint();
                        label.transicionProduccion();
                    }
                }
                int currentY1 = label.getY();
                label.setLocation(label.getX(), currentY1 - 10);
                label.repaint();
                if (currentY1 <= 180) {
                    cambio1 = false;
                    casos(2);
                    cambio2 = true;
                }
            }
            if (cambio2) {
                components = Simulacion.Produccion1.getComponents();
                for (Component component : components) {
                    if (component.equals(label)) {
                        //movArriba(label);
                        Simulacion.Produccion1.remove(label);
                        Simulacion.Produccion1.revalidate();
                        Simulacion.Produccion1.repaint();
                        panelP.add(label);
                        Component[] components1 = Simulacion.Produccion1.getComponents();
                        Simulacion.numProd.setText(components1.length + "");
                        label.setBounds(410, 40, 50, 50);
                        label.repaint();
                        label.transicionEmpaquetado();
                    }
                }

                int currentX1 = label.getX();
                label.setLocation(label.getX() - 10, label.getY());
                label.repaint();
                if (currentX1 <= 360) {
                    cambio2 = false;
                    cambio1 = false;
                    casos(3);
                    cambio3 = true;
                }
            }
            if (cambio3) {
                Component[] components2 = Simulacion.Empaquetado.getComponents();
                for (Component component : components2) {
                    if (component.equals(label)) {
                        //movArriba(label);
                        Simulacion.Empaquetado.remove(label);
                        Simulacion.Empaquetado.revalidate();
                        Simulacion.Empaquetado.repaint();
                        panelP.add(label);
                        Component[] components1 = Simulacion.Empaquetado.getComponents();
                        Simulacion.numEmpa.setText(components1.length + "");
                        label.setBounds(70, 185, 50, 50);
                        label.repaint();
                        label.transicionSalida();
                    }
                }

                label.setLocation(label.getX(), label.getY() + 10);
                label.repaint();
                int currentY3 = label.getY();
                if (currentY3 >= 260) {
                    cambio1 = false;
                    cambio2 = false;
                    cambio3 = false;
                    casos(4);
                    cambio4 = true;
                }

            }
            if (cambio4) {
                Component[] components3 = Simulacion.Salida1.getComponents();
                for (Component component : components3) {
                    if (component.equals(label)) {
                        //movArriba(label);
                        Simulacion.Salida1.remove(label);
                        Simulacion.Salida1.revalidate();
                        Simulacion.Salida1.repaint();
                        Simulacion.panelTransicion.add(label);
                        Component[] components1 = Simulacion.Salida1.getComponents();
                        Component[] compoF = Simulacion.panelTransicion.getComponents();
                        Simulacion.numSalida.setText(components1.length + "");
                        if (compoF.length >= 15) {
                            Simulacion.t.apagar();
                        }
                        label.setBounds(60, 100, 50, 50);
                        label.repaint();

                    }
                }
                System.out.println(varInventario);
                int currentX4 = label.getX();
                if (currentX4 <= 20) {
                    label.setLocation(label.getX(), label.getY() - 10);
                    if (label.getY() <= 0) {
                        label.setVisible(false);
                        cambio1 = false;
                        cambio2 = false;
                        cambio3 = false;
                        cambio4 = false;
                        casos(5);
                    }
                } else {
                    label.setLocation(label.getX() - 10, label.getY());
                }
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void casos(int opcion) {
        switch (opcion) {
            case 1:

                panelP.remove(label);
                panelP.revalidate();
                Simulacion.InventarioE.add(label);
                label.repaint();
                Component[] components1 = InventarioE.getComponents();
                numInventario.setText(components1.length + "");
                try {
                    Thread.sleep(1000 - 100);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                primero = false;
                cambio1 = true;
                //casos(2);
                break;
            case 2:
                panelP.remove(label);
                panelP.revalidate();
                Simulacion.Produccion1.add(label);
                label.repaint();
                Component[] c2 = Simulacion.Produccion1.getComponents();
                Simulacion.numProd.setText(c2.length + "");
                try {
                    Thread.sleep(2000 - 100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                panelP.remove(label);
                panelP.revalidate();
                Simulacion.Empaquetado.add(label);
                label.repaint();
                Component[] c3 = Simulacion.Empaquetado.getComponents();
                Simulacion.numEmpa.setText(c3.length + "");
                try {
                    Thread.sleep(2000 - 100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                panelP.remove(label);
                panelP.revalidate();
                Simulacion.Salida1.add(label);
                label.repaint();
                Component[] c4 = Simulacion.Salida1.getComponents();
                Simulacion.numSalida.setText(c4.length + "");
                try {
                    Thread.sleep(2000 - 100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 5:
                Component[] c5 = Simulacion.panelTransicion.getComponents();
                Simulacion.lblContadorF.setText(c5.length + "");
                if (c5.length >= 15) {
                    Simulacion.t.apagar();
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    public void movArriba(JLabel la) {
        for (int j = 0; j < 6; j++) {
            Rectangle r = la.getBounds();
            r.y = r.y - j;
            la.setLocation(la.getX(), r.y);
            try {
                Thread.sleep(60);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
