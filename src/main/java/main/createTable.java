package main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.cfg.Configuration;
import repository.*;


public class createTable {
    public static void main(String[] args) {

        Configuration configuration = new Configuration().configure();
        SessionFactory factory = configuration.buildSessionFactory();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();


        BoardsRepositoryImpl repositoryBoards = new BoardsRepositoryImpl(factory);
        SupplyModuleRepositoryImpl repositorySupplyModules = new SupplyModuleRepositoryImpl(factory);
        String excelListName = "boards";


//        String folderUrl = "D:\\Архив\\001\\469";
//        String folderUrl = "C:\\javaprojects\\arch\\Архив\\469";
//
//        ArrayList list2 = (ArrayList) Executor.execute(folderUrl);
//        list2.forEach(name -> repositoryBoards.create((String) name,""));

//        folderUrl = "F:\\PersonalKAV\\arch\\test.xls";
//        folderUrl = "C:\\javaprojects\\arch\\test.xls";
//
//        Map<String, String> boardsMap = Executor.excelExecute(folderUrl, excelListName,3,2);
//        for (String key : boardsMap.keySet()) {
//            Boards boards = repositoryBoards.getByDecimalNumber(key);
//            repositoryBoards.update(boards.getId(),key, boardsMap.get(key),"");
//        }


//        folderUrl = "F:\\PersonalKAV\\Журнал.xls";
        String folderUrl = "F:\\PersonalKAV\\arch\\SupplyModule.xls";
        excelListName = "SupplyModules";
//
//
//        Map<String, String> supplyModulesMap = Executor.excelExecute(folderUrl, excelListName,2,3);
//        for (String key : supplyModulesMap.keySet()) {
//            try {
//                SupplyModule supplyModule = repositorySupplyModules.getByDecimalNumber(key);
//                repositorySupplyModules.update(supplyModule.getId(),supplyModulesMap.get(key));
//            }
//            catch (NullPointerException e){
//                continue;
//            }
//        }

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
