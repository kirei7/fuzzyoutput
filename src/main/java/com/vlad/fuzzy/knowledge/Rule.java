package com.vlad.fuzzy.knowledge;

import java.util.List;

public class Rule {
    private List<Conclusion> conclusions;
    private List<Condition> conditions;

    public Rule(List<Condition> conditions, List<Conclusion> conclusions) {
        this.conclusions = conclusions;
        this.conditions = conditions;
    }

    public List<Conclusion> getConclusions() {
        return conclusions;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

}
