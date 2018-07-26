package gui;

import repository.BoardsRepositoryImpl;
import repository.SupplyModuleRepositoryImpl;

import javax.swing.*;
import java.awt.*;

public class FirstPanel extends JFrame {
    public static void main(String[] args) {




        JFrame frame = new JFrame("First panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2,1));


        JComboBox comboBox = new JComboBox();

        JButton button = new JButton("Search");


        frame.add(comboBox);
        frame.add(button);


        frame.setSize(600,400);
        frame.setVisible(true);
    }
}
