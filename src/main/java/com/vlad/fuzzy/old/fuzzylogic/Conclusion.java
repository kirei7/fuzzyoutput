package com.vlad.fuzzy.old.fuzzylogic;

public class Conclusion extends Statement {

    private double weight;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Conclusion withWeight(double weight) {
        setWeight(weight);
        return this;
    }
}
