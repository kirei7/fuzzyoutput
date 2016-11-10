package com.vlad.fuzzy.old.util;

public class MockInputVariablesProvider implements InputVariablesProvider {
    @Override
    public double[] getVariables() {
        return new double[]{27, 5, 10};
    }
}
