package br.com.perfilsocioeconomico.fatec.view;

import br.com.perfilsocioeconomico.fatec.services.EstatisticasService;
import br.com.perfilsocioeconomico.fatec.services.QuestionarioService;
import br.com.perfilsocioeconomico.fatec.util.ManipuladorDeArquivo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class InterfaceGrafica extends JFrame implements ActionListener {
    private final JButton ChartButton;

    private final JButton loadButton;

    private final ManipuladorDeArquivo manipuladorDeArquivo;
    private final EstatisticasService estatisticasService;
    private final QuestionarioService questionarioService;



    public InterfaceGrafica(QuestionarioService questionarioService, EstatisticasService estatisticasService) {
        super("Perfil Socioeconomico - FATEC - Franca");
        this.questionarioService = questionarioService;
        this.estatisticasService = estatisticasService;

        this.manipuladorDeArquivo = new ManipuladorDeArquivo();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new FlowLayout());
        loadButton = new JButton("Carregar novo arquivo");
        ChartButton = new JButton("gerar graficos com dados ja existentes");

        ChartButton.addActionListener(this);
        loadButton.addActionListener(this);

        add(ChartButton);
        add(loadButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ChartButton) {
            new GeradorDeGrafico(this.questionarioService, this.estatisticasService);


        } else if (e.getSource() == loadButton) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                manipuladorDeArquivo.excelToCSV(selectedFile);
                questionarioService.save();

            }
        }

    }
}