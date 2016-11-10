package com.vlad.fuzzy.old.fuzzylogic;

public class ActivatedFuzzySet implements FuzzySetInterface{
    public double getTruthDegree() {
        return truthDegree;
    }

    private double truthDegree;
    private FuzzySetInterface fuzzySetInterface;


    public ActivatedFuzzySet(FuzzySetInterface fuzzySet) {
        fuzzySetInterface = fuzzySet;
    }

    //fix
    public double getActivatedValue(double val) {
        return 0;
    }

    public void setTruthDegree(double truthDegree) {
        this.truthDegree = truthDegree;
    }

    @Override
    public double getValue(double val) {
        return fuzzySetInterface.getValue(val);
    }
}
