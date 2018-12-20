package dominio.swing.B_GenerarHorari;

import dominio.controladores.CtrlPresentacio;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class vistaGenerarHorari extends JFrame {

    Object[][] data = {
            {"Hora", "Dilluns", "Dimarts", "Dimecres", "Dijous", "Divendres"},
            {"8", "----", "----","----", "----", "----"},
            {"9", "----", "----","----", "----", "----"},
            {"10", "----", "----","----", "----", "----"},
            {"11", "----", "----","----", "----", "----"},
            {"12", "----", "----","----", "----", "----"},
            {"13", "----", "----","----", "----", "----"},
            {"14", "----", "----","----", "----", "----"},
            {"15", "----", "----","----", "----", "----"},
            {"16", "----", "----","----", "----", "----"},
            {"17", "----", "----","----", "----", "----"},
            {"18", "----", "----","----", "----", "----"},
            {"19", "----", "----","----", "----", "----"}

    };

    String[] columnNames = {"Hora","Dilluns", "Dimarts", "Dimecres", "Dijous", "Divendres"};

    String[] conjuntAules = {"No hi ha dades carregades"};


    private JLabel titol = new JLabel("Generar Horari");
    private JLabel seleccioAules = new JLabel("Seleccionar Aula:");
    private JButton endarrereButton = new JButton("Endarrere");
    private JTable horari = new JTable(data, columnNames);
    private JComboBox aules = new JComboBox(conjuntAules);
    private JTextField tf = new JTextField(128);

    private ArrayList<Pair<String, String[][][]>> horariImprimir;
    private Object[][] informacioHorari = new Object[14][6];

    private CtrlPresentacio iCtrlPresentacio = CtrlPresentacio.getInstance();

    private void imprimirAula(String info) {
        String[][][] valor = null;
        for (Pair<String, String[][][]> a : horariImprimir) {
            if (a.getKey() == info)
                valor = a.getValue();
        }
        if (valor != null) {
            for (int hora = 0; hora < 12; ++hora) {
                informacioHorari[hora + 1][0] = 8 + hora;
                for (int dia = 0; dia < 5; ++dia) {
                    if (valor[dia][hora][0] != null)
                        informacioHorari[hora + 1][dia + 1] = valor[dia][hora][0] + " " + valor[dia][hora][1] + " " + valor[dia][hora][2];
                    else informacioHorari[hora + 1][dia + 1] = "----";

                }
            }
        }
    }

    public vistaGenerarHorari(ArrayList<Pair<String, String[][][]>> horariImprimir, boolean complet) {


        if (complet == true) {

            this.horariImprimir = horariImprimir;

            String[] cjtAules = new String[horariImprimir.size()];
            int i = 0;
            for (Pair<String, String[][][]> aula : horariImprimir) {
                cjtAules[i] = aula.getKey();
                ++i;
            }
            informacioHorari[0][0] = "Hora";
            informacioHorari[0][1] = "Dilluns";
            informacioHorari[0][2] = "Dimarts";
            informacioHorari[0][3] = "Dimecres";
            informacioHorari[0][4] = "Dijous";
            informacioHorari[0][5] = "Divendres";

            Pair<String, String[][][]> aula = horariImprimir.get(0);
            String info = aula.getKey();

            imprimirAula(info);


            aules = new JComboBox(cjtAules);
            horari = new JTable(informacioHorari, columnNames);
        }
        setSize(700, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        /* Títol */

        titol.setBounds(300,50,200,30);
        add(titol);


        /* Títol Selecció Aula*/

        seleccioAules.setBounds(50, 175, 150, 30);
        add(seleccioAules);

        /* Selecció Aula */

        aules.setBounds(200, 175, 250, 30);
        add(aules);

        /* Taula */

        horari.setBounds(50,250,600,210);
        add(horari);

        /* Botó Endarrere */

        endarrereButton.setBounds(530,500,120,30);
        add(endarrereButton);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ActionListener vistaPrincipal = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iCtrlPresentacio.inicialitzarPresentacio();
                setVisible(false);
            }

        };

        ActionListener seleccioAules = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String info = aules.getSelectedItem().toString();
                System.out.println(info);
                imprimirAula(info);
                horari = new JTable(informacioHorari, columnNames);
            }

        };

        endarrereButton.addActionListener(vistaPrincipal);
        aules.addActionListener(seleccioAules);
    }

}