package gui;

import openers.FileOpener;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.awt.*;

public class SettingsMenu {
    public SettingsMenu(Configuration configuration){
        JFrame settings = new JFrame("Settings");
        settings.setLayout(new GridLayout(5,1));
        settings.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel path = new JPanel();
        path.setLayout(new GridLayout(1,2));

        JPanel setLogin = new JPanel();
        setLogin.setLayout(new GridLayout(1,2));

        JPanel setPass = new JPanel();
        setPass.setLayout(new GridLayout(1,2));

        JPanel setIpAddress = new JPanel();
        setIpAddress.setLayout(new GridLayout(1,2));

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1,2));


        JTextField pathName = new JTextField("Путь к папке с архивом:");
        pathName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JTextField pathField = new JTextField("D:\\Архив\\001\\");
        pathField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JButton pathSearch = new JButton("...");
        pathSearch.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pathField.setEditable(true);
        pathName.setEditable(false);

        path.add(pathName);
        path.add(pathField);

        JTextArea name = new JTextArea("Login:");
        name.setBorder(BorderFactory.createLineBorder(Color.black));
        JTextArea nameSpace = new JTextArea(configuration.getProperty("hibernate.connection.username"));
        nameSpace.setBorder(BorderFactory.createLineBorder(Color.black));

        JTextArea password = new JTextArea("Password:");
        password.setBorder(BorderFactory.createLineBorder(Color.black));
        JTextArea passSpace = new JTextArea(configuration.getProperty("hibernate.connection.password"));
        passSpace.setBorder(BorderFactory.createLineBorder(Color.black));

        JTextArea ipAddress = new JTextArea("Ip address of MySQL server:");
        ipAddress.setBorder(BorderFactory.createLineBorder(Color.black));
        JTextArea ipAddressSpace = new JTextArea(configuration.getProperty("hibernate.connection.ip"));
        ipAddressSpace.setBorder(BorderFactory.createLineBorder(Color.black));

        name.setEditable(false);
        password.setEditable(false);
        ipAddress.setEditable(false);
        nameSpace.setEditable(false);
        passSpace.setEditable(false);
        ipAddressSpace.setEditable(false);


        setLogin.add(name);
        setLogin.add(nameSpace);

        setPass.add(password);
        setPass.add(passSpace);

        setIpAddress.add(ipAddress);
        setIpAddress.add(ipAddressSpace);

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
        settings.add(setLogin);
        settings.add(setPass);
        settings.add(setIpAddress);
        settings.add(buttons);

        settings.setVisible(true);
        settings.setSize(600, 150);
        settings.setResizable(false);
        settings.setLocation(600,400);

    }
}
