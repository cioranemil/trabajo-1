package ejercicio_propuesto_12;

import Utilidades.ManejadorPersistencia;
import Utilidades.UIUtils;
import Utilidades.ValorInvalidoException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Panel interactivo para el Ejercicio Propuesto No 12.
 * Liquidación de Salario Bruto, Retención en la Fuente y Salario Neto.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 3.0/2026
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
    private GraficoNomina canvasNomina;

    private EjercicioPropuesto12 ultimoCalculo;

    public VentanaEjercicioPropuesto12() {
        setLayout(new BorderLayout(15, 15));
        setBackground(UIUtils.COLOR_FONDO);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // Enunciado
        JPanel pnlEnunciado = UIUtils.crearPanelTarjeta("Ejercicio Propuesto No 12: Liquidación de Salario (Pág 50)", UIUtils.COLOR_ACCENTO2);
        JTextArea txtEnunciado = new JTextArea(
            "Un empleado trabaja determinado número de horas a la semana a una tarifa por hora fija.\n" +
            "El porcentaje de retención en la fuente se aplica sobre el salario bruto.\n\n" +
            "Fórmulas:\n" +
            "  • Salario Bruto = Horas Trabajadas * Valor Hora\n" +
            "  • Retención Fuente = Salario Bruto * (% Retención / 100)\n" +
            "  • Salario Neto = Salario Bruto - Retención Fuente"
        );
        txtEnunciado.setFont(UIUtils.FUENTE_NORMAL);
        txtEnunciado.setForeground(UIUtils.COLOR_TEXTO);
        txtEnunciado.setBackground(UIUtils.COLOR_PANEL);
        txtEnunciado.setEditable(false);
        pnlEnunciado.add(txtEnunciado, BorderLayout.CENTER);

        // Inputs y Gráfico
        JPanel pnlCentro = new JPanel(new GridLayout(1, 3, 12, 0));
        pnlCentro.setBackground(UIUtils.COLOR_FONDO);

        JPanel pnlForm = UIUtils.crearPanelTarjeta("Datos de Nómina", UIUtils.COLOR_ACCENTO1);
        pnlForm.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        pnlForm.add(crearLabelForm("Código:"), gbc);
        gbc.gridx = 1;
        txtCodigo = new JTextField("EMP-101", 8);
        UIUtils.estilizarCampoTexto(txtCodigo);
        pnlForm.add(txtCodigo, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        pnlForm.add(crearLabelForm("Nombres:"), gbc);
        gbc.gridx = 1;
        txtNombres = new JTextField("Carlos Pérez", 10);
        UIUtils.estilizarCampoTexto(txtNombres);
        pnlForm.add(txtNombres, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        pnlForm.add(crearLabelForm("Horas:"), gbc);
        gbc.gridx = 1;
        txtHoras = new JTextField("48", 8);
        UIUtils.estilizarCampoTexto(txtHoras);
        pnlForm.add(txtHoras, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        pnlForm.add(crearLabelForm("Valor ($):"), gbc);
        gbc.gridx = 1;
        txtValorHora = new JTextField("5000", 8);
        UIUtils.estilizarCampoTexto(txtValorHora);
        pnlForm.add(txtValorHora, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        pnlForm.add(crearLabelForm("Ret. (%):"), gbc);
        gbc.gridx = 1;
        txtPorcentajeRetencion = new JTextField("12.5", 8);
        UIUtils.estilizarCampoTexto(txtPorcentajeRetencion);
        pnlForm.add(txtPorcentajeRetencion, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        JButton btnLiquidar = new JButton("Liquidar Nómina");
        UIUtils.estilizarBotonAccion(btnLiquidar, UIUtils.COLOR_ACCENTO2);
        pnlForm.add(btnLiquidar, gbc);

        // Canvas Gráfico
        JPanel pnlCanvasHolder = UIUtils.crearPanelTarjeta("Gráfico de Nómina", UIUtils.COLOR_ACCENTO2);
        canvasNomina = new GraficoNomina();
        pnlCanvasHolder.add(canvasNomina, BorderLayout.CENTER);

        // Resumen
        JPanel pnlResultados = UIUtils.crearPanelTarjeta("Resumen", UIUtils.COLOR_ACCENTO3);
        JPanel pnlCards = new JPanel(new GridLayout(4, 1, 6, 6));
        pnlCards.setBackground(UIUtils.COLOR_PANEL);

        lblResBruto = crearCardResultado("Salario Bruto:", "$ 240.000", UIUtils.COLOR_ACCENTO1);
        lblResRetencion = crearCardResultado("Retención Fuente:", "$ 30.000", UIUtils.COLOR_ACCENTO4);
        lblResNeto = crearCardResultado("Salario Neto:", "$ 210.000", UIUtils.COLOR_ACCENTO2);

        JButton btnExportar = new JButton("Exportar Comprobante (.txt)");
        UIUtils.estilizarBotonAccion(btnExportar, UIUtils.COLOR_ACCENTO3);
        btnExportar.addActionListener(e -> exportarComprobante());

        pnlCards.add(lblResBruto);
        pnlCards.add(lblResRetencion);
        pnlCards.add(lblResNeto);
        pnlCards.add(btnExportar);
        pnlResultados.add(pnlCards, BorderLayout.CENTER);

        pnlCentro.add(pnlForm);
        pnlCentro.add(pnlCanvasHolder);
        pnlCentro.add(pnlResultados);

        // Consola
        JPanel pnlSur = UIUtils.crearPanelTarjeta("Detalle de Comprobante", UIUtils.COLOR_TEXTO_DIM);
        txtResultado = new JTextArea(4, 40);
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
        JLabel lbl = new JLabel("<html><body><b>" + titulo + "</b><br><font size='4' color='" +
            toHex(colorAccento) + "'>" + valorInicial + "</font></body></html>");
        lbl.setOpaque(true);
        lbl.setBackground(UIUtils.COLOR_CONSOLE_BG);
        lbl.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(colorAccento, 1, true),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
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

            double hrs, valH, retP;
            try { hrs = Double.parseDouble(txtHoras.getText().trim()); }
            catch (Exception ex) { throw new ValorInvalidoException("El campo Horas debe ser un número válido.", "Horas"); }

            try { valH = Double.parseDouble(txtValorHora.getText().trim()); }
            catch (Exception ex) { throw new ValorInvalidoException("El campo Valor Hora debe ser un número válido.", "Valor Hora"); }

            try { retP = Double.parseDouble(txtPorcentajeRetencion.getText().trim()); }
            catch (Exception ex) { throw new ValorInvalidoException("El Porcentaje de Retención debe ser un número válido.", "Retención"); }

            if (hrs < 0 || valH < 0 || retP < 0 || retP > 100) {
                throw new ValorInvalidoException("Los valores de horas y tarifa deben ser positivos y la retención estar entre 0% y 100%.", "Nómina");
            }

            ultimoCalculo = new EjercicioPropuesto12(cod, nom, hrs, valH, retP);
            canvasNomina.setValores(ultimoCalculo.getSalarioBruto(), ultimoCalculo.getRetencionFuente(), ultimoCalculo.getSalarioNeto());

            lblResBruto.setText("<html><body><b>Salario Bruto:</b><br><font size='4' color='" + toHex(UIUtils.COLOR_ACCENTO1) + "'>" +
                ultimoCalculo.formatoMoneda(ultimoCalculo.getSalarioBruto()) + "</font></body></html>");
            lblResRetencion.setText("<html><body><b>Retención Fuente (" + retP + "%):</b><br><font size='4' color='" + toHex(UIUtils.COLOR_ACCENTO4) + "'>" +
                ultimoCalculo.formatoMoneda(ultimoCalculo.getRetencionFuente()) + "</font></body></html>");
            lblResNeto.setText("<html><body><b>Salario Neto:</b><br><font size='4' color='" + toHex(UIUtils.COLOR_ACCENTO2) + "'>" +
                ultimoCalculo.formatoMoneda(ultimoCalculo.getSalarioNeto()) + "</font></body></html>");

            StringBuilder sb = new StringBuilder();
            sb.append(">>> COMPROBANTE DE LIQUIDACIÓN DE NÓMINA <<<\n");
            sb.append("  • Código Empleado     : ").append(ultimoCalculo.getCodigoEmpleado()).append("\n");
            sb.append("  • Empleado            : ").append(ultimoCalculo.getNombres()).append("\n");
            sb.append(String.format("  • Horas Laboradas     : %.1f hrs @ %s / hora\n", ultimoCalculo.getHorasTrabajadas(), ultimoCalculo.formatoMoneda(ultimoCalculo.getValorHora())));
            sb.append("  -------------------------------------------------------------\n");
            sb.append("  • SALARIO BRUTO       : ").append(ultimoCalculo.formatoMoneda(ultimoCalculo.getSalarioBruto())).append("\n");
            sb.append(String.format("  • RETENCIÓN FUENTE    : %s (%.2f%% del bruto)\n", ultimoCalculo.formatoMoneda(ultimoCalculo.getRetencionFuente()), retP));
            sb.append("  • SALARIO NETO        : ").append(ultimoCalculo.formatoMoneda(ultimoCalculo.getSalarioNeto())).append("\n");
            txtResultado.setText(sb.toString());

            ManejadorPersistencia.guardarRegistro("Ejercicio Propuesto 12",
                "Empleado=" + nom + " (" + cod + "), Horas=" + hrs + ", Tarifa=" + valH,
                "Bruto=" + ultimoCalculo.getSalarioBruto() + ", Neto=" + ultimoCalculo.getSalarioNeto());

        } catch (ValorInvalidoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Validación", JOptionPane.ERROR_MESSAGE);
            txtResultado.setText("ERROR DE VALIDACIÓN: " + ex.getMessage());
        } catch (Exception ex) {
            txtResultado.setText("ERROR INESPERADO: " + ex.getMessage());
        }
    }

    private void exportarComprobante() {
        if (ultimoCalculo == null) return;
        try {
            java.io.File archivo = new java.io.File("Comprobante_Nomina_" + ultimoCalculo.getCodigoEmpleado() + ".txt");
            try (java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.FileWriter(archivo))) {
                pw.println("============================================================");
                pw.println("      UNIVERSIDAD NACIONAL DE COLOMBIA - ACTIVIDAD 1       ");
                pw.println("                COMPROBANTE OFICIAL DE NÓMINA               ");
                pw.println("============================================================");
                pw.println("Código Empleado     : " + ultimoCalculo.getCodigoEmpleado());
                pw.println("Nombres             : " + ultimoCalculo.getNombres());
                pw.println("Horas Trabajadas    : " + ultimoCalculo.getHorasTrabajadas() + " hrs");
                pw.println("Valor por Hora      : " + ultimoCalculo.formatoMoneda(ultimoCalculo.getValorHora()));
                pw.println("Porcentaje Retención: " + ultimoCalculo.getPorcentajeRetencion() + "%");
                pw.println("------------------------------------------------------------");
                pw.println("SALARIO BRUTO       : " + ultimoCalculo.formatoMoneda(ultimoCalculo.getSalarioBruto()));
                pw.println("RETENCIÓN EN FUENTE : " + ultimoCalculo.formatoMoneda(ultimoCalculo.getRetencionFuente()));
                pw.println("SALARIO NETO        : " + ultimoCalculo.formatoMoneda(ultimoCalculo.getSalarioNeto()));
                pw.println("============================================================");
            }
            JOptionPane.showMessageDialog(this, "Comprobante generado con éxito en:\n" + archivo.getAbsolutePath(),
                "Exportación Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al generar archivo: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
