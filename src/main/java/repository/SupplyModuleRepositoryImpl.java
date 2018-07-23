package repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class SupplyModuleRepositoryImpl implements Repository {
    private final SessionFactory factory;

    public SupplyModuleRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
    }

    public SupplyModule create(String decimalNumber, String name) {
        try (Session session = factory.openSession()) {
            Transaction tr = session.beginTransaction();
            SupplyModule supplyModule = new SupplyModule();
            supplyModule.setDecimalNumber(decimalNumber);
            supplyModule.setName(name);
            session.persist(supplyModule);
            tr.commit();

            return supplyModule;
        }
    }

    public SupplyModule update(int id, String decimalNumber, String name, String includeElements) {
        try (Session session = factory.openSession()) {
            Transaction tr = session.beginTransaction();
            SupplyModule supplyModule = session.get(SupplyModule.class, id);
            supplyModule.setDecimalNumber(decimalNumber);
            supplyModule.setName(name);
            supplyModule.setIncludedElements(includeElements);
            session.update(supplyModule);
            tr.commit();
            return supplyModule;
        }
    }

    public SupplyModule update(int id, String includeElements){
        try (Session session = factory.openSession()) {
            Transaction tr = session.beginTransaction();
            SupplyModule supplyModule = session.get(SupplyModule.class, id);
            supplyModule.setIncludedElements(includeElements);
            session.update(supplyModule);
            tr.commit();
            return supplyModule;
        }
    }

    public void remove(int id) {
        try (Session session = factory.openSession()) {
            SupplyModule supplyModule = session.get(SupplyModule.class, id);
            Transaction tr = session.beginTransaction();
            session.delete(supplyModule);
            tr.commit();
        }
    }

    public SupplyModule getById(int id) {
        try (Session session = factory.openSession()) {
            Transaction tr = session.beginTransaction();
            SupplyModule supplyModule = session.get(SupplyModule.class, id);
            tr.commit();
            return supplyModule;
        }    }

    public SupplyModule getByDecimalNumber(String decimalNumber) {
        try (Session session = factory.openSession()) {
            Transaction tr = session.beginTransaction();
            SupplyModule supplyModule = (SupplyModule) session.createQuery("FROM SupplyModule WHERE decimalNumber = :decimalNumber")
                    .setParameter("decimalNumber",decimalNumber)
                    .getSingleResult();
            tr.commit();
            return supplyModule;
        }    }

    @Override
    public List getAll() {
        return null;
    }
}
