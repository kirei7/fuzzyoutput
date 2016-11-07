package com.vlad.fuzzy.knowledge;

public class ActivatedFuzzySet extends FuzzySet {
    private double truthDegree;

    //fix
    public ActivatedFuzzySet() {

    }

    //fix
    public double getActivatedValue(double val) {
        return 0;
    }

    public void setTruthDegree(double truthDegree) {
        this.truthDegree = truthDegree;
    }
}
