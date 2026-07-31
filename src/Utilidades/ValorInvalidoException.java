package Utilidades;

/**
 * Excepción personalizada para el control de errores y tratamiento de datos
 * fuera de rango, no numéricos o incoherentes en la Actividad 1.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 3.0/2026
 */
public class ValorInvalidoException extends Exception {

    private final String campo;

    public ValorInvalidoException(String mensaje, String campo) {
        super(mensaje);
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }

    @Override
    public String toString() {
        return "Error en el campo [" + campo + "]: " + getMessage();
    }
}
