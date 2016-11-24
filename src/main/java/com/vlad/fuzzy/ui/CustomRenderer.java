package com.vlad.fuzzy.ui;

import com.vlad.fuzzy.engine.MembershipFunction;
import com.vlad.fuzzy.util.DataFetcher;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

import java.util.List;

public class CustomRenderer {
    protected Function2DRenderer renderer;
    protected DataFetcher dataFetcher;
    public CustomRenderer() {}
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
