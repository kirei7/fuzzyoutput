package com.vlad.fuzzy.old.fuzzylogic;

public class FuzzySet implements FuzzySetInterface {

    protected final double omega;
    protected final double x0;


    public FuzzySet(double omega, double x0) {
        this.omega = omega;
        this.x0 = x0;
    }

    @Override
    public double getValue(double val) {
        return Math.exp(
                -( ((val - x0)*(val - x0)) / omega)
        );
    }

}
