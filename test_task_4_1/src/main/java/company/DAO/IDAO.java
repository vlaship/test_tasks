package company.DAO;

import java.util.List;

public interface IDAO<T> {

    void add(T t);

    void remove(T t);

    void update(T t);

    T getById(int id);

    List<T> getList();

}