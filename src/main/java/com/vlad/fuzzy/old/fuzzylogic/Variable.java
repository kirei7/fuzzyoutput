package com.vlad.fuzzy.old.fuzzylogic;

import java.util.Set;

public class Variable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public Set<FuzzySet> terms;

    public Variable withId(int id) {
        setId(id);
        return this;
    }

}
