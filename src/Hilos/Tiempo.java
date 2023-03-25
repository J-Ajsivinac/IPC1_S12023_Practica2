package Hilos;

import javax.swing.JLabel;

public class Tiempo extends Thread {

    JLabel rotulo;
    JLabel rotuloS;
    public static boolean activar;

    public Tiempo(JLabel rotulo, JLabel rotuloS, boolean activar) {
        this.rotulo = rotulo;
        this.activar = activar;
        this.rotuloS = rotuloS;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(710);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int min = 0;
        int segundos = 0;
        while (activar) {
            
            rotulo.setText(String.format("%02d", min));
            rotuloS.setText(String.format("%02d", segundos));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            segundos++;
            if (segundos == 60) {
                segundos = 0;
                min++;
            }

        }

    }

    public void apagar() {
        activar = false;
    }

    public void activar() {
        activar = true;
    }
}
