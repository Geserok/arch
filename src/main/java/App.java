import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import repository.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class App {

    public static void main(String[] args) throws IOException, InvalidFormatException {
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
                .buildSessionFactory(registry);


//        SupplyModuleRepositoryImpl repository = new SupplyModuleRepositoryImpl(factory);
        BoardsRepositoryImpl repository = new BoardsRepositoryImpl(factory);
//
        String folderUrl = "F:\\PersonalKAV\\arch\\test.xls";
//        String excelListName = "2014";
        List<Boards> list = repository.getAll();
        List decimalNumbers = new ArrayList();
        for (Boards aList : list) {
            decimalNumbers.add(aList.getDecimalNumber());
        }
        System.out.println(decimalNumbers);
        Executor.excelWriter(folderUrl,decimalNumbers);
//        Map<String, String> supplyModuleMap = Executor.excelExecute(folderUrl, excelListName);
//        System.out.println(supplyModuleMap);
//        for (String key : supplyModuleMap.keySet()) {
//
//            repository.create(key, supplyModuleMap.get(key));
//
//        }

        //Запись всех файлов в лист
//                String folderUrl = "D:\\Архив\\001\\469";
//        ArrayList list2 = (ArrayList) Executor.execute(folderUrl);
//        list2.forEach(name -> repository.create((String) name,""));

        factory.close();

    }
}
