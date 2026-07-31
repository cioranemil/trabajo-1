package ejercicio_resuelto_5;

import Utilidades.UIUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Panel interactivo para el Ejercicio Resuelto No 5.
 * Prueba de escritorio y trazabilidad de variables X, Y y SUMA.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 2.0/2026
 */
public class VentanaEjercicioResuelto5 extends JPanel {

    private JTextField txtXInicial;
    private JTextField txtYInicial;
    private JTable tblPasos;
    private DefaultTableModel modelTabla;
    private JTextArea txtResultado;

    public VentanaEjercicioResuelto5() {
        setLayout(new BorderLayout(15, 15));
        setBackground(UIUtils.COLOR_FONDO);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // ── Tarjeta Norte: Enunciado del Algoritmo ──────────────────────
        JPanel pnlEnunciado = UIUtils.crearPanelTarjeta("Ejercicio Resuelto No 5: Prueba de Escritorio (Pág 49)", UIUtils.COLOR_ACCENTO5);
        JTextArea txtEnunciado = new JTextArea(
            "Algoritmo a evaluar:\n" +
            "  1. SUMA = 0               2. X = 20\n" +
            "  3. SUMA = SUMA + X         4. Y = 40\n" +
            "  5. X = X + Y ** 2          6. SUMA = SUMA + X / Y\n" +
            "  7. ESCRIBA: \"EL VALOR DE LA SUMA ES:\", SUMA"
        );
        txtEnunciado.setFont(UIUtils.FUENTE_CONSOLA);
        txtEnunciado.setForeground(UIUtils.COLOR_TEXTO);
        txtEnunciado.setBackground(UIUtils.COLOR_PANEL);
        txtEnunciado.setEditable(false);
        pnlEnunciado.add(txtEnunciado, BorderLayout.CENTER);

        // ── Tarjeta Centro: Entradas y Tabla de Trazabilidad ─────────────
        JPanel pnlCentro = new JPanel(new BorderLayout(10, 10));
        pnlCentro.setBackground(UIUtils.COLOR_FONDO);

        // Barra superior de inputs
        JPanel pnlInputs = UIUtils.crearPanelTarjeta("Parámetros Iniciales de Prueba", UIUtils.COLOR_ACCENTO1);
        pnlInputs.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));

        pnlInputs.add(new JLabel("Valor Inicial X:"));
        txtXInicial = new JTextField("20", 6);
        UIUtils.estilizarCampoTexto(txtXInicial);
        pnlInputs.add(txtXInicial);

        pnlInputs.add(new JLabel("Valor Inicial Y:"));
        txtYInicial = new JTextField("40", 6);
        UIUtils.estilizarCampoTexto(txtYInicial);
        pnlInputs.add(txtYInicial);

        JButton btnEjecutar = new JButton("▶ Ejecutar Prueba de Escritorio");
        UIUtils.estilizarBotonAccion(btnEjecutar, UIUtils.COLOR_ACCENTO2);
        pnlInputs.add(btnEjecutar);

        // Tabla de trazabilidad de la prueba de escritorio
        String[] columnas = {"Paso", "Instrucción del Algoritmo", "Valor de X", "Valor de Y", "Valor de SUMA"};
        modelTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tblPasos = new JTable(modelTabla);
        tblPasos.setFont(UIUtils.FUENTE_CONSOLA);
        tblPasos.setRowHeight(24);
        tblPasos.setBackground(UIUtils.COLOR_CONSOLE_BG);
        tblPasos.setForeground(UIUtils.COLOR_ACCENTO3);
        tblPasos.setGridColor(UIUtils.COLOR_BORDES);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblPasos.getColumnCount(); i++) {
            tblPasos.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollTabla = new JScrollPane(tblPasos);
        scrollTabla.setBorder(BorderFactory.createLineBorder(UIUtils.COLOR_BORDES, 1, true));

        pnlCentro.add(pnlInputs, BorderLayout.NORTH);
        pnlCentro.add(scrollTabla, BorderLayout.CENTER);

        // ── Tarjeta Sur: Consola de Consolidado ────────────────────────
        JPanel pnlSur = UIUtils.crearPanelTarjeta("Resultado Final del Algoritmo", UIUtils.COLOR_ACCENTO3);
        txtResultado = new JTextArea(4, 40);
        pnlSur.add(UIUtils.crearConsolaEstilizada(txtResultado), BorderLayout.CENTER);

        add(pnlEnunciado, BorderLayout.NORTH);
        add(pnlCentro, BorderLayout.CENTER);
        add(pnlSur, BorderLayout.SOUTH);

        btnEjecutar.addActionListener(e -> ejecutarPrueba());
        ejecutarPrueba();
    }

    private void ejecutarPrueba() {
        try {
            double xVal = Double.parseDouble(txtXInicial.getText().trim());
            double yVal = Double.parseDouble(txtYInicial.getText().trim());

            EjercicioResuelto5 ej = new EjercicioResuelto5(xVal, yVal);
            modelTabla.setRowCount(0);

            for (EjercicioResuelto5.PasoPruebaEscritorio paso : ej.getPasos()) {
                modelTabla.addRow(new Object[]{
                    "Paso " + paso.getPaso(),
                    paso.getInstruccion(),
                    String.format("%.2f", paso.getValorX()),
                    String.format("%.2f", paso.getValorY()),
                    String.format("%.2f", paso.getValorSuma())
                });
            }

            StringBuilder sb = new StringBuilder();
            sb.append(">>> RESULTADO FINAL DE LA PRUEBA DE ESCRITORIO <<<\n");
            sb.append(String.format("  • Valor acumulado final de X    = %.2f\n", ej.getXFinal()));
            sb.append(String.format("  • Valor acumulado final de Y    = %.2f\n", ej.getYFinal()));
            sb.append(String.format("  • VALOR FINAL DE LA SUMA        = %.2f\n", ej.getSumaFinal()));
            txtResultado.setText(sb.toString());

        } catch (Exception ex) {
            txtResultado.setText("ERROR: Por favor ingrese valores numéricos válidos para X e Y.");
        }
    }
}
