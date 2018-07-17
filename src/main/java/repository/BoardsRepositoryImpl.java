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
            Transaction tr = session.beginTransaction();
            Boards board = new Boards();
            board.setDecimalNumber(decimalNumber);
            board.setName(name);
            session.persist(board);
            tr.commit();

            return board;
        }
    }

    @Override
    public Boards update(int id, String decimalNumber, String name) {
        try (Session session = factory.openSession()) {
            Transaction tr = session.beginTransaction();
            Boards board = session.get(Boards.class, id);
            board.setDecimalNumber(decimalNumber);
            board.setName(name);
            session.persist(board);
            tr.commit();
            return board;
        }
    }

    @Override
    public void remove(int id) {
        try (Session session = factory.openSession()) {
            Boards board = session.get(Boards.class, id);
            Transaction tr = session.beginTransaction();
            session.delete(board);
            tr.commit();
        }
    }

    @Override
    public Boards getById(int id) {
        try (Session session = factory.openSession()) {
            Transaction tr = session.beginTransaction();
            Boards board = session.get(Boards.class, id);
            tr.commit();
            return board;
        }
    }

    @Override
    public Boards getByDecimalNumber(String decimalNumber) {
        try (Session session = factory.openSession()) {
            Transaction tr = session.beginTransaction();
            Boards board = (Boards) session.createQuery("FROM boards WHERE decimalNumber = :decimalNumber")
                    .setParameter("decimalNumber",decimalNumber)
                    .getSingleResult();
            tr.commit();
            return board;
        }
    }
}
