package repository;

public interface BoardsRepository {

    Boards create(String decimalNumber, String name);

    Boards update(String decimalNumber, String name);

    Boards delete(String decimalNumber);

    Boards getById(String decimalNumber);

}
