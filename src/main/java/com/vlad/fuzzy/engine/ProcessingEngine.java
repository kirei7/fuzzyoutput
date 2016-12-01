package com.vlad.fuzzy.engine;


import com.vlad.fuzzy.ui.CustomRenderer;
import com.vlad.fuzzy.ui.GuiEngine;
import com.vlad.fuzzy.util.*;
import org.jfree.data.xy.XYSeries;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessingEngine
{

    private float[] sigmaArray;
    public float[] getSigmaArray() {
        return sigmaArray;
    }
    private double calcedArea;

    public ProcessingEngine() {
        sigmaArray = new SigmaProvider().getFromFile("/sigma.txt");
    }

    public List<Map<Float, Double>> calculate(double[] input, int opsNum) {
        List<Rule> rules = new CalculatedRuleProvider(opsNum).getListOfRules();
        List<FuzzyOutput> algorythms = new ArrayList<>();
        for (float sigma : sigmaArray) {
            algorythms.add(
                    new FuzzyOutput(rules, sigma)
            );
        }
        List<Map<Float, Double>> outputs = new ArrayList<>();
        int i = 0;
        for (FuzzyOutput algorythm : algorythms) {
            outputs.add(algorythm.start(input));
            Map<Float,Double> defMap = new HashMap<>();
            defMap.put(sigmaArray[i], algorythm.getDefuzzyfiedValue());
            i++;
        }
        return outputs;
    }

    public JPanel calcArea(double[] input, float realVal, float sigma, int numOfOperations) {
        FuzzyOutput algorythm = new FuzzyOutput(
                new CalculatedRuleProvider(numOfOperations).getListOfRules(),
                sigma
        );
        MembershipFunction membershipFunction = new MembershipFunction(realVal, sigma);
        Interpolator interpolator = new Interpolator();
        double area = 0;
        XYSeries[] series = new XYSeries[2];
        series[0] = new XYSeries("muY");
        series[1] = new XYSeries("Actual y mf");
        Map<Float, Double> output = algorythm.start(input);
        for (double y = 0; y < 1; y += 0.01) {
            double outputPoint = interpolator.interpolate(output, y);
            double mfPoint;
            mfPoint = membershipFunction.evaluate(y);
            /*for(double j = y; j < (y+0.1); j += 0.01) {
                mfPoint = membershipFunction.evaluate(j);
                series[1].add(j, mfPoint);
            }*/
            double currentPoint = (outputPoint < mfPoint) ? outputPoint : mfPoint;
            area += currentPoint * 0.01;
            series[0].add(y, outputPoint);
            series[1].add(y, mfPoint);
        }
        calcedArea = area;
        CustomRenderer renderer = new CustomRenderer("Area", series, "Y", "\u03BC(Y)", 0);
        return renderer.render();
    }
}
