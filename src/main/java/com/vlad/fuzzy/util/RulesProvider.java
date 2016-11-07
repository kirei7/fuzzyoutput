package com.vlad.fuzzy.util;

import com.vlad.fuzzy.knowledge.Rule;

import java.util.List;

public interface RulesProvider {
    List<Rule> getRules();
}
