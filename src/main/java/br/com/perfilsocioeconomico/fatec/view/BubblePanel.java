package br.com.perfilsocioeconomico.fatec.view;
import br.com.perfilsocioeconomico.fatec.model.Estatisticas;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBubbleRenderer;
import org.jfree.data.xy.DefaultXYZDataset;
import javax.swing.*;
import java.util.Random;

public class BubblePanel extends JPanel {
    public BubblePanel(Estatisticas estatisticas) {

        DefaultXYZDataset graph = new DefaultXYZDataset();

        double xMin = 0.0; // valor mínimo do eixo X
        double xMax = 1000.0; // valor máximo do eixo X
        double yMin = 0.0; // valor mínimo do eixo Y
        double yMax = 1000.0; // valor máximo do eixo Y

        var estatisticasOrdenandas = estatisticas.getEstatisticas();
        estatisticasOrdenandas.sort(estatisticas);

        Random random = new Random();

        for (int i = 0; i < estatisticasOrdenandas.size(); i++) {

            double valor = Math.pow(Double.parseDouble(String.valueOf(estatisticasOrdenandas.get(i).getValue())), 1.3) * 10;
            String chave = estatisticasOrdenandas.get(i).getKey() + ": " + estatisticasOrdenandas.get(i).getValue();
            double x = 150 + (850 - 150) * random.nextDouble();
            double y = 150 + (850 - 150) * random.nextDouble();

//
//            if (i == 0) {
//                x = eixoFixo;
//                y = eixoFixo;
//                acumulador = 100;
//            }
//            else {
//                double fator = 1 + acumulador / 2000; // ajuste o valor 10000 para controlar a velocidade de afastamento da espiral
//                double angulo = i * Math.PI / 11 + acumulador * 0.00099; // ajuste o valor 0.1 para controlar o afastamento do centro
//                x = eixoFixo + fator * acumulador / i * Math.cos(angulo);
//                y = eixoFixo + fator * acumulador / i * Math.sin(angulo);
//                acumulador += valor;
//            }

            double[][] data = {{x}, {y}, {valor}};
            graph.addSeries(chave, data);
        }
        JFreeChart grafico = ChartFactory.createBubbleChart(
                estatisticas.getPergunta(), // título do gráfico
                "Nuvem de palavras", // nome do eixo X
                "", // nome do eixo Y
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
//
        NumberAxis xAxis = new NumberAxis("X Axis");
        xAxis.setRange(xMin, xMax); // definindo o limite mínimo e máximo do eixo X

        NumberAxis yAxis = new NumberAxis("Y Axis");
        yAxis.setRange(yMin, yMax); // definindo o limite mínimo e máximo do eixo Y
//
//        NumberAxis zAxis = new NumberAxis("Z Axis");
//
        plot.setDomainAxis(xAxis);
        plot.setRangeAxis(yAxis);
//        plot.setDomainAxis(zAxis);

//
        ChartPanel panel = new ChartPanel(grafico);
        add(panel);
    }
}