package br.com.perfilsocioeconomico.fatec.util;
import br.com.perfilsocioeconomico.fatec.model.Estatisticas;
import br.com.perfilsocioeconomico.fatec.services.QuestionarioService;

import javax.swing.*;

public class Grafico extends JFrame {

    public Grafico(Estatisticas estatisticas) {
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("Perfil Socioeconomico");
        setSize(950, 700);
        setLocationRelativeTo(null);
        JPanel graficoPanel = new GraficoPanel(estatisticas);
        add(graficoPanel);
        setVisible(true);
    }





}
