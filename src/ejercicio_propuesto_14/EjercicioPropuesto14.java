package ejercicio_propuesto_14;

/**
 * Ejercicio Propuesto No 14 (Página 50 - Efraín Oviedo).
 * Enunciado:
 * Elaborar un algoritmo que lea un número y obtenga su cuadrado y su cubo.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 1.0/2026
 */
public class EjercicioPropuesto14 {
    private double numero;
    private double cuadrado;
    private double cubo;

    public EjercicioPropuesto14(double numero) {
        this.numero = numero;
        calcularPotencias();
    }

    private void calcularPotencias() {
        this.cuadrado = Math.pow(numero, 2);
        this.cubo = Math.pow(numero, 3);
    }

    public double getNumero() { return numero; }
    public double getCuadrado() { return cuadrado; }
    public double getCubo() { return cubo; }

    public double getRaizCuadrada() {
        return numero >= 0 ? Math.sqrt(numero) : Double.NaN;
    }

    public void imprimir() {
        System.out.println("=== EJERCICIO PROPUESTO NO 14: CUADRADO Y CUBO ===");
        System.out.printf("  Número de Entrada : %.2f\n", numero);
        System.out.printf("  Cuadrado (n^2)    : %.2f\n", cuadrado);
        System.out.printf("  Cubo (n^3)        : %.2f\n", cubo);
    }

    public static void main(String[] args) {
        EjercicioPropuesto14 ej = new EjercicioPropuesto14(5);
        ej.imprimir();
    }
}
