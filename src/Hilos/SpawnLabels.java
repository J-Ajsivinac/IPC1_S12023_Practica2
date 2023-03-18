package Hilos;

import Elementos.SVGImages;
import Interfaz.Simulacion;
import java.awt.Component;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private boolean cambio1 = false;
    int contador;

    public SpawnLabels(JLayeredPane panelP) {
        this.panelP = panelP;
        label = new SVGImages();
        label.setSvgImages("img/pelota.svg", 25, 25);
        panelP.add(label, JLayeredPane.PALETTE_LAYER);
        label.setBounds(730, 210, 50, 50);
        label.setVisible(true);
    }

    public void run() {
        while (Tiempo.activar) {
            // Obtiene la posición actual del JLabel.
            int currentY = label.getY();

            System.out.println(contador);
            if (label.getY() <= 340 && primero) {
                label.setLocation(label.getX(), currentY + 10);
            }

            if (label.getY() == 340) {
                label.setLocation(label.getX() - 10, currentY);
                if (label.getX() <= 710 && contador < 15) {
                    casos(1);
                }
            }

            if (cambio1) {
                Component[] components = Simulacion.InventarioE.getComponents();
                for (Component component : components) {
                    if (component.equals(label)) {
                        Simulacion.InventarioE.remove(label);
                        Simulacion.InventarioE.revalidate();
                        Simulacion.InventarioE.repaint();
                        panelP.add(label);
                        label.setBounds(430, 250, 50, 50);
                        label.repaint();
                    }
                }
                int currentY1 = label.getY();
                label.setLocation(label.getX(), currentY1 - 10);
                label.repaint();
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
                try {
                    Thread.sleep(401);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                primero = false;
                cambio1 = true;
                //casos(2);
                break;
            case 2:

                break;
            default:
                throw new AssertionError();
        }
    }

}
