package ejercicio_propuesto_12;

import Utilidades.UIUtils;

import javax.swing.*;
import java.awt.*;

/**
 * Componente Canvas Java2D para la representación gráfica de la proporción de Salario Neto y Retención.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 2.0/2026
 */
public class GraficoNomina extends JPanel {

    private double salarioBruto = 240000;
    private double retencion = 30000;
    private double salarioNeto = 210000;

    public GraficoNomina() {
        setBackground(UIUtils.COLOR_CONSOLE_BG);
        setBorder(BorderFactory.createLineBorder(UIUtils.COLOR_ACCENTO2, 1, true));
        setPreferredSize(new Dimension(280, 220));
    }

    public void setValores(double salarioBruto, double retencion, double salarioNeto) {
        this.salarioBruto = Math.max(1, salarioBruto);
        this.retencion = Math.max(0, retencion);
        this.salarioNeto = Math.max(0, salarioNeto);
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

        int barWidth = 60;
        int maxBarHeight = height - 80;

        double pctNeto = salarioNeto / salarioBruto;
        double pctRet = retencion / salarioBruto;

        int hNeto = (int) (maxBarHeight * pctNeto);
        int hRet = (int) (maxBarHeight * pctRet);

        int xNeto = width / 2 - barWidth - 20;
        int xRet = width / 2 + 20;

        int yBase = height - 40;

        // Dibujar Barra Salario Neto (Verde Accento)
        g2d.setColor(UIUtils.COLOR_ACCENTO2);
        g2d.fillRect(xNeto, yBase - hNeto, barWidth, hNeto);
        g2d.setColor(UIUtils.COLOR_ACCENTO2.brighter());
        g2d.drawRect(xNeto, yBase - hNeto, barWidth, hNeto);

        // Dibujar Barra Retención (Rosa Accento)
        g2d.setColor(UIUtils.COLOR_ACCENTO4);
        g2d.fillRect(xRet, yBase - hRet, barWidth, hRet);
        g2d.setColor(UIUtils.COLOR_ACCENTO4.brighter());
        g2d.drawRect(xRet, yBase - hRet, barWidth, hRet);

        // Leyendas de texto
        g2d.setFont(UIUtils.FUENTE_BOLD);
        g2d.setColor(UIUtils.COLOR_TEXTO);
        g2d.drawString("Neto", xNeto + 14, yBase + 18);
        g2d.drawString(String.format("%.0f%%", pctNeto * 100), xNeto + 12, yBase - hNeto - 8);

        g2d.drawString("Retención", xRet + 2, yBase + 18);
        g2d.drawString(String.format("%.1f%%", pctRet * 100), xRet + 10, yBase - hRet - 8);

        g2d.setColor(UIUtils.COLOR_TEXTO_DIM);
        g2d.drawString("Distribución del Salario Bruto", 20, 22);
    }
}
