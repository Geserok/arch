package gui;

import openers.FileOpener;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import repository.Boards;
import repository.BoardsRepositoryImpl;
import repository.SupplyModule;
import repository.SupplyModuleRepositoryImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static gui.AutoCompletion.createAndShowGUI;

public class Items {
    public static void moduleWindow() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url",
                "jdbc:mysql://localhost:3306/arch?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false");
        properties.setProperty("hibernate.connection.driver_class",
                "com.mysql.cj.jdbc.Driver");
        properties.setProperty("hibernate.connection.username", "root");
        properties.setProperty("hibernate.connection.password", "root");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");


        properties.setProperty("hibernate.hbm2ddl.auto", "update");


        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(properties)
                .build();
        SessionFactory factory = new Configuration()
                .addAnnotatedClass(Boards.class)
                .addAnnotatedClass(SupplyModule.class)
                .buildSessionFactory(registry);

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        JFrame frame = new JFrame("First panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        List elements = Items.elements(factory);
        JComboBox comboBox = createAndShowGUI(elements);


        JButton button = new JButton("Search");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String decNum = comboBox.getSelectedItem().toString().split("БЕЖК.")[1];
                Items.includeElementWindow(decNum.substring(0, decNum.length() - 1));

            }
        });
        JButton dwgButton = new JButton("Спецификация");
        JButton pe3Button = new JButton("Перечень элементов");
        JButton schButton = new JButton("Электрическая принципиальная схема");
        JButton sbButton = new JButton("Сборочный чертеж");
        JButton gbButton = new JButton("Габаритный чертеж");
        JButton tuButton = new JButton("ТУ");

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


        frame.add(comboBox, BorderLayout.NORTH);
        jPanel.add(dwgButton);
        jPanel.add(sbButton);
        jPanel.add(gbButton);
        jPanel.add(pe3Button);
        jPanel.add(tuButton);
        jPanel.add(schButton);

        frame.add(jPanel, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);


        frame.setSize(600, 400);
        frame.setVisible(true);
        transaction.commit();
        session.close();
        factory.close();
    }

    public static void includeElementWindow(String selectedItem) {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url",
                "jdbc:mysql://localhost:3306/arch?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false");
        properties.setProperty("hibernate.connection.driver_class",
                "com.mysql.cj.jdbc.Driver");
        properties.setProperty("hibernate.connection.username", "root");
        properties.setProperty("hibernate.connection.password", "root");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");


        properties.setProperty("hibernate.hbm2ddl.auto", "update");


        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(properties)
                .build();
        SessionFactory factory = new Configuration()
                .addAnnotatedClass(Boards.class)
                .addAnnotatedClass(SupplyModule.class)
                .buildSessionFactory(registry);

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        JFrame frame = new JFrame("Include element panel");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        String decimalNumber = selectedItem.trim();
        decimalNumber.replaceAll("\\.","");
        List elements = Items.includeElements(factory, decimalNumber);
        JComboBox comboBox = createAndShowGUI(elements);


        JButton dwgButton = new JButton("Спецификация");
        JButton pe3Button = new JButton("Перечень элементов");
        JButton schButton = new JButton("Электрическая принципиальная схема");
        JButton sbButton = new JButton("Сборочный чертеж");
        JButton gbButton = new JButton("Габаритный чертеж");
        JButton tuButton = new JButton("ТУ");

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

        frame.add(comboBox, BorderLayout.NORTH);
        jPanel.add(dwgButton);
        jPanel.add(sbButton);
        jPanel.add(gbButton);
        jPanel.add(pe3Button);
        jPanel.add(tuButton);
        jPanel.add(schButton);


        frame.add(jPanel, BorderLayout.CENTER);

        frame.setSize(600, 400);
        frame.setVisible(true);
        transaction.commit();
        session.close();
        factory.close();
    }


    public static List elements(SessionFactory factory) {
        BoardsRepositoryImpl repositoryBoards = new BoardsRepositoryImpl(factory);
        SupplyModuleRepositoryImpl repositorySupplyModules = new SupplyModuleRepositoryImpl(factory);
        List<SupplyModule> modulesAll = repositorySupplyModules.getAll();
        List<String> names = new ArrayList();
        for (SupplyModule sup : modulesAll) {
            names.add(sup.getName() + " (БЕЖК." + sup.getDecimalNumber() + ")");
        }
        return names;
    }

    public static List includeElements(SessionFactory factory, String decimalNumber) {
        BoardsRepositoryImpl repositoryBoards = new BoardsRepositoryImpl(factory);
        SupplyModuleRepositoryImpl supplyModuleRepository = new SupplyModuleRepositoryImpl(factory);
        System.out.println(decimalNumber);
        String[] InludeBoards = supplyModuleRepository.getByDecimalNumber(decimalNumber).getIncludedElements().split("\\*");
        List<String> names = new ArrayList();
        for (String boards : InludeBoards) {
            names.add(repositoryBoards.getByDecimalNumber(boards.substring(3)).getName() + " (БЕЖК." + boards + ")");
        }
        return names;
    }
}