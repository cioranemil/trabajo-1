package MenuPrincipal;

import Utilidades.DialogoAcercaDe;
import Utilidades.UIUtils;
import Utilidades.VentanaHistorial;
import ejercicio_resuelto_4.VentanaEjercicioResuelto4;
import ejercicio_resuelto_5.VentanaEjercicioResuelto5;
import ejercicio_propuesto_12.VentanaEjercicioPropuesto12;
import ejercicio_propuesto_14.VentanaEjercicioPropuesto14;
import ejercicio_propuesto_17.VentanaEjercicioPropuesto17;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * VentanaPrincipalActividad1 - Menú Principal Unificado de la Actividad 1.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 3.0/2026
 */
public class VentanaPrincipalActividad1 extends JFrame {

    private CardLayout cardLayout;
    private JPanel panelContenido;
    private JButton[] botonesNavegacion;
    private JComboBox<UIUtils.Tema> comboTemas;

    private JPanel panelRaiz;
    private JPanel pnlHeader;
    private JPanel pnlSidebar;

    public VentanaPrincipalActividad1() {
        UIUtils.aplicarTema();

        setTitle("Actividad 1 - Lógica de Programación (UNAL)");
        setSize(1100, 740);
        setMinimumSize(new Dimension(950, 620));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelRaiz = new JPanel(new BorderLayout());
        panelRaiz.setBackground(UIUtils.COLOR_FONDO);

        // Header
        pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(UIUtils.COLOR_PANEL);
        pnlHeader.setBorder(new EmptyBorder(12, 20, 12, 20));

        JLabel lblTitulo = new JLabel("UNIVERSIDAD NACIONAL DE COLOMBIA - ACTIVIDAD 1");
        lblTitulo.setFont(UIUtils.FUENTE_TITULO);
        lblTitulo.setForeground(UIUtils.COLOR_ACCENTO1);

        JLabel lblSub = new JLabel("Lógica de Programación | Autor: Cristian Ruiz Hernandez | Repo: github.com/cioranemil/trabajo-1");
        lblSub.setFont(UIUtils.FUENTE_SUBTITULO);
        lblSub.setForeground(UIUtils.COLOR_TEXTO_DIM);

        JPanel pnlTitulos = new JPanel(new GridLayout(2, 1));
        pnlTitulos.setBackground(UIUtils.COLOR_PANEL);
        pnlTitulos.add(lblTitulo);
        pnlTitulos.add(lblSub);

        // Controles a la derecha
        JPanel pnlAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        pnlAcciones.setBackground(UIUtils.COLOR_PANEL);

        JLabel lblTema = new JLabel("Tema:");
        lblTema.setFont(UIUtils.FUENTE_BOLD);
        lblTema.setForeground(UIUtils.COLOR_TEXTO);

        comboTemas = new JComboBox<>(UIUtils.Tema.values());
        comboTemas.setFont(UIUtils.FUENTE_BOLD);
        comboTemas.addActionListener(e -> cambiarTema((UIUtils.Tema) comboTemas.getSelectedItem()));

        JButton btnHistorial = new JButton("Historial");
        UIUtils.estilizarBotonAccion(btnHistorial, UIUtils.COLOR_ACCENTO2);
        btnHistorial.addActionListener(e -> new VentanaHistorial(this).setVisible(true));

        JButton btnAcercaDe = new JButton("Acerca de");
        UIUtils.estilizarBotonAccion(btnAcercaDe, UIUtils.COLOR_ACCENTO1);
        btnAcercaDe.addActionListener(e -> new DialogoAcercaDe(this).setVisible(true));

        pnlAcciones.add(lblTema);
        pnlAcciones.add(comboTemas);
        pnlAcciones.add(btnHistorial);
        pnlAcciones.add(btnAcercaDe);

        pnlHeader.add(pnlTitulos, BorderLayout.WEST);
        pnlHeader.add(pnlAcciones, BorderLayout.EAST);

        // Sidebar
        pnlSidebar = new JPanel(new GridLayout(6, 1, 0, 8));
        pnlSidebar.setBackground(UIUtils.COLOR_PANEL);
        pnlSidebar.setPreferredSize(new Dimension(280, 0));
        pnlSidebar.setBorder(new EmptyBorder(15, 12, 15, 12));

        JLabel lblSecciones = new JLabel("  EJERCICIOS DISPONIBLES");
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

        // CardLayout
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

    private void cambiarTema(UIUtils.Tema nuevoTema) {
        if (nuevoTema == null) return;
        UIUtils.cargarPaleta(nuevoTema);
        UIUtils.aplicarTema();

        getContentPane().removeAll();
        
        panelRaiz = new JPanel(new BorderLayout());
        panelRaiz.setBackground(UIUtils.COLOR_FONDO);

        pnlHeader.setBackground(UIUtils.COLOR_PANEL);
        pnlSidebar.setBackground(UIUtils.COLOR_PANEL);
        panelContenido.setBackground(UIUtils.COLOR_FONDO);

        for (int i = 0; i < botonesNavegacion.length; i++) {
            UIUtils.estilizarBotonNavegacion(botonesNavegacion[i], i == 0);
        }

        panelRaiz.add(pnlHeader, BorderLayout.NORTH);
        panelRaiz.add(pnlSidebar, BorderLayout.WEST);
        panelRaiz.add(panelContenido, BorderLayout.CENTER);

        add(panelRaiz);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipalActividad1().setVisible(true);
        });
    }
}
