package model;

import java.util.List;

public interface Persistable<T> {
    void save(List<T> data);
}
