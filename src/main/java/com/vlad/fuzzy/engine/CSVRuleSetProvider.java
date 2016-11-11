package com.vlad.fuzzy.engine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVRuleSetProvider implements RuleSetProvider{

    List<Rule> rules = new ArrayList<>();

    public CSVRuleSetProvider(String fileName) {

        String line = "";
        String cvsSplitBy = ",";

        try  {
            BufferedReader br = new BufferedReader(new InputStreamReader(CSVRuleSetProvider.class.getResourceAsStream(fileName)));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] data = line.split(cvsSplitBy);
                rules.add(formRule(data));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Rule formRule(String[] data) {
        return new Rule()
                .withX1(Float.parseFloat(data[0]))
                .withX2(Float.parseFloat(data[1]))
                .withX3(Float.parseFloat(data[2]))
                .withX4(Float.parseFloat(data[3]))
                .withX5(Integer.parseInt(data[4]))
                .withY(Float.parseFloat(data[5]));
    }

    @Override
    public List<Rule> getListOfRules() {
        return rules;
    }
}
