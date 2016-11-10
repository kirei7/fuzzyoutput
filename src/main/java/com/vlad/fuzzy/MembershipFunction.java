package com.vlad.fuzzy;

//gaussian membership function
public class MembershipFunction {
    private double top;
    private float sigma;

    public MembershipFunction(double top, float sigma) {
        this.top = top;
        this.sigma = sigma;
    }

    //some googling claims that there should be not just sigma but 2*sigma
    public double evaluate(double x) {
        return Math.exp(
                - ( Math.pow((x - top), 2) / Math.pow(sigma, 2))
        );
    }
}
