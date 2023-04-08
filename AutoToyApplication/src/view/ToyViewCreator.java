package view;

import format.ModelTXT;
import model.Toy;
import validation.ToyValidator;

import java.time.LocalDateTime;

public class ToyViewCreator extends Toy implements ToyCreator {
    private View view;
    private ToyValidator validator;
    public ToyViewCreator(View view, ToyValidator validator){
        this.view = view;
        this.validator = validator;
    }
    @Override
    public Toy create() throws Exception{
        String[] data = new String[ModelTXT.values().length];
        data[ModelTXT.NAME.ordinal()] = view.prompt("Введите название: ");
        data[ModelTXT.ID.ordinal()] = "0";
        data[ModelTXT.QUANTITY.ordinal()] = view.prompt("Введите количество: ");
        data[ModelTXT.WEIGHT.ordinal()] = view.prompt("Введите вес выпадения: ");
        validator.setToy(data);
        validator.validate();
        this.setId(data[ModelTXT.ID.ordinal()]);
        this.setName(data[ModelTXT.NAME.ordinal()]);
        this.setQuantity(Integer.parseInt(data[ModelTXT.QUANTITY.ordinal()]));
        this.setWeight(Float.parseFloat(data[ModelTXT.WEIGHT.ordinal()]));
        return this;
    }

    @Override
    public Toy parse(String data) {
        Toy toy = new Toy();
        toy.setId(data.split(comments[0])[1].split(comments[1])[0]);
        toy.setName(data.split(comments[1])[1].split(comments[2])[0]);
        toy.setQuantity(Integer.parseInt(data.split(comments[2])[1].split(comments[3])[0]));
        toy.setWeight(Float.parseFloat(data.split(comments[3])[1]));
        return toy;
    }

    @Override
    public Toy setName() {
        this.setName(view.prompt("Введите новое название: "));
        return this;
    }

    @Override
    public Toy setWeight() {
        this.setWeight(Float.parseFloat(view.prompt("Введите новый вес выпадения: ")));
        return this;
    }
}
