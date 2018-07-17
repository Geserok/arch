package repository;

public interface BoardsRepository {

    Boards create(String decimalNumber, String name);

    Boards update(int id, String decimalNumber, String name);

    void remove(int id);

    Boards getById(int id);

    Boards getByDecimalNumber(String decimalNumber);

}
