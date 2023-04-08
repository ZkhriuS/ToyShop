package validation;

import model.Toy;

public interface ToyValidator extends Validator{
    void setToy(String[] item);
}
