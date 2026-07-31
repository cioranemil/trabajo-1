package Utilidades;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Utilidades de Interfaz Gráfica para la Actividad 1.
 * Implementa la paleta de diseño moderna de alto contraste (Catppuccin Macchiato/Mocha)
 * con tipografía cuidada, tarjetas con sombras, bordes suaves e interacción refinada.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 2.0/2026
 */
public class UIUtils {

    // ── Paleta de Colores (Catppuccin Macchiato / Mocha) ─────────────
    public static final Color COLOR_FONDO       = new Color(30, 30, 46);     // Dark Base #1E1E2E
    public static final Color COLOR_PANEL       = new Color(49, 50, 68);     // Surface 0 #313244
    public static final Color COLOR_TARJETA     = new Color(69, 71, 90);     // Surface 1 #45475A
    public static final Color COLOR_BORDES      = new Color(88, 91, 112);    // Surface 2 #585B70
    
    public static final Color COLOR_ACCENTO1    = new Color(137, 180, 250);  // Blue #89B4FA
    public static final Color COLOR_ACCENTO2    = new Color(166, 227, 161);  // Green #A6E3A1
    public static final Color COLOR_ACCENTO3    = new Color(249, 226, 175);  // Yellow #F9E2AF
    public static final Color COLOR_ACCENTO4    = new Color(243, 139, 168);  // Red/Pink #F38BA8
    public static final Color COLOR_ACCENTO5    = new Color(203, 166, 247);  // Mauve #CBA6F7

    public static final Color COLOR_TEXTO       = new Color(205, 214, 244);  // Text #CDD6F4
    public static final Color COLOR_TEXTO_DIM   = new Color(166, 173, 200);  // Subtext 0 #A6ADC8
    public static final Color COLOR_CONSOLE_BG  = new Color(24, 24, 37);     // Mantle #181825

    // ── Fuentes Tipográficas ──────────────────────────────────────────
    public static final Font FUENTE_TITULO     = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font FUENTE_SUBTITULO  = new Font("Segoe UI", Font.BOLD, 15);
    public static final Font FUENTE_NORMAL     = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FUENTE_BOLD       = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FUENTE_CONSOLA    = new Font("Consolas", Font.PLAIN, 13);

    /**
     * Aplica los valores por defecto del tema en UIManager para Java Swing.
     */
    public static void aplicarTema() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Panel.background", COLOR_FONDO);
            UIManager.put("Label.font", FUENTE_NORMAL);
            UIManager.put("Label.foreground", COLOR_TEXTO);
            UIManager.put("Button.font", FUENTE_BOLD);
            UIManager.put("TextField.font", FUENTE_NORMAL);
            UIManager.put("TextArea.font", FUENTE_CONSOLA);
            UIManager.put("Table.font", FUENTE_NORMAL);
            UIManager.put("TableHeader.font", FUENTE_SUBTITULO);
        } catch (Exception ignored) {}
    }

    /**
     * Estiliza un botón para la barra lateral de navegación.
     */
    public static void estilizarBotonNavegacion(JButton boton, boolean seleccionado) {
        boton.setFont(FUENTE_BOLD);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setHorizontalAlignment(SwingConstants.LEFT);
        boton.setIconTextGap(10);
        boton.setBorder(new CompoundBorder(
            new LineBorder(seleccionado ? COLOR_ACCENTO1 : COLOR_BORDES, 1, true),
            new EmptyBorder(10, 15, 10, 15)
        ));
        
        if (seleccionado) {
            boton.setBackground(COLOR_ACCENTO1);
            boton.setForeground(COLOR_CONSOLE_BG);
        } else {
            boton.setBackground(COLOR_TARJETA);
            boton.setForeground(COLOR_TEXTO);
        }

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (!boton.getBackground().equals(COLOR_ACCENTO1)) {
                    boton.setBackground(COLOR_BORDES);
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (!boton.getBackground().equals(COLOR_ACCENTO1)) {
                    boton.setBackground(COLOR_TARJETA);
                }
            }
        });
    }

    /**
     * Estiliza un botón de acción principal (Ejecutar / Calcular).
     */
    public static void estilizarBotonAccion(JButton boton, Color colorFondo) {
        boton.setFont(FUENTE_BOLD);
        boton.setBackground(colorFondo);
        boton.setForeground(COLOR_CONSOLE_BG);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(new CompoundBorder(
            new LineBorder(colorFondo.brighter(), 1, true),
            new EmptyBorder(8, 20, 8, 20)
        ));
    }

    /**
     * Crea un panel tipo tarjeta con bordes suaves y margen interior.
     */
    public static JPanel crearPanelTarjeta(String titulo, Color colorBorde) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(COLOR_PANEL);
        Border bordeLineal = new LineBorder(colorBorde != null ? colorBorde : COLOR_BORDES, 1, true);
        Border bordeMargen = new EmptyBorder(12, 16, 12, 16);
        panel.setBorder(new CompoundBorder(bordeLineal, bordeMargen));

        if (titulo != null && !titulo.isEmpty()) {
            JLabel lbl = new JLabel(titulo);
            lbl.setFont(FUENTE_SUBTITULO);
            lbl.setForeground(colorBorde != null ? colorBorde : COLOR_ACCENTO1);
            lbl.setBorder(new EmptyBorder(0, 0, 8, 0));
            panel.add(lbl, BorderLayout.NORTH);
        }
        return panel;
    }

    /**
     * Estiliza una caja de texto interactiva.
     */
    public static void estilizarCampoTexto(JTextField campo) {
        campo.setFont(FUENTE_NORMAL);
        campo.setBackground(COLOR_CONSOLE_BG);
        campo.setForeground(COLOR_TEXTO);
        campo.setCaretColor(COLOR_ACCENTO1);
        campo.setBorder(new CompoundBorder(
            new LineBorder(COLOR_BORDES, 1, true),
            new EmptyBorder(6, 10, 6, 10)
        ));
    }

    /**
     * Estiliza un área de texto tipo consola de salida.
     */
    public static JScrollPane crearConsolaEstilizada(JTextArea areaTexto) {
        areaTexto.setFont(FUENTE_CONSOLA);
        areaTexto.setBackground(COLOR_CONSOLE_BG);
        areaTexto.setForeground(COLOR_ACCENTO2);
        areaTexto.setCaretColor(COLOR_TEXTO);
        areaTexto.setEditable(false);
        areaTexto.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setBorder(new LineBorder(COLOR_BORDES, 1, true));
        scroll.getViewport().setBackground(COLOR_CONSOLE_BG);
        return scroll;
    }
}
