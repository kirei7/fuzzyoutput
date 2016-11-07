package com.vlad.fuzzy.knowledge;

public class FuzzySet implements FuzzySetInterface {

    private final double omega;
    private final double x0;

    //fix (delete?)
    public FuzzySet() {
        omega = 0;
        x0 = 0;
    }
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
