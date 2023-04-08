package model;

import java.util.List;

public abstract class Shop<T extends Sellable> implements Repository<T>{
    protected List<T> items;
}
