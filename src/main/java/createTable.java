import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import repository.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

public class createTable {
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


        properties.setProperty("hibernate.hbm2ddl.auto", "create");


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






        transaction.commit();
        session.close();
//        SupplyModuleRepositoryImpl repository = new SupplyModuleRepositoryImpl(factory);
//        BoardsRepositoryImpl repository = new BoardsRepositoryImpl(factory);
//
        BoardsRepositoryImpl repositoryBoards = new BoardsRepositoryImpl(factory);
        SupplyModuleRepositoryImpl repositorySupplyModules = new SupplyModuleRepositoryImpl(factory);
        String excelListName = "boards";


        String folderUrl = "D:\\Архив\\001\\469";

        ArrayList list2 = (ArrayList) Executor.execute(folderUrl);
        list2.forEach(name -> repositoryBoards.create((String) name,""));

        folderUrl = "F:\\PersonalKAV\\arch\\test.xls";

        Map<String, String> boardsMap = Executor.excelExecute(folderUrl, excelListName,3,2);
        for (String key : boardsMap.keySet()) {
            Boards boards = repositoryBoards.getByDecimalNumber(key);
            repositoryBoards.update(boards.getId(),key, boardsMap.get(key));
        }


        folderUrl = "F:\\PersonalKAV\\Журнал.xls";
        excelListName = "2014";


        Map<String, String> supplyModulesMap = Executor.excelExecute(folderUrl, excelListName,3,2);
        for (String key : supplyModulesMap.keySet()) {
            try {
                repositorySupplyModules.create(supplyModulesMap.get(key), key);
            }
            catch (Exception e){
                continue;
            }
        }

//        List<Boards> list = repository.getAll();
//        List decimalNumbers = new ArrayList();
//        for (Boards aList : list) {
//            decimalNumbers.add(aList.getDecimalNumber());
//        }
//        System.out.println(decimalNumbers);
//        Executor.excelWriter(folderUrl,excelListName,decimalNumbers);

        //Запись всех файлов в лист
//                String folderUrl = "D:\\Архив\\001\\469";
//        ArrayList list2 = (ArrayList) Executor.execute(folderUrl);
//        list2.forEach(name -> repository.create((String) name,""));

        factory.close();

    }
}
