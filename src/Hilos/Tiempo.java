package Hilos;

import javax.swing.JLabel;

public class Tiempo extends Thread {

    private JLabel rotulo;
    private JLabel rotuloS;
    private boolean activar;

    public Tiempo(JLabel rotulo, JLabel rotuloS, boolean activar) {
        setRotulo(rotulo);
        this.activar = activar;
        setRotuloS(rotuloS);
    }

    public JLabel getRotulo() {
        return rotulo;
    }

    public void setRotulo(JLabel rotulo) {
        this.rotulo = rotulo;
    }

    public JLabel getRotuloS() {
        return rotuloS;
    }

    public void setRotuloS(JLabel rotuloS) {
        this.rotuloS = rotuloS;
    }

    public boolean isActivar() {
        return activar;
    }

    public void setActivar(boolean activar) {
        this.activar = activar;
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
            
            getRotulo().setText(String.format("%02d", min));
            getRotuloS().setText(String.format("%02d", segundos));
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
