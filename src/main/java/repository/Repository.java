package repository;

public interface Repository {

    Object create(String decimalNumber, String name);

    Object update(int id, String decimalNumber, String name);

    void remove(int id);

    Object getById(int id);

    Object getByDecimalNumber(String decimalNumber);

}
