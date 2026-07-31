package ejercicio_resuelto_4;

import Utilidades.UIUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Panel interactivo para el Ejercicio Resuelto No 4.
 * Cálculo de edades de Juan, Alberto, Ana y la Mamá.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 2.0/2026
 */
public class VentanaEjercicioResuelto4 extends JPanel {

    private JTextField txtEdadJuan;
    private JSlider sliderEdadJuan;
    private JTextArea txtResultado;

    private JLabel lblResJuan;
    private JLabel lblResAlberto;
    private JLabel lblResAna;
    private JLabel lblResMama;

    public VentanaEjercicioResuelto4() {
        setLayout(new BorderLayout(15, 15));
        setBackground(UIUtils.COLOR_FONDO);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // ── Tarjeta Norte: Enunciado ──────────────────────────────────
        JPanel pnlEnunciado = UIUtils.crearPanelTarjeta("Ejercicio Resuelto No 4: Edades de la Familia (Pág 48)", UIUtils.COLOR_ACCENTO1);
        JTextArea txtEnunciado = new JTextArea(
            "A la mamá de Juan le preguntan su edad y contesta:\n" +
            "  • Tengo 3 hijos, pregúntele a Juan su edad.\n" +
            "  • Alberto tiene 2/3 de la edad de Juan.\n" +
            "  • Ana tiene 4/3 de la edad de Juan.\n" +
            "  • Mi edad es la suma de las tres edades.\n\n" +
            "Fórmulas Matemáticas:\n" +
            "  Alberto = (2/3) · Juan   |   Ana = (4/3) · Juan   |   Mamá = Juan + Alberto + Ana"
        );
        txtEnunciado.setFont(UIUtils.FUENTE_NORMAL);
        txtEnunciado.setForeground(UIUtils.COLOR_TEXTO);
        txtEnunciado.setBackground(UIUtils.COLOR_PANEL);
        txtEnunciado.setEditable(false);
        pnlEnunciado.add(txtEnunciado, BorderLayout.CENTER);

        // ── Tarjeta Centro: Controles e Interacción ─────────────────────
        JPanel pnlCentro = new JPanel(new GridLayout(1, 2, 15, 0));
        pnlCentro.setBackground(UIUtils.COLOR_FONDO);

        // Panel Izquierdo: Inputs
        JPanel pnlInput = UIUtils.crearPanelTarjeta("Parámetros de Entrada", UIUtils.COLOR_ACCENTO3);
        pnlInput.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblEdad = new JLabel("Edad de Juan (años):");
        lblEdad.setFont(UIUtils.FUENTE_BOLD);
        pnlInput.add(lblEdad, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        txtEdadJuan = new JTextField("9", 6);
        UIUtils.estilizarCampoTexto(txtEdadJuan);
        pnlInput.add(txtEdadJuan, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        sliderEdadJuan = new JSlider(1, 50, 9);
        sliderEdadJuan.setBackground(UIUtils.COLOR_PANEL);
        sliderEdadJuan.setForeground(UIUtils.COLOR_TEXTO_DIM);
        sliderEdadJuan.setMajorTickSpacing(10);
        sliderEdadJuan.setMinorTickSpacing(1);
        sliderEdadJuan.setPaintTicks(true);
        sliderEdadJuan.setPaintLabels(true);
        pnlInput.add(sliderEdadJuan, gbc);

        gbc.gridy = 2;
        JButton btnCalcular = new JButton("⚡ Calcular Edades");
        UIUtils.estilizarBotonAccion(btnCalcular, UIUtils.COLOR_ACCENTO1);
        pnlInput.add(btnCalcular, gbc);

        // Panel Derecho: Resultados Rápidos
        JPanel pnlTarget = UIUtils.crearPanelTarjeta("Desglose de Edades Calculadas", UIUtils.COLOR_ACCENTO2);
        JPanel pnlValores = new JPanel(new GridLayout(4, 1, 8, 8));
        pnlValores.setBackground(UIUtils.COLOR_PANEL);

        lblResJuan = crearEtiquetaResultado("Juan: 9.00 años");
        lblResAlberto = crearEtiquetaResultado("Alberto (2/3): 6.00 años");
        lblResAna = crearEtiquetaResultado("Ana (4/3): 12.00 años");
        lblResMama = crearEtiquetaResultado("Mamá (Suma): 27.00 años");

        pnlValores.add(lblResJuan);
        pnlValores.add(lblResAlberto);
        pnlValores.add(lblResAna);
        pnlValores.add(lblResMama);
        pnlTarget.add(pnlValores, BorderLayout.CENTER);

        pnlCentro.add(pnlInput);
        pnlCentro.add(pnlTarget);

        // ── Tarjeta Sur: Consola de Salida ──────────────────────────────
        JPanel pnlSur = UIUtils.crearPanelTarjeta("Registro de Consola y Ejecución", UIUtils.COLOR_TEXTO_DIM);
        txtResultado = new JTextArea(6, 40);
        pnlSur.add(UIUtils.crearConsolaEstilizada(txtResultado), BorderLayout.CENTER);

        add(pnlEnunciado, BorderLayout.NORTH);
        add(pnlCentro, BorderLayout.CENTER);
        add(pnlSur, BorderLayout.SOUTH);

        // Eventos
        btnCalcular.addActionListener(e -> calcular());
        sliderEdadJuan.addChangeListener(e -> {
            txtEdadJuan.setText(String.valueOf(sliderEdadJuan.getValue()));
            calcular();
        });
        txtEdadJuan.addActionListener(e -> {
            try {
                int val = Integer.parseInt(txtEdadJuan.getText().trim());
                if (val >= 1 && val <= 50) sliderEdadJuan.setValue(val);
            } catch (Exception ignored) {}
            calcular();
        });

        calcular();
    }

    private JLabel crearEtiquetaResultado(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(UIUtils.FUENTE_BOLD);
        lbl.setForeground(UIUtils.COLOR_TEXTO);
        lbl.setBorder(new EmptyBorder(4, 8, 4, 8));
        return lbl;
    }

    private void calcular() {
        try {
            double edadJ = Double.parseDouble(txtEdadJuan.getText().trim());
            EjercicioResuelto4 ej = new EjercicioResuelto4(edadJ);

            lblResJuan.setText(String.format("Juan: %.2f años", ej.getEdadJuan()));
            lblResAlberto.setText(String.format("Alberto (2/3): %.2f años", ej.getEdadAlberto()));
            lblResAna.setText(String.format("Ana (4/3): %.2f años", ej.getEdadAna()));
            lblResMama.setText(String.format("Mamá (Suma): %.2f años", ej.getEdadMama()));

            StringBuilder sb = new StringBuilder();
            sb.append(">>> EJECUCIÓN DEL EJERCICIO RESUELTO NO 4 <<<\n");
            sb.append(String.format("  • Edad de Juan    = %.2f años\n", ej.getEdadJuan()));
            sb.append(String.format("  • Edad de Alberto = (2/3) * %.2f = %.2f años\n", ej.getEdadJuan(), ej.getEdadAlberto()));
            sb.append(String.format("  • Edad de Ana     = (4/3) * %.2f = %.2f años\n", ej.getEdadJuan(), ej.getEdadAna()));
            sb.append(String.format("  • Edad de la Mamá = %.2f + %.2f + %.2f = %.2f años\n\n",
                ej.getEdadJuan(), ej.getEdadAlberto(), ej.getEdadAna(), ej.getEdadMama()));
            sb.append(ej.esEdadExacta() ? "  [INFORMACIÓN] La edad de Juan permite edades enteras exactas." :
                                         "  [INFORMACIÓN] La edad de Juan genera edades con fracciones de año.");
            txtResultado.setText(sb.toString());

        } catch (Exception ex) {
            txtResultado.setText("ERROR: Por favor ingrese un número válido para la edad de Juan.");
        }
    }
}
