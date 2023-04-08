package model;

import java.util.ArrayList;
import java.util.List;

public class ToyShop extends Shop<Toy>{
    private final FilePersistent<Toy> file;
    public ToyShop(FilePersistent<Toy> file){
        this.file = file;
        items = new ArrayList<>();
    }
    @Override
    public Toy find(Toy toy){
        for (Toy item:items) {
            if(item.getId().equals(toy.getId()))
            {
                return item;
            }
        }
        return toy;
    }
    @Override
    public String create(Toy toy) {
        toy.setId(String.valueOf(idIncrement()));
        items = readAll();
        if (items==null)
            items= new ArrayList<>();
        items.add(toy);
        file.save(items);
        return toy.getId();
    }
    @Override
    public List<Toy> readAll() {
        return file.getData();
    }
    @Override
    public String update(Toy toy) {
        items=readAll();
        String id = null;
        List<Toy> updatedNotes = new ArrayList<>();
        for (Toy item:items) {
            if(item.getId().equals(toy.getId()))
            {
                id = item.getId();
                item=toy;
                item.setId(id);
            }
            updatedNotes.add(item);
        }
        file.save(updatedNotes);
        return id;
    }
    @Override
    public String delete(Toy toy) {
        items = readAll();
        items.remove(toy);
        file.save(items);
        return toy.getId();
    }
    @Override
    public int idIncrement() {
        int max = 0;
        for (Toy item : items) {
            int id = Integer.parseInt(item.getId());
            if (max < id){
                max = id;
            }
        }
        return max + 1;
    }
    public Toy reduceQuantity(Toy item){
        Toy toy = find(item);
        if(toy.getQuantity()>1) {
            toy.setQuantity(toy.getQuantity() - 1);
            update(toy);
        }
        else{
            delete(toy);
        }
        return toy;
    }
}
