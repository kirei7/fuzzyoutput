package com.vlad.fuzzy.old;

import com.vlad.fuzzy.old.fuzzylogic.*;

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
    //не знаю почему 1, вроде 1
    private int numberOfOutputVariables;
    private int numberOfRules;

    private List<Rule> rules;

    public MamdaniAlgorythm(List<Rule> rules) {
        this.rules = rules;
        for (Rule rule : rules) {
            for (Condition condition : rule.getConditions())
                numberOfConditions++;
            for (Conclusion conclusion : rule.getConclusions())
            {
                numberOfConclusions++;
                numberOfOutputVariables = Math.max(numberOfOutputVariables, conclusion.getVariable().getId());
            }
        }
        numberOfOutputVariables++;
    }

    /*
    * ВАЖНО
    * индекс элемента массива входных значений должен
    * соответствовать индексу условия, в вычислении значения
    * которого используется это значение*/
    public void run(double[] inputVariables) {
        //находим значения истинности для всех предусловий
        double[] b = fuzzification(inputVariables);
        //находим минимальное значение истинности для каждого правила
        double[] c = aggregation(b);

        List<ActivatedFuzzySet> activatedFuzzySets = activation(c);
        /*for (ActivatedFuzzySet activatedFuzzySet : activatedFuzzySets) {
            System.out.println(activatedFuzzySet.getTruthDegree());
        }*/
        List<UnionOfFuzzySets> union = accumulation(activatedFuzzySets);

        double[] out = defuzzification(union, c);
        for (double a : out) System.out.println(a);
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
        double[] c = new double[rules.size()];
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
    private List<ActivatedFuzzySet> activation(double[] c) {
        int i = 0;
        List<ActivatedFuzzySet> activatedFuzzySets = new ArrayList<ActivatedFuzzySet>();
        double[] d = new double[numberOfConclusions];
        for (Rule rule : rules) {
            for (Conclusion conclusion : rule.getConclusions()) {
                d[i] = c[i]*conclusion.getWeight();
                ActivatedFuzzySet activatedFuzzySet = new ActivatedFuzzySet(conclusion.getTerm());
                activatedFuzzySet.setTruthDegree(d[i]);
                activatedFuzzySets.add(activatedFuzzySet);
                i++;
            }
        }
        return activatedFuzzySets;
    }


    /*
    Целью этого этапа является получение нечеткого множества (или их объединения)
    для каждой из выходных переменных. Выполняется он следующим образом: i-ой
    выходной переменной сопоставляется объединение множеств Ei = ∪ Dj. Где j —
    номера подзаключений в которых участвует i-aя выходная переменная (i = 1..s). */

    private List<UnionOfFuzzySets> accumulation(List<ActivatedFuzzySet> activatedFuzzySets) {
        List<UnionOfFuzzySets> unionsOfFuzzySets = new ArrayList<>();
        for (int i = 0; i < numberOfOutputVariables; i++) {
            unionsOfFuzzySets.add( new UnionOfFuzzySets());
        }
        for (Rule rule : rules) {
            for (Conclusion conclusion : rule.getConclusions()) {
                int id = conclusion.getVariable().getId();
                unionsOfFuzzySets.get(id).addFuzzySet(activatedFuzzySets.get(id));
            }
        }
        return unionsOfFuzzySets;
    }


    /*
    Цель дефаззификациии получить количественное значение (crisp value)
    для каждой из выходных лингвистических переменных. Формально, это происходит
    следующим образом. Рассматривается i-ая выходная переменная и относящееся к
    ней множество Ei (i = 1..s). Затем при помощи метода дефаззификации находится
    итоговое количественное значение выходной переменной. В данной реализации
    алгоритма используется метод центра тяжести*/

    private double[] defuzzification(List<UnionOfFuzzySets> unionsOfFuzzySets, double[] c) {
        double[] y = new double[numberOfOutputVariables];
        double averageValue = avg(c);
        for(int i = 0; i < numberOfOutputVariables; i++) {
            double i1 = integral(unionsOfFuzzySets.get(i), c[i], true);
            double i2 = integral(unionsOfFuzzySets.get(i), c[i], false);
            y[i] = i1 / i2;
        }
        return y;
    }

    private double integral(UnionOfFuzzySets unionOfFuzzySets, double number, boolean b) {
        if (b)
            return Math.pow(unionOfFuzzySets.getValue(number), 2);
        else
            return unionOfFuzzySets.getValue(number);

    }

    private double avg(double[] c) {
        if (c.length == 0)
            return 0;

        double sum = 0;
        for (double number : c)
            sum += number;

        return sum / c.length;
    }
}
