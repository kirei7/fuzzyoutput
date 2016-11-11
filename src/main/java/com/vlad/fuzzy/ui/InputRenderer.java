package com.vlad.fuzzy.ui;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

public class InputRenderer {
    private Function2DRenderer renderer;

    public InputRenderer(XYSeries[] series) {
        renderer = new Function2DRenderer("JFreeChart: Function2DRenderer.java", createDataset(series));
    }
    public void render() {
        renderer.pack();
        RefineryUtilities.centerFrameOnScreen(renderer);
        renderer.setVisible(true);
    }

    private XYDataset createDataset(XYSeries[] series) {
        final XYSeriesCollection dataset = new XYSeriesCollection();
        for (XYSeries series1 : series) {
            dataset.addSeries(series1);
        }
        return dataset;
    }
}
