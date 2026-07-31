package ejercicio_propuesto_17;

import Utilidades.UIUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Panel interactivo para el Ejercicio Propuesto No 17.
 * Cálculo del Área y Perímetro (Longitud) con dibujo geométrico interactivo en Java2D.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 2.0/2026
 */
public class VentanaEjercicioPropuesto17 extends JPanel {

    private JTextField txtRadio;
    private JLabel lblResArea;
    private JLabel lblResLongitud;
    private JLabel lblResDiametro;
    private JTextArea txtResultado;
    private GraficoCirculo canvasCirculo;

    public VentanaEjercicioPropuesto17() {
        setLayout(new BorderLayout(15, 15));
        setBackground(UIUtils.COLOR_FONDO);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // ── Tarjeta Norte: Enunciado ──────────────────────────────────
        JPanel pnlEnunciado = UIUtils.crearPanelTarjeta("Ejercicio Propuesto No 17: Área y Perímetro del Círculo (Pág 50)", UIUtils.COLOR_ACCENTO4);
        JTextArea txtEnunciado = new JTextArea(
            "Dado el radio de un círculo, elaborar un algoritmo que obtenga el área y la longitud de la circunferencia.\n\n" +
            "Fórmulas Geométricas:\n" +
            "  • Área del Círculo                = π · r²\n" +
            "  • Longitud de la Circunferencia  = 2 · π · r\n" +
            "  • Diámetro                        = 2 · r"
        );
        txtEnunciado.setFont(UIUtils.FUENTE_NORMAL);
        txtEnunciado.setForeground(UIUtils.COLOR_TEXTO);
        txtEnunciado.setBackground(UIUtils.COLOR_PANEL);
        txtEnunciado.setEditable(false);
        pnlEnunciado.add(txtEnunciado, BorderLayout.CENTER);

        // ── Tarjeta Centro: Inputs, Gráfico Java2D y Resultados ─────────
        JPanel pnlCentro = new JPanel(new GridLayout(1, 3, 12, 0));
        pnlCentro.setBackground(UIUtils.COLOR_FONDO);

        // Panel 1: Input Form
        JPanel pnlInput = UIUtils.crearPanelTarjeta("Parámetros", UIUtils.COLOR_ACCENTO1);
        pnlInput.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblRad = new JLabel("Radio (r) [cm]:");
        lblRad.setFont(UIUtils.FUENTE_BOLD);
        pnlInput.add(lblRad, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        txtRadio = new JTextField("5.0", 6);
        UIUtils.estilizarCampoTexto(txtRadio);
        pnlInput.add(txtRadio, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        JButton btnCalcular = new JButton("📐 Calcular Geometría");
        UIUtils.estilizarBotonAccion(btnCalcular, UIUtils.COLOR_ACCENTO4);
        pnlInput.add(btnCalcular, gbc);

        // Panel 2: Canvas Visual Java2D
        JPanel pnlCanvasHolder = UIUtils.crearPanelTarjeta("Visualización Geométrica", UIUtils.COLOR_ACCENTO4);
        canvasCirculo = new GraficoCirculo();
        pnlCanvasHolder.add(canvasCirculo, BorderLayout.CENTER);

        // Panel 3: Cards Resultados
        JPanel pnlResultados = UIUtils.crearPanelTarjeta("Resultados", UIUtils.COLOR_ACCENTO2);
        JPanel pnlCards = new JPanel(new GridLayout(3, 1, 8, 8));
        pnlCards.setBackground(UIUtils.COLOR_PANEL);

        lblResArea = crearCard("Área (π·r²)", "78.5398 cm²", UIUtils.COLOR_ACCENTO1);
        lblResLongitud = crearCard("Longitud (2·π·r)", "31.4159 cm", UIUtils.COLOR_ACCENTO2);
        lblResDiametro = crearCard("Diámetro (2r)", "10.00 cm", UIUtils.COLOR_ACCENTO3);

        pnlCards.add(lblResArea);
        pnlCards.add(lblResLongitud);
        pnlCards.add(lblResDiametro);
        pnlResultados.add(pnlCards, BorderLayout.CENTER);

        pnlCentro.add(pnlInput);
        pnlCentro.add(pnlCanvasHolder);
        pnlCentro.add(pnlResultados);

        // ── Tarjeta Sur: Consola ────────────────────────────────────────
        JPanel pnlSur = UIUtils.crearPanelTarjeta("Consola de Cálculo Geométrico", UIUtils.COLOR_TEXTO_DIM);
        txtResultado = new JTextArea(4, 40);
        pnlSur.add(UIUtils.crearConsolaEstilizada(txtResultado), BorderLayout.CENTER);

        add(pnlEnunciado, BorderLayout.NORTH);
        add(pnlCentro, BorderLayout.CENTER);
        add(pnlSur, BorderLayout.SOUTH);

        btnCalcular.addActionListener(e -> calcular());
        txtRadio.addActionListener(e -> calcular());
        calcular();
    }

    private JLabel crearCard(String titulo, String val, Color c) {
        JLabel lbl = new JLabel("<html><body><b>" + titulo + ":</b><br><font size='4' color='" +
            toHex(c) + "'>" + val + "</font></body></html>");
        lbl.setOpaque(true);
        lbl.setBackground(UIUtils.COLOR_CONSOLE_BG);
        lbl.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(c, 1, true),
            BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
        return lbl;
    }

    private String toHex(Color c) {
        return String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
    }

    private void calcular() {
        try {
            double r = Double.parseDouble(txtRadio.getText().trim());
            EjercicioPropuesto17 ej = new EjercicioPropuesto17(r);

            canvasCirculo.setRadio(r);

            lblResArea.setText("<html><body><b>Área (π·r²):</b><br><font size='4' color='" +
                toHex(UIUtils.COLOR_ACCENTO1) + "'>" + String.format("%.4f cm²", ej.getArea()) + "</font></body></html>");
            lblResLongitud.setText("<html><body><b>Longitud (2·π·r):</b><br><font size='4' color='" +
                toHex(UIUtils.COLOR_ACCENTO2) + "'>" + String.format("%.4f cm", ej.getLongitudCircunferencia()) + "</font></body></html>");
            lblResDiametro.setText("<html><body><b>Diámetro (2r):</b><br><font size='4' color='" +
                toHex(UIUtils.COLOR_ACCENTO3) + "'>" + String.format("%.2f cm", ej.getDiametro()) + "</font></body></html>");

            StringBuilder sb = new StringBuilder();
            sb.append(">>> CÓMPUTO GEOMÉTRICO DEL CÍRCULO <<<\n");
            sb.append(String.format("  • Radio (r)                      = %.4f cm\n", ej.getRadio()));
            sb.append(String.format("  • Diámetro (2r)                  = %.4f cm\n", ej.getDiametro()));
            sb.append(String.format("  • ÁREA DEL CÍRCULO (π · r²)       = %.6f cm²\n", ej.getArea()));
            sb.append(String.format("  • LONGITUD CIRCUNFERENCIA (2πr)  = %.6f cm\n", ej.getLongitudCircunferencia()));
            txtResultado.setText(sb.toString());

        } catch (Exception ex) {
            txtResultado.setText("ERROR: Ingrese un número positivo válido para el radio.");
        }
    }
}
