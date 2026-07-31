package ejercicio_resuelto_5;

import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio Resuelto No 5 (Páginas 49 a 50 - Efraín Oviedo).
 * Realiza la prueba de escritorio (seguimiento paso a paso) del siguiente grupo de instrucciones:
 * INICIO
 *   SUMA = 0
 *   X = 20
 *   SUMA = SUMA + X
 *   Y = 40
 *   X = X + Y ** 2
 *   SUMA = SUMA + X / Y
 *   ESCRIBA: "EL VALOR DE LA SUMA ES:", SUMA
 * FIN_INICIO
 * 
 * @author Cristian Ruiz Hernandez
 * @version 1.0/2026
 */
public class EjercicioResuelto5 {
    private double suma;
    private double x;
    private double y;
    private List<PasoPruebaEscritorio> pasos;

    public static class PasoPruebaEscritorio {
        private int paso;
        private String instruccion;
        private double valorX;
        private double valorY;
        private double valorSuma;

        public PasoPruebaEscritorio(int paso, String instruccion, double valorX, double valorY, double valorSuma) {
            this.paso = paso;
            this.instruccion = instruccion;
            this.valorX = valorX;
            this.valorY = valorY;
            this.valorSuma = valorSuma;
        }

        public int getPaso() { return paso; }
        public String getInstruccion() { return instruccion; }
        public double getValorX() { return valorX; }
        public double getValorY() { return valorY; }
        public double getValorSuma() { return valorSuma; }
    }

    public EjercicioResuelto5() {
        this(20.0, 40.0);
    }

    public EjercicioResuelto5(double xInicial, double yInicial) {
        this.pasos = new ArrayList<>();
        ejecutarPruebaEscritorio(xInicial, yInicial);
    }

    private void ejecutarPruebaEscritorio(double xInicial, double yInicial) {
        pasos.clear();
        this.suma = 0;
        pasos.add(new PasoPruebaEscritorio(1, "SUMA = 0", 0, 0, suma));

        this.x = xInicial;
        pasos.add(new PasoPruebaEscritorio(2, "X = " + xInicial, x, 0, suma));

        this.suma = this.suma + this.x;
        pasos.add(new PasoPruebaEscritorio(3, "SUMA = SUMA + X (" + (suma - x) + " + " + x + ")", x, 0, suma));

        this.y = yInicial;
        pasos.add(new PasoPruebaEscritorio(4, "Y = " + yInicial, x, y, suma));

        double xAnterior = this.x;
        this.x = this.x + Math.pow(this.y, 2);
        pasos.add(new PasoPruebaEscritorio(5, "X = X + Y**2 (" + xAnterior + " + " + Math.pow(y, 2) + ")", x, y, suma));

        double sumaAnterior = this.suma;
        this.suma = this.suma + (this.x / this.y);
        pasos.add(new PasoPruebaEscritorio(6, "SUMA = SUMA + X / Y (" + sumaAnterior + " + " + (x / y) + ")", x, y, suma));
    }

    public double getSumaFinal() { return suma; }
    public double getXFinal() { return x; }
    public double getYFinal() { return y; }
    public List<PasoPruebaEscritorio> getPasos() { return pasos; }

    public void imprimir() {
        System.out.println("=== PRUEBA DE ESCRITORIO — EJERCICIO RESUELTO NO 5 ===");
        for (PasoPruebaEscritorio p : pasos) {
            System.out.printf("Paso %d: %-35s | X=%.2f | Y=%.2f | SUMA=%.2f\n",
                p.getPaso(), p.getInstruccion(), p.getValorX(), p.getValorY(), p.getValorSuma());
        }
        System.out.printf("\nEL VALOR FINAL DE LA SUMA ES: %.2f\n", suma);
    }

    public static void main(String[] args) {
        EjercicioResuelto5 ej = new EjercicioResuelto5();
        ej.imprimir();
    }
}
