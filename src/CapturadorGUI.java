import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

import MenuPrincipal.VentanaPrincipalActividad1;
import ejercicio_resuelto_4.VentanaEjercicioResuelto4;
import ejercicio_resuelto_5.VentanaEjercicioResuelto5;
import ejercicio_propuesto_12.VentanaEjercicioPropuesto12;
import ejercicio_propuesto_14.VentanaEjercicioPropuesto14;
import ejercicio_propuesto_17.VentanaEjercicioPropuesto17;

/**
 * CapturadorGUI - Genera capturas de pantalla PNG para la documentación de la Actividad 1.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 1.0/2026
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

                // 1. Menú Principal
                VentanaPrincipalActividad1 vMain = new VentanaPrincipalActividad1();
                vMain.setSize(520, 440);
                capturarFrame(vMain, "doc/images/gui_menu_principal.png");

                // 2. Ejercicio Resuelto 4
                VentanaEjercicioResuelto4 v4 = new VentanaEjercicioResuelto4();
                v4.setSize(700, 480);
                capturarFrame(v4, "doc/images/gui_ejercicio_resuelto_4.png");

                // 3. Ejercicio Resuelto 5
                VentanaEjercicioResuelto5 v5 = new VentanaEjercicioResuelto5();
                v5.setSize(720, 500);
                capturarFrame(v5, "doc/images/gui_ejercicio_resuelto_5.png");

                // 4. Ejercicio Propuesto 12
                VentanaEjercicioPropuesto12 v12 = new VentanaEjercicioPropuesto12();
                v12.setSize(780, 520);
                capturarFrame(v12, "doc/images/gui_ejercicio_propuesto_12.png");

                // 5. Ejercicio Propuesto 14
                VentanaEjercicioPropuesto14 v14 = new VentanaEjercicioPropuesto14();
                v14.setSize(680, 460);
                capturarFrame(v14, "doc/images/gui_ejercicio_propuesto_14.png");

                // 6. Ejercicio Propuesto 17
                VentanaEjercicioPropuesto17 v17 = new VentanaEjercicioPropuesto17();
                v17.setSize(700, 480);
                capturarFrame(v17, "doc/images/gui_ejercicio_propuesto_17.png");

                System.out.println("✓ ¡Todas las capturas de la Actividad 1 se guardaron con éxito!");
                System.exit(0);

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        });
    }

    private static void capturarFrame(JFrame frame, String pathOut) throws Exception {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Thread.sleep(350);

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
        frame.dispose();
    }
}
