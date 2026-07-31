package Utilidades;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ManejadorPersistencia - Administrador de Persistencia de Datos Local.
 * Almacena y recupera automáticamente en disco (archivos JSON y CSV)
 * el historial de operaciones, liquidaciones de nómina y trazabilidades.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 3.0/2026
 */
public class ManejadorPersistencia {

    private static final String DIR_DATA = "data";
    private static final String ARCHIVO_JSON = "data/historial.json";
    private static final String ARCHIVO_CSV = "data/historial.csv";

    public static class RegistroHistorial {
        private String fecha;
        private String ejercicio;
        private String resumenEntrada;
        private String resultado;

        public RegistroHistorial(String ejercicio, String resumenEntrada, String resultado) {
            this.fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            this.ejercicio = ejercicio;
            this.resumenEntrada = resumenEntrada;
            this.resultado = resultado;
        }

        public RegistroHistorial(String fecha, String ejercicio, String resumenEntrada, String resultado) {
            this.fecha = fecha;
            this.ejercicio = ejercicio;
            this.resumenEntrada = resumenEntrada;
            this.resultado = resultado;
        }

        public String getFecha() { return fecha; }
        public String getEjercicio() { return ejercicio; }
        public String getResumenEntrada() { return resumenEntrada; }
        public String getResultado() { return resultado; }
    }

    public static synchronized void guardarRegistro(String ejercicio, String entradas, String resultados) {
        try {
            File dir = new File(DIR_DATA);
            if (!dir.exists()) dir.mkdirs();

            RegistroHistorial reg = new RegistroHistorial(ejercicio, entradas, resultados);
            List<RegistroHistorial> lista = cargarHistorial();
            lista.add(0, reg);

            // Guardar en JSON
            try (PrintWriter pwJson = new PrintWriter(new FileWriter(ARCHIVO_JSON))) {
                pwJson.println("[");
                for (int i = 0; i < lista.size(); i++) {
                    RegistroHistorial r = lista.get(i);
                    pwJson.println("  {");
                    pwJson.println("    \"fecha\": \"" + escapeJson(r.getFecha()) + "\",");
                    pwJson.println("    \"ejercicio\": \"" + escapeJson(r.getEjercicio()) + "\",");
                    pwJson.println("    \"entradas\": \"" + escapeJson(r.getResumenEntrada()) + "\",");
                    pwJson.println("    \"resultados\": \"" + escapeJson(r.getResultado()) + "\"");
                    pwJson.print("  }");
                    if (i < lista.size() - 1) pwJson.println(",");
                    else pwJson.println();
                }
                pwJson.println("]");
            }

            // Guardar en CSV
            try (PrintWriter pwCsv = new PrintWriter(new FileWriter(ARCHIVO_CSV, true))) {
                File fCsv = new File(ARCHIVO_CSV);
                if (fCsv.length() == 0) {
                    pwCsv.println("Fecha,Ejercicio,Entradas,Resultados");
                }
                pwCsv.printf("\"%s\",\"%s\",\"%s\",\"%s\"\n",
                    reg.getFecha(), escapeCsv(reg.getEjercicio()), escapeCsv(reg.getResumenEntrada()), escapeCsv(reg.getResultado()));
            }

        } catch (Exception e) {
            System.err.println("Error al guardar persistencia: " + e.getMessage());
        }
    }

    public static synchronized List<RegistroHistorial> cargarHistorial() {
        List<RegistroHistorial> lista = new ArrayList<>();
        File fileJson = new File(ARCHIVO_JSON);
        if (!fileJson.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(fileJson))) {
            String line;
            String fecha = "", ejercicio = "", entradas = "", resultados = "";
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("\"fecha\":")) {
                    fecha = extraerValorJson(line);
                } else if (line.startsWith("\"ejercicio\":")) {
                    ejercicio = extraerValorJson(line);
                } else if (line.startsWith("\"entradas\":")) {
                    entradas = extraerValorJson(line);
                } else if (line.startsWith("\"resultados\":")) {
                    resultados = extraerValorJson(line);
                    if (!ejercicio.isEmpty()) {
                        lista.add(new RegistroHistorial(fecha, ejercicio, entradas, resultados));
                        fecha = ""; ejercicio = ""; entradas = ""; resultados = "";
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar persistencia JSON: " + e.getMessage());
        }
        return lista;
    }

    public static synchronized void limpiarHistorial() {
        try {
            File fJson = new File(ARCHIVO_JSON);
            File fCsv = new File(ARCHIVO_CSV);
            if (fJson.exists()) fJson.delete();
            if (fCsv.exists()) fCsv.delete();
        } catch (Exception ignored) {}
    }

    private static String extraerValorJson(String linea) {
        int idxFirst = linea.indexOf("\": \"");
        if (idxFirst != -1) {
            int start = idxFirst + 4;
            int end = linea.lastIndexOf("\"");
            if (end > start) {
                return linea.substring(start, end);
            }
        }
        return "";
    }

    private static String escapeJson(String str) {
        return str == null ? "" : str.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", " ");
    }

    private static String escapeCsv(String str) {
        return str == null ? "" : str.replace("\"", "\"\"");
    }
}
