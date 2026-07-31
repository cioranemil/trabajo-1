package ejercicio_propuesto_14;

import Utilidades.UIUtils;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Ventana Swing interactiva para el Ejercicio Propuesto No 14.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 1.0/2026
 */
public class VentanaEjercicioPropuesto14 extends JFrame {
    private JTextField txtNumero;
    private DefaultTableModel modeloTabla;
    private JTable tablaPotencias;

    public VentanaEjercicioPropuesto14() {
        UIUtils.aplicarTema();
        setTitle("Ejercicio Propuesto No 14: Cuadrado y Cubo de un Número");
        setSize(680, 460);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelCentral = new JPanel(new BorderLayout(12, 12));
        UIUtils.estilizarPanel(panelCentral);

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 10));
        form.setBackground(UIUtils.COLOR_FONDO);

        form.add(new JLabel("Número de Entrada:"));
        txtNumero = new JTextField("5", 8);
        form.add(txtNumero);

        JButton btnCalcular = new JButton("Calcular Cuadrado y Cubo");
        UIUtils.estilizarBoton(btnCalcular);
        btnCalcular.addActionListener(e -> calcular());
        form.add(btnCalcular);

        String[] columnas = {"Número (n)", "Cuadrado (n²)", "Cubo (n³)", "Raíz Cuadrada (√n)"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaPotencias = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaPotencias);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Historial de Cálculos de Potencias"));

        panelCentral.add(form, BorderLayout.NORTH);
        panelCentral.add(scrollTabla, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);
        calcular();
    }

    private void calcular() {
        try {
            double num = Double.parseDouble(txtNumero.getText().trim());
            EjercicioPropuesto14 ej = new EjercicioPropuesto14(num);

            Object[] fila = {
                String.format("%.2f", ej.getNumero()),
                String.format("%.2f", ej.getCuadrado()),
                String.format("%.2f", ej.getCubo()),
                Double.isNaN(ej.getRaizCuadrada()) ? "N/A (Negativo)" : String.format("%.4f", ej.getRaizCuadrada())
            };
            modeloTabla.addRow(fila);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un valor numérico válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }
}
