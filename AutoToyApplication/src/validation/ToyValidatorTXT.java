package validation;

import format.FormatTXT;
import format.ModelTXT;
import format.ToyFormatTXT;
import model.Toy;

public class ToyValidatorTXT implements ToyValidator {
    private final FormatTXT<Toy> format;
    private String[] toy;
    public ToyValidatorTXT(){
        format = new ToyFormatTXT();
    }

    @Override
    public void setToy(String[] toy) {
        this.toy = toy;
    }

    @Override
    public void validate() throws Exception {
        if(!validateQuantity(toy[ModelTXT.QUANTITY.ordinal()]))
            throw new Exception("Неверное значение количества!");
        if(!validateWeight(toy[ModelTXT.WEIGHT.ordinal()]))
            throw new Exception("Неверное значение веса!");
    }

    private boolean validateQuantity(String field){
        try {
            int quantity = Integer.parseInt(field);
            if(quantity<=0)
                throw new NumberFormatException();
            return true;
        } catch (NumberFormatException e){
            System.out.println("Введенное значение не является количеством");
            return false;
        }
    }
    private boolean validateWeight(String field){
        try{
            float weight = Float.parseFloat(field);
            if(weight>1 || weight<0)
                throw new NumberFormatException();
            return true;
        }catch (NumberFormatException e){
            System.out.println("Введенное значение не является весом выпадения");
            return false;
        }
    }
}
