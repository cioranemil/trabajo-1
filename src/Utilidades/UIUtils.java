package Utilidades;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Utilidades de Interfaz Gráfica para la Actividad 1.
 * Define la paleta de colores y estilos visuales para los componentes Swing.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 3.0/2026
 */
public class UIUtils {

    public enum Tema {
        CATPPUCCIN_DARK("Catppuccin Oscuro"),
        CATPPUCCIN_LIGHT("Catppuccin Claro"),
        DRACULA("Dracula"),
        NORD("Nord");

        private final String nombre;
        Tema(String nombre) { this.nombre = nombre; }
        public String getNombre() { return nombre; }
        @Override public String toString() { return nombre; }
    }

    public static Tema temaActual = Tema.CATPPUCCIN_DARK;

    // Colores por defecto
    public static Color COLOR_FONDO       = new Color(30, 30, 46);
    public static Color COLOR_PANEL       = new Color(49, 50, 68);
    public static Color COLOR_TARJETA     = new Color(69, 71, 90);
    public static Color COLOR_BORDES      = new Color(88, 91, 112);
    
    public static Color COLOR_ACCENTO1    = new Color(137, 180, 250);
    public static Color COLOR_ACCENTO2    = new Color(166, 227, 161);
    public static Color COLOR_ACCENTO3    = new Color(249, 226, 175);
    public static Color COLOR_ACCENTO4    = new Color(243, 139, 168);
    public static Color COLOR_ACCENTO5    = new Color(203, 166, 247);

    public static Color COLOR_TEXTO       = new Color(205, 214, 244);
    public static Color COLOR_TEXTO_DIM   = new Color(166, 173, 200);
    public static Color COLOR_CONSOLE_BG  = new Color(24, 24, 37);

    // Tipografía
    public static final Font FUENTE_TITULO     = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font FUENTE_SUBTITULO  = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FUENTE_NORMAL     = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FUENTE_BOLD       = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FUENTE_CONSOLA    = new Font("Consolas", Font.PLAIN, 13);

    public static void cargarPaleta(Tema tema) {
        temaActual = tema;
        switch (tema) {
            case CATPPUCCIN_LIGHT:
                COLOR_FONDO       = new Color(239, 241, 245);
                COLOR_PANEL       = new Color(230, 233, 239);
                COLOR_TARJETA     = new Color(204, 208, 218);
                COLOR_BORDES      = new Color(172, 176, 190);
                COLOR_ACCENTO1    = new Color(30, 102, 245);
                COLOR_ACCENTO2    = new Color(64, 160, 43);
                COLOR_ACCENTO3    = new Color(223, 142, 29);
                COLOR_ACCENTO4    = new Color(210, 15, 57);
                COLOR_ACCENTO5    = new Color(136, 57, 239);
                COLOR_TEXTO       = new Color(76, 79, 105);
                COLOR_TEXTO_DIM   = new Color(108, 111, 133);
                COLOR_CONSOLE_BG  = new Color(242, 243, 247);
                break;

            case DRACULA:
                COLOR_FONDO       = new Color(40, 42, 54);
                COLOR_PANEL       = new Color(68, 71, 90);
                COLOR_TARJETA     = new Color(98, 114, 164);
                COLOR_BORDES      = new Color(98, 114, 164);
                COLOR_ACCENTO1    = new Color(139, 233, 253);
                COLOR_ACCENTO2    = new Color(80, 250, 123);
                COLOR_ACCENTO3    = new Color(241, 250, 140);
                COLOR_ACCENTO4    = new Color(255, 121, 198);
                COLOR_ACCENTO5    = new Color(189, 147, 249);
                COLOR_TEXTO       = new Color(248, 248, 242);
                COLOR_TEXTO_DIM   = new Color(189, 147, 249);
                COLOR_CONSOLE_BG  = new Color(33, 34, 44);
                break;

            case NORD:
                COLOR_FONDO       = new Color(46, 52, 64);
                COLOR_PANEL       = new Color(59, 66, 82);
                COLOR_TARJETA     = new Color(67, 76, 94);
                COLOR_BORDES      = new Color(76, 86, 106);
                COLOR_ACCENTO1    = new Color(136, 192, 208);
                COLOR_ACCENTO2    = new Color(163, 190, 140);
                COLOR_ACCENTO3    = new Color(235, 203, 139);
                COLOR_ACCENTO4    = new Color(191, 97, 106);
                COLOR_ACCENTO5    = new Color(180, 142, 173);
                COLOR_TEXTO       = new Color(236, 239, 244);
                COLOR_TEXTO_DIM   = new Color(216, 222, 233);
                COLOR_CONSOLE_BG  = new Color(36, 41, 51);
                break;

            case CATPPUCCIN_DARK:
            default:
                COLOR_FONDO       = new Color(30, 30, 46);
                COLOR_PANEL       = new Color(49, 50, 68);
                COLOR_TARJETA     = new Color(69, 71, 90);
                COLOR_BORDES      = new Color(88, 91, 112);
                COLOR_ACCENTO1    = new Color(137, 180, 250);
                COLOR_ACCENTO2    = new Color(166, 227, 161);
                COLOR_ACCENTO3    = new Color(249, 226, 175);
                COLOR_ACCENTO4    = new Color(243, 139, 168);
                COLOR_ACCENTO5    = new Color(203, 166, 247);
                COLOR_TEXTO       = new Color(205, 214, 244);
                COLOR_TEXTO_DIM   = new Color(166, 173, 200);
                COLOR_CONSOLE_BG  = new Color(24, 24, 37);
                break;
        }
    }

    public static void aplicarTema() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Panel.background", COLOR_FONDO);
            UIManager.put("Label.font", FUENTE_NORMAL);
            UIManager.put("Label.foreground", COLOR_TEXTO);
            UIManager.put("Button.font", FUENTE_BOLD);
            UIManager.put("TextField.font", FUENTE_NORMAL);
            UIManager.put("TextArea.font", FUENTE_CONSOLA);
        } catch (Exception ignored) {}
    }

    public static void estilizarBotonNavegacion(JButton boton, boolean seleccionado) {
        boton.setFont(FUENTE_BOLD);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setHorizontalAlignment(SwingConstants.LEFT);
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
    }

    public static void estilizarBotonAccion(JButton boton, Color colorFondo) {
        boton.setFont(FUENTE_BOLD);
        boton.setBackground(colorFondo);
        boton.setForeground(COLOR_CONSOLE_BG);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(new CompoundBorder(
            new LineBorder(colorFondo.brighter(), 1, true),
            new EmptyBorder(8, 16, 8, 16)
        ));
    }

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
