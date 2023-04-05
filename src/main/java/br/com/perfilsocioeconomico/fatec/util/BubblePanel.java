package br.com.perfilsocioeconomico.fatec.util;
import br.com.perfilsocioeconomico.fatec.model.Estatisticas;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.BubbleXYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBubbleRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Map;
import java.util.Random;

public class BubblePanel extends JPanel {
    public BubblePanel(Estatisticas estatisticas) {

        double xMin = 0.0; // valor mínimo do eixo X
        double xMax = 40.0; // valor máximo do eixo X
        double yMin = 0.0; // valor mínimo do eixo Y
        double yMax = 40.0; // valor máximo do eixo Y

        Random random = new Random();
        DefaultXYZDataset graph = new DefaultXYZDataset();
        int i = 0;
        var estatisticasOrdenandas = estatisticas.getEstatisticas();
        estatisticasOrdenandas.sort(estatisticas);
        for (int j = 0; j < estatisticasOrdenandas.size(); j++) {
            Long valor = estatisticasOrdenandas.get(j).getValue();
            String chave = estatisticasOrdenandas.get(j).getKey();
            double z = Double.parseDouble(valor.toString()); // tamanho do ponto
            double x = xMin + random.nextDouble() * (xMax - xMin); // coordenada X aleatória
            double y = yMin + random.nextDouble() * (yMax - yMin); // coordenada Y aleatória

            System.out.println(x);
            double[][] data = {{x}, {y}, {z}};
            graph.addSeries(chave, data);
        }

        JFreeChart grafico = ChartFactory.createBubbleChart(
                estatisticas.getPergunta(), // título do gráfico
                "Nuvem de palavras", // nome do eixo X
                "Contagem", // nome do eixo Y
                graph, // dados
                PlotOrientation.HORIZONTAL, // orientação do gráfico
                true, // legenda
                true, // dicas de ferramenta
                false // URLs
        );

// Definir tamanho dos pontos
        XYPlot plot = grafico.getXYPlot();
        XYBubbleRenderer renderer = new XYBubbleRenderer();

        renderer.setSeriesVisible(0, true);
        plot.setRenderer(renderer);

        NumberAxis xAxis = new NumberAxis("X Axis");
        xAxis.setRange(xMin, xMax); // definindo o limite mínimo e máximo do eixo X

        NumberAxis yAxis = new NumberAxis("Y Axis");
        yAxis.setRange(yMin, yMax); // definindo o limite mínimo e máximo do eixo Y

        NumberAxis zAxis = new NumberAxis("Z Axis");

        plot.setDomainAxis(xAxis);
        plot.setRangeAxis(yAxis);
        plot.setDomainAxis(zAxis);

        ChartPanel panel = new ChartPanel(grafico);
        add(panel);
    }
}
