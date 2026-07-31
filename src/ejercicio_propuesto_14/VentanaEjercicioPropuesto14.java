package ejercicio_propuesto_14;

import Utilidades.UIUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Panel interactivo para el Ejercicio Propuesto No 14.
 * Cálculo del Cuadrado (n^2) y del Cubo (n^3) con proyección 3D Java2D.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 3.0/2026
 */
public class VentanaEjercicioPropuesto14 extends JPanel {

    private JTextField txtNumero;
    private JLabel lblResCuadrado;
    private JLabel lblResCubo;
    private JLabel lblResRaiz;
    private JTextArea txtResultado;
    private GraficoPotencias canvasPotencias;

    public VentanaEjercicioPropuesto14() {
        setLayout(new BorderLayout(15, 15));
        setBackground(UIUtils.COLOR_FONDO);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // ── Tarjeta Norte: Enunciado ──────────────────────────────────
        JPanel pnlEnunciado = UIUtils.crearPanelTarjeta("Ejercicio Propuesto No 14: Cuadrado y Cubo (Pág 50)", UIUtils.COLOR_ACCENTO3);
        JTextArea txtEnunciado = new JTextArea(
            "Elaborar un algoritmo que lea un número y obtenga su cuadrado y su cubo.\n\n" +
            "Fórmulas:\n" +
            "  • Cuadrado = n² = n · n\n" +
            "  • Cubo     = n³ = n · n · n"
        );
        txtEnunciado.setFont(UIUtils.FUENTE_NORMAL);
        txtEnunciado.setForeground(UIUtils.COLOR_TEXTO);
        txtEnunciado.setBackground(UIUtils.COLOR_PANEL);
        txtEnunciado.setEditable(false);
        pnlEnunciado.add(txtEnunciado, BorderLayout.CENTER);

        // ── Tarjeta Centro: Inputs, Canvas 3D y Cards ───────────────────
        JPanel pnlCentro = new JPanel(new GridLayout(1, 3, 12, 0));
        pnlCentro.setBackground(UIUtils.COLOR_FONDO);

        // Panel 1: Input Form
        JPanel pnlInput = UIUtils.crearPanelTarjeta("Entrada", UIUtils.COLOR_ACCENTO1);
        pnlInput.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblNum = new JLabel("Número (n):");
        lblNum.setFont(UIUtils.FUENTE_BOLD);
        pnlInput.add(lblNum, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        txtNumero = new JTextField("5", 6);
        UIUtils.estilizarCampoTexto(txtNumero);
        pnlInput.add(txtNumero, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        JButton btnCalcular = new JButton("🔢 Calcular Potencias");
        UIUtils.estilizarBotonAccion(btnCalcular, UIUtils.COLOR_ACCENTO3);
        pnlInput.add(btnCalcular, gbc);

        // Panel 2: Canvas Visual Java2D (Área + Cubo 3D Isométrico)
        JPanel pnlCanvasHolder = UIUtils.crearPanelTarjeta("Visualización Geometría 3D", UIUtils.COLOR_ACCENTO3);
        canvasPotencias = new GraficoPotencias();
        pnlCanvasHolder.add(canvasPotencias, BorderLayout.CENTER);

        // Panel 3: Cards Resultados
        JPanel pnlResultados = UIUtils.crearPanelTarjeta("Potencias", UIUtils.COLOR_ACCENTO2);
        JPanel pnlCards = new JPanel(new GridLayout(3, 1, 8, 8));
        pnlCards.setBackground(UIUtils.COLOR_PANEL);

        lblResCuadrado = crearCard("Cuadrado (n²)", "25.00", UIUtils.COLOR_ACCENTO1);
        lblResCubo = crearCard("Cubo (n³)", "125.00", UIUtils.COLOR_ACCENTO5);
        lblResRaiz = crearCard("Raíz (√n)", "2.24", UIUtils.COLOR_ACCENTO2);

        pnlCards.add(lblResCuadrado);
        pnlCards.add(lblResCubo);
        pnlCards.add(lblResRaiz);
        pnlResultados.add(pnlCards, BorderLayout.CENTER);

        pnlCentro.add(pnlInput);
        pnlCentro.add(pnlCanvasHolder);
        pnlCentro.add(pnlResultados);

        // ── Tarjeta Sur: Consola ────────────────────────────────────────
        JPanel pnlSur = UIUtils.crearPanelTarjeta("Consola de Resultados", UIUtils.COLOR_TEXTO_DIM);
        txtResultado = new JTextArea(4, 40);
        pnlSur.add(UIUtils.crearConsolaEstilizada(txtResultado), BorderLayout.CENTER);

        add(pnlEnunciado, BorderLayout.NORTH);
        add(pnlCentro, BorderLayout.CENTER);
        add(pnlSur, BorderLayout.SOUTH);

        btnCalcular.addActionListener(e -> calcular());
        txtNumero.addActionListener(e -> calcular());
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
            double n = Double.parseDouble(txtNumero.getText().trim());
            EjercicioPropuesto14 ej = new EjercicioPropuesto14(n);

            canvasPotencias.setNumero(n);

            lblResCuadrado.setText("<html><body><b>Cuadrado (n²):</b><br><font size='4' color='" +
                toHex(UIUtils.COLOR_ACCENTO1) + "'>" + String.format("%.2f", ej.getCuadrado()) + "</font></body></html>");
            lblResCubo.setText("<html><body><b>Cubo (n³):</b><br><font size='4' color='" +
                toHex(UIUtils.COLOR_ACCENTO5) + "'>" + String.format("%.2f", ej.getCubo()) + "</font></body></html>");
            
            double r = ej.getRaizCuadrada();
            String raizStr = Double.isNaN(r) ? "N/A (Imaginaria)" : String.format("%.4f", r);
            lblResRaiz.setText("<html><body><b>Raíz (√n):</b><br><font size='4' color='" +
                toHex(UIUtils.COLOR_ACCENTO2) + "'>" + raizStr + "</font></body></html>");

            StringBuilder sb = new StringBuilder();
            sb.append(">>> CÓMPUTO DE POTENCIAS Y VOLUMEN <<<\n");
            sb.append(String.format("  • Entrada (n)      = %.4f\n", ej.getNumero()));
            sb.append(String.format("  • Cuadrado (n²)    = %.4f\n", ej.getCuadrado()));
            sb.append(String.format("  • Cubo (n³)        = %.4f\n", ej.getCubo()));
            if (!Double.isNaN(r)) {
                sb.append(String.format("  • Raíz (√n)        = %.4f\n", r));
            }
            txtResultado.setText(sb.toString());

        } catch (Exception ex) {
            txtResultado.setText("ERROR: Ingrese un valor numérico válido.");
        }
    }
}
