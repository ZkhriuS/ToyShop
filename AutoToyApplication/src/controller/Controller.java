package controller;

public interface Controller<T> {
    String create(T item);
    String update(T item);
    String delete(T item);
    String getItem(String id);
    String list();
}
