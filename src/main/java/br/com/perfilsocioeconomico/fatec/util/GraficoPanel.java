package br.com.perfilsocioeconomico.fatec.util;

import br.com.perfilsocioeconomico.fatec.model.Estatisticas;
import br.com.perfilsocioeconomico.fatec.services.EstatisticasService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.Map;

public class GraficoPanel extends JPanel {
    public GraficoPanel(Estatisticas estatisticas) {
        DefaultCategoryDataset graph = new DefaultCategoryDataset();
        for (Map.Entry entry : estatisticas.getEstatisticas()) {
            graph.setValue(Integer.parseInt(entry.getValue().toString()), entry.getKey().toString(), "");
        }
        JFreeChart grafico = ChartFactory.createBarChart(estatisticas.getPergunta(), "respostas", "quantidade",
                graph, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel panel = new ChartPanel(grafico);
        add(panel);
    }
}
