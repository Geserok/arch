package gui;

import openers.FileOpener;

import javax.swing.*;
import java.awt.*;

public class SettingsMenu {
    public SettingsMenu(){
        JFrame settings = new JFrame();
        settings.setLayout(new GridLayout(3,1));
        settings.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel path = new JPanel();
        path.setLayout(new GridLayout(1,3));

        JPanel set = new JPanel();
        set.setLayout(new GridLayout(2,2));

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1,2));

        JTextField pathName = new JTextField("Путь к папке с архивом:");
        pathName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JTextField pathField = new JTextField("D:\\Архив\\001\\");
        pathField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JButton pathSearch = new JButton("...");
        pathSearch.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        path.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pathField.setEditable(true);
        pathName.setEditable(false);
        Box box = Box.createVerticalBox();
        Box box2 = Box.createHorizontalBox();
        box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        box.add(pathName);

        box2.add(pathField);
        box2.add(pathSearch);
        box.add(box2);
        path.add(box);


        JTextArea name = new JTextArea("Login:");
        JTextArea password = new JTextArea("Password:");
        password.setBorder(BorderFactory.createLineBorder(Color.black));
        JTextArea nameSpace = new JTextArea();
        nameSpace.setBorder(BorderFactory.createLineBorder(Color.black));
        JTextArea passSpace = new JTextArea();
        passSpace.setBorder(BorderFactory.createLineBorder(Color.black));
        name.setEditable(false);
        password.setEditable(false);
        nameSpace.setEditable(true);
        passSpace.setEditable(true);
        set.add(name);
        set.add(nameSpace);
        set.add(password);
        set.add(passSpace);

        JButton ok = new JButton("ok");
        ok.setBorder(BorderFactory.createLineBorder(Color.black));
        ok.addActionListener(e -> {
            FileOpener.setUrl(pathField.getText());
            settings.dispose();
        });

        JButton cancel = new JButton("cancel");
        cancel.setBorder(BorderFactory.createLineBorder(Color.black));
        cancel.addActionListener(e -> settings.dispose());
        buttons.add(ok);
        buttons.add(cancel);

        settings.add(path);
        settings.add(set);
        settings.add(buttons);

        settings.setVisible(true);
        settings.setSize(600, 400);
    }
}
