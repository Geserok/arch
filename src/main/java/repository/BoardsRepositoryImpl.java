package repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class BoardsRepositoryImpl implements BoardsRepository {

    private final SessionFactory factory;

    public BoardsRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Boards create(String decimalNumber, String name) {
        try (Session session = factory.openSession()) {
            Boards board = new Boards();
            board.setDecimalNumber(decimalNumber);
            board.setName(name);
            Transaction tr = session.beginTransaction();
            session.persist(board);
            tr.commit();

            return board;
        }
    }

    @Override
    public Boards update(String decimalNumber, String name) {
        return null;
    }

    @Override
    public Boards delete(String decimalNumber) {
        return null;
    }

    @Override
    public Boards getById(String decimalNumber) {
        return null;
    }
}
