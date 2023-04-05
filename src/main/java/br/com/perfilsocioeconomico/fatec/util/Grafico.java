package br.com.perfilsocioeconomico.fatec.util;

import br.com.perfilsocioeconomico.fatec.model.Estatisticas;

import javax.swing.*;

public class Grafico extends JFrame {
    public Grafico(Estatisticas estatisticas) {

    setDefaultCloseOperation(HIDE_ON_CLOSE);

    setTitle("Perfil Socioeconomico");

    setSize(1200,1000);

    setLocationRelativeTo(null);

    JPanel graficoPanel = new GraficoPanel(estatisticas);

    add(graficoPanel);

    setVisible(true);
}
}
