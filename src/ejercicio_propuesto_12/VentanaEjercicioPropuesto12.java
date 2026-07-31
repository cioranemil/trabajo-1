package ejercicio_propuesto_12;

import Utilidades.UIUtils;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Ventana Swing interactiva para el Ejercicio Propuesto No 12.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 1.0/2026
 */
public class VentanaEjercicioPropuesto12 extends JFrame {
    private JTextField txtCodigo, txtNombres, txtHoras, txtValorHora, txtRetencion;
    private DefaultTableModel modeloTabla;
    private JTable tablaSalarios;
    private ArrayList<EjercicioPropuesto12> listaLiquidaciones = new ArrayList<>();

    public VentanaEjercicioPropuesto12() {
        UIUtils.aplicarTema();
        setTitle("Ejercicio Propuesto No 12: Liquidación de Salario y Retención");
        setSize(780, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelCentral = new JPanel(new BorderLayout(12, 12));
        UIUtils.estilizarPanel(panelCentral);

        JPanel form = new JPanel(new GridLayout(3, 4, 8, 8));
        form.setBackground(UIUtils.COLOR_FONDO);

        form.add(new JLabel("Código:"));
        txtCodigo = new JTextField("EMP-101");
        form.add(txtCodigo);

        form.add(new JLabel("Nombres:"));
        txtNombres = new JTextField("Juan Pérez");
        form.add(txtNombres);

        form.add(new JLabel("Horas Semanales:"));
        txtHoras = new JTextField("48");
        form.add(txtHoras);

        form.add(new JLabel("Valor por Hora ($):"));
        txtValorHora = new JTextField("5000");
        form.add(txtValorHora);

        form.add(new JLabel("% Retención Fuente:"));
        txtRetencion = new JTextField("12.5");
        form.add(txtRetencion);

        JPanel pnlAcciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        pnlAcciones.setBackground(UIUtils.COLOR_FONDO);

        JButton btnCalcular = new JButton("Calcular Liquidación");
        UIUtils.estilizarBoton(btnCalcular);
        btnCalcular.addActionListener(e -> calcularLiquidacion());

        JButton btnDefecto = new JButton("Cargar Datos del Libro (48h, $5.000, 12.5%)");
        UIUtils.estilizarBoton(btnDefecto);
        btnDefecto.addActionListener(e -> {
            txtCodigo.setText("EMP-LIBRO");
            txtNombres.setText("Trabajador Enunciado");
            txtHoras.setText("48");
            txtValorHora.setText("5000");
            txtRetencion.setText("12.5");
            calcularLiquidacion();
        });

        pnlAcciones.add(btnCalcular);
        pnlAcciones.add(btnDefecto);
        form.add(pnlAcciones);

        String[] columnas = {"Código", "Nombres", "Horas", "Valor/Hora", "Salario Bruto", "Retención (12.5%)", "Salario Neto"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaSalarios = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaSalarios);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Historial de Liquidaciones Salariales"));

        panelCentral.add(form, BorderLayout.NORTH);
        panelCentral.add(scrollTabla, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);
        calcularLiquidacion();
    }

    private void calcularLiquidacion() {
        try {
            String cod = txtCodigo.getText().trim();
            String nom = txtNombres.getText().trim();
            double h = Double.parseDouble(txtHoras.getText().trim());
            double vh = Double.parseDouble(txtValorHora.getText().trim());
            double ret = Double.parseDouble(txtRetencion.getText().trim());

            EjercicioPropuesto12 ej = new EjercicioPropuesto12(cod, nom, h, vh, ret);
            listaLiquidaciones.add(ej);

            Object[] fila = {
                ej.getCodigoEmpleado(),
                ej.getNombres(),
                String.format("%.1f", ej.getHorasTrabajadas()),
                ej.formatoMoneda(ej.getValorHora()),
                ej.formatoMoneda(ej.getSalarioBruto()),
                ej.formatoMoneda(ej.getRetencionFuente()),
                ej.formatoMoneda(ej.getSalarioNeto())
            };
            modeloTabla.addRow(fila);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Las horas, tarifa y retención deben ser valores numéricos válidos.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }
}
