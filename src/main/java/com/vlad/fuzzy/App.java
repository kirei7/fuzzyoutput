package com.vlad.fuzzy;


import com.vlad.fuzzy.engine.*;
import com.vlad.fuzzy.ui.GuiEngine;

import java.io.FileNotFoundException;

public class App
{
    FuzzyOutput engine;
    GuiEngine gui;

    public App() {
        engine = new FuzzyOutput();

    }

    public static void main(String[] args) throws FileNotFoundException {
        App app = new App();
        app.run();
    }

    public void run() {
        engine.start();
        gui = new GuiEngine(engine.getMembershipFunctions());
        gui.start();
    }


}
