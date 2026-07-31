package ejercicio_resuelto_5;

import Utilidades.UIUtils;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Ventana Swing interactiva para el Ejercicio Resuelto No 5 (Prueba de Escritorio).
 * 
 * @author Cristian Ruiz Hernandez
 * @version 1.0/2026
 */
public class VentanaEjercicioResuelto5 extends JFrame {
    private JTextField txtX, txtY;
    private DefaultTableModel modeloTabla;
    private JTable tablaPasos;
    private JLabel lblResultadoFinal;

    public VentanaEjercicioResuelto5() {
        UIUtils.aplicarTema();
        setTitle("Ejercicio Resuelto No 5: Prueba de Escritorio (Seguimiento)");
        setSize(720, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelCentral = new JPanel(new BorderLayout(12, 12));
        UIUtils.estilizarPanel(panelCentral);

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 10));
        form.setBackground(UIUtils.COLOR_FONDO);

        form.add(new JLabel("Valor inicial de X:"));
        txtX = new JTextField("20", 5);
        form.add(txtX);

        form.add(new JLabel("Valor inicial de Y:"));
        txtY = new JTextField("40", 5);
        form.add(txtY);

        JButton btnEjecutar = new JButton("Ejecutar Prueba de Escritorio");
        UIUtils.estilizarBoton(btnEjecutar);
        btnEjecutar.addActionListener(e -> ejecutarPrueba());

        JButton btnDefecto = new JButton("Valores Originales del Libro (20, 40)");
        UIUtils.estilizarBoton(btnDefecto);
        btnDefecto.addActionListener(e -> {
            txtX.setText("20");
            txtY.setText("40");
            ejecutarPrueba();
        });

        form.add(btnEjecutar);
        form.add(btnDefecto);

        String[] columnas = {"Paso", "Instrucción del Algoritmo", "Valor de X", "Valor de Y", "Valor Acumulado SUMA"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaPasos = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaPasos);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Traza / Prueba de Escritorio Paso a Paso"));

        lblResultadoFinal = new JLabel("EL VALOR DE LA SUMA ES: 60.50", SwingConstants.CENTER);
        lblResultadoFinal.setFont(UIUtils.FUENTE_TITULO);
        lblResultadoFinal.setForeground(UIUtils.COLOR_PRIMARIO);

        panelCentral.add(form, BorderLayout.NORTH);
        panelCentral.add(scrollTabla, BorderLayout.CENTER);
        panelCentral.add(lblResultadoFinal, BorderLayout.SOUTH);

        add(panelCentral, BorderLayout.CENTER);
        ejecutarPrueba();
    }

    private void ejecutarPrueba() {
        try {
            double xVal = Double.parseDouble(txtX.getText().trim());
            double yVal = Double.parseDouble(txtY.getText().trim());
            if (yVal == 0) throw new IllegalArgumentException("Y no puede ser cero para evitar división por cero.");

            EjercicioResuelto5 ej = new EjercicioResuelto5(xVal, yVal);
            modeloTabla.setRowCount(0);

            for (EjercicioResuelto5.PasoPruebaEscritorio p : ej.getPasos()) {
                Object[] fila = {
                    p.getPaso(),
                    p.getInstruccion(),
                    String.format("%.2f", p.getValorX()),
                    String.format("%.2f", p.getValorY()),
                    String.format("%.2f", p.getValorSuma())
                };
                modeloTabla.addRow(fila);
            }

            lblResultadoFinal.setText(String.format("EL VALOR DE LA SUMA ES: %.2f", ej.getSumaFinal()));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese números válidos para X e Y.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }
}
