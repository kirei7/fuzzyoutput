package com.vlad.fuzzy.ui;


import com.vlad.fuzzy.engine.MembershipFunction;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

import java.util.List;

public class GuiEngine {
    private InputRenderer inputRenderer;
    private OutputRenderer outputRenderer;

    public GuiEngine(List<MembershipFunction> membershipFunctions) {
        inputRenderer = new InputRenderer(fetchDataSeries(membershipFunctions));
    }

    public void start() {
        inputRenderer.render();
    }

    private XYSeries[] fetchDataSeries(List<MembershipFunction> membershipFunctions) {
        XYSeries[] seriesArray = new XYSeries[membershipFunctions.size() - 1];
        for (int i = 0; i < membershipFunctions.size() - 1; i++) {
            XYSeries series = new XYSeries("x" + (i + 1));
            for (double y = 0.1; y < 1; y += 0.1) {
                series.add(
                        y,
                        membershipFunctions.get(i).evaluate(y)
                );
            }
            seriesArray[i] = series;
        }
        return seriesArray;
    }
}
