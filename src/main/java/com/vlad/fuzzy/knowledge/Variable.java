package com.vlad.fuzzy.knowledge;

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

}
