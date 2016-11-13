package com.vlad.fuzzy.util;

import com.vlad.fuzzy.engine.Rule;

import java.util.List;

public interface RuleSetProvider {
    List<Rule> getListOfRules();
}
