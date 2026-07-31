package ejercicio_propuesto_17;

import Utilidades.ManejadorPersistencia;
import Utilidades.UIUtils;
import Utilidades.ValorInvalidoException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Panel interactivo para el Ejercicio Propuesto No 17.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 3.0/2026
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

        // Enunciado
        JPanel pnlEnunciado = UIUtils.crearPanelTarjeta("Ejercicio Propuesto No 17: Área y Perímetro del Círculo (Pág 50)", UIUtils.COLOR_ACCENTO4);
        JTextArea txtEnunciado = new JTextArea(
            "Dado el radio de un círculo, elaborar un algoritmo que obtenga el área y la longitud de la circunferencia.\n\n" +
            "Fórmulas Geométricas:\n" +
            "  • Área del Círculo                = PI * r^2\n" +
            "  • Longitud de la Circunferencia  = 2 * PI * r\n" +
            "  • Diámetro                        = 2 * r"
        );
        txtEnunciado.setFont(UIUtils.FUENTE_NORMAL);
        txtEnunciado.setForeground(UIUtils.COLOR_TEXTO);
        txtEnunciado.setBackground(UIUtils.COLOR_PANEL);
        txtEnunciado.setEditable(false);
        pnlEnunciado.add(txtEnunciado, BorderLayout.CENTER);

        // Inputs y Canvas
        JPanel pnlCentro = new JPanel(new GridLayout(1, 3, 12, 0));
        pnlCentro.setBackground(UIUtils.COLOR_FONDO);

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
        JButton btnCalcular = new JButton("Calcular Geometría");
        UIUtils.estilizarBotonAccion(btnCalcular, UIUtils.COLOR_ACCENTO4);
        pnlInput.add(btnCalcular, gbc);

        // Canvas
        JPanel pnlCanvasHolder = UIUtils.crearPanelTarjeta("Visualización Geométrica", UIUtils.COLOR_ACCENTO4);
        canvasCirculo = new GraficoCirculo();
        pnlCanvasHolder.add(canvasCirculo, BorderLayout.CENTER);

        // Cards
        JPanel pnlResultados = UIUtils.crearPanelTarjeta("Resultados", UIUtils.COLOR_ACCENTO2);
        JPanel pnlCards = new JPanel(new GridLayout(3, 1, 8, 8));
        pnlCards.setBackground(UIUtils.COLOR_PANEL);

        lblResArea = crearCard("Área (PI*r^2)", "78.5398 cm²", UIUtils.COLOR_ACCENTO1);
        lblResLongitud = crearCard("Longitud (2*PI*r)", "31.4159 cm", UIUtils.COLOR_ACCENTO2);
        lblResDiametro = crearCard("Diámetro (2r)", "10.00 cm", UIUtils.COLOR_ACCENTO3);

        pnlCards.add(lblResArea);
        pnlCards.add(lblResLongitud);
        pnlCards.add(lblResDiametro);
        pnlResultados.add(pnlCards, BorderLayout.CENTER);

        pnlCentro.add(pnlInput);
        pnlCentro.add(pnlCanvasHolder);
        pnlCentro.add(pnlResultados);

        // Consola
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
            String strR = txtRadio.getText().trim();
            double r;
            try {
                r = Double.parseDouble(strR);
            } catch (NumberFormatException nfe) {
                throw new ValorInvalidoException("El campo Radio debe ser un número entero o decimal válido.", "Radio (r)");
            }

            if (r <= 0) {
                throw new ValorInvalidoException("El radio del círculo debe ser un valor positivo mayor a cero.", "Radio (r)");
            }

            EjercicioPropuesto17 ej = new EjercicioPropuesto17(r);
            canvasCirculo.setRadio(r);

            lblResArea.setText("<html><body><b>Área (PI*r^2):</b><br><font size='4' color='" +
                toHex(UIUtils.COLOR_ACCENTO1) + "'>" + String.format("%.4f cm²", ej.getArea()) + "</font></body></html>");
            lblResLongitud.setText("<html><body><b>Longitud (2*PI*r):</b><br><font size='4' color='" +
                toHex(UIUtils.COLOR_ACCENTO2) + "'>" + String.format("%.4f cm", ej.getLongitudCircunferencia()) + "</font></body></html>");
            lblResDiametro.setText("<html><body><b>Diámetro (2r):</b><br><font size='4' color='" +
                toHex(UIUtils.COLOR_ACCENTO3) + "'>" + String.format("%.2f cm", ej.getDiametro()) + "</font></body></html>");

            StringBuilder sb = new StringBuilder();
            sb.append(">>> CÓMPUTO GEOMÉTRICO DEL CÍRCULO <<<\n");
            sb.append(String.format("  • Radio (r)                      = %.4f cm\n", ej.getRadio()));
            sb.append(String.format("  • Diámetro (2r)                  = %.4f cm\n", ej.getDiametro()));
            sb.append(String.format("  • ÁREA DEL CÍRCULO (PI * r^2)    = %.6f cm²\n", ej.getArea()));
            sb.append(String.format("  • LONGITUD CIRCUNFERENCIA (2PIr) = %.6f cm\n", ej.getLongitudCircunferencia()));
            txtResultado.setText(sb.toString());

            ManejadorPersistencia.guardarRegistro("Ejercicio Propuesto 17",
                "r=" + r + " cm",
                String.format("Área=%.4f cm², Perímetro=%.4f cm", ej.getArea(), ej.getLongitudCircunferencia()));

        } catch (ValorInvalidoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Validación", JOptionPane.ERROR_MESSAGE);
            txtResultado.setText("ERROR DE VALIDACIÓN: " + ex.getMessage());
        } catch (Exception ex) {
            txtResultado.setText("ERROR INESPERADO: " + ex.getMessage());
        }
    }
}
