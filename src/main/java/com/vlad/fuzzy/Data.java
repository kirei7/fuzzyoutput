package com.vlad.fuzzy;

public class Data {
    private double value;


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Data data1 = (Data) o;

        return Double.compare(data1.getValue(), getValue()) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(getValue());
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String toString() {
        return "Data{" +
                "value=" + value +
                '}';
    }
}
