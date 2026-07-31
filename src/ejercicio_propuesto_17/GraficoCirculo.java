package ejercicio_propuesto_17;

import Utilidades.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

/**
 * Componente Canvas Java2D para la representación visual interactiva a escala del círculo.
 * Renderiza el radio, diámetro, área sombreada y cotas geométricas en tiempo real.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 2.0/2026
 */
public class GraficoCirculo extends JPanel {

    private double radio = 5.0;

    public GraficoCirculo() {
        setBackground(UIUtils.COLOR_CONSOLE_BG);
        setBorder(BorderFactory.createLineBorder(UIUtils.COLOR_ACCENTO4, 1, true));
        setPreferredSize(new Dimension(280, 220));
    }

    public void setRadio(double radio) {
        this.radio = Math.max(0.1, radio);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int padding = 35;

        double maxRadioVisual = Math.min(width, height) / 2.0 - padding;
        double scale = maxRadioVisual / Math.max(10.0, radio);
        double rPixels = Math.min(maxRadioVisual, radio * scale);

        double centerX = width / 2.0;
        double centerY = height / 2.0;

        // 1. Dibujar Área Sombreada del Círculo
        Shape circulo = new Ellipse2D.Double(centerX - rPixels, centerY - rPixels, rPixels * 2, rPixels * 2);
        g2d.setColor(new Color(243, 139, 168, 40)); // Accento con transparencia
        g2d.fill(circulo);

        // 2. Borde de la Circunferencia
        g2d.setStroke(new BasicStroke(2.5f));
        g2d.setColor(UIUtils.COLOR_ACCENTO4);
        g2d.draw(circulo);

        // 3. Dibujar Centro
        g2d.setColor(UIUtils.COLOR_ACCENTO3);
        g2d.fill(new Ellipse2D.Double(centerX - 4, centerY - 4, 8, 8));

        // 4. Dibujar Línea de Radio
        g2d.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{6, 4}, 0));
        g2d.setColor(UIUtils.COLOR_ACCENTO1);
        g2d.draw(new Line2D.Double(centerX, centerY, centerX + rPixels, centerY));

        // 5. Etiquetas de Cotas
        g2d.setFont(UIUtils.FUENTE_BOLD);
        g2d.setColor(UIUtils.COLOR_TEXTO);
        g2d.drawString(String.format("r = %.2f cm", radio), (float) (centerX + rPixels / 2.0 - 20), (float) (centerY - 8));
        
        g2d.setColor(UIUtils.COLOR_TEXTO_DIM);
        g2d.drawString(String.format("Área: %.2f cm²", Math.PI * radio * radio), 12, height - 12);
    }
}
