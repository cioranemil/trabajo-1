package MenuPrincipal;

import Utilidades.UIUtils;
import ejercicio_resuelto_4.VentanaEjercicioResuelto4;
import ejercicio_resuelto_5.VentanaEjercicioResuelto5;
import ejercicio_propuesto_12.VentanaEjercicioPropuesto12;
import ejercicio_propuesto_14.VentanaEjercicioPropuesto14;
import ejercicio_propuesto_17.VentanaEjercicioPropuesto17;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * VentanaPrincipalActividad1 - Menú Principal Unificado para la Actividad 1.
 * Ofrece una interfaz gráfica moderna de alto rendimiento en Java Swing
 * inspirada en la arquitectura y estética de la Actividad 6.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 2.0/2026
 */
public class VentanaPrincipalActividad1 extends JFrame {

    private CardLayout cardLayout;
    private JPanel panelContenido;
    private JButton[] botonesNavegacion;

    public VentanaPrincipalActividad1() {
        UIUtils.aplicarTema();

        setTitle("UNAL - POO Actividad 1 - Lógica de Programación");
        setSize(1050, 720);
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelRaiz = new JPanel(new BorderLayout());
        panelRaiz.setBackground(UIUtils.COLOR_FONDO);

        // ── ENCABEZADO NORTE ──────────────────────────────────────────
        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(UIUtils.COLOR_PANEL);
        pnlHeader.setBorder(new EmptyBorder(12, 20, 12, 20));

        JLabel lblTitulo = new JLabel("UNIVERSIDAD NACIONAL DE COLOMBIA — POO ACTIVIDAD 1");
        lblTitulo.setFont(UIUtils.FUENTE_TITULO);
        lblTitulo.setForeground(UIUtils.COLOR_ACCENTO1);

        JLabel lblSub = new JLabel("Lógica de Programación (Efraín Oviedo) | Autor: Cristian Ruiz Hernandez | Repo: github.com/cioranemil/trabajo-1");
        lblSub.setFont(UIUtils.FUENTE_SUBTITULO);
        lblSub.setForeground(UIUtils.COLOR_TEXTO_DIM);

        pnlHeader.add(lblTitulo, BorderLayout.NORTH);
        pnlHeader.add(lblSub, BorderLayout.SOUTH);

        // ── PANEL LATERAL (SIDEBAR DE NAVEGACIÓN) ────────────────────
        JPanel pnlSidebar = new JPanel(new GridLayout(6, 1, 0, 8));
        pnlSidebar.setBackground(UIUtils.COLOR_PANEL);
        pnlSidebar.setPreferredSize(new Dimension(280, 0));
        pnlSidebar.setBorder(new EmptyBorder(15, 12, 15, 12));

        JLabel lblSecciones = new JLabel("  SECCIONES Y EJERCICIOS");
        lblSecciones.setFont(UIUtils.FUENTE_SUBTITULO);
        lblSecciones.setForeground(UIUtils.COLOR_ACCENTO3);
        pnlSidebar.add(lblSecciones);

        String[] titulos = {
            "1. Resuelto #4: Edades Familia",
            "2. Resuelto #5: Prueba Escritorio",
            "3. Propuesto #12: Salarios",
            "4. Propuesto #14: Cuadrado y Cubo",
            "5. Propuesto #17: Geometría Círculo"
        };

        botonesNavegacion = new JButton[5];
        for (int i = 0; i < titulos.length; i++) {
            final int index = i;
            botonesNavegacion[i] = new JButton(titulos[i]);
            UIUtils.estilizarBotonNavegacion(botonesNavegacion[i], i == 0);
            botonesNavegacion[i].addActionListener(e -> cambiarTarjeta(index));
            pnlSidebar.add(botonesNavegacion[i]);
        }

        // ── PANEL DE CONTENIDO (CARD LAYOUT) ──────────────────────────
        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);
        panelContenido.setBackground(UIUtils.COLOR_FONDO);

        panelContenido.add(new VentanaEjercicioResuelto4(), "CARD_0");
        panelContenido.add(new VentanaEjercicioResuelto5(), "CARD_1");
        panelContenido.add(new VentanaEjercicioPropuesto12(), "CARD_2");
        panelContenido.add(new VentanaEjercicioPropuesto14(), "CARD_3");
        panelContenido.add(new VentanaEjercicioPropuesto17(), "CARD_4");

        panelRaiz.add(pnlHeader, BorderLayout.NORTH);
        panelRaiz.add(pnlSidebar, BorderLayout.WEST);
        panelRaiz.add(panelContenido, BorderLayout.CENTER);

        add(panelRaiz);
    }

    public void cambiarTarjeta(int index) {
        for (int i = 0; i < botonesNavegacion.length; i++) {
            UIUtils.estilizarBotonNavegacion(botonesNavegacion[i], i == index);
        }
        cardLayout.show(panelContenido, "CARD_" + index);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipalActividad1().setVisible(true);
        });
    }
}
