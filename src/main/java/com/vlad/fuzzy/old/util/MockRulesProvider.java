package com.vlad.fuzzy.old.util;

import com.vlad.fuzzy.old.fuzzylogic.*;

import java.util.ArrayList;
import java.util.List;

public class MockRulesProvider implements RulesProvider{

    private List<Rule> rules= new ArrayList<>();

    public MockRulesProvider() {
        //набор условий и заключений для первого правила
        List<Condition> conditions = new ArrayList<>();
        conditions.add(
                //условие описывает тяжелый бронежилет (от 15 до 35 кг)
                (Condition) new Condition()
                        //нечеткое множество с центром (омега) в точке 25
                        //и радиусом в 10, т.е. ширина области = 20
                        .withTerm(new FuzzySet(20,25))
                        .withVariable(new Variable().withId(0))
        );
        conditions.add(
                //какое-то левое условие
                (Condition) new Condition()
                        .withTerm(new FuzzySet(4,5))
                        .withVariable(new Variable().withId(1))
        );
        List<Conclusion> conclusions = new ArrayList<>();
        conclusions.add(
                (Conclusion) new Conclusion()
                    .withWeight(1)
                    .withTerm(new FuzzySet(4,5))
                    .withVariable(new Variable().withId(0))
        );

        rules.add(
                new Rule(conditions, conclusions)
        );
        //набор условий и заключений для второго правила
        List<Condition> conditions2 = new ArrayList<>();
        conditions2.add(
                //условие описывает легкий бронежилет (от 5 до 15 кг)
                (Condition) new Condition()
                        //нечеткое множество с центром (омега) в точке 10
                        //и радиусом в 5, т.е. ширина области = 10
                        .withTerm(new FuzzySet(5,10))
                        .withVariable(new Variable().withId(2))
        );
        List<Conclusion> conclusions2 = new ArrayList<>();
        conclusions2.add(
                (Conclusion) new Conclusion()
                        .withWeight(1)
                        .withTerm(new FuzzySet(4,5))
                        .withVariable(new Variable().withId(1))
        );

        rules.add(
                new Rule(conditions2, conclusions2)
        );
    }

    @Override
    public List<Rule> getRules() {
        return rules;
    }
}
