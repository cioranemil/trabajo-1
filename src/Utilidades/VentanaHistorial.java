package Utilidades;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Panel/Modal Swing para consultar y restaurar los registros almacenados
 * históricamente en disco (data/historial.json).
 * 
 * @author Cristian Ruiz Hernandez
 * @version 3.0/2026
 */
public class VentanaHistorial extends JDialog {

    private JTable tblHistorial;
    private DefaultTableModel modelTabla;

    public VentanaHistorial(JFrame parent) {
        super(parent, "Historial de Operaciones y Persistencia de Datos", true);
        setSize(780, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel panelContenido = new JPanel(new BorderLayout(12, 12));
        panelContenido.setBackground(UIUtils.COLOR_FONDO);
        panelContenido.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Header
        JPanel pnlHeader = UIUtils.crearPanelTarjeta("Historial Local de Operaciones (data/historial.json)", UIUtils.COLOR_ACCENTO1);
        JLabel lblInfo = new JLabel("Registros almacenados en disco de forma persistente entre sesiones:");
        lblInfo.setFont(UIUtils.FUENTE_NORMAL);
        lblInfo.setForeground(UIUtils.COLOR_TEXTO_DIM);
        pnlHeader.add(lblInfo, BorderLayout.SOUTH);

        // Tabla
        String[] cols = {"Fecha y Hora", "Ejercicio", "Parámetros de Entrada", "Resultados Obtenidos"};
        modelTabla = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblHistorial = new JTable(modelTabla);
        tblHistorial.setFont(UIUtils.FUENTE_CONSOLA);
        tblHistorial.setRowHeight(24);
        tblHistorial.setBackground(UIUtils.COLOR_CONSOLE_BG);
        tblHistorial.setForeground(UIUtils.COLOR_ACCENTO2);
        tblHistorial.setGridColor(UIUtils.COLOR_BORDES);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblHistorial.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        JScrollPane scroll = new JScrollPane(tblHistorial);
        scroll.setBorder(BorderFactory.createLineBorder(UIUtils.COLOR_BORDES, 1, true));

        // Botones
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        pnlBotones.setBackground(UIUtils.COLOR_FONDO);

        JButton btnRefrescar = new JButton("🔄 Refrescar");
        UIUtils.estilizarBotonAccion(btnRefrescar, UIUtils.COLOR_ACCENTO1);
        btnRefrescar.addActionListener(e -> cargarDatos());

        JButton btnLimpiar = new JButton("🗑️ Limpiar Historial");
        UIUtils.estilizarBotonAccion(btnLimpiar, UIUtils.COLOR_ACCENTO4);
        btnLimpiar.addActionListener(e -> {
            int op = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de eliminar todo el historial almacenado en disco?",
                "Confirmar Limpieza", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (op == JOptionPane.YES_OPTION) {
                ManejadorPersistencia.limpiarHistorial();
                cargarDatos();
            }
        });

        JButton btnCerrar = new JButton("Cerrar");
        UIUtils.estilizarBotonAccion(btnCerrar, UIUtils.COLOR_TARJETA);
        btnCerrar.addActionListener(e -> dispose());

        pnlBotones.add(btnRefrescar);
        pnlBotones.add(btnLimpiar);
        pnlBotones.add(btnCerrar);

        panelContenido.add(pnlHeader, BorderLayout.NORTH);
        panelContenido.add(scroll, BorderLayout.CENTER);
        panelContenido.add(pnlBotones, BorderLayout.SOUTH);

        add(panelContenido);
        cargarDatos();
    }

    private void cargarDatos() {
        modelTabla.setRowCount(0);
        List<ManejadorPersistencia.RegistroHistorial> lista = ManejadorPersistencia.cargarHistorial();
        for (ManejadorPersistencia.RegistroHistorial r : lista) {
            modelTabla.addRow(new Object[]{
                r.getFecha(), r.getEjercicio(), r.getResumenEntrada(), r.getResultado()
            });
        }
    }
}
