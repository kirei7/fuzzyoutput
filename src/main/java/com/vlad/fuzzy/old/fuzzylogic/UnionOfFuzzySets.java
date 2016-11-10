package com.vlad.fuzzy.old.fuzzylogic;

import java.util.ArrayList;
import java.util.List;

public class UnionOfFuzzySets implements  FuzzySetInterface{
    private List<FuzzySetInterface> fuzzySets;

    public UnionOfFuzzySets() {
        fuzzySets = new ArrayList<>();
    }

    private double getMaxValue(double x) {
        double result = 0.0;
        for (FuzzySetInterface fuzzySet : fuzzySets) {
            result = Math.max(result, fuzzySet.getValue(x));
        }
        return result;
    }

    public void addFuzzySet(FuzzySetInterface fuzzySet){
        fuzzySets.add(fuzzySet);
    }

    //fix
    @Override
    public double getValue(double val) {
        return getMaxValue(val);
    }
}
