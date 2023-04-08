package model;

import java.util.Objects;

public class Toy implements Sellable, Playable{
    protected static final String[] comments = {"\nID: ", "\nНазвание: ", "\nКоличество: ", "\nВес: "};
    protected String id;
    protected String name;
    protected int quantity;
    protected float weight;
    public Toy(){

    }

    public Toy(String name){
        this.name = name;
        this.quantity = 0;
        this.weight = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Toy)) return false;
        Toy toy = (Toy) o;
        return id.equals(toy.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, weight);
    }

    @Override
    public String toString() {
        return comments[0]+id+
                comments[1]+name+
                comments[2]+quantity+
                comments[3]+weight;
    }
}
