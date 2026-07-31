import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

import MenuPrincipal.VentanaPrincipalActividad1;

/**
 * CapturadorGUI - Genera capturas de pantalla PNG para la documentación de la Actividad 1.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 2.0/2026
 */
public class CapturadorGUI {

    public static void main(String[] args) {
        System.out.println("Iniciando generación de capturas PNG para la Actividad 1...");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            try {
                File dirImages = new File("doc/images");
                if (!dirImages.exists()) dirImages.mkdirs();

                VentanaPrincipalActividad1 vMain = new VentanaPrincipalActividad1();
                vMain.setSize(1050, 720);
                vMain.setLocationRelativeTo(null);
                vMain.setVisible(true);
                Thread.sleep(400);

                // 1. Captura Menú Principal (Ejercicio 4)
                capturarFrame(vMain, "doc/images/gui_menu_principal.png");
                capturarFrame(vMain, "doc/images/gui_ejercicio_resuelto_4.png");

                // 2. Ejercicio 5
                vMain.cambiarTarjeta(1);
                Thread.sleep(300);
                capturarFrame(vMain, "doc/images/gui_ejercicio_resuelto_5.png");

                // 3. Ejercicio 12
                vMain.cambiarTarjeta(2);
                Thread.sleep(300);
                capturarFrame(vMain, "doc/images/gui_ejercicio_propuesto_12.png");

                // 4. Ejercicio 14
                vMain.cambiarTarjeta(3);
                Thread.sleep(300);
                capturarFrame(vMain, "doc/images/gui_ejercicio_propuesto_14.png");

                // 5. Ejercicio 17
                vMain.cambiarTarjeta(4);
                Thread.sleep(300);
                capturarFrame(vMain, "doc/images/gui_ejercicio_propuesto_17.png");

                System.out.println("✓ ¡Todas las capturas de la Actividad 1 se guardaron con éxito!");
                vMain.dispose();
                System.exit(0);

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        });
    }

    private static void capturarFrame(JFrame frame, String pathOut) throws Exception {
        BufferedImage image = new BufferedImage(
            frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        frame.paint(g2d);
        g2d.dispose();

        File fileOut = new File(pathOut);
        ImageIO.write(image, "png", fileOut);
        System.out.println("✓ Capturada: " + fileOut.getName());
    }
}
