package MenuPrincipal;

import Utilidades.UIUtils;
import ejercicio_resuelto_4.VentanaEjercicioResuelto4;
import ejercicio_resuelto_5.VentanaEjercicioResuelto5;
import ejercicio_propuesto_12.VentanaEjercicioPropuesto12;
import ejercicio_propuesto_14.VentanaEjercicioPropuesto14;
import ejercicio_propuesto_17.VentanaEjercicioPropuesto17;

import javax.swing.*;
import java.awt.*;

/**
 * VentanaPrincipalActividad1 - Menú Principal para la Actividad 1.
 * Estructura limpia e interactiva idéntica a la Actividad 4.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 1.0/2026
 */
public class VentanaPrincipalActividad1 extends JFrame {

    public VentanaPrincipalActividad1() {
        UIUtils.aplicarTema();

        setTitle("Actividad 1 - Menú Principal");
        setSize(520, 440);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelCentral = new JPanel(new GridLayout(5, 1, 10, 12));
        UIUtils.estilizarPanel(panelCentral);

        JLabel lblTitulo = new JLabel("Lógica de Programación — Actividad 1", SwingConstants.CENTER);
        lblTitulo.setFont(UIUtils.FUENTE_TITULO);

        JLabel lblAutor = new JLabel("Cristian Ruiz Hernandez — UNAL 2026", SwingConstants.CENTER);
        lblAutor.setFont(UIUtils.FUENTE_SUBTITULO);
        lblAutor.setForeground(UIUtils.COLOR_TEXTO);
        
        JPanel pnlNorte = new JPanel(new BorderLayout());
        pnlNorte.setBackground(UIUtils.COLOR_FONDO);
        pnlNorte.add(lblTitulo, BorderLayout.NORTH);
        pnlNorte.add(lblAutor, BorderLayout.SOUTH);
        pnlNorte.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));

        JButton btnEj1 = new JButton("1. Ejercicio Resuelto No 4: Edades de la Familia (Pág 48)");
        UIUtils.estilizarBoton(btnEj1);
        btnEj1.addActionListener(e -> new VentanaEjercicioResuelto4().setVisible(true));

        JButton btnEj2 = new JButton("2. Ejercicio Resuelto No 5: Prueba de Escritorio (Pág 49)");
        UIUtils.estilizarBoton(btnEj2);
        btnEj2.addActionListener(e -> new VentanaEjercicioResuelto5().setVisible(true));

        JButton btnEj3 = new JButton("3. Ejercicio Propuesto No 12: Salario y Retención (Pág 50)");
        UIUtils.estilizarBoton(btnEj3);
        btnEj3.addActionListener(e -> new VentanaEjercicioPropuesto12().setVisible(true));

        JButton btnEj4 = new JButton("4. Ejercicio Propuesto No 14: Cuadrado y Cubo (Pág 50)");
        UIUtils.estilizarBoton(btnEj4);
        btnEj4.addActionListener(e -> new VentanaEjercicioPropuesto14().setVisible(true));

        JButton btnEj5 = new JButton("5. Ejercicio Propuesto No 17: Área Círculo y Perímetro (Pág 50)");
        UIUtils.estilizarBoton(btnEj5);
        btnEj5.addActionListener(e -> new VentanaEjercicioPropuesto17().setVisible(true));

        panelCentral.add(btnEj1);
        panelCentral.add(btnEj2);
        panelCentral.add(btnEj3);
        panelCentral.add(btnEj4);
        panelCentral.add(btnEj5);

        add(pnlNorte, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipalActividad1().setVisible(true);
        });
    }
}
