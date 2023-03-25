package Hilos;

import Elementos.SVGImages;
import Interfaz.Menu_Inicial;
import Interfaz.Simulacion;
import static Interfaz.Simulacion.InventarioE;
import static Interfaz.Simulacion.numInventario;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

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
    private boolean cambio5 = false;
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
        label.setBounds(755, 210, 50, 50);
        label.setVisible(true);
        LIMITE_INV = Menu_Inicial.tiempoI * 1000;
        LIMITE_PROD = Menu_Inicial.tiempoP * 1000;
        LIMITE_EMPAQUETADO = Menu_Inicial.tiempoE * 1000;
        LIMITE_SALIDA = Menu_Inicial.tiempoS * 1000;
        label.cambiar();
        this.crono = crono;
        this.varInventario = i;
    }

    public void run() {
        while (label.isVisible()) {
            Component[] inicio = Simulacion.InventarioE.getComponents();
            Simulacion.numInventario.setText(inicio.length + "");
            // Obtiene la posición actual del JLabel.
            int currentY = label.getY();
            if (label.getY() <= 310 && primero) {
                label.setLocation(label.getX(), currentY + 10);
            }

            if (label.getY() == 310 && lateral) {
                label.setLocation(label.getX() - 10, currentY);
                if (label.getX() <= 730 && contador < 15) {
                    casos(1);
                }
            }
            if (cambio1) {
                Component[] components = Simulacion.InventarioE.getComponents();
                for (Component component : components) {
                    if (component.equals(label)) {
                        casos(2);
                    }
                }
            } else if (cambio2) {
                Component[] components = Simulacion.Produccion1.getComponents();
                for (Component component : components) {
                    if (component.equals(label)) {
                        casos(3);
                    }
                }
            } else if (cambio3) {
                Component[] components = Simulacion.Empaquetado.getComponents();
                for (Component component : components) {
                    if (component.equals(label)) {
                        casos(4);
                    }
                }
            } else if (cambio4) {
                Component[] components = Simulacion.Salida1.getComponents();
                for (Component component : components) {
                    if (component.equals(label)) {
                        casos(5);
                    }
                }
            } else if (cambio5) {
                int currentX4 = label.getX();
                if (currentX4 <= 20) {
                    label.setLocation(label.getX(), label.getY() - 10);
                    if (label.getY() <= 0) {
                        label.setVisible(false);
                        Component[] c5 = Simulacion.panelTransicion.getComponents();
                        Simulacion.lblContadorF.setText(c5.length + "");
                        if (c5.length == 30) {
                            Simulacion.bloqueoBTN = true;
                            this.interrupt();
                            JOptionPane.showMessageDialog(null, "La simulación ha finalizado","Finalizado",JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
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
                lateral = false;
                Simulacion.InventarioE.revalidate();
                Simulacion.InventarioE.repaint();
                panelP.revalidate();
                panelP.repaint();

                primero = false;
                cambio1 = true;
                try {
                    panelP.remove(label);
                    Simulacion.InventarioE.add(label);

                    Simulacion.InventarioE.revalidate();
                    Simulacion.InventarioE.repaint();
                    panelP.revalidate();
                    panelP.repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Component[] components1 = InventarioE.getComponents();
                numInventario.setText(components1.length + "");

                try {
                    Thread.sleep(LIMITE_INV - 280);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                label.T1();
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                cambio2 = true;
                cambio1 = false;
                Simulacion.flecha1.cambiFlecha1();
                Simulacion.InventarioE.remove(label);
                Simulacion.conector1.add(label);
                Simulacion.InventarioE.revalidate();
                Simulacion.InventarioE.repaint();
                Component[] components2 = Simulacion.InventarioE.getComponents();
                Simulacion.numInventario.setText(components2.length + "");
                try {
                    Thread.sleep(80);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Simulacion.conector1.remove(label);
                Simulacion.Produccion1.add(label);
                label.transicionProduccion();
                Component[] c2 = Simulacion.Produccion1.getComponents();
                Simulacion.numProd.setText(c2.length + "");

                try {
                    Thread.sleep(LIMITE_PROD - 280);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                label.T2();
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                cambio2 = false;
                cambio1 = false;
                cambio3 = true;
                Simulacion.lblIzq.cambiFlecha2();
                Simulacion.Produccion1.remove(label);
                Simulacion.conector1.add(label);
                Simulacion.Produccion1.revalidate();
                Simulacion.Produccion1.repaint();
                Component[] components3 = Simulacion.Produccion1.getComponents();
                Simulacion.numProd.setText(components3.length + "");
                try {
                    Thread.sleep(80);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Simulacion.conector1.remove(label);
                Simulacion.Empaquetado.add(label);
                label.transicionEmpaquetado();
                int c3 = Simulacion.Empaquetado.getComponentCount();
                Simulacion.numEmpa.setText(c3 + "");

                try {
                    Thread.sleep(LIMITE_EMPAQUETADO - 280);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                label.T3();
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                cambio2 = false;
                cambio1 = false;
                cambio3 = false;
                cambio4 = true;
                Simulacion.lblAbajo.cambiFlecha3();
                Simulacion.Empaquetado.remove(label);
                Simulacion.conector1.add(label);
                Simulacion.Empaquetado.revalidate();
                Simulacion.Empaquetado.repaint();
                Component[] components4 = Simulacion.Empaquetado.getComponents();
                Simulacion.numEmpa.setText(components4.length + "");
                try {
                    Thread.sleep(80);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Simulacion.conector1.remove(label);
                Simulacion.Salida1.add(label);
                label.transicionSalida();
                int c4 = Simulacion.Salida1.getComponentCount();
                Simulacion.numSalida.setText(c4 + "");

                try {
                    Thread.sleep(LIMITE_SALIDA-200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                label.T4();
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 5:
                cambio2 = false;
                cambio1 = false;
                cambio3 = false;
                cambio4 = false;
                cambio5 = true;
                Simulacion.Salida1.remove(label);
                Simulacion.panelTransicion.add(label);
                Simulacion.Salida1.revalidate();
                Simulacion.Salida1.repaint();
                Component[] components5 = Simulacion.Salida1.getComponents();
                Simulacion.numSalida.setText(components5.length + "");
                int c5 = Simulacion.panelTransicion.getComponentCount();
                Simulacion.numSalida.setText(components5.length + "");
                if (c5 >= 30) {
                    Simulacion.t.apagar();
                }
                label.setBounds(60, 90, 50, 50);
                label.repaint();
                break;
            default:
                throw new AssertionError();
        }
    }
}
