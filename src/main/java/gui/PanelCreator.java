package gui;

import openers.FileOpener;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.List;

import static gui.AutoCompletion.createAndShowGUI;

public class PanelCreator {

    public static JPanel panelCreator(SessionFactory factory, JFrame frame ,List elements){

        JPanel jPanelLeft = new JPanel();
        jPanelLeft.setLayout(new BorderLayout());
        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JComboBox comboBox = createAndShowGUI(elements);
        JToggleButton button = new JToggleButton(">>");
        JButton dwgButton = new JButton("Спецификация");
        JButton pe3Button = new JButton("Перечень элементов");
        JButton schButton = new JButton("Электрическая принципиальная схема");
        JButton sbButton = new JButton("Сборочный чертеж");
        JButton gbButton = new JButton("Габаритный чертеж");
        JButton tuButton = new JButton("ТУ");

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] split = comboBox.getSelectedItem().toString().split("БЕЖК\\.");
                if(split[1].startsWith("436") || split[1].startsWith("468")){
                    box.removeAll();
                    box.add(dwgButton);
                    box.add(pe3Button);
                    box.add(schButton);
                    box.add(sbButton);
                    box.add(gbButton);
                    box.add(tuButton);
                    jPanelLeft.add(box,BorderLayout.CENTER);
                    jPanelLeft.add(button,BorderLayout.EAST);
                    jPanelLeft.revalidate();
                }
                else if(split[1].startsWith("469")){
                    box.removeAll();
                    box.add(dwgButton);
                    box.add(pe3Button);
                    box.add(schButton);
                    box.add(gbButton);
                    jPanelLeft.add(box,BorderLayout.CENTER);
                    jPanelLeft.revalidate();
                }
            }
        });

        button.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(button.isSelected()){
                    button.setText("<<");

                    String decNum = comboBox.getSelectedItem().toString().split("БЕЖК.")[1];
//
                    List includeElements = Items.includeElements(factory, decNum.substring(0, decNum.length() - 1));
                    JPanel pan = PanelCreator.panelCreator(factory,frame,includeElements);
                    frame.add(pan);
                    frame.setSize(frame.getWidth()+300,frame.getHeight());
                }
                else {
                    button.setText(">>");
                    Container parent = button.getParent().getParent();
                    int componentZOrder = parent.getComponentZOrder(button.getParent())+1;
                    int componentCount = parent.getComponentCount();
            for(int i = componentZOrder; i < componentCount; i++) {
                parent.remove(i);
                frame.setSize( frame.getWidth()-300,frame.getHeight());
            }



                }
            }
        });
        dwgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String d = comboBox.getSelectedItem().toString().split("БЕЖК.")[1];

                String decimalNumber = d.substring(0, d.length() - 1);

                try {
                    FileOpener.dwgOpener(decimalNumber.trim());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        gbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String d = comboBox.getSelectedItem().toString().split("БЕЖК.")[1];

                String decimalNumber = d.substring(0, d.length() - 1);

                try {
                    FileOpener.gbOpener(decimalNumber.trim());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        pe3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String d = comboBox.getSelectedItem().toString().split("БЕЖК.")[1];

                String decimalNumber = d.substring(0, d.length() - 1);

                try {
                    FileOpener.peOpener(decimalNumber.trim());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        tuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String d = comboBox.getSelectedItem().toString().split("БЕЖК.")[1];

                String decimalNumber = d.substring(0, d.length() - 1);

                try {
                    FileOpener.tuOpener(decimalNumber.trim());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        schButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String d = comboBox.getSelectedItem().toString().split("БЕЖК.")[1];

                String decimalNumber = d.substring(0, d.length() - 1);

                try {
                    FileOpener.schOpener(decimalNumber.trim());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        sbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String d = comboBox.getSelectedItem().toString().split("БЕЖК.")[1];

                String decimalNumber = d.substring(0, d.length() - 1);

                try {
                    FileOpener.sbDwgOpener(decimalNumber.trim());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        jPanelLeft.add(comboBox,BorderLayout.NORTH);

   return jPanelLeft;
    }


}
