package ejercicio_resuelto_4;

/**
 * Ejercicio Resuelto No 4 (Páginas 48 a 49 - Efraín Oviedo).
 * Enunciado:
 * A la mamá de Juan le preguntan su edad, y contesta: tengo 3 hijos, pregúntele a Juan su edad.
 * Alberto tiene 2/3 de la edad de Juan, Ana tiene 4/3 de la edad de Juan y mi edad es la suma de las tres.
 * Hacer un algoritmo que muestre la edad de los cuatro.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 1.0/2026
 */
public class EjercicioResuelto4 {
    private double edadJuan;
    private double edadAlberto;
    private double edadAna;
    private double edadMama;

    public EjercicioResuelto4(double edadJuan) {
        if (edadJuan <= 0) {
            throw new IllegalArgumentException("La edad de Juan debe ser un número positivo mayor a 0.");
        }
        this.edadJuan = edadJuan;
        calcularEdades();
    }

    private void calcularEdades() {
        this.edadAlberto = (2.0 / 3.0) * edadJuan;
        this.edadAna = (4.0 / 3.0) * edadJuan;
        this.edadMama = edadJuan + edadAlberto + edadAna;
    }

    public double getEdadJuan() { return edadJuan; }
    public double getEdadAlberto() { return edadAlberto; }
    public double getEdadAna() { return edadAna; }
    public double getEdadMama() { return edadMama; }

    public boolean esEdadExacta() {
        return (edadJuan % 3 == 0);
    }

    public void imprimir() {
        System.out.println("=== EJERCICIO RESUELTO NO 4: EDADES DE LA FAMILIA ===");
        System.out.printf("  Edad de Juan    : %.2f años\n", edadJuan);
        System.out.printf("  Edad de Alberto : %.2f años (2/3 de la edad de Juan)\n", edadAlberto);
        System.out.printf("  Edad de Ana     : %.2f años (4/3 de la edad de Juan)\n", edadAna);
        System.out.printf("  Edad de la Mamá : %.2f años (Suma de las 3 edades)\n", edadMama);
    }

    public static void main(String[] args) {
        EjercicioResuelto4 ej = new EjercicioResuelto4(9);
        ej.imprimir();
    }
}
