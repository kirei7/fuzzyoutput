package com.vlad.fuzzy.ui;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

public class CustomRenderer {
    protected Function2DRenderer renderer;
    public CustomRenderer(String chartName, XYSeries[] series, String axisX, String axisY, int rulesNum) {
        renderer = new Function2DRenderer(
                chartName,
                createDataset(series),
                axisX,
                axisY,
                rulesNum
        );
    }
    public void render() {
        renderer.pack();
        RefineryUtilities.centerFrameOnScreen(renderer);
        renderer.setVisible(true);
    }

    protected XYDataset createDataset(XYSeries[] series) {
        final XYSeriesCollection dataset = new XYSeriesCollection();
        for (XYSeries series1 : series) {
            dataset.addSeries(series1);
        }
        return dataset;
    }
}
