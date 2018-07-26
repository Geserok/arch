package gui;

import org.hibernate.SessionFactory;
import repository.BoardsRepositoryImpl;
import repository.SupplyModule;
import repository.SupplyModuleRepositoryImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Items {
    public static List elements(SessionFactory factory) {
        BoardsRepositoryImpl repositoryBoards = new BoardsRepositoryImpl(factory);
        SupplyModuleRepositoryImpl repositorySupplyModules = new SupplyModuleRepositoryImpl(factory);
        List<SupplyModule> modulesAll = repositorySupplyModules.getAll();
        List<String> names = new ArrayList();
        for (SupplyModule sup: modulesAll) {
            names.add(sup.getName() + " (БЕЖК." + sup.getDecimalNumber() + ")");
        }
        return names;
    }
}