package com.vlad.fuzzy.ui.tab;

import com.vlad.fuzzy.ui.GuiEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalculationPane extends JPanel {

    private JTabbedPane jTabbedPane;

    //MF output buttons
    private JLabel mfOutputLabel;
    private JButton ops2Button;
    private JButton ops4Button;
    //calc area fields
    private JLabel calcAreaLabel;
    private JLabel rulesListLabel;
    private JLabel sigmaListLabel;
    private JLabel realValLabel;
    private JButton calcAreaButton;
    private Integer[] numOfOperations;

    public Integer[] getNumOfOperations() {
        return numOfOperations;
    }

    public JTextField getRealValInput() {
        return realValInput;
    }

    public JComboBox getRulesList() {
        return rulesList;
    }

    public JComboBox getSigmaList() {
        return sigmaList;
    }

    private JComboBox rulesList;
    private JComboBox sigmaList;
    private JTextField realValInput;


    public CalculationPane(Dimension size, ActionListener outputListener, ActionListener areaListener, Float[] sigmaArray) {
        super(new GridLayout(1,1));
        jTabbedPane = new JTabbedPane();
        jTabbedPane.addTab("Membership functions", prepareOutputMFPanel(outputListener, size));
        jTabbedPane.addTab("Area", prepareAreaCalcPanel(areaListener, sigmaArray, size));
        add(jTabbedPane);
    }

    private JPanel prepareOutputMFPanel(ActionListener outputListener, Dimension size) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ops2Button = new JButton("Two operations");
        ops2Button.addActionListener(outputListener);
        ops4Button = new JButton("Four operations");
        ops4Button.addActionListener(outputListener);
        panel.add(ops2Button);
        panel.add(ops4Button);
        panel.setPreferredSize(size);
        return panel;
    }
    private JPanel prepareAreaCalcPanel(ActionListener areaListener, Float[] sigmaArray, Dimension size) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        calcAreaButton = new JButton("Area");
        rulesListLabel = new JLabel("Number of operations: ");
        sigmaListLabel = new JLabel("Sigma: ");
        realValLabel = new JLabel("Real value: ");
        calcAreaButton.addActionListener(areaListener);
        numOfOperations = new Integer[] {2,4};
        rulesList = new JComboBox(numOfOperations);
        rulesList.setSelectedIndex(0);
        sigmaList = new JComboBox(sigmaArray);
        sigmaList.setSelectedIndex(0);
        realValInput = new JTextField("", 3);

        panel.add(realValLabel);
        panel.add(realValInput);
        panel.add(sigmaListLabel);
        panel.add(sigmaList);
        panel.add(rulesListLabel);
        panel.add(rulesList);
        panel.add(calcAreaButton);
        panel.setPreferredSize(size);
        return panel;
    }

    public JButton getOps2Button() {
        return ops2Button;
    }

    public JButton getOps4Button() {
        return ops4Button;
    }
}
