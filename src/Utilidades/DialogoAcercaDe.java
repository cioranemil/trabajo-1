package Utilidades;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Diálogo modal con información del sistema y datos institucionales.
 * 
 * @author Cristian Ruiz Hernandez
 * @version 3.0/2026
 */
public class DialogoAcercaDe extends JDialog {

    public DialogoAcercaDe(JFrame owner) {
        super(owner, "Acerca del Sistema - UNAL POO Actividad 1", true);
        setSize(540, 420);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        JPanel panelContent = new JPanel(new BorderLayout(15, 15));
        panelContent.setBackground(UIUtils.COLOR_PANEL);
        panelContent.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("UNIVERSIDAD NACIONAL DE COLOMBIA", SwingConstants.CENTER);
        lblTitulo.setFont(UIUtils.FUENTE_TITULO);
        lblTitulo.setForeground(UIUtils.COLOR_ACCENTO1);

        JLabel lblSub = new JLabel("Facultad de Ingeniería — Departamento de Ciencias de la Computación", SwingConstants.CENTER);
        lblSub.setFont(UIUtils.FUENTE_SUBTITULO);
        lblSub.setForeground(UIUtils.COLOR_TEXTO_DIM);

        JPanel pnlNorte = new JPanel(new GridLayout(2, 1, 4, 4));
        pnlNorte.setBackground(UIUtils.COLOR_PANEL);
        pnlNorte.add(lblTitulo);
        pnlNorte.add(lblSub);

        JTextArea txtInfo = new JTextArea();
        txtInfo.setFont(UIUtils.FUENTE_CONSOLA);
        txtInfo.setBackground(UIUtils.COLOR_CONSOLE_BG);
        txtInfo.setForeground(UIUtils.COLOR_ACCENTO2);
        txtInfo.setEditable(false);

        Runtime rt = Runtime.getRuntime();
        double maxMem = rt.maxMemory() / (1024.0 * 1024.0);
        double totalMem = rt.totalMemory() / (1024.0 * 1024.0);
        double freeMem = rt.freeMemory() / (1024.0 * 1024.0);

        StringBuilder sb = new StringBuilder();
        sb.append("============================================================\n");
        sb.append("              DIAGNÓSTICO DEL ENTORNO DE EJECUCIÓN           \n");
        sb.append("============================================================\n");
        sb.append(" • Sistema Operativo : ").append(System.getProperty("os.name")).append(" ").append(System.getProperty("os.version")).append("\n");
        sb.append(" • Versión de Java   : ").append(System.getProperty("java.version")).append(" (").append(System.getProperty("java.vendor")).append(")\n");
        sb.append(" • Núcleos de CPU    : ").append(rt.availableProcessors()).append(" hilos\n");
        sb.append(String.format(" • Memoria Asignada  : %.1f MB / %.1f MB Máx\n", totalMem - freeMem, maxMem));
        sb.append("------------------------------------------------------------\n");
        sb.append("              CRÉDITOS Y DATOS DEL ESTUDIANTE               \n");
        sb.append("------------------------------------------------------------\n");
        sb.append(" • Asignatura        : Programación Orientada a Objetos\n");
        sb.append(" • Docente           : Walter Hugo Arboleda\n");
        sb.append(" • Estudiante        : Cristian Ruiz Hernandez\n");
        sb.append(" • Correo            : cruizh@unal.edu.co\n");
        sb.append(" • Repositorio GitHub: https://github.com/cioranemil/trabajo-1\n");
        sb.append("============================================================\n");

        txtInfo.setText(sb.toString());

        JScrollPane scroll = new JScrollPane(txtInfo);
        scroll.setBorder(BorderFactory.createLineBorder(UIUtils.COLOR_BORDES, 1, true));

        JButton btnCerrar = new JButton("Entendido");
        UIUtils.estilizarBotonAccion(btnCerrar, UIUtils.COLOR_ACCENTO1);
        btnCerrar.addActionListener(e -> dispose());

        panelContent.add(pnlNorte, BorderLayout.NORTH);
        panelContent.add(scroll, BorderLayout.CENTER);
        panelContent.add(btnCerrar, BorderLayout.SOUTH);

        add(panelContent);
    }
}
