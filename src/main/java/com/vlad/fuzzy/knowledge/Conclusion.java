package com.vlad.fuzzy.knowledge;

public class Conclusion extends Statement {
    public double getWeigth() {
        return weigth;
    }

    public void setWeigth(double weigth) {
        this.weigth = weigth;
    }

    private double weigth;

}
