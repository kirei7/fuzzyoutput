package com.vlad.fuzzy.old.fuzzylogic;

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

    public Statement withTerm(FuzzySet term) {
        setTerm(term);
        return this;
    }
    public Statement withVariable(Variable variable) {
        setVariable(variable);
        return this;
    }

}
