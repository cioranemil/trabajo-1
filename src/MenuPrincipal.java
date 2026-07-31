import MenuPrincipal.VentanaPrincipalActividad1;

/**
 * MenuPrincipal - Lanzador ejecutable principal de Actividad 1.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 1.0/2026
 */
public class MenuPrincipal {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new VentanaPrincipalActividad1().setVisible(true);
        });
    }
}
