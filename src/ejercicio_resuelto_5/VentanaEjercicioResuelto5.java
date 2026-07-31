package ejercicio_resuelto_5;

import Utilidades.UIUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Panel interactivo para el Ejercicio Resuelto No 5.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 3.0/2026
 */
public class VentanaEjercicioResuelto5 extends JPanel {

    private JTextField txtXInicial;
    private JTextField txtYInicial;
    private JTable tblPasos;
    private DefaultTableModel modelTabla;
    private JTextArea txtResultado;

    private Timer timerAnimacion;
    private int pasoAnimado = -1;
    private EjercicioResuelto5 ultimoCalculo;

    public VentanaEjercicioResuelto5() {
        setLayout(new BorderLayout(15, 15));
        setBackground(UIUtils.COLOR_FONDO);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // Enunciado
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

        // Barra de Controles
        JPanel pnlCentro = new JPanel(new BorderLayout(10, 10));
        pnlCentro.setBackground(UIUtils.COLOR_FONDO);

        JPanel pnlBarra = UIUtils.crearPanelTarjeta("Parámetros e Instrucciones de Prueba", UIUtils.COLOR_ACCENTO1);
        pnlBarra.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 4));

        pnlBarra.add(new JLabel("X0:"));
        txtXInicial = new JTextField("20", 4);
        UIUtils.estilizarCampoTexto(txtXInicial);
        pnlBarra.add(txtXInicial);

        pnlBarra.add(new JLabel("Y0:"));
        txtYInicial = new JTextField("40", 4);
        UIUtils.estilizarCampoTexto(txtYInicial);
        pnlBarra.add(txtYInicial);

        JButton btnEjecutar = new JButton("Trazar Todo");
        UIUtils.estilizarBotonAccion(btnEjecutar, UIUtils.COLOR_ACCENTO2);
        pnlBarra.add(btnEjecutar);

        JButton btnPlay = new JButton("Reproducir Animación");
        UIUtils.estilizarBotonAccion(btnPlay, UIUtils.COLOR_ACCENTO1);
        pnlBarra.add(btnPlay);

        JButton btnStop = new JButton("Pausa");
        UIUtils.estilizarBotonAccion(btnStop, UIUtils.COLOR_ACCENTO4);
        pnlBarra.add(btnStop);

        // Tabla
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

        pnlCentro.add(pnlBarra, BorderLayout.NORTH);
        pnlCentro.add(scrollTabla, BorderLayout.CENTER);

        // Consola
        JPanel pnlSur = UIUtils.crearPanelTarjeta("Resultado Final del Algoritmo", UIUtils.COLOR_ACCENTO3);
        txtResultado = new JTextArea(4, 40);
        pnlSur.add(UIUtils.crearConsolaEstilizada(txtResultado), BorderLayout.CENTER);

        add(pnlEnunciado, BorderLayout.NORTH);
        add(pnlCentro, BorderLayout.CENTER);
        add(pnlSur, BorderLayout.SOUTH);

        timerAnimacion = new Timer(750, e -> avanzarPasoAnimacion());

        btnEjecutar.addActionListener(e -> ejecutarPrueba(true));
        btnPlay.addActionListener(e -> iniciarAnimacion());
        btnStop.addActionListener(e -> detenerAnimacion());

        ejecutarPrueba(true);
    }

    private void ejecutarPrueba(boolean mostrarTodo) {
        detenerAnimacion();
        try {
            double xVal = Double.parseDouble(txtXInicial.getText().trim());
            double yVal = Double.parseDouble(txtYInicial.getText().trim());

            ultimoCalculo = new EjercicioResuelto5(xVal, yVal);
            modelTabla.setRowCount(0);

            if (mostrarTodo) {
                for (EjercicioResuelto5.PasoPruebaEscritorio paso : ultimoCalculo.getPasos()) {
                    modelTabla.addRow(new Object[]{
                        "Paso " + paso.getPaso(),
                        paso.getInstruccion(),
                        String.format("%.2f", paso.getValorX()),
                        String.format("%.2f", paso.getValorY()),
                        String.format("%.2f", paso.getValorSuma())
                    });
                }
                actualizarTextoConsola(ultimoCalculo.getPasos().size());
            }

        } catch (Exception ex) {
            txtResultado.setText("ERROR: Por favor ingrese valores numéricos válidos para X e Y.");
        }
    }

    private void iniciarAnimacion() {
        ejecutarPrueba(false);
        pasoAnimado = 0;
        timerAnimacion.start();
    }

    private void detenerAnimacion() {
        if (timerAnimacion != null && timerAnimacion.isRunning()) {
            timerAnimacion.stop();
        }
    }

    private void avanzarPasoAnimacion() {
        if (ultimoCalculo == null) return;
        if (pasoAnimado < ultimoCalculo.getPasos().size()) {
            EjercicioResuelto5.PasoPruebaEscritorio paso = ultimoCalculo.getPasos().get(pasoAnimado);
            modelTabla.addRow(new Object[]{
                "Paso " + paso.getPaso(),
                paso.getInstruccion(),
                String.format("%.2f", paso.getValorX()),
                String.format("%.2f", paso.getValorY()),
                String.format("%.2f", paso.getValorSuma())
            });
            tblPasos.setRowSelectionInterval(pasoAnimado, pasoAnimado);
            actualizarTextoConsola(pasoAnimado + 1);
            pasoAnimado++;
        } else {
            detenerAnimacion();
        }
    }

    private void actualizarTextoConsola(int pasosMostrados) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(">>> PRUEBA DE ESCRITORIO (%d / %d PASOS EVALUADOS) <<<\n",
            pasosMostrados, ultimoCalculo.getPasos().size()));
        sb.append(String.format("  • Valor actual de X        = %.2f\n", ultimoCalculo.getXFinal()));
        sb.append(String.format("  • Valor actual de Y        = %.2f\n", ultimoCalculo.getYFinal()));
        sb.append(String.format("  • VALOR FINAL DE LA SUMA   = %.2f\n", ultimoCalculo.getSumaFinal()));
        txtResultado.setText(sb.toString());
    }
}
