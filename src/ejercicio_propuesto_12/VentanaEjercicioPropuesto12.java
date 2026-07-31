package ejercicio_propuesto_12;

import Utilidades.UIUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Panel interactivo para el Ejercicio Propuesto No 12.
 * Liquidación de Salario Bruto, Retención en la Fuente y Salario Neto.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 2.0/2026
 */
public class VentanaEjercicioPropuesto12 extends JPanel {

    private JTextField txtCodigo;
    private JTextField txtNombres;
    private JTextField txtHoras;
    private JTextField txtValorHora;
    private JTextField txtPorcentajeRetencion;

    private JLabel lblResBruto;
    private JLabel lblResRetencion;
    private JLabel lblResNeto;

    private JTextArea txtResultado;

    public VentanaEjercicioPropuesto12() {
        setLayout(new BorderLayout(15, 15));
        setBackground(UIUtils.COLOR_FONDO);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // ── Tarjeta Norte: Enunciado ──────────────────────────────────
        JPanel pnlEnunciado = UIUtils.crearPanelTarjeta("Ejercicio Propuesto No 12: Liquidación de Salario (Pág 50)", UIUtils.COLOR_ACCENTO2);
        JTextArea txtEnunciado = new JTextArea(
            "Un empleado trabaja determinado número de horas a la semana a una tarifa por hora fija.\n" +
            "El porcentaje de retención en la fuente se aplica sobre el salario bruto.\n\n" +
            "Fórmulas:\n" +
            "  • Salario Bruto = Horas Trabajadas · Valor Hora\n" +
            "  • Retención Fuente = Salario Bruto · (% Retención / 100)\n" +
            "  • Salario Neto = Salario Bruto - Retención Fuente"
        );
        txtEnunciado.setFont(UIUtils.FUENTE_NORMAL);
        txtEnunciado.setForeground(UIUtils.COLOR_TEXTO);
        txtEnunciado.setBackground(UIUtils.COLOR_PANEL);
        txtEnunciado.setEditable(false);
        pnlEnunciado.add(txtEnunciado, BorderLayout.CENTER);

        // ── Tarjeta Centro: Inputs y Desglose ───────────────────────────
        JPanel pnlCentro = new JPanel(new GridLayout(1, 2, 15, 0));
        pnlCentro.setBackground(UIUtils.COLOR_FONDO);

        // Formulario de Inputs
        JPanel pnlForm = UIUtils.crearPanelTarjeta("Datos del Empleado y Nómina", UIUtils.COLOR_ACCENTO1);
        pnlForm.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        pnlForm.add(crearLabelForm("Código Empleado:"), gbc);
        gbc.gridx = 1;
        txtCodigo = new JTextField("EMP-101", 10);
        UIUtils.estilizarCampoTexto(txtCodigo);
        pnlForm.add(txtCodigo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        pnlForm.add(crearLabelForm("Nombres y Apellidos:"), gbc);
        gbc.gridx = 1;
        txtNombres = new JTextField("Carlos Andrés Pérez", 15);
        UIUtils.estilizarCampoTexto(txtNombres);
        pnlForm.add(txtNombres, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        pnlForm.add(crearLabelForm("Horas Trabajadas:"), gbc);
        gbc.gridx = 1;
        txtHoras = new JTextField("48", 10);
        UIUtils.estilizarCampoTexto(txtHoras);
        pnlForm.add(txtHoras, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        pnlForm.add(crearLabelForm("Valor Hora ($):"), gbc);
        gbc.gridx = 1;
        txtValorHora = new JTextField("5000", 10);
        UIUtils.estilizarCampoTexto(txtValorHora);
        pnlForm.add(txtValorHora, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        pnlForm.add(crearLabelForm("% Retención Fuente:"), gbc);
        gbc.gridx = 1;
        txtPorcentajeRetencion = new JTextField("12.5", 10);
        UIUtils.estilizarCampoTexto(txtPorcentajeRetencion);
        pnlForm.add(txtPorcentajeRetencion, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        JButton btnLiquidar = new JButton("💼 Liquidar Nómina");
        UIUtils.estilizarBotonAccion(btnLiquidar, UIUtils.COLOR_ACCENTO2);
        pnlForm.add(btnLiquidar, gbc);

        // Desglose de Resultados
        JPanel pnlResultados = UIUtils.crearPanelTarjeta("Resumen de Liquidación", UIUtils.COLOR_ACCENTO3);
        JPanel pnlCards = new JPanel(new GridLayout(3, 1, 10, 10));
        pnlCards.setBackground(UIUtils.COLOR_PANEL);

        lblResBruto = crearCardResultado("Salario Bruto:", "$ 240.000", UIUtils.COLOR_ACCENTO1);
        lblResRetencion = crearCardResultado("Retención en la Fuente:", "$ 30.000", UIUtils.COLOR_ACCENTO4);
        lblResNeto = crearCardResultado("Salario Neto a Pagar:", "$ 210.000", UIUtils.COLOR_ACCENTO2);

        pnlCards.add(lblResBruto);
        pnlCards.add(lblResRetencion);
        pnlCards.add(lblResNeto);
        pnlResultados.add(pnlCards, BorderLayout.CENTER);

        pnlCentro.add(pnlForm);
        pnlCentro.add(pnlResultados);

        // ── Tarjeta Sur: Consola ────────────────────────────────────────
        JPanel pnlSur = UIUtils.crearPanelTarjeta("Detalle de Comprobante", UIUtils.COLOR_TEXTO_DIM);
        txtResultado = new JTextArea(5, 40);
        pnlSur.add(UIUtils.crearConsolaEstilizada(txtResultado), BorderLayout.CENTER);

        add(pnlEnunciado, BorderLayout.NORTH);
        add(pnlCentro, BorderLayout.CENTER);
        add(pnlSur, BorderLayout.SOUTH);

        btnLiquidar.addActionListener(e -> liquidar());
        liquidar();
    }

    private JLabel crearLabelForm(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(UIUtils.FUENTE_BOLD);
        lbl.setForeground(UIUtils.COLOR_TEXTO);
        return lbl;
    }

    private JLabel crearCardResultado(String titulo, String valorInicial, Color colorAccento) {
        JLabel lbl = new JLabel("<html><body style='width: 180px;'><b>" + titulo + "</b><br><font size='5' color='" +
            toHex(colorAccento) + "'>" + valorInicial + "</font></body></html>");
        lbl.setOpaque(true);
        lbl.setBackground(UIUtils.COLOR_CONSOLE_BG);
        lbl.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(colorAccento, 1, true),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        return lbl;
    }

    private String toHex(Color c) {
        return String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
    }

    private void liquidar() {
        try {
            String cod = txtCodigo.getText().trim();
            String nom = txtNombres.getText().trim();
            double hrs = Double.parseDouble(txtHoras.getText().trim());
            double valH = Double.parseDouble(txtValorHora.getText().trim());
            double retP = Double.parseDouble(txtPorcentajeRetencion.getText().trim());

            EjercicioPropuesto12 ej = new EjercicioPropuesto12(cod, nom, hrs, valH, retP);

            lblResBruto.setText("<html><body><b>Salario Bruto:</b><br><font size='5' color='" + toHex(UIUtils.COLOR_ACCENTO1) + "'>" +
                ej.formatoMoneda(ej.getSalarioBruto()) + "</font></body></html>");
            lblResRetencion.setText("<html><body><b>Retención Fuente (" + retP + "%):</b><br><font size='5' color='" + toHex(UIUtils.COLOR_ACCENTO4) + "'>" +
                ej.formatoMoneda(ej.getRetencionFuente()) + "</font></body></html>");
            lblResNeto.setText("<html><body><b>Salario Neto a Pagar:</b><br><font size='5' color='" + toHex(UIUtils.COLOR_ACCENTO2) + "'>" +
                ej.formatoMoneda(ej.getSalarioNeto()) + "</font></body></html>");

            StringBuilder sb = new StringBuilder();
            sb.append(">>> COMPROBANTE DE LIQUIDACIÓN DE NÓMINA <<<\n");
            sb.append("  • Código Empleado     : ").append(ej.getCodigoEmpleado()).append("\n");
            sb.append("  • Empleado            : ").append(ej.getNombres()).append("\n");
            sb.append(String.format("  • Horas Laboradas     : %.1f hrs @ %s / hora\n", ej.getHorasTrabajadas(), ej.formatoMoneda(ej.getValorHora())));
            sb.append("  -------------------------------------------------------------\n");
            sb.append("  • SALARIO BRUTO       : ").append(ej.formatoMoneda(ej.getSalarioBruto())).append("\n");
            sb.append(String.format("  • RETENCIÓN FUENTE    : %s (%.2f%% del bruto)\n", ej.formatoMoneda(ej.getRetencionFuente()), retP));
            sb.append("  • SALARIO NETO        : ").append(ej.formatoMoneda(ej.getSalarioNeto())).append("\n");
            txtResultado.setText(sb.toString());

        } catch (Exception ex) {
            txtResultado.setText("ERROR: Por favor verifique que los campos numéricos (Horas, Valor Hora, Retención) sean válidos.");
        }
    }
}
