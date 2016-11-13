package com.vlad.fuzzy;


import com.vlad.fuzzy.engine.FuzzyOutput;
import com.vlad.fuzzy.engine.MembershipFunction;
import com.vlad.fuzzy.engine.Rule;
import com.vlad.fuzzy.ui.CustomRenderer;
import com.vlad.fuzzy.ui.GuiEngine;
import com.vlad.fuzzy.util.*;
import org.jfree.data.xy.XYSeries;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App
{
    private GuiEngine gui;
    private List<Map<Float, Double>> defuzzyfied = new ArrayList<>();

    public App() {
        gui = new GuiEngine();

    }

    public static void main(String[] args) throws FileNotFoundException {
        App app = new App();
        app.run();
    }

    public void run() {
        float[] sigmaArray = new SigmaProvider().getFromFile("/sigma.txt");
        InputProvider inputProvider = new ConsoleInputProvider();
        double[] input = inputProvider.getInput();
        List<Map<Float, Double>>
                outputs2 = calculate(input, "/2ops.csv", sigmaArray),
                outputs4 = calculate(input, "/4ops.csv", sigmaArray);
        Thread t1 = new Thread(() -> {
            gui.renderOutputs(outputs2, sigmaArray, 2);
        });
        t1.start();
        gui.renderOutputs(outputs4, sigmaArray, 4);

        double realVal = new RealValueProvider().getFromFile("/realval.txt");

        //take second chart
        double area = calcArea(outputs2.get(1), new MembershipFunction(realVal, sigmaArray[1]));

        System.out.println("Area:" + area);

    }

    public List<Map<Float, Double>> calculate(double[] input, String rulesPath, float[] sigmaArray) {
        List<Rule> rules = new CSVRuleSetProvider(rulesPath).getListOfRules();
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
            defuzzyfied.add(
                    defMap
            );
            i++;
        }
        return outputs;
    }

    private double calcArea(Map<Float, Double> output, MembershipFunction membershipFunction) {
        Interpolator interpolator = new Interpolator();
        double area = 0;
        XYSeries[] series = new XYSeries[2];
        series[0] = new XYSeries("muY");
        series[1] = new XYSeries("Actual y mf");

        for (double y = 0; y < 1; y += 0.01) {
            double outputPoint = interpolator.interpolate(output, y);
            double mfPoint = membershipFunction.evaluate(y);
            double currentPoint = (outputPoint < mfPoint) ? outputPoint : mfPoint;
            area += currentPoint * 0.01;
            series[0].add(y, outputPoint);
            series[1].add(y, mfPoint);
        }
        CustomRenderer renderer = new CustomRenderer("Area", series, "Y", "\u03BC(Y)", 0);
        renderer.render();
        return area;
    }
}
