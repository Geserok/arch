package gui;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repository.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Items {
    public static void moduleWindow() {

        Configuration configuration = new Configuration().configure();
        SessionFactory factory = configuration.buildSessionFactory();

        JFrame frame = new JFrame("First panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 6));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Properties");
        fileMenu.add(openItem);
        menuBar.add(fileMenu);

        openItem.addActionListener(e -> {
            SettingsMenu settingsMenu = new SettingsMenu();
        });

        JPanel jPanelLeft = new JPanel();
        JPanel jpanelRight = new JPanel();
        jpanelRight.setLayout(new BorderLayout());
        jPanelLeft.setLayout(new BorderLayout());

        Box box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Box box1 = Box.createVerticalBox();
        box1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        List elements = Items.elements(factory);

        JPanel pan = PanelCreator.panelCreator(factory, frame, elements);
        frame.add(pan);
        frame.setJMenuBar(menuBar);
        frame.setBounds((screenSize.width - 300) / 2, (screenSize.height - 400) / 2, 300, 400);
        frame.setVisible(true);
    }

    private static List elements(SessionFactory factory) {
        BoardsRepositoryImpl repositoryBoards = new BoardsRepositoryImpl(factory);
        SupplyModuleRepositoryImpl repositorySupplyModules = new SupplyModuleRepositoryImpl(factory);
        List<SupplyModule> modulesAll = repositorySupplyModules.getAll();
        List<String> names = new ArrayList();
        for (SupplyModule sup : modulesAll) {
            names.add(sup.getName() + " (БЕЖК." + sup.getDecimalNumber() + ")");
        }
        Collections.sort(names);
        return names;
    }

    public static List includeElements(SessionFactory factory, String decimalNumber) {
        BoardsRepositoryImpl repositoryBoards = new BoardsRepositoryImpl(factory);
        SupplyModuleRepositoryImpl supplyModuleRepository = new SupplyModuleRepositoryImpl(factory);
        List<String> names = new ArrayList();
        return namesCreate(decimalNumber, supplyModuleRepository, repositoryBoards, names);
    }

    private static List namesCreate(String decimalNumber, Repository supplyModuleRepository,
                                    Repository repositoryBoards, List<String> names){
        Product product;
        if (decimalNumber.startsWith("436") || decimalNumber.startsWith("468")) {
            product = (SupplyModule) supplyModuleRepository.getByDecimalNumber(decimalNumber);
        } else if (decimalNumber.startsWith("469")) {
            product = (Boards) repositoryBoards.getByDecimalNumber(decimalNumber);
        } else {
            throw new IllegalArgumentException("Wrong!");
        }
        String[] includeBoards = product.getIncludedElements().split("\\*");

        if (includeBoards == null) {
            names.add("Empty");
            return names;
        }
        for (String boards : includeBoards) {
            StringBuffer sb = new StringBuffer(boards);
            sb.insert(6, ".");
            boards = sb.toString();
            if (boards.startsWith("436") || boards.startsWith("468")) {
                SupplyModule sup = (SupplyModule) supplyModuleRepository.getByDecimalNumber(boards);
                names.add(sup.getName() + " (БЕЖК." + boards + ")");
            } else {
                Boards boar = (Boards) repositoryBoards.getByDecimalNumber(boards);
                names.add(boar.getName() + " (БЕЖК." + boards + ")");
            }
        }
        return names;
    }
}

