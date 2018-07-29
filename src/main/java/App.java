import gui.Items;
import openers.FileOpener;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import repository.*;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static gui.AutoCompletion.createAndShowGUI;

public class App{

    public static void main(String[] args) throws IOException, InvalidFormatException, NoSuchFieldException, IllegalAccessException {
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

//        SessionFactory factory = new Configuration()
//                .addAnnotatedClass(Boards.class)
//                .buildSessionFactory(registry);
        SessionFactory factory = new Configuration()
                .addAnnotatedClass(Boards.class)
                .addAnnotatedClass(SupplyModule.class)
                .buildSessionFactory(registry);

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        BoardsRepositoryImpl repositoryBoards = new BoardsRepositoryImpl(factory);
        SupplyModuleRepositoryImpl repositorySupplyModules = new SupplyModuleRepositoryImpl(factory);

//        FileOpener.includeElementsMenu(factory);


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
                System.out.println(comboBox.getSelectedItem());
            }
        });
        JButton dwgButton = new JButton("Спецификация");
        JButton pe3Button = new JButton("Перечень элементов");
        JButton schButton = new JButton("Электрическая принципиальная схема");
        JButton sbButton = new JButton("Сборочный чертеж");

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
        frame.add(comboBox,BorderLayout.NORTH);
        jPanel.add(dwgButton);
        jPanel.add(pe3Button);
        jPanel.add(schButton);
        jPanel.add(sbButton);
        frame.add(jPanel,BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);


        frame.setSize(600, 400);
        frame.setVisible(true);


        transaction.commit();
        session.close();
        factory.close();

    }
}
