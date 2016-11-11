package com.vlad.fuzzy;

import com.vlad.fuzzy.engine.*;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        /*Rule rule = new Rule()
                .withX1(0)
                .withX2(0)
                .withX3(0)
                .withX4(0)
                .withX5(2)
                .withY(0.5f);
        System.out.println(rule);*/
        //get list of rules
        //RuleSetProvider setProvider = new CSVRuleSetProvider("/home/userok/study/rules/2ops.txt");
        RuleSetProvider setProvider = new CSVRuleSetProvider("/2ops.csv");
        List<Rule> rules = setProvider.getListOfRules();
        //variables of Y in all rules
        Set<Float> rulesOutput = new HashSet<>();
        for (Rule r : rules) {
            rulesOutput.add(r.getY());
        }

        System.out.println(rulesOutput.toString());

        //get input variables
        InputProvider inputProvider = new ConsoleInputProvider();
        //double[] input = inputProvider.getInput();
        double[] input = {1, 0, 1, 1, 1};

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

        //array for values of Y membership functions
        //N.B. array is automatically filled with zeros right after declaration
        double[] muY = new double[rulesOutput.size()];
        int i = 0;
        //for each variable of Y in rules
        for (Float Y : rulesOutput) {
            //for each rule find minimal truth degree (which is a result of evaluation of
            //the value of the membership function)
            List<Double> minPerRule = new ArrayList<>();
            for (Rule r : rules) {
                //if output variable of this rule equals to Y
                double min;
                if ( (r.getY() == Y) && r.getX5() == input[4]) {
                    min = membershipFunctions.get(input[0]).evaluate(r.getX1());
                    min = Math.min(min, membershipFunctions.get(input[1]).evaluate(r.getX2()));
                    min = Math.min(min, membershipFunctions.get(input[2]).evaluate(r.getX3()));
                    min = Math.min(min, membershipFunctions.get(input[3]).evaluate(r.getX4()));
                    minPerRule.add(min);
                }
            }//end for rules
            muY[i] = Collections.max(minPerRule);
            i++;
        }//end for outputs

        //outputting our muY
        for (double d : muY) {
            System.out.println(d);
        }
    }
}
