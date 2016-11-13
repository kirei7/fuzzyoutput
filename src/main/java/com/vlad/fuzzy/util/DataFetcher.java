package com.vlad.fuzzy.util;

import com.vlad.fuzzy.engine.MembershipFunction;
import org.jfree.data.xy.XYSeries;

import java.util.List;
import java.util.Map;

public class DataFetcher {
    public XYSeries[] fetchMembershipData(List<MembershipFunction> membershipFunctions) {
        //size - 1 since we are not considering last input argument
        XYSeries[] seriesArray = new XYSeries[membershipFunctions.size() - 1];
        for (int i = 0; i < membershipFunctions.size() - 1; i++) {
            XYSeries series = new XYSeries("x" + (i + 1));
            for (double y = 0; y < 1; y += 0.1) {
                series.add(
                        y,
                        membershipFunctions.get(i).evaluate(y)
                );
            }
            seriesArray[i] = series;
        }
        return seriesArray;
    }

    public XYSeries[] fetchMuData(List<Map<Float, Double>> outputs, float[] sigmaArray) {
        Interpolator interpolator = new Interpolator();
        XYSeries[] seriesArray = new XYSeries[outputs.size()];
        for (int i = 0; i < outputs.size(); i++) {
            XYSeries series = new XYSeries("\u03C3=" + sigmaArray[i]);
            for (double y = 0; y < 1; y += 0.01) {
                series.add(
                        y,
                        interpolator.interpolate(outputs.get(i), y)
                );
            }
            seriesArray[i] = series;
        }
        return seriesArray;
    }
}
