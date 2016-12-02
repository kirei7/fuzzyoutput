package com.vlad.fuzzy.ui;


import com.vlad.fuzzy.engine.MembershipFunction;
import com.vlad.fuzzy.engine.ProcessingEngine;
import com.vlad.fuzzy.ui.tab.CalculationPane;
import com.vlad.fuzzy.ui.tab.TabPanel;
import com.vlad.fuzzy.util.DataFetcher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class GuiEngine extends JFrame{

    //domain fields
    private ProcessingEngine processingEngine;
    private DataFetcher dataFetcher;
    private CustomRenderer inputRenderer;
    private CustomRenderer outputRenderer;
    private  float[] sigmaArray;

    //GUI fields
    private Dimension sectionSize = new Dimension(520, 60);
    private Container topContainer;
    private Panel chartPanel;
    //input fields
    private JLabel inputLabel;
    private JTextField[] inputX;
    private JLabel[] labelX;
    //MF output buttons
    private JLabel mfOutputLabel;
    private JButton ops2Button;
    private JButton ops4Button;
    //calc area fields
    private CalculationPane calculationPane;
    private JLabel calcAreaLabel;
    private JLabel rulesListLabel;
    private JLabel sigmaListLabel;
    private JLabel realValLabel;
    private JButton calcAreaButton;
    private Integer[] numOfOperations;
    private JComboBox rulesList;
    private JComboBox sigmaList;
    private JTextField realValInput;

    public GuiEngine(ProcessingEngine processingEngine) {
        //layout
        super("Fuzzy output");
        this.processingEngine = processingEngine;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sigmaArray = processingEngine.getSigmaArray();
        //main  container
        topContainer = this.getContentPane();
        topContainer.setLayout(new BoxLayout(topContainer, BoxLayout.Y_AXIS));
        //отримуємо загальний контейнер вікна
        Container container = new JPanel(new GridLayout(2,1));
        //output membership function fields
        mfOutputLabel = new JLabel("Draw output membership functions");
        //area of intersection fields
        calcAreaLabel = new JLabel("Figure's intersection area:");
        //place all elements into layout
        container.add(prepareInputPanel());
        calculationPane = new CalculationPane(
                sectionSize,
                new OperationsOutputListener(),
                new AreaButtonListener(),
                getSigmaArray()
        );
        container.add(calculationPane);
        /*container.add(mfOutputLabel);
        container.add(prepareOutputMFPanel());
        container.add(calcAreaLabel);
        container.add(prepareAreaCalcPanel());*/
        dataFetcher = new DataFetcher();
        chartPanel = new Panel();
        chartPanel.setPreferredSize(new Dimension(520, 250));
        topContainer.add(container);
        topContainer.add(chartPanel);
        this.pack();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    private JPanel prepareInputPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        int inputsNum = 5;
        inputX = new JTextField[inputsNum];
        labelX = new JLabel[inputsNum];
        for (int i = 0; i < inputsNum; i++) {
            inputX[i] = new JTextField("",5);
            labelX[i] = new JLabel("X" + (i+1) + ":");
        }
        for(int i = 0; i < inputX.length; i++) {
            JPanel container = new JPanel(new FlowLayout());
            container.add(labelX[i]);
            container.add(inputX[i]);
            panel.add(container);
        }
        panel.setPreferredSize(sectionSize);
        return panel;
    }
    /*private JPanel prepareOutputMFPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ops2Button = new JButton("Two operations");
        ops2Button.addActionListener(new OperationsOutputListener());
        ops4Button = new JButton("Four operations");
        ops4Button.addActionListener(new OperationsOutputListener());
        panel.add(ops2Button);
        panel.add(ops4Button);
        return panel;
    }*/
    private JPanel prepareAreaCalcPanel() {
        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, 2));
        inputLabel = new JLabel("Input variables");
        containerPanel.add(inputLabel);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        calcAreaButton = new JButton("Area");
        rulesListLabel = new JLabel("Number of operations: ");
        sigmaListLabel = new JLabel("Sigma: ");
        realValLabel = new JLabel("Real value: ");
        calcAreaButton.addActionListener(new AreaButtonListener());
        numOfOperations = new Integer[] {2,4};
        rulesList = new JComboBox(numOfOperations);
        rulesList.setSelectedIndex(0);
        sigmaList = new JComboBox(getSigmaArray());
        sigmaList.setSelectedIndex(0);
        realValInput = new JTextField("", 3);

        panel.add(realValLabel);
        panel.add(realValInput);
        panel.add(sigmaListLabel);
        panel.add(sigmaList);
        panel.add(rulesListLabel);
        panel.add(rulesList);
        panel.add(calcAreaButton);

        containerPanel.add(panel);
        return containerPanel;
    }
    private JPanel renderInputs(List<MembershipFunction> membershipFunctions) {
        inputRenderer = new CustomRenderer(
                "Input data membership functions",
                dataFetcher.fetchMembershipData(membershipFunctions),
                "Xi",
                "µ(Xi)",
                0
        );
        return inputRenderer.render();
    }

    private JPanel renderOutputs(List<Map<Float, Double>> outputs, float[] sigmaArray, int rulesNum) {
        outputRenderer = new CustomRenderer(
                "Output data",
                dataFetcher.fetchMuData(outputs, sigmaArray),
                "y",
                "µ(y)",
                rulesNum
        );
        return outputRenderer.render();
    }
    private Float[] getSigmaArray() {
        Float[] floats = new Float[sigmaArray.length];
        for (int i = 0; i < sigmaArray.length; i++) {
            floats[i] = sigmaArray[i];
        }
        return floats;
    }
    private class OperationsOutputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double[] input = new double[inputX.length];
                for (int i = 0; i < input.length; i++) {
                    input[i] = Double.parseDouble(inputX[i].getText().trim());
                }
                if (e.getSource() == calculationPane.getOps2Button()) {
                    try {
                            topContainer.remove(1);
                        } catch (ArrayIndexOutOfBoundsException ex){}

                    topContainer.add(renderOutputs(
                            processingEngine.calculate(input, 2),
                            sigmaArray,
                            2
                    ));
                    pack();
                }
                if (e.getSource() == calculationPane.getOps4Button()) {
                    try {
                        topContainer.remove(1);
                    } catch (ArrayIndexOutOfBoundsException ex){}
                    topContainer.add(renderOutputs(
                            processingEngine.calculate(input, 4),
                            sigmaArray,
                            4
                    ));
                    pack();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                        null,
                        "Fill all fields with float numbers between 0 and 1",
                        "Input error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class AreaButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double[] input = new double[inputX.length];
                for (int i = 0; i < input.length; i++) {
                    input[i] = Double.parseDouble(inputX[i].getText().trim());
                }
                float realVal = Float.parseFloat(calculationPane.getRealValInput().getText().trim());
                float sigma = sigmaArray[calculationPane.getSigmaList().getSelectedIndex()];
                int numberOfOperations = calculationPane.getNumOfOperations()[calculationPane.getRulesList().getSelectedIndex()];
                try {
                    topContainer.remove(1);
                } catch (ArrayIndexOutOfBoundsException ex){}
                topContainer.add(processingEngine.calcArea(input, realVal, sigma, numberOfOperations));
                pack();
                //display(((Container)topContainer.getComponent(1)).getComponents());
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                        null,
                        "Fill all fields with float numbers between 0 and 1",
                        "Input error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void display(Component[] components) {
        for (Component component : components) {
            System.out.println(component.toString());
            Container container = (Container)component;
            display(container.getComponents());

        }
    }
}
