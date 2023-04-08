package br.com.perfilsocioeconomico.fatec.view;

import br.com.perfilsocioeconomico.fatec.model.Estatisticas;

import javax.swing.*;

public class Grafico extends JFrame {
    public Grafico(Estatisticas estatisticas) {

    setDefaultCloseOperation(HIDE_ON_CLOSE);

    setTitle("Perfil Socioeconomico");

    setSize(1100,900);

    setLocationRelativeTo(null);

    JPanel graficoPanel = new GraficoPanel(estatisticas);

    add(graficoPanel);

    setVisible(true);
}
}
