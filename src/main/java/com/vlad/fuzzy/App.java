package com.vlad.fuzzy;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class App
{
    public static void main(String[] args) throws FileNotFoundException {
        //get list of rules
        RuleSetProvider setProvider = new CSVRuleSetProvider("/2ops.csv");
        List<Rule> rules = setProvider.getListOfRules();
        //variables of Y in all rules
        Set<Float> rulesOutput = new HashSet<>();
        for (Rule r : rules) {
            rulesOutput.add(r.getY());
        }
        //get input variables
        InputProvider inputProvider = new ConsoleInputProvider();
        //double[] input = {1, 0, 1, 1, 1};
        double[] input = inputProvider.getInput();

        //get a membership function for each input variable
        //with hardcoded SIGMA variable
        float  SIGMA = 1f;
        Map<Double, MembershipFunction> membershipFunctions = new HashMap<>();
        for (Double variable : input) {
            membershipFunctions.put(
                    variable,
                    new MembershipFunction(variable, SIGMA)
            );
        }

        //map for values of Y membership functions
        Map<Float, Double> muY = new HashMap<>();
        //for each variable of Y in rules
        for (Float Y : rulesOutput) {
            //for each rule find minimal truth degree (which is a result of evaluation of
            //the value of the membership function)
            List<Double> minPerRule = new ArrayList<>();
            for (Rule r : rules) {
                //if output variable of this rule equals to Y
                double min;
                if ( (r.getY() == Y)) {
                    min = membershipFunctions.get(input[0]).evaluate(r.getX1());
                    min = Math.min(min, membershipFunctions.get(input[1]).evaluate(r.getX2()));
                    min = Math.min(min, membershipFunctions.get(input[2]).evaluate(r.getX3()));
                    min = Math.min(min, membershipFunctions.get(input[3]).evaluate(r.getX4()));
                    min = Math.min(min, membershipFunctions.get(input[4]).evaluate(r.getX5()));
                    minPerRule.add(min);
                }
            }//end for rules
            muY.put(Y, Collections.max(minPerRule));
        }//end for outputs

        //outputting our muY
        for (Map.Entry<Float, Double> entry : muY.entrySet()) {
            System.out.println("muY(" + entry.getKey() + ") = " + entry.getValue());
        }
    }
}
