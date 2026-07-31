package Utilidades;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Utilidad general de exportación multi-formato (HTML, CSV, JSON, TXT)
 * con membrete y diseño institucional de la Universidad Nacional.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 3.0/2026
 */
public class ExportadorMultiFormato {

    public enum Formato { HTML, CSV, JSON, TXT }

    public static File exportarReporte(String titulo, Map<String, Object> datos, Formato formato) throws Exception {
        String baseName = titulo.replaceAll("[^a-zA-Z0-9]", "_");
        File archivo = new File(baseName + "." + formato.name().toLowerCase());

        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            switch (formato) {
                case HTML:
                    pw.println("<!DOCTYPE html>");
                    pw.println("<html lang='es'><head><meta charset='UTF-8'><title>" + titulo + "</title>");
                    pw.println("<style>");
                    pw.println("body { font-family: 'Segoe UI', sans-serif; background-color: #1E1E2E; color: #CDD6F4; padding: 20px; }");
                    pw.println(".card { background: #313244; padding: 20px; border-radius: 8px; border: 1px solid #89B4FA; }");
                    pw.println("h1 { color: #89B4FA; font-size: 20px; }");
                    pw.println("table { width: 100%; border-collapse: collapse; margin-top: 15px; }");
                    pw.println("td, th { padding: 10px; border-bottom: 1px solid #45475A; text-align: left; }");
                    pw.println(".value { color: #A6E3A1; font-weight: bold; }");
                    pw.println("</style></head><body>");
                    pw.println("<div class='card'>");
                    pw.println("<h1>UNIVERSIDAD NACIONAL DE COLOMBIA — " + titulo + "</h1>");
                    pw.println("<p><b>Asignatura:</b> POO | <b>Autor:</b> Cristian Ruiz Hernandez</p><table>");
                    for (Map.Entry<String, Object> entry : datos.entrySet()) {
                        pw.println("<tr><td>" + entry.getKey() + "</td><td class='value'>" + entry.getValue() + "</td></tr>");
                    }
                    pw.println("</table></div></body></html>");
                    break;

                case CSV:
                    pw.println("Clave,Valor");
                    for (Map.Entry<String, Object> entry : datos.entrySet()) {
                        pw.println("\"" + entry.getKey() + "\",\"" + entry.getValue() + "\"");
                    }
                    break;

                case JSON:
                    pw.println("{");
                    pw.println("  \"institucion\": \"Universidad Nacional de Colombia\",");
                    pw.println("  \"titulo\": \"" + titulo + "\",");
                    pw.println("  \"autor\": \"Cristian Ruiz Hernandez\",");
                    pw.println("  \"datos\": {");
                    int count = 0;
                    for (Map.Entry<String, Object> entry : datos.entrySet()) {
                        count++;
                        pw.print("    \"" + entry.getKey() + "\": \"" + entry.getValue() + "\"");
                        if (count < datos.size()) pw.println(",");
                        else pw.println();
                    }
                    pw.println("  }");
                    pw.println("}");
                    break;

                case TXT:
                default:
                    pw.println("============================================================");
                    pw.println("      UNIVERSIDAD NACIONAL DE COLOMBIA — POO ACTIVIDAD 1    ");
                    pw.println("                  " + titulo.toUpperCase());
                    pw.println("============================================================");
                    for (Map.Entry<String, Object> entry : datos.entrySet()) {
                        pw.printf(" • %-30s : %s\n", entry.getKey(), entry.getValue());
                    }
                    pw.println("============================================================");
                    break;
            }
        }
        return archivo;
    }
}
