package com.vlad.fuzzy.knowledge;

public class Statement {

    private FuzzySet term;
    private Variable variable;


    public FuzzySet getTerm() {
        return term;
    }

    public void setTerm(FuzzySet term) {
        this.term = term;
    }

    public Variable getVariable() {
        return variable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }
}
