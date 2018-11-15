package gui;

import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class PanelCreator {

    public static JPanel panelCreator(SessionFactory factory, JFrame frame, List elements) {

        JPanel jPanelLeft = new JPanel();
        jPanelLeft.setLayout(new BorderLayout());
        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JComboBox comboBox = new FilterComboBox(elements);
        JToggleButton button = new JToggleButton(">>");
        JButton dwgButton = new JButton("Спецификация");
        JButton pe3Button = new JButton("Перечень элементов");
        JButton schButton = new JButton("Электрическая принципиальная схема");
        JButton sbButton = new JButton("Сборочный чертеж");
        JButton gbButton = new JButton("Габаритный чертеж");
        JButton tuButton = new JButton("ТУ");

        comboBox.addActionListener(e -> {
            String[] split = comboBox.getSelectedItem().toString().split("БЕЖК\\.");
            if(split.length <= 1){
                return;
            }
            box.removeAll();
            box.add(dwgButton);
            box.add(pe3Button);
            box.add(schButton);
            box.add(sbButton);
            if (split[1].startsWith("436") || split[1].startsWith("468")) {
                box.add(gbButton);
                box.add(tuButton);
                jPanelLeft.add(box, BorderLayout.CENTER);
                jPanelLeft.add(button, BorderLayout.EAST);
                jPanelLeft.revalidate();
            } else if (split[1].startsWith("469")) {
                jPanelLeft.add(box, BorderLayout.CENTER);
                if (Items.includeElements(factory, split[1].substring(0, split[1].length() - 1)).size() >= 1) {
                    jPanelLeft.add(button, BorderLayout.EAST);
                } else {
                    jPanelLeft.remove(button);
                }
                jPanelLeft.revalidate();
            }
        });
        button.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (button.isSelected()) {
                    button.setText("<<");
                    String decNum = comboBox.getSelectedItem().toString().split("БЕЖК\\.")[1];
                    List includeElements = Items.includeElements(factory, decNum.substring(0, decNum.length() - 1));
                    JPanel pan = PanelCreator.panelCreator(factory, frame, includeElements);
                    frame.add(pan);
                    frame.setSize(frame.getWidth() + 300, frame.getHeight());
                } else {
                    button.setText(">>");
                    Container parent = button.getParent().getParent();
                    int componentZOrder = parent.getComponentZOrder(button.getParent()) + 1;
                    int componentCount = parent.getComponentCount();
                    while (componentCount > componentZOrder) {
                        parent.remove(componentZOrder);
                        frame.setSize(frame.getWidth() - 300, frame.getHeight());
                        componentCount--;
                    }
                }
            }
        });

        dwgButton.addActionListener(new ButtonActionListener(comboBox,"DWG"));
        gbButton.addActionListener(new ButtonActionListener(comboBox,"GB"));
        pe3Button.addActionListener(new ButtonActionListener(comboBox,"PE3"));
        tuButton.addActionListener(new ButtonActionListener(comboBox,"TU"));
        schButton.addActionListener(new ButtonActionListener(comboBox,"SCH"));
        sbButton.addActionListener(new ButtonActionListener(comboBox,"SB"));

        jPanelLeft.add(comboBox, BorderLayout.NORTH);

        return jPanelLeft;
    }

    static String checkDecNum(JComboBox comboBox) {
        String d = comboBox.getSelectedItem().toString().split("БЕЖК.")[1];
        return d.substring(0, d.length() - 1);
    }

}
