package com.vlad.fuzzy.engine;

import java.util.*;

public class FuzzyOutput {

    private List<MembershipFunction> membershipFunctions;
    private List<Rule> rules;
    private float SIGMA;
    private double defuzzyfiedValue;

    public FuzzyOutput(List<Rule> rules, float SIGMA) {
        this.rules = rules;
        this.SIGMA = SIGMA;
    }
    public Map<Float, Double> start(double[] input) {
        //get a membership function for each input variable
        membershipFunctions = createMembershipFunctions(input, SIGMA);

        //map for values of Y membership functions
        //Map<Float, Double> muY = new HashMap<>();
        Map<Float, Double> muY = evaluateTruthDegree(
                rules,
                membershipFunctions,
                input
        );
        defuzzyfiedValue = defuzzyficate(muY);
        return muY;
    }
    private Map<Float, Double> evaluateTruthDegree(List<Rule> rules,
                                                   List<MembershipFunction> membershipFunctions,
                                                   double[] input) {
        //variables of Y in all rules
        Set<Float> rulesOutput = getRulesOutput(rules);
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
                    min = membershipFunctions.get(0).evaluate(r.getX1());
                    min = Math.min(min, membershipFunctions.get(1).evaluate(r.getX2()));
                    min = Math.min(min, membershipFunctions.get(2).evaluate(r.getX3()));
                    min = Math.min(min, membershipFunctions.get(3).evaluate(r.getX4()));
                    min = Math.min(min, membershipFunctions.get(4).evaluate(r.getX5()));
                    minPerRule.add(min);
                }
            }//end for rules
            muY.put(Y, Collections.max(minPerRule));
        }//end for outputs
        return muY;
    }

    private Set<Float> getRulesOutput(List<Rule> rules) {
        Set<Float> rulesOutput = new HashSet<>();
        for (Rule r : rules) {
            rulesOutput.add(r.getY());
        }
        return rulesOutput;
    }

    private List<MembershipFunction> createMembershipFunctions(double[] input, float SIGMA) {
        List<MembershipFunction> membershipFunctions = new ArrayList<>();
        for (Double variable : input) {
            membershipFunctions.add( new MembershipFunction(variable, SIGMA) );
        }
        return membershipFunctions;
    }

    public List<MembershipFunction> getMembershipFunctions() {
        return membershipFunctions;
    }

    private double defuzzyficate(Map<Float, Double> muY) {
        double upSum = 0;
        double lowSum = 0;
        for(Map.Entry<Float, Double> entry : muY.entrySet()) {
            upSum += entry.getKey() * entry.getValue();
            lowSum += entry.getKey();
        }
        return upSum/lowSum;
    }
    public double getDefuzzyfiedValue() {
        return defuzzyfiedValue;
    }
}
