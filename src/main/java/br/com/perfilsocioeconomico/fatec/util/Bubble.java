package br.com.perfilsocioeconomico.fatec.util;

import br.com.perfilsocioeconomico.fatec.model.Estatisticas;

import javax.swing.*;

public class Bubble extends JFrame{
    public Bubble(Estatisticas estatisticas) {

        setDefaultCloseOperation(HIDE_ON_CLOSE);

        setTitle("Perfil Socioeconomico");

        setSize(1200,1000);

        setLocationRelativeTo(null);

        JPanel bubblePanel = new BubblePanel(estatisticas);

        add(bubblePanel);

        setVisible(true);
    }
}
