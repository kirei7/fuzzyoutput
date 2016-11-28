package com.vlad.fuzzy;

import com.vlad.fuzzy.engine.ProcessingEngine;
import com.vlad.fuzzy.ui.GuiEngine;
import java.io.FileNotFoundException;

public class App
{

    public static void main(String[] args) throws FileNotFoundException {
        App app = new App();
        app.run();
    }

    public void run() {
        ProcessingEngine processingEngine = new ProcessingEngine();
        GuiEngine guiEngine = new GuiEngine(processingEngine);
        guiEngine.setVisible(true);
    }


}
