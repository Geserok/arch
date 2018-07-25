import openers.FileOpener;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import repository.*;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class App {

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
//        String excelListName = "boards";

//        String folderUrl = "F:\\PersonalKAV\\arch\\test.xls";
//        String folderUrl = "C:\\javaprojects\\arch\\test.xls";
//        List list = repositoryBoards.getAll();
//        Executor.excelWriter(folderUrl, excelListName,list);
        String excelListName = "SupplyModules";

        String folderUrl = "F:\\PersonalKAV\\arch\\test.xls";
//        String folderUrl = "C:\\javaprojects\\arch\\test.xls";
        List list = repositorySupplyModules.getAll();
//        Executor.excelWriter(folderUrl, excelListName, list);


//        System.out.println("Введите id");
//        Scanner in = new Scanner(System.in);
//        int searchid = in.nextInt();
//        SupplyModule byId = repositorySupplyModules.getById(searchid);

//        String[] split = byId.getIncludedElements().split("\\*");
//        for (String string: split) {
//
//            Boards byDecimalNumber = repositoryBoards.getByDecimalNumber(string.substring(3));
//            System.out.println(string+ " , " + byDecimalNumber.getName() + " , " + byDecimalNumber.getId());
//        }
//       FileOpener.schOpener(split[0]);
//       FileOpener.schOpener("469139018");
//       FileOpener.peOpener("469139018");
//       FileOpener.sbDwgOpener("469139018");
       FileOpener.dwgOpener("469139018");

        transaction.commit();
        session.close();
        factory.close();

    }
}
