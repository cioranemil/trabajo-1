package ejercicio_propuesto_14;

import Utilidades.UIUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Panel interactivo para el Ejercicio Propuesto No 14.
 * Cálculo del Cuadrado (n^2) y del Cubo (n^3) de un número.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 2.0/2026
 */
public class VentanaEjercicioPropuesto14 extends JPanel {

    private JTextField txtNumero;
    private JLabel lblResCuadrado;
    private JLabel lblResCubo;
    private JLabel lblResRaiz;
    private JTextArea txtResultado;

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

        // ── Tarjeta Centro: Inputs y Resultados ─────────────────────────
        JPanel pnlCentro = new JPanel(new GridLayout(1, 2, 15, 0));
        pnlCentro.setBackground(UIUtils.COLOR_FONDO);

        // Panel Input
        JPanel pnlInput = UIUtils.crearPanelTarjeta("Número de Entrada", UIUtils.COLOR_ACCENTO1);
        pnlInput.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblNum = new JLabel("Ingrese un Número (n):");
        lblNum.setFont(UIUtils.FUENTE_BOLD);
        pnlInput.add(lblNum, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        txtNumero = new JTextField("5", 8);
        UIUtils.estilizarCampoTexto(txtNumero);
        pnlInput.add(txtNumero, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        JButton btnCalcular = new JButton("🔢 Calcur Potencias");
        UIUtils.estilizarBotonAccion(btnCalcular, UIUtils.COLOR_ACCENTO3);
        pnlInput.add(btnCalcular, gbc);

        // Panel Resultados Card
        JPanel pnlResultados = UIUtils.crearPanelTarjeta("Potencias Calculadas", UIUtils.COLOR_ACCENTO2);
        JPanel pnlCards = new JPanel(new GridLayout(3, 1, 8, 8));
        pnlCards.setBackground(UIUtils.COLOR_PANEL);

        lblResCuadrado = crearCard("Cuadrado (n²)", "25.00", UIUtils.COLOR_ACCENTO1);
        lblResCubo = crearCard("Cubo (n³)", "125.00", UIUtils.COLOR_ACCENTO5);
        lblResRaiz = crearCard("Raíz Cuadrada (√n)", "2.24", UIUtils.COLOR_ACCENTO2);

        pnlCards.add(lblResCuadrado);
        pnlCards.add(lblResCubo);
        pnlCards.add(lblResRaiz);
        pnlResultados.add(pnlCards, BorderLayout.CENTER);

        pnlCentro.add(pnlInput);
        pnlCentro.add(pnlResultados);

        // ── Tarjeta Sur: Consola ────────────────────────────────────────
        JPanel pnlSur = UIUtils.crearPanelTarjeta("Consola de Resultados", UIUtils.COLOR_TEXTO_DIM);
        txtResultado = new JTextArea(5, 40);
        pnlSur.add(UIUtils.crearConsolaEstilizada(txtResultado), BorderLayout.CENTER);

        add(pnlEnunciado, BorderLayout.NORTH);
        add(pnlCentro, BorderLayout.CENTER);
        add(pnlSur, BorderLayout.SOUTH);

        btnCalcular.addActionListener(e -> calcular());
        calcular();
    }

    private JLabel crearCard(String titulo, String val, Color c) {
        JLabel lbl = new JLabel("<html><body><b>" + titulo + ":</b> <font size='5' color='" +
            toHex(c) + "'>" + val + "</font></body></html>");
        lbl.setOpaque(true);
        lbl.setBackground(UIUtils.COLOR_CONSOLE_BG);
        lbl.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(c, 1, true),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
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

            lblResCuadrado.setText("<html><body><b>Cuadrado (n²):</b> <font size='5' color='" +
                toHex(UIUtils.COLOR_ACCENTO1) + "'>" + String.format("%.2f", ej.getCuadrado()) + "</font></body></html>");
            lblResCubo.setText("<html><body><b>Cubo (n³):</b> <font size='5' color='" +
                toHex(UIUtils.COLOR_ACCENTO5) + "'>" + String.format("%.2f", ej.getCubo()) + "</font></body></html>");
            
            double r = ej.getRaizCuadrada();
            String raizStr = Double.isNaN(r) ? "N/A (Imaginaria)" : String.format("%.4f", r);
            lblResRaiz.setText("<html><body><b>Raíz Cuadrada (√n):</b> <font size='5' color='" +
                toHex(UIUtils.COLOR_ACCENTO2) + "'>" + raizStr + "</font></body></html>");

            StringBuilder sb = new StringBuilder();
            sb.append(">>> CÓMPUTO DE POTENCIAS DE UN NÚMERO <<<\n");
            sb.append(String.format("  • Valor de Entrada (n)  = %.4f\n", ej.getNumero()));
            sb.append(String.format("  • Cuadrado (n * n)       = %.4f\n", ej.getCuadrado()));
            sb.append(String.format("  • Cubo (n * n * n)     = %.4f\n", ej.getCubo()));
            if (!Double.isNaN(r)) {
                sb.append(String.format("  • Raíz Cuadrada (√n)    = %.4f\n", r));
            }
            txtResultado.setText(sb.toString());

        } catch (Exception ex) {
            txtResultado.setText("ERROR: Ingrese un valor numérico válido.");
        }
    }
}
