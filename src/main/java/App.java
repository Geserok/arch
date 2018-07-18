import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import repository.Executor;
import repository.SupplyModule;
import repository.SupplyModuleRepositoryImpl;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class App {

    public static void main(String[] args) throws IOException {
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
                .addAnnotatedClass(SupplyModule.class)
                .buildSessionFactory(registry);


        SupplyModuleRepositoryImpl repository = new SupplyModuleRepositoryImpl(factory);
//            repository.create("БЕЖК01","Блок1");
//            repository.create("БЕЖК02","Блок2");
//            repository.create("БЕЖК03","Блок3");
//            repository.create("БЕЖК04","Блок4");
//            repository.create("БЕЖК05","Блок5");


        String folderUrl = "C:\\javaprojects\\arch\\test.xlsx";
        String excelListName = "Лист1";
        Map<String, String> supplyModuleMap = Executor.excelExecute(folderUrl, excelListName);
        System.out.println(supplyModuleMap);
        for (String key : supplyModuleMap.keySet()) {
            SupplyModule sm = repository.getByDecimalNumber(key);
            repository.update(sm.getId(), sm.getDecimalNumber(), supplyModuleMap.get(key));

        }

        //Запись всех файлов в лист
//        ArrayList list = (ArrayList) Executor.execute(folderUrl);
//        list.forEach(name -> repository.create((String) name,""));

        factory.close();

    }
}
