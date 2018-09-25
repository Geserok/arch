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

        Configuration configuration = new Configuration().configure();
        SessionFactory factory = configuration.buildSessionFactory();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        JFrame frame = new JFrame("First panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1,6));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JPanel jPanelLeft = new JPanel();
        JPanel jpanelRight = new JPanel();
        jpanelRight.setLayout(new BorderLayout());
        jPanelLeft.setLayout(new BorderLayout());

        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        Box box1 = Box.createVerticalBox();
        box1.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        List elements = Items.elements(factory);

        JPanel pan = PanelCreator.panelCreator(factory,frame,elements);
        frame.add(pan);
        frame.setBounds((screenSize.width-300)/2,(screenSize.height-400)/2,300,400);
        frame.setVisible(true);
    }

    public static void includeElementWindow(String selectedItem) {
        Configuration configuration = new Configuration().configure();
        SessionFactory factory = configuration.buildSessionFactory();
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
            if(boards.startsWith("436") || boards.startsWith("468")){
                StringBuffer sb = new StringBuffer(boards);
                sb.insert(6,".");
                boards = sb.toString();
                names.add(supplyModuleRepository.getByDecimalNumber(boards).getName() + " (БЕЖК." + boards + ")");
                continue;
            }
            names.add(repositoryBoards.getByDecimalNumber(boards).getName() + " (БЕЖК." + boards + ")");
        }
        return names;
    }


}

