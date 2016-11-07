package com.vlad.fuzzy;

import com.vlad.fuzzy.knowledge.*;

import java.util.ArrayList;
import java.util.List;


/*
* Ссылка на статью:
* habrahabr.ru/post/113020/
* */
public class MamdaniAlgorythm {

    private int numberOfConclusions;
    private int numberOfConditions;
    private int numberOfInputVariables;
    private int numberOfOutputVariables;
    private int numberOfRules;

    private List<Rule> rules;

    public MamdaniAlgorythm(List<Rule> rules) {
        this.rules = rules;
        for (Rule rule : rules) {
            for (Condition condition : rule.getConditions())
                numberOfConditions++;
            for (Conclusion conclusion : rule.getConclusions())
                numberOfConclusions++;
        }
    }

    public void run() {

    }
    public void testRun() {
        List<Conclusion> conclusions = new ArrayList<>();
        List<Condition> conditions = new ArrayList<>();
        Condition condition = new Condition();
        condition.setTerm(new FuzzySet(20, 25));
        Variable var = new Variable();
        var.setId(0);
        condition.setVariable(var);
        conditions.add(condition);
        numberOfConditions = 1;
        rules.add(
                new Rule(conditions, conclusions)
        );
        double[] testArr = {14};
        System.out.println(fuzzification(testArr)[0]);
    }
    /*
    Этот этап часто называют приведением к нечеткости. На вход поступают
    сформированная база правил и массив входных данных А = {a1, ..., am}.
    В этом массиве содержатся значения всех входных переменных. Целью этого
    этапа является получение значений истинности для всех подусловий из базы правил.
    Это происходит так: для каждого из подусловий находится значение bi = μ(ai).
    Таким образом получается множество значений bi (i = 1..k).

    Примечание: Массив входных данных сформирован таким образом,
    что i-ый элемент массива соответствует i-ой входной переменной
    (номер переменной храниться в целочисленном поле «id»).
    */
    private double[] fuzzification(double[] inputData) {
        numberOfInputVariables = inputData.length;
        int i = 0;
        double[] b = new double[numberOfConditions];
        for (Rule rule : rules) {
            for (Condition condition : rule.getConditions()) {
                int j = condition.getVariable().getId();
                System.out.println(condition);
                FuzzySet term = condition.getTerm();
                b[i] = term.getValue(inputData[j]);
                i++;
            }
        }
        return b;
    }


    /*условие правила может быть составным, т.е. включать подусловия,
    связанные между собой при помощи логической операции «AND». Целью
    этого этапа является определение степени истинности условий для
    каждого правила системы нечеткого вывода. Упрощенно говоря, для
    каждого условия находим минимальное значение истинности всех его подусловий. */
    private double[] aggregation(double[] b) {
        int i = 0;
        int j = 0;
        double[] c = new double[numberOfInputVariables];
        for (Rule rule : rules) {
            double truthOfConditions = 1.0;
            for (Condition condition : rule.getConditions()) {
                truthOfConditions = Math.min(truthOfConditions, b[i]);
                i++;
            }
            c[j] = truthOfConditions;
            j++;
        }
        return c;
    }

    /*
    На этом этапе происходит переход от условий к подзаключениям.
    Для каждого подзаключения находится степень истинности di = ci*Fi, где i = 1..q.
    Затем, опять же каждому i-му подзаключению, сопоставляется множество
    Di с новой функцией принадлежности. Её значение определяется как минимум
    из di и значения функции принадлежности терма из подзаключения.*/
    /*private List<ActivatedFuzzySet> activation(double[] c) {
        int i = 0;
        List<ActivatedFuzzySet> activatedFuzzySets = new ArrayList<ActivatedFuzzySet>();
        double[] d = new double[numberOfConclusions];
        for (Rule rule : rules) {
            for (Conclusion conclusion : rule.getConclusions()) {
                d[i] = c[i]*conclusion.getWeight();
                ActivatedFuzzySet activatedFuzzySet = (ActivatedFuzzySet) conclusion.getTerm();
                activatedFuzzySet.setTruthDegree(d[i]);
                activatedFuzzySets.add(activatedFuzzySet);
                i++;
            }
        }
        return activatedFuzzySets;
    }*/


    /*
    Целью этого этапа является получение нечеткого множества (или их объединения)
    для каждой из выходных переменных. Выполняется он следующим образом: i-ой
    выходной переменной сопоставляется объединение множеств Ei = ∪ Dj. Где j —
    номера подзаключений в которых участвует i-aя выходная переменная (i = 1..s). */

    /*private List<UnionOfFuzzySets> accumulation(List<ActivatedFuzzySet> activatedFuzzySets) {
        List<UnionOfFuzzySets> unionsOfFuzzySets =
                new ArrayList<UnionOfFuzzySets>(numberOfOutputVariables);
        for (Rule rule : rules) {
            for (Conclusion conclusion : rule.getConclusions()) {
                int id = conclusion.getVariable().getId();
                unionsOfFuzzySets.get(id).addFuzzySet(activatedFuzzySets.get(id));
            }
        }
        return unionsOfFuzzySets;
    }*/

    /*private double getMaxValue(double x) {
        double result = 0.0;
        for (FuzzySet fuzzySet : fuzzySets) {
            result = Math.max(result, fuzzySet.getValue(x));
        }
        return result;
    }*/

    /*
    Цель дефаззификациии получить количественное значение (crisp value)
    для каждой из выходных лингвистических переменных. Формально, это происходит
    следующим образом. Рассматривается i-ая выходная переменная и относящееся к
    ней множество Ei (i = 1..s). Затем при помощи метода дефаззификации находится
    итоговое количественное значение выходной переменной. В данной реализации
    алгоритма используется метод центра тяжести*/

    /*private double[] defuzzification(List<UnionOfFuzzySets> unionsOfFuzzySets) {
        double[] y = new double[numberOfOutputVariables];
        for(int i = 0; i < numberOfOutputVariables; i++) {
            double i1 = integral(unionsOfFuzzySets.get(i), true);
            double i2 = integral(unionsOfFuzzySets.get(i), false);
            y[i] = i1 / i2;
        }
        return y;
    }*/
}
