package ejercicio_propuesto_17;

import Utilidades.UIUtils;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Ventana Swing interactiva para el Ejercicio Propuesto No 17.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 1.0/2026
 */
public class VentanaEjercicioPropuesto17 extends JFrame {
    private JTextField txtRadio;
    private DefaultTableModel modeloTabla;
    private JTable tablaCirculos;

    public VentanaEjercicioPropuesto17() {
        UIUtils.aplicarTema();
        setTitle("Ejercicio Propuesto No 17: Área del Círculo y Longitud de la Circunferencia");
        setSize(700, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelCentral = new JPanel(new BorderLayout(12, 12));
        UIUtils.estilizarPanel(panelCentral);

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 10));
        form.setBackground(UIUtils.COLOR_FONDO);

        form.add(new JLabel("Radio del Círculo (r > 0):"));
        txtRadio = new JTextField("5.0", 8);
        form.add(txtRadio);

        JButton btnCalcular = new JButton("Calcular Área y Perímetro");
        UIUtils.estilizarBoton(btnCalcular);
        btnCalcular.addActionListener(e -> calcular());
        form.add(btnCalcular);

        String[] columnas = {"Radio (r)", "Diámetro (2r)", "Área del Círculo (π·r²)", "Longitud Circunferencia (2πr)"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaCirculos = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaCirculos);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Historial de Cálculos Geométricos"));

        panelCentral.add(form, BorderLayout.NORTH);
        panelCentral.add(scrollTabla, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);
        calcular();
    }

    private void calcular() {
        try {
            double r = Double.parseDouble(txtRadio.getText().trim());
            EjercicioPropuesto17 ej = new EjercicioPropuesto17(r);

            Object[] fila = {
                String.format("%.2f cm", ej.getRadio()),
                String.format("%.2f cm", ej.getDiametro()),
                String.format("%.4f cm²", ej.getArea()),
                String.format("%.4f cm", ej.getLongitudCircunferencia())
            };
            modeloTabla.addRow(fila);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un valor numérico válido para el radio.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }
}
