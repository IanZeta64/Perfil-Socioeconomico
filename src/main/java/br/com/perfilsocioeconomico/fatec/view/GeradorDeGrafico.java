package br.com.perfilsocioeconomico.fatec.view;
import br.com.perfilsocioeconomico.fatec.services.EstatisticasService;
import br.com.perfilsocioeconomico.fatec.services.QuestionarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeradorDeGrafico extends JFrame implements ActionListener {
    private final JLabel labelPerguntas;
    private final JLabel labelAno;
    private final JComboBox<String> comboBoxPerguntas;
    private final JComboBox<String> comboBoxAno;
    private final JButton chartButton;

    private final  EstatisticasService estatisticasService;


    public GeradorDeGrafico(QuestionarioService questionarioService, EstatisticasService estatisticasService) {
        super("Perfil Socioeconomico - FATEC - Franca");
        this.estatisticasService = estatisticasService;

        String[] QUESTIONARIO_PERGUNTAS = questionarioService.findPerguntas();

        String[] ANOS_POR_QUESTIONARIO = questionarioService.findByYear();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        labelAno = new JLabel("Selecione o ano:");
        comboBoxAno = new JComboBox<>(ANOS_POR_QUESTIONARIO);
        labelAno.setPreferredSize(new Dimension(300, 30));
        comboBoxAno.setPreferredSize(new Dimension(300, 30));
        labelPerguntas = new JLabel("Selecione a pergunta:");
        comboBoxPerguntas = new JComboBox<>(QUESTIONARIO_PERGUNTAS);
        labelPerguntas.setPreferredSize(new Dimension(300, 30));
        comboBoxPerguntas.setPreferredSize(new Dimension(300, 30));
        chartButton = new JButton("Gerar gráfico");
        chartButton.setPreferredSize(new Dimension(300, 30));

        chartButton.addActionListener(this);
        panel.add(labelAno);
        panel.add(comboBoxAno);
        panel.add(labelPerguntas);
        panel.add(comboBoxPerguntas);
        panel.add(new JLabel());
        panel.add(chartButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(500, 150));
        mainPanel.add(panel);

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chartButton) {
            String ano = String.valueOf(comboBoxAno.getSelectedItem());
            String pergunta = String.valueOf(comboBoxPerguntas.getSelectedItem());
            if (pergunta.equals("Escreva algumas linhas sobre sua história e seus sonhos de vida.")) {
                var estatisticasMap = estatisticasService.getAllStats(ano);
                new Bubble(estatisticasMap.get(pergunta));
            } else {
                var estatisticasMap = estatisticasService.getAllStats(ano);
                new Grafico(estatisticasMap.get(pergunta));
            }

        }
    }
}