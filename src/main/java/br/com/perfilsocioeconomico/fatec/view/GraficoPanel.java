package br.com.perfilsocioeconomico.fatec.view;

import br.com.perfilsocioeconomico.fatec.model.Estatisticas;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.util.Map;

public class GraficoPanel extends JPanel {
    public GraficoPanel(Estatisticas estatisticas) {

        JFreeChart grafico = null;
        if (estatisticas.getEstatisticas().size() >= 99) {
            
            DefaultCategoryDataset graph = new DefaultCategoryDataset();
            for (Map.Entry entry : estatisticas.getEstatisticas()) {
                graph.setValue(Integer.parseInt(entry.getValue().toString()), entry.getKey().toString(), "");
                grafico = ChartFactory.createBarChart(estatisticas.getPergunta(), "respostas", "quantidade",
                        graph, PlotOrientation.VERTICAL, true, true, false);
            }
            ChartPanel panel = new ChartPanel(grafico);
            add(panel);
        }else{
        DefaultPieDataset pie = new DefaultPieDataset();
        for (Map.Entry entry : estatisticas.getEstatisticas()) {
            pie.setValue(entry.getKey().toString() + ": " + entry.getValue(), Long.parseLong(String.valueOf(entry.getValue())));
            grafico = ChartFactory.createPieChart(estatisticas.getPergunta(),
                    pie, true, true, false);
        }
            ChartPanel panel = new ChartPanel(grafico);
            add(panel);
        }



       
    }
}
