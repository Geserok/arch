package repository;

import java.util.List;

public interface Repository {

    Object create(String decimalNumber, String name);

    Object update(int id, String decimalNumber, String name,String includeElements);

    void remove(int id);

    Object getById(int id);

    Object getByDecimalNumber(String decimalNumber);

    List getAll();
}
