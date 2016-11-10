package com.vlad.fuzzy.old.util;

import com.vlad.fuzzy.old.fuzzylogic.Rule;

import java.util.List;

/* ВАЖНО
* судя по всему, нумерация условий и заключений должна производиться
* отдельно, т.е. если в правилах есть условия с номерами 0, 1, 2
* то номера заключений не продолжают нумерацию, а начинаются с 0*/
public interface RulesProvider {
    List<Rule> getRules();
}
