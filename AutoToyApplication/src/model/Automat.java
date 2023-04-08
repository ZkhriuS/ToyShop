package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Automat<T extends Playable> implements Repository<T>{
    private List<T> prizes;
    private final Random random;
    private final FilePersistent<T> file;
    public Automat(FilePersistent<T> file){
        this.file = file;
        random = new Random();
        prizes = this.file.getData();
    }
    public T playOutPrize(T item){
        float value = random.nextFloat();
        if(value<=item.getWeight()){
            prizes.add(item);
            file.save(prizes);
            return item;
        }
        return null;
    }
    public T getPrize(){
        if(prizes.isEmpty())
            return null;
        T item = prizes.remove(0);
        file.save(prizes);
        return item;
    }
    @Override
    public T find(T item) {
        return null;
    }

    @Override
    public String create(T item) {
        return null;
    }

    @Override
    public List<T> readAll() {
        return null;
    }

    @Override
    public String update(T item) {
        return null;
    }

    @Override
    public String delete(T item) {
        return null;
    }

    @Override
    public int idIncrement() {
        return 0;
    }
}
