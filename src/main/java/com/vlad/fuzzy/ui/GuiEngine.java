package com.vlad.fuzzy.ui;


import com.vlad.fuzzy.engine.MembershipFunction;
import com.vlad.fuzzy.util.DataFetcher;

import java.util.List;
import java.util.Map;

public class GuiEngine {
    private DataFetcher dataFetcher;
    private CustomRenderer inputRenderer;
    private CustomRenderer outputRenderer;

    public GuiEngine() {
        dataFetcher = new DataFetcher();
    }


    public void renderInputs(List<MembershipFunction> membershipFunctions) {
        inputRenderer = new CustomRenderer(
                "Input data membership functions",
                dataFetcher.fetchMembershipData(membershipFunctions),
                "Xi",
                "µ(Xi)",
                0
        );
        inputRenderer.render();
    }

    public void renderOutputs(List<Map<Float, Double>> outputs, float[] sigmaArray, int rulesNum) {
        outputRenderer = new CustomRenderer(
                "Output data",
                dataFetcher.fetchMuData(outputs, sigmaArray),
                "y",
                "µ(y)",
                rulesNum
        );
        outputRenderer.render();
    }
}
