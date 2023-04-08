package view;

import model.Sellable;
import model.Toy;

public interface ToyCreator extends Sellable {
    Sellable create() throws Exception;
    Sellable parse(String data);
    Sellable setName();
    Sellable setWeight();
}
