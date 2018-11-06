package gui;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import repository.Boards;
import repository.BoardsRepositoryImpl;
import repository.SupplyModule;
import repository.SupplyModuleRepositoryImpl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Items {
    public static void moduleWindow() {

        Configuration configuration = new Configuration().configure();
        SessionFactory factory = configuration.buildSessionFactory();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        JFrame frame = new JFrame("First panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 6));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

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
        frame.setBounds((screenSize.width - 300) / 2, (screenSize.height - 400) / 2, 300, 400);
        frame.setVisible(true);
    }

    public static List elements(SessionFactory factory) {
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
        if (decimalNumber.startsWith("436") || decimalNumber.startsWith("468")) {
            BoardsRepositoryImpl repositoryBoards = new BoardsRepositoryImpl(factory);
            SupplyModuleRepositoryImpl supplyModuleRepository = new SupplyModuleRepositoryImpl(factory);
            List<String> names = new ArrayList();
            SupplyModule supmod = supplyModuleRepository.getByDecimalNumber(decimalNumber);
            String[] InludeBoards = supmod.getIncludedElements().split("\\*");
            if (InludeBoards == null) {
                names.add("Empty");
                return names;
            }
            for (String boards : InludeBoards) {
                StringBuffer sb = new StringBuffer(boards);
                sb.insert(6, ".");
                boards = sb.toString();
                if (boards.startsWith("436") || boards.startsWith("468")) {
                    names.add(supplyModuleRepository.getByDecimalNumber(boards).getName() + " (БЕЖК." + boards + ")");
                    continue;
                }
                names.add(repositoryBoards.getByDecimalNumber(boards).getName() + " (БЕЖК." + boards + ")");
            }
            return names;
        } else if (decimalNumber.startsWith("469")) {
            BoardsRepositoryImpl repositoryBoards = new BoardsRepositoryImpl(factory);
            SupplyModuleRepositoryImpl supplyModuleRepository = new SupplyModuleRepositoryImpl(factory);
            List<String> names = new ArrayList();
            Boards board = repositoryBoards.getByDecimalNumber(decimalNumber);
            String[] InludeBoards = board.getIncludedElements().split("\\*");
            if (InludeBoards == null) {
                names.add("Empty");
                return names;
            }
            for (String boards : InludeBoards) {
                StringBuffer sb = new StringBuffer(boards);
                sb.insert(6, ".");
                boards = sb.toString();
                if (boards.startsWith("436") || boards.startsWith("468")) {
                    names.add(supplyModuleRepository.getByDecimalNumber(boards).getName() + " (БЕЖК." + boards + ")");
                    continue;
                }
                names.add(repositoryBoards.getByDecimalNumber(boards).getName() + " (БЕЖК." + boards + ")");
            }
            return names;
        }
        return null;
    }
}

