@echo off
title Actividad 1 - Logica de Programacion (UNAL)
cls
echo ============================================================
echo   Compilando y Ejecutando Actividad 1
echo   Cristian Ruiz Hernandez
echo ============================================================
echo.

if not exist bin mkdir bin

echo Compilando archivos Java...
javac -encoding UTF-8 -d bin src/*.java src/Utilidades/*.java src/MenuPrincipal/*.java src/ejercicio_resuelto_4/*.java src/ejercicio_resuelto_5/*.java src/ejercicio_propuesto_12/*.java src/ejercicio_propuesto_14/*.java src/ejercicio_propuesto_17/*.java

if errorlevel 1 (
    echo.
    echo [ERROR] Ocurrio un error al compilar los archivos Java.
    pause
    exit /b 1
)

echo Generando ejecutable Actividad_1.jar...
jar cfe Actividad_1.jar MenuPrincipal -C bin .

echo.
echo Compilado con exito. Iniciando interfaz grafica...
java -cp bin MenuPrincipal

pause
