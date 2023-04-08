package model;

import java.util.List;

public interface Repository<T> {
    T find(T item);
    String create(T item);
    List<T> readAll();
    String update(T item);
    String delete(T item);
    int idIncrement();
}
