package com.vlad.fuzzy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputProvider implements InputProvider {
    @Override
    public double[] getInput() {
        double[] input = new double[5];
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            for (int i = 0; i < input.length; i++) {
                try{
                    System.out.print("x" + (i+1) + " = ");
                    input[i] = Double.parseDouble(br.readLine());
                }catch(NumberFormatException nfe){
                    System.out.println("Incorrect format, try again");
                    i--;
                }
            }
    } catch (IOException ex) {
            ex.printStackTrace();
        }

        return input;
    }
}
