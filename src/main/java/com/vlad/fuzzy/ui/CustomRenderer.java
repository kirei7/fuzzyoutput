package com.vlad.fuzzy.ui;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;

public class CustomRenderer {
    protected Function2DInnerRenderer renderer;
    public CustomRenderer(String chartName, XYSeries[] series, String axisX, String axisY, int rulesNum) {
        renderer = new Function2DInnerRenderer(
                chartName,
                createDataset(series),
                axisX,
                axisY,
                rulesNum
        );
    }
    public JPanel render() {
        renderer.setVisible(true);
        return renderer;
    }

    protected XYDataset createDataset(XYSeries[] series) {
        final XYSeriesCollection dataset = new XYSeriesCollection();
        for (XYSeries series1 : series) {
            dataset.addSeries(series1);
        }
        return dataset;
    }
}
