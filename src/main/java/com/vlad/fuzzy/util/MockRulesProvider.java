package com.vlad.fuzzy.util;

import com.vlad.fuzzy.knowledge.Rule;

import java.util.ArrayList;
import java.util.List;

public class MockRulesProvider implements RulesProvider{

    private List<Rule> rules= new ArrayList<>();

    public MockRulesProvider() {

    }
    @Override
    public List<Rule> getRules() {
        return null;
    }
}
