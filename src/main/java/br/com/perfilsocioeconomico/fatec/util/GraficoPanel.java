package br.com.perfilsocioeconomico.fatec.util;

import br.com.perfilsocioeconomico.fatec.model.Estatisticas;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.Map;

public class GraficoPanel extends JPanel {
    private final Estatisticas estatisticas;
    public GraficoPanel(Estatisticas estatisticas) {
        this.estatisticas = estatisticas;
        DefaultCategoryDataset graph = new DefaultCategoryDataset();
    for (
    Map.Entry entry : this.estatisticas.estatisticas()){
        graph.setValue(Integer.parseInt(entry.getValue().toString()), entry.getKey().toString(), "");
    }

    JFreeChart grafico = ChartFactory.createBarChart(this.estatisticas.pergunta(), "respostas", "quantidade",
            graph, PlotOrientation.VERTICAL, true, true, false);
    ChartPanel panel = new ChartPanel(grafico);
    add(panel);
}
}
