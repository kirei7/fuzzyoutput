package com.vlad.fuzzy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RealValueProvider {
    public double getFromFile(String fileName) {
        String line;
        double realVal = 0;
        try  {
            BufferedReader br = new BufferedReader(new InputStreamReader(RealValueProvider.class.getResourceAsStream(fileName)));
            line = br.readLine();
            realVal = Double.parseDouble(line);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return realVal;
    }
}
