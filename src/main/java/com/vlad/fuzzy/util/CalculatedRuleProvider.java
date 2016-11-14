package com.vlad.fuzzy.util;

import com.vlad.fuzzy.engine.Rule;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serg on 14.11.2016.
 */
public class CalculatedRuleProvider implements RuleSetProvider {
    int opsNum;

    public CalculatedRuleProvider(int opsNum) {
        this.opsNum = opsNum;
    }

    @Override
    public List<Rule> getListOfRules() {
        float[] xArr = {0, 0.25f, 0.5f, 0.75f, 1f};
        int[] x5Arr = {1,2, 3, 4, 5, 6};
        //int[] opsNumArr = {2,  4};
        List<Rule> rules = new ArrayList<>();
            for (float x1 : xArr) {
                for (float x2 : xArr) {
                    for (float x3: xArr) {
                        for (float x4 : xArr) {
                            for (int x5 : x5Arr) {
                                rules.add(
                                        determineRule(new Rule().withX1(x1).withX2(x2).withX3(x3).withX4(x4).withX5(x5), opsNum)
                                );
                            }
                        }
                    }
            }
        }
        return rules;
    }

    private Rule determineRule(Rule rule, int opsNum) {
        int x5 = rule.getX5();
        float x1 = rule.getX1(),
                x2 = rule.getX2(),
                x3 = rule.getX3(),
                x4 = rule.getX4();
        try {
            switch (x5) {
                case 1:
                    switch (opsNum) {
                        case 2:
                            rule.setY((x1+x3)/2);
                            break;
                        case 4:
                            rule.setY((x1 + 0.5f*x2 + 0.5f*x4 + x3)/4);
                            break;
                    }
                    break;
                case 2:
                    switch (opsNum) {
                        case 2:
                            rule.setY((x2+x4)/2);
                            break;
                        case 4:
                            rule.setY((x2 + 0.5f*x1 + 0.5f*x3 + x4)/4);
                            break;
                    }
                    break;
                case 3:
                    switch (opsNum) {
                        case 2:
                            rule.setY((x1+x2)/2);
                            break;
                        case 4:
                            rule.setY((x1 + x2 + 0.5f*x3 + 0.25f*x4)/4);
                            break;
                    }
                    break;
                case 4:
                    switch (opsNum) {
                        case 2:
                            rule.setY((x3+x4)/2);
                            break;
                        case 4:
                            rule.setY((0.25f*x2 + 0.5f*x1 + x3 + x4)/4);
                            break;
                    }
                    break;
                case 5:
                    switch (opsNum) {
                        case 2:
                            rule.setY((x2+x3)/2);
                            break;
                        case 4:
                            rule.setY((x2 + 0.5f*x1 + x3 + 0.25f*x4)/4);
                            break;
                    }
                    break;
                case 6:
                    switch (opsNum) {
                        case 2:
                            rule.setY((x1+x4)/2);
                            break;
                        case 4:
                            rule.setY((0.25f*x2 + x1 + 0.5f*x3 + x4)/4);
                            break;
                    }
                    break;

            /*case 3:
                rule.setY((x1+x3)/2);
                break;
            case 4:
                rule.setY((x1+x3)/2);
                break;*/
            }
        } catch (IllegalArgumentException ex) {
            System.out.println(rule);
        }

        return rule;
    }

   /* public static void main(String[] args) {
        try {
            CalculatedRuleProvider provider = new CalculatedRuleProvider();

            File file = new File("/home/userok/allrules.csv");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for (Rule rule : provider.getListOfRules()) {
                StringBuilder builder = new StringBuilder();
                builder.append(
                        rule.getX1() + "," +
                        rule.getX2() + "," +
                        rule.getX3() + "," +
                        rule.getX4() + "," +
                        rule.getX5() + "," +
                        rule.getY() + System.getProperty("line.separator")
                );
                bw.write(builder.toString());
            }
            bw.close();
            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
