package Elementos;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.util.function.Function;
import javax.swing.JLabel;

public class SVGImages extends JLabel {

    private FlatSVGIcon svgIcono;
    private int inconColor = 0;
    public void setSvgImages(String imagen, int width, int height) {
        svgIcono = new FlatSVGIcon(imagen, width, height);
        svgIcono.setColorFilter(new FlatSVGIcon.ColorFilter(new Function<Color, Color>() {
            @Override
            public Color apply(Color t) {
                return new Color(177, 179, 183);
            }

        }));
        setIcon(svgIcono);
        cambiar();
    }

    public void cambiar() {
        Thread moveThread2 = new Thread(() -> {
            while (this.isVisible()) {
                svgIcono.setColorFilter(new FlatSVGIcon.ColorFilter(new Function<Color, Color>() {
                    @Override
                    public Color apply(Color t) {
                        
                        if (inconColor >= 0 && inconColor <= 50) {
                            inconColor += 1;
                            inconColor %= 255;
                        }
                        return Color.getHSBColor(inconColor / 255f, 1, 1);
                    }
                    
                }));
                SVGImages.this.repaint();
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        moveThread2.start();
    }
}
