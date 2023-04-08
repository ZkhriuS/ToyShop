package format;

import model.Toy;

import java.util.ArrayList;
import java.util.List;

public class ToyFormatTXT extends FormatTXT<Toy>{
    public ToyFormatTXT(){
        extension = "txt";
        format = "\nid: %s\nname: %s\nquantity: %d\nweight: %.2f";
        regex = "\nid: |\nname: |\nquantity: |\nweight: ";
    }
    @Override
    public String toFormat(Toy item) {
        return String.format(format, item.getId(), item.getName(), item.getQuantity(), item.getWeight());
    }
    @Override
    public Toy fromFormat(String data) {
        int modelSize = ModelTXT.values().length;
        String[] model = data.split(regex, modelSize);
        if(model.length<modelSize)
            return null;
        Toy item = new Toy(model[ModelTXT.NAME.ordinal()]);
        item.setId(model[ModelTXT.ID.ordinal()]);
        item.setQuantity(Integer.parseInt(model[ModelTXT.QUANTITY.ordinal()]));
        item.setWeight(Float.parseFloat(model[ModelTXT.WEIGHT.ordinal()].replace(',','.')));
        return item;
    }
    @Override
    public String allToFormat(List<Toy> toys) {
        StringBuilder data = new StringBuilder();
        for (Toy item:toys) {
            data.append(toFormat(item)).append("\n\n");
        }
        return data.toString();
    }
    @Override
    public List<Toy> allFromFormat(String data) {
        List<Toy> toys = new ArrayList<>();
        String[] strToys = data.split("\n\n");
        for (String s: strToys) {
            Toy toy = fromFormat(s);
            if(toy!=null)
                toys.add(toy);
        }
        return toys;
    }
}
