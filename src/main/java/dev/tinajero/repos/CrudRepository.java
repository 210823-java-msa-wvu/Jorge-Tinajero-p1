package dev.tinajero.repos;
import java.util.List;

public interface CrudRepository <T> {

    //Create

    //Read
    int getById(Integer id);
    T getId(Integer id);
    List<T> getAll();
    List<T> getAllUsers(Boolean val);
    //Update
    Boolean update(T t);

    //Delete
}
