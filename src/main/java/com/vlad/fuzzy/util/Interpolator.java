package com.vlad.fuzzy.util;

import java.util.Map;

public class Interpolator {

    public double interpolate(Map<Float, Double> points, double xVal) {
        int size = points.size();

        double[] x1 = new double[size];
        double[] y1 = new double[size];

        int k = 0;
        for (Map.Entry<Float, Double> entry : points.entrySet()) {
            x1[k] = entry.getValue();
            y1[k] = entry.getKey();
            k++;
        }

        int i, j;
        double term,sum = 0 ;

        for(i = 0; i < size; ++i){
            term = x1[i];
            for(j = 0; j < size; ++j){
                if(i != j)
                    term *= (xVal - y1[j]) / (y1[i] - y1[j]);
            }
            sum += term ;
        }
        if (sum < 0) sum = 0;
        if (sum > 1) sum = 1;
        return sum;
    }

}
