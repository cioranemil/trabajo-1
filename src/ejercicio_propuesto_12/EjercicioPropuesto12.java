package ejercicio_propuesto_12;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Ejercicio Propuesto No 12 (Página 50 - Efraín Oviedo).
 * Enunciado:
 * Un empleado trabaja 48 horas en la semana a razón de $5.000 hora.
 * El porcentaje de retención en la fuente es del 12.5% del salario bruto.
 * Se desea saber cuál es el salario bruto, la retención en la fuente y el salario neto del trabajador.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 1.0/2026
 */
public class EjercicioPropuesto12 {
    private String codigoEmpleado;
    private String nombres;
    private double horasTrabajadas;
    private double valorHora;
    private double porcentajeRetencion;

    private double salarioBruto;
    private double retencionFuente;
    private double salarioNeto;

    /**
     * Constructor con valores por defecto enunciados en el problema (48 horas, $5.000/hora, 12.5%).
     */
    public EjercicioPropuesto12() {
        this("EMP-001", "Empleado Estándar", 48.0, 5000.0, 12.5);
    }

    /**
     * Constructor parametrizado para personalizar la liquidación.
     */
    public EjercicioPropuesto12(String codigoEmpleado, String nombres, double horasTrabajadas, double valorHora, double porcentajeRetencion) {
        if (horasTrabajadas < 0 || valorHora < 0 || porcentajeRetencion < 0) {
            throw new IllegalArgumentException("Los valores de horas, tarifa y retención deben ser mayores o iguales a 0.");
        }
        this.codigoEmpleado = (codigoEmpleado == null || codigoEmpleado.trim().isEmpty()) ? "EMP-001" : codigoEmpleado.trim();
        this.nombres = (nombres == null || nombres.trim().isEmpty()) ? "Empleado" : nombres.trim();
        this.horasTrabajadas = horasTrabajadas;
        this.valorHora = valorHora;
        this.porcentajeRetencion = porcentajeRetencion;
        calcularSalarios();
    }

    private void calcularSalarios() {
        this.salarioBruto = horasTrabajadas * valorHora;
        this.retencionFuente = salarioBruto * (porcentajeRetencion / 100.0);
        this.salarioNeto = salarioBruto - retencionFuente;
    }

    public String getCodigoEmpleado() { return codigoEmpleado; }
    public String getNombres() { return nombres; }
    public double getHorasTrabajadas() { return horasTrabajadas; }
    public double getValorHora() { return valorHora; }
    public double getPorcentajeRetencion() { return porcentajeRetencion; }
    public double getSalarioBruto() { return salarioBruto; }
    public double getRetencionFuente() { return retencionFuente; }
    public double getSalarioNeto() { return salarioNeto; }

    public String formatoMoneda(double valor) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        return nf.format(valor);
    }

    public void imprimir() {
        System.out.println("=== LIQUIDACIÓN DE SALARIO — EJERCICIO PROPUESTO NO 12 ===");
        System.out.println("  Código Empleado     : " + codigoEmpleado);
        System.out.println("  Nombres             : " + nombres);
        System.out.printf("  Horas Trabajadas    : %.1f hrs\n", horasTrabajadas);
        System.out.println("  Valor Hora          : " + formatoMoneda(valorHora));
        System.out.printf("  Porcentaje Retención: %.2f%%\n", porcentajeRetencion);
        System.out.println("  ---------------------------------------------");
        System.out.println("  SALARIO BRUTO       : " + formatoMoneda(salarioBruto));
        System.out.println("  RETENCIÓN EN FUENTE : " + formatoMoneda(retencionFuente));
        System.out.println("  SALARIO NETO        : " + formatoMoneda(salarioNeto));
    }

    public static void main(String[] args) {
        EjercicioPropuesto12 ej = new EjercicioPropuesto12();
        ej.imprimir();
    }
}
