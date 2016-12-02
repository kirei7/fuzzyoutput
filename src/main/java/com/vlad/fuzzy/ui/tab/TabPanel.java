package com.vlad.fuzzy.ui.tab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabPanel extends JPanel {

    //switch tabs buttons
    private JButton toMFButton = new SwitchButton("Membership functions");
    private JButton toAreaButton = new SwitchButton("Area");
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
    private JComboBox rulesList;
    private JComboBox sigmaList;
    private JTextField realValInput;

    public TabPanel() {
        this.setLayout(new GridLayout(2, 1));
        JPanel switchHeader = new JPanel(new GridLayout(1, 2));
        switchHeader.add(toMFButton);
        switchHeader.add(toAreaButton);
        ActionListener buttonListener = new SwitchButtonListener();
        toMFButton.addActionListener(buttonListener);
        toAreaButton.addActionListener(buttonListener);
        this.add(switchHeader);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Container container = frame.getContentPane();
        container.add(new TabPanel());
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private class SwitchButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("clicked");
        }
    }
}
