import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import repository.*;

import java.io.IOException;
import java.util.*;
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

        SupplyModule byId = repositorySupplyModules.getById(36);

        String[] split = byId.getIncludedElements().split("\\*");
        System.out.println(split[0]);
        Boards byDecimalNumber = repositoryBoards.getByDecimalNumber(split[0].substring(3));
        System.out.println(byDecimalNumber.getId());

        transaction.commit();
        session.close();
        factory.close();

    }
}
