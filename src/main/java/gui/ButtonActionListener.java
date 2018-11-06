package gui;

import openers.FileOpener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ButtonActionListener implements ActionListener {
    JComboBox comboBox;
    String type;
    public ButtonActionListener(JComboBox comboBox, String type){
        this.comboBox = comboBox;
        this.type = type;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String decimalNumber = PanelCreator.checkDecNum(comboBox);
        try {
            FileOpener.getOpen(decimalNumber.trim(),TypesOfDoc.valueOf(type));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
