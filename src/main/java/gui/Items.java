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


        JPanel jPanelLeft = new JPanel();
        JPanel jpanelRight = new JPanel();
        jpanelRight.setLayout(new BorderLayout());
        jPanelLeft.setLayout(new GridLayout(6,1));

        Box box1 = Box.createVerticalBox();
        box1.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        List elements = Items.elements(factory);



        JComboBox comboBox = createAndShowGUI(elements);


//Buttons of Modules
        JButton button = new JButton(">>");
        JButton dwgButton = new JButton("Спецификация");
        JButton pe3Button = new JButton("Перечень элементов");
        JButton schButton = new JButton("Электрическая принципиальная схема");
        JButton sbButton = new JButton("Сборочный чертеж");
        JButton gbButton = new JButton("Габаритный чертеж");
        JButton tuButton = new JButton("ТУ");





        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpanelRight.removeAll();
                box1.removeAll();
                JButton pe3BoardButton = new JButton("Перечень элементов");
                JButton schBoardButton = new JButton("Электрическая принципиальная схема");
                JButton sbBoardButton = new JButton("Сборочный чертеж");

                box1.add(pe3BoardButton);
                box1.add(schBoardButton);
                box1.add(sbBoardButton);
                JComboBox comboBoxInclude;
                String decNum = comboBox.getSelectedItem().toString().split("БЕЖК.")[1];

                List includeElements = Items.includeElements(factory, decNum.substring(0, decNum.length() - 1));
                comboBoxInclude = createAndShowGUI(includeElements);
                pe3BoardButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String d = comboBoxInclude.getSelectedItem().toString().split("БЕЖК.")[1];

                        String decimalNumber = d.substring(0, d.length() - 1);

                        try {
                            FileOpener.peOpener(decimalNumber.trim());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
                schBoardButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String d = comboBoxInclude.getSelectedItem().toString().split("БЕЖК.")[1];

                        String decimalNumber = d.substring(0, d.length() - 1);

                        try {
                            FileOpener.schOpener(decimalNumber.trim());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
                sbBoardButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String d = comboBoxInclude.getSelectedItem().toString().split("БЕЖК.")[1];

                        String decimalNumber = d.substring(0, d.length() - 1);

                        try {
                            FileOpener.sbDwgOpener(decimalNumber.trim());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
                jpanelRight.add(comboBoxInclude,BorderLayout.NORTH);
                jpanelRight.add(box1,BorderLayout.CENTER);
                frame.setSize(700,400);
                jpanelRight.revalidate();
                frame.invalidate();
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


        frame.add(comboBox, BorderLayout.NORTH);
        jPanelLeft.add(dwgButton);
        jPanelLeft.add(sbButton);
        jPanelLeft.add(gbButton);
        jPanelLeft.add(pe3Button);
        jPanelLeft.add(tuButton);
        jPanelLeft.add(schButton);
        jpanelRight.setSize(300,400);
        JPanel jpanelCenter = new JPanel();
        jpanelCenter.setSize(10,400);
        jpanelCenter.add(button);
        button.setSize(10,400);
        frame.add(jPanelLeft, BorderLayout.WEST);
        frame.add(jpanelCenter, BorderLayout.CENTER);
        frame.add(jpanelRight, BorderLayout.EAST);


        frame.setSize(400, 400);
        frame.setVisible(true);
//        transaction.commit();
//        session.close();
//        factory.close();
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
        List<String> names = new ArrayList();
        SupplyModule supmod = supplyModuleRepository.getByDecimalNumber(decimalNumber);
        String[] InludeBoards = supmod.getIncludedElements().split("\\*");
        if(InludeBoards == null){
            names.add("Empty");
            return names;
        }
        for (String boards : InludeBoards) {
            if(boards.startsWith("436")){
                StringBuffer sb = new StringBuffer(boards);
                sb.insert(6,".");
                boards = sb.toString();
                names.add(supplyModuleRepository.getByDecimalNumber(boards).getName() + " (БЕЖК." + boards + ")");
                continue;
            }
            names.add(repositoryBoards.getByDecimalNumber(boards.substring(3)).getName() + " (БЕЖК." + boards + ")");
        }
        return names;
    }
}