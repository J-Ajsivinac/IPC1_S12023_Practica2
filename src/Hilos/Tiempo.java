package Hilos;

import javax.swing.JLabel;

public class Tiempo extends Thread {

    JLabel rotulo;
    public static boolean activar;

    public Tiempo(JLabel rotulo, boolean activar) {
        this.rotulo = rotulo;
        this.activar = activar;
    }

    @Override
    public void run() {
        int min = 0;
        int segundos = 0;
        while (activar) {
            segundos++;
            if (segundos == 60) {
                segundos = 0;
                min++;
            }
            rotulo.setText(String.format("%02d", min) + ":" + String.format("%02d", segundos));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void apagar(){
        activar = false;
    }
}
