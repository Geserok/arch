import repository.Boards;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import repository.BoardsRepository;
import repository.BoardsRepositoryImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

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

        SessionFactory factory = new Configuration()
                .addAnnotatedClass(Boards.class)
                .buildSessionFactory(registry);


        BoardsRepository repository = new BoardsRepositoryImpl(factory);

            String folderUrl = "C:\\Java\\jdk-10.0.1\\bin\\dtplugin";
            //Запись всех файлов в лист
        List files = Files.walk(Paths.get(folderUrl))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());

        Iterator iterator = files.iterator();
        while (iterator.hasNext()) {
            String[] splitFileName = iterator.next().toString().split("\\\\");
            int length = splitFileName.length;
            String nameWithType = splitFileName[length - 1];
            String[] split = nameWithType.split("\\.");
            String fileName = split[0];
            Boards boards = repository.create(fileName,"");
            System.out.println(fileName);
        }
//        Boards board1 = repository.create("БЕЖК01","Плата управления");
//        Boards board2 = repository.create("БЕЖК02","Плата управления");
//        Boards board3 = repository.create("БЕЖК03","Плата управления");
//        Boards board4 = repository.create("БЕЖК04","Плата управления");
//        Boards board5 = repository.create("БЕЖК05","Плата управления");
//        Boards board6 = repository.create("БЕЖК06","Плата управления");
//        Boards board7 = repository.getByDecimalNumber("БЕЖК03");
//        System.out.println(board7);



        factory.close();

    }
}
