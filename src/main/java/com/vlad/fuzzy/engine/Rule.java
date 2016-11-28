package com.vlad.fuzzy.engine;

public class Rule {

    private int lowerBound = 0, upperbound = 1;

    private float x1;
    private float x2;
    private float x3;
    private float x4;
    private int x5;
    private float y;

    public Rule withY(float y) {
        setY(y);
        return this;
    }

    public Rule withX1(float x1) {
        setX1(x1);
        return this;
    }

    public Rule withX2(float x2) {
        setX2(x2);
        return this;
    }

    public Rule withX3(float x3) {
        setX3(x3);
        return this;
    }

    public Rule withX4(float x4) {
        setX4(x4);
        return this;
    }

    public Rule withX5(int x5) {
        setX5(x5);
        return this;
    }

    private void checkNum(float num) {
        // check if the given value is in allowed range
        if (num < lowerBound || num > upperbound)
            throw new IllegalArgumentException(
                    "Value must be in range: [" +
                            lowerBound + "; " + upperbound + "], but given: " + num
            );
    }
    private void checkDirection(int num) {
        // check if the given wind direction is right
        if (!(num == 1 || num == 2 || num == 3 || num == 4 || num == 5 || num == 6))
            throw new IllegalArgumentException("Wind direction value must be between 1 and 6, but given: " + num);
    }
    //Getters/setters here

    public float getX1() {
        return x1;
    }

    public void setX1(float x1) {
        checkNum(x1);
        this.x1 = x1;
    }

    public float getX2() {
        return x2;
    }

    public void setX2(float x2) {
        checkNum(x2);
        this.x2 = x2;
    }

    public float getX3() {
        return x3;
    }

    public void setX3(float x3) {
        checkNum(x3);
        this.x3 = x3;
    }

    public float getX4() {
        return x4;
    }

    public void setX4(float x4) {
        checkNum(x4);
        this.x4 = x4;
    }

    public int getX5() {
        return x5;
    }

    public void setX5(int x5) {
        checkDirection(x5);
        this.x5 = x5;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        checkNum(y);
        this.y = y;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "x1=" + x1 +
                ", x2=" + x2 +
                ", x3=" + x3 +
                ", x4=" + x4 +
                ", x5=" + x5 +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule rule = (Rule) o;

        if (Float.compare(rule.getX1(), getX1()) != 0) return false;
        if (Float.compare(rule.getX2(), getX2()) != 0) return false;
        if (Float.compare(rule.getX3(), getX3()) != 0) return false;
        if (Float.compare(rule.getX4(), getX4()) != 0) return false;
        if (getX5() != rule.getX5()) return false;
        return Float.compare(rule.getY(), getY()) == 0;

    }

    @Override
    public int hashCode() {
        int result = (getX1() != +0.0f ? Float.floatToIntBits(getX1()) : 0);
        result = 31 * result + (getX2() != +0.0f ? Float.floatToIntBits(getX2()) : 0);
        result = 31 * result + (getX3() != +0.0f ? Float.floatToIntBits(getX3()) : 0);
        result = 31 * result + (getX4() != +0.0f ? Float.floatToIntBits(getX4()) : 0);
        result = 31 * result + getX5();
        result = 31 * result + (getY() != +0.0f ? Float.floatToIntBits(getY()) : 0);
        return result;
    }
}
