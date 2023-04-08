package controller;

import model.Automat;
import model.Shop;
import model.Toy;
import model.ToyShop;

import java.util.List;

public class ToyController implements Controller<Toy>{
    private final ToyShop shop;
    private final Automat<Toy> automat;

    public ToyController(ToyShop shop, Automat<Toy> automat){
        this.shop = shop;
        this.automat = automat;
    }
    @Override
    public String create(Toy item){
        String id = shop.create(item);
        return "Новая игрушка с ID"+ id +" успешно создана!";
    }
    @Override
    public String update(Toy item){
        String id = shop.update(item);
        if(id==null)
            return "Игрушка не найдена!";
        return "Игрушка с ID"+id+" успешно обновлена!";
    }
    @Override
    public String delete(Toy item){
        String id = shop.delete(item);
        if(id==null)
            return "Игрушка не найдена!";
        return "Игрушка с ID"+id+" успешно удалена!";
    }
    @Override
    public String getItem(String id){
        List<Toy> toys = shop.readAll();
        for (Toy toy:toys) {
            if(toy.getId().equals(id)){
                return toy.toString();
            }
        }
        return "Игрушка не найдена!";
    }
    @Override
    public String list(){
        List<Toy> toys = shop.readAll();
        if(toys.isEmpty())
            return "Не создано ни одной игрушки!";
        StringBuilder result = new StringBuilder();
        for (Toy toy : toys) {
            result.append(toy).append("\n");
        }
        return result.toString();
    }

    public String playOut(Toy item){
        Toy prize = automat.playOutPrize(item);
        if(prize!= null) {
            prize = shop.reduceQuantity(prize);
            return "Ваш приз - "+prize.getName()+"!";
        } else{
            return "Пусто. Попробуйте еще раз";
        }
    }

    public String getPrize(){
        Toy prize = automat.getPrize();
        if(prize==null)
            return "Список призов пуст";
        return "Ваш приз - "+prize.getName()+"!";
    }

}
