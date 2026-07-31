package ejercicio_resuelto_4;

import Utilidades.UIUtils;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Ventana Swing interactiva para el Ejercicio Resuelto No 4.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 1.0/2026
 */
public class VentanaEjercicioResuelto4 extends JFrame {
    private JTextField txtEdadJuan;
    private DefaultTableModel modeloTabla;
    private JTable tablaHistorial;
    private ArrayList<EjercicioResuelto4> listaConsultas = new ArrayList<>();

    public VentanaEjercicioResuelto4() {
        UIUtils.aplicarTema();
        setTitle("Ejercicio Resuelto No 4: Edades de la Familia");
        setSize(700, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelCentral = new JPanel(new BorderLayout(12, 12));
        UIUtils.estilizarPanel(panelCentral);

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 10));
        form.setBackground(UIUtils.COLOR_FONDO);

        form.add(new JLabel("Edad de Juan (Años):"));
        txtEdadJuan = new JTextField("9", 6);
        form.add(txtEdadJuan);

        JButton btnCalcular = new JButton("Calcular Edades de la Familia");
        UIUtils.estilizarBoton(btnCalcular);
        btnCalcular.addActionListener(e -> calcularEdades());
        form.add(btnCalcular);

        String[] columnas = {"Edad Juan", "Edad Alberto (2/3)", "Edad Ana (4/3)", "Edad Mamá (Suma)", "¿Valores Enteros Exactos?"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaHistorial = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaHistorial);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Historial de Cálculos de Edades"));

        panelCentral.add(form, BorderLayout.NORTH);
        panelCentral.add(scrollTabla, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);
        calcularEdades();
    }

    private void calcularEdades() {
        try {
            double edadJ = Double.parseDouble(txtEdadJuan.getText().trim());
            EjercicioResuelto4 ej = new EjercicioResuelto4(edadJ);
            listaConsultas.add(ej);

            Object[] fila = {
                String.format("%.2f", ej.getEdadJuan()),
                String.format("%.2f", ej.getEdadAlberto()),
                String.format("%.2f", ej.getEdadAna()),
                String.format("%.2f", ej.getEdadMama()),
                ej.esEdadExacta() ? "Sí (Múltiplo de 3)" : "No (Decimales)"
            };
            modeloTabla.addRow(fila);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un valor numérico para la edad de Juan.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }
}
