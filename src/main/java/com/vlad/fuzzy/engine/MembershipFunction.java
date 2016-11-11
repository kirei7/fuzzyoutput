package com.vlad.fuzzy.engine;

//gaussian membership function
public class MembershipFunction {
    private double top;
    private float sigma;

    public MembershipFunction(double top, float sigma) {
        this.top = top;
        this.sigma = sigma;
    }

    //evaluates the value of the function in point x
    public double evaluate(double x) {
        return Math.exp(
                (- 0.5) * ( Math.pow((x - top), 2) / Math.pow(sigma, 2))
        );
    }
}
