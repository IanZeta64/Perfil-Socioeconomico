package br.com.perfilsocioeconomico.fatec.view;

import br.com.perfilsocioeconomico.fatec.model.Estatisticas;


import javax.swing.*;

public class Bubble extends JFrame{
    public Bubble(Estatisticas estatisticas) {

        setDefaultCloseOperation(HIDE_ON_CLOSE);

        setTitle("Perfil Socioeconomico");

        setSize(1100,900);

        setLocationRelativeTo(null);

        JPanel bubblePanel = new BubblePanel(estatisticas);

        add(bubblePanel);

        setVisible(true);
    }
}
