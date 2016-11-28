package com.vlad.fuzzy.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;

public class Function2DRenderer extends JFrame {

    protected int rulesNum;
    protected String axisX;
    protected String axisY;
    protected String chartTitle;

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public Function2DRenderer(final String title, final XYDataset dataset, String axisX, String axisY, int rulesNum) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.rulesNum = rulesNum;
        if (rulesNum == 0) {
            chartTitle = title;
        } else chartTitle = "Number of operations: " + rulesNum;
        this.axisX = axisX;
        this.axisY = axisY;
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }


    /**
     * Creates a chart.
     *
     * @param dataset  the data for the chart.
     *
     * @return a chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesStroke(0, new BasicStroke(2f));
        renderer.setSeriesStroke(1, new BasicStroke(2f));
        renderer.setSeriesStroke(2, new BasicStroke(2f));
        renderer.setSeriesStroke(3, new BasicStroke(2f));
        renderer.setSeriesStroke(4, new BasicStroke(2f));

        renderer.setSeriesPaint(0, Color.red);
        renderer.setSeriesPaint(1, Color.blue);
        //brown
        renderer.setSeriesPaint(0, new Color(139,69,19));
        renderer.setSeriesPaint(3, Color.magenta);
        renderer.setSeriesPaint(4, Color.yellow);

        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
                chartTitle,      // chart title
                axisX,                      // x axis label
                axisY,                      // y axis label
                dataset,                  // data
                PlotOrientation.VERTICAL,
                true,                     // include legend
                true,                     // tooltips
                false                     // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.lightGray);

//        final StandardLegend legend = (StandardLegend) chart.getLegend();
        //      legend.setDisplaySeriesShapes(true);

        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.lightGray);

        //makes ugly square points on the lines invisible
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesShapesVisible(2, false);
        renderer.setSeriesShapesVisible(3, false);
        renderer.setSeriesShapesVisible(4, false);
        plot.setRenderer(renderer);

        Font font3 = new Font("Dialog", Font.PLAIN, 25);
        plot.getDomainAxis().setLabelFont(font3);
        plot.getRangeAxis().setLabelFont(font3);

        LegendTitle legend = chart.getLegend();
        Font labelFont = new Font("Arial", Font.BOLD, 25);
        legend.setItemFont(labelFont);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setRange(0, 1.0);
        rangeAxis.setTickUnit(new NumberTickUnit(0.1));
        rangeAxis.setTickLabelFont(new Font("Dialog", Font.PLAIN, 19));
        //rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        final NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setRange(0, 1.0);
        domainAxis.setTickUnit(new NumberTickUnit(0.1));
        domainAxis.setTickLabelFont(new Font("Dialog", Font.PLAIN, 19));
        //domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.
        return chart;

    }

    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    *
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */


}
