package ejercicio_propuesto_17;

/**
 * Ejercicio Propuesto No 17 (Página 50 - Efraín Oviedo).
 * Enunciado:
 * Dado el radio de un círculo. Haga un algoritmo que obtenga el área del círculo y la longitud de la circunferencia.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 1.0/2026
 */
public class EjercicioPropuesto17 {
    private double radio;
    private double area;
    private double longitudCircunferencia;

    public EjercicioPropuesto17(double radio) {
        if (radio <= 0) {
            throw new IllegalArgumentException("El radio debe ser un número positivo mayor a 0.");
        }
        this.radio = radio;
        calcularGeometria();
    }

    private void calcularGeometria() {
        this.area = Math.PI * Math.pow(radio, 2);
        this.longitudCircunferencia = 2 * Math.PI * radio;
    }

    public double getRadio() { return radio; }
    public double getArea() { return area; }
    public double getLongitudCircunferencia() { return longitudCircunferencia; }
    public double getDiametro() { return 2 * radio; }

    public void imprimir() {
        System.out.println("=== EJERCICIO PROPUESTO NO 17: GEOMETRÍA DEL CÍRCULO ===");
        System.out.printf("  Radio (r)                  : %.2f cm\n", radio);
        System.out.printf("  Diámetro (2r)              : %.2f cm\n", getDiametro());
        System.out.printf("  Área del Círculo (π·r²)    : %.4f cm²\n", area);
        System.out.printf("  Longitud Circunferencia(2πr): %.4f cm\n", longitudCircunferencia);
    }

    public static void main(String[] args) {
        EjercicioPropuesto17 ej = new EjercicioPropuesto17(5);
        ej.imprimir();
    }
}
