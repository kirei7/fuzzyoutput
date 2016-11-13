package com.vlad.fuzzy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SigmaProvider {

    public float[] getFromFile(String fileName) {
        List<Float> sigmaList = new ArrayList<>();
        String line;
        try  {
            BufferedReader br = new BufferedReader(new InputStreamReader(SigmaProvider.class.getResourceAsStream(fileName)));
            while ((line = br.readLine()) != null) {
                sigmaList.add(
                        Float.parseFloat(line)
                );
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        float[] sigmaArray = new float[sigmaList.size()];
        for (int i = 0; i < sigmaList.size(); i++) {
            sigmaArray[i] = sigmaList.get(i);
        }
        return sigmaArray;
    }
}
