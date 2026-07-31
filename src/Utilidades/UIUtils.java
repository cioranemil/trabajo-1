package Utilidades;

import javax.swing.*;
import java.awt.*;

/**
 * Utilidades de Interfaz Gráfica para Actividad 1.
 * Mantiene la paleta cromática limpia y moderna.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 1.0/2026
 */
public class UIUtils {
    public static final Color COLOR_FONDO = new Color(245, 247, 250);
    public static final Color COLOR_PRIMARIO = new Color(52, 152, 219);
    public static final Color COLOR_SECUNDARIO = new Color(41, 128, 185);
    public static final Color COLOR_TEXTO = new Color(44, 62, 80);
    public static final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font FUENTE_SUBTITULO = new Font("Segoe UI", Font.BOLD, 15);
    public static final Font FUENTE_NORMAL = new Font("Segoe UI", Font.PLAIN, 14);

    public static void aplicarTema() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Panel.background", COLOR_FONDO);
            UIManager.put("Label.font", FUENTE_NORMAL);
            UIManager.put("Label.foreground", COLOR_TEXTO);
            UIManager.put("Button.font", FUENTE_NORMAL);
            UIManager.put("TextField.font", FUENTE_NORMAL);
            UIManager.put("TextArea.font", new Font("Consolas", Font.PLAIN, 13));
            UIManager.put("Table.font", FUENTE_NORMAL);
            UIManager.put("TableHeader.font", FUENTE_SUBTITULO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void estilizarBoton(JButton boton) {
        boton.setFont(FUENTE_NORMAL);
        boton.setForeground(new Color(30, 30, 30));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setFocusPainted(false);
    }

    public static void estilizarPanel(JPanel panel) {
        panel.setBackground(COLOR_FONDO);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
    }
}
