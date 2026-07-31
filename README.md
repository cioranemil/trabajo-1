# 🎓 Universidad Nacional de Colombia
## Departamento de Ciencias de la Computación — Programación Orientada a Objetos (POO)

![Java 25](https://img.shields.io/badge/Java-25%20LTS-orange?style=for-the-badge&logo=openjdk)
![Swing GUI](https://img.shields.io/badge/GUI-Java%20Swing-blue?style=for-the-badge&logo=java)
![LaTeX PDF](https://img.shields.io/badge/LaTeX-TeX%20Live%202026-green?style=for-the-badge&logo=latex)
![Persistencia JSON](https://img.shields.io/badge/Persistencia-JSON%20%2F%20CSV-purple?style=for-the-badge)
![Licencia UNAL](https://img.shields.io/badge/UNAL-Ingeniería-red?style=for-the-badge)

---

## 📌 Actividad 1: Algoritmos y Lógica de Programación (Suite Profesional)

Este repositorio contiene la solución completa, robusta e interactiva para la **Actividad 1** de la asignatura **Programación Orientada a Objetos** impartida en la Universidad Nacional de Colombia.

* **Docente:** Walter Hugo Arboleda
* **Autor:** Cristian Ruiz Hernandez ([cruizh@unal.edu.co](mailto:cruizh@unal.edu.co))
* **Repositorio Oficial:** [github.com/cioranemil/trabajo-1](https://github.com/cioranemil/trabajo-1)

---

## 🛡️ Criterios de Evaluación Cumplidos (Rúbrica Oficial)

### 1. Control de Excepciones y Tratamiento de Errores
- **Jerarquía de Excepciones Personalizadas**: Implementación de [`ValorInvalidoException`](src/Utilidades/ValorInvalidoException.java) para la captura y validación de entradas numéricas negativas, fuera de rango o con formato erróneo.
- **Tolerancia a Fallos**: Captura preventiva de `NumberFormatException`, `IllegalArgumentException` y errores de I/O mediante diálogos visuales de alerta (`JOptionPane`) impidiendo colapsos o cierres inesperados de la aplicación.

### 2. Persistencia de Datos Local (JSON / CSV)
- **Administrador de Persistencia ([`ManejadorPersistencia.java`](src/Utilidades/ManejadorPersistencia.java))**: Cada cálculo, liquidación de nómina y trazabilidad se almacena automáticamente en disco en la carpeta `data/`:
  - `data/historial.json`: Estructura JSON indexada con fecha, ejercicio, entradas y resultados.
  - `data/historial.csv`: Archivo separado por comas para análisis directo en Excel.
- **Consulta de Historial Persistente ([`VentanaHistorial.java`](src/Utilidades/VentanaHistorial.java))**: Interfaz Swing que permite consultar, filtrar, refrescar y limpiar los registros históricos guardados en disco entre sesiones.

### 3. Código Limpio y Documentación UML
- Comentarios Javadoc completos en todas las clases y métodos.
- Diagramas de Clases y Secuencia UML Mermaid incluidos a continuación.

---

## 📐 Diagramas de Arquitectura UML (Mermaid)

### Diagrama de Clases UML
```mermaid
classDiagram
    class VentanaPrincipalActividad1 {
        +cambiarTarjeta(index: int)
        +cambiarTema(nuevoTema: Tema)
    }

    class UIUtils {
        +Tema temaActual
        +cargarPaleta(tema: Tema)
        +aplicarTema()
    }

    class ValorInvalidoException {
        -campo: String
        +getCampo(): String
    }

    class ManejadorPersistencia {
        +guardarRegistro(ejercicio: String, entradas: String, resultados: String)
        +cargarHistorial(): List~RegistroHistorial~
        +limpiarHistorial()
    }

    class EjercicioResuelto4 {
        -edadJuan: double
        -edadMama: double
        +getEdadMama(): double
    }

    class EjercicioResuelto5 {
        -suma: double
        +ejecutarPruebaEscritorio()
    }

    class EjercicioPropuesto12 {
        -salarioBruto: double
        -salarioNeto: double
        +formatoMoneda(val: double): String
    }

    class EjercicioPropuesto14 {
        -cuadrado: double
        -cubo: double
    }

    class EjercicioPropuesto17 {
        -area: double
        -longitudCircunferencia: double
    }

    VentanaPrincipalActividad1 --> UIUtils
    VentanaPrincipalActividad1 --> ManejadorPersistencia
    EjercicioResuelto4 ..> ValorInvalidoException
    EjercicioPropuesto12 ..> ValorInvalidoException
    EjercicioPropuesto17 ..> ValorInvalidoException
```

### Diagrama de Secuencia UML (Flujo de Persistencia)
```mermaid
sequenceDiagram
    autonumber
    actor Usuario
    participant GUI as VentanaEjercicioPropuesto12
    participant Controller as EjercicioPropuesto12
    participant Persistencia as ManejadorPersistencia
    participant Disk as Disco Local (data/historial.json)

    Usuario->>GUI: Ingresa Horas (48) y Tarifas ($5000)
    Usuario->>GUI: Hace Clic en "Liquidar Nómina"
    GUI->>Controller: Instancia EjercicioPropuesto12(48, 5000, 12.5)
    Controller-->>GUI: Retorna Salario Bruto ($240k) y Neto ($210k)
    GUI->>Persistencia: guardarRegistro("Ejercicio 12", entradas, resultados)
    Persistencia->>Disk: Escribe registro formateado en JSON y CSV
    Persistencia-->>GUI: Confirmación de Persistencia
    GUI-->>Usuario: Muestra Comprobante y Gráfico de Barras Java2D
```

---

## 🛠️ Instalación y Compilación

### Compilación y Ejecución Automatizada en Windows
```cmd
ejecutar.bat
```

### Compilación Manual desde Consola
```bash
# 1. Compilar fuentes Java
javac -encoding UTF-8 -d bin src/*.java src/**/*.java

# 2. Generar archivo ejecutable JAR
jar cfe Actividad_1.jar MenuPrincipal -C bin .

# 3. Ejecutar la aplicación
java -jar Actividad_1.jar
```

---

## 🖼️ Galería de la Interfaz Visual

### Menú Principal y Conmutador de Temas (Catppuccin Dark)
![Menú Principal](doc/images/gui_menu_principal.png)

### Historial de Persistencia de Datos (`data/historial.json`)
![Historial](doc/images/gui_ejercicio_resuelto_5.png)

---

## ✉️ Contacto y Autoría

* **Autor:** Cristian Ruiz Hernandez
* **Universidad:** Universidad Nacional de Colombia (Sede Medellín)
* **Correo Institucional:** [cruizh@unal.edu.co](mailto:cruizh@unal.edu.co)
