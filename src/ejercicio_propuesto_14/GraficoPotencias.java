package ejercicio_propuesto_14;

import Utilidades.UIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

/**
 * Componente Canvas Java2D que renderiza la cuadrícula plana del área n^2
 * y la proyección geométrica tridimensional isométrica del volumen del cubo n^3.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 3.0/2026
 */
public class GraficoPotencias extends JPanel {

    private double numero = 5.0;

    public GraficoPotencias() {
        setBackground(UIUtils.COLOR_CONSOLE_BG);
        setBorder(BorderFactory.createLineBorder(UIUtils.COLOR_ACCENTO3, 1, true));
        setPreferredSize(new Dimension(280, 220));
    }

    public void setNumero(double numero) {
        this.numero = Math.max(0.1, numero);
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

        // 1. Dibujar Proyección Isométrica de Cubo n^3 (Lado Derecho)
        double size = 65.0;
        double dx = size * 0.5;
        double dy = size * 0.3;

        double originX = width * 0.65;
        double originY = height * 0.55;

        // Cara Frontal (Rosa Accento)
        g2d.setColor(new Color(243, 139, 168, 60));
        g2d.fill(new Rectangle2D.Double(originX, originY - size, size, size));
        g2d.setColor(UIUtils.COLOR_ACCENTO4);
        g2d.setStroke(new BasicStroke(2.0f));
        g2d.draw(new Rectangle2D.Double(originX, originY - size, size, size));

        // Cara Superior (Azul Accento)
        Path2D topFace = new Path2D.Double();
        topFace.moveTo(originX, originY - size);
        topFace.lineTo(originX + dx, originY - size - dy);
        topFace.lineTo(originX + size + dx, originY - size - dy);
        topFace.lineTo(originX + size, originY - size);
        topFace.closePath();

        g2d.setColor(new Color(137, 180, 250, 60));
        g2d.fill(topFace);
        g2d.setColor(UIUtils.COLOR_ACCENTO1);
        g2d.draw(topFace);

        // Cara Lateral (Verde Accento)
        Path2D sideFace = new Path2D.Double();
        sideFace.moveTo(originX + size, originY - size);
        sideFace.lineTo(originX + size + dx, originY - size - dy);
        sideFace.lineTo(originX + size + dx, originY - dy);
        sideFace.lineTo(originX + size, originY);
        sideFace.closePath();

        g2d.setColor(new Color(166, 227, 161, 60));
        g2d.fill(sideFace);
        g2d.setColor(UIUtils.COLOR_ACCENTO2);
        g2d.draw(sideFace);

        // 2. Cuadrícula Plana n^2 (Lado Izquierdo)
        double sqSize = 55.0;
        double sqX = width * 0.15;
        double sqY = height * 0.35;

        g2d.setColor(new Color(249, 226, 175, 50));
        g2d.fill(new Rectangle2D.Double(sqX, sqY, sqSize, sqSize));
        g2d.setColor(UIUtils.COLOR_ACCENTO3);
        g2d.draw(new Rectangle2D.Double(sqX, sqY, sqSize, sqSize));

        // Línea divisoria en n^2
        g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{3, 3}, 0));
        g2d.draw(new Line2D.Double(sqX + sqSize / 2.0, sqY, sqX + sqSize / 2.0, sqY + sqSize));
        g2d.draw(new Line2D.Double(sqX, sqY + sqSize / 2.0, sqX + sqSize, sqY + sqSize / 2.0));

        // Leyendas
        g2d.setFont(UIUtils.FUENTE_BOLD);
        g2d.setColor(UIUtils.COLOR_TEXTO);
        g2d.drawString(String.format("Área n² = %.2f", numero * numero), (int) sqX - 5, (int) sqY + (int) sqSize + 20);
        g2d.drawString(String.format("Volumen n³ = %.2f", numero * numero * numero), (int) originX - 10, (int) originY + 25);
    }
}
