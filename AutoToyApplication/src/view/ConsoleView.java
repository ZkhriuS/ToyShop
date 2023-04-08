package view;

import controller.Controller;
import controller.ToyController;
import model.Toy;

import java.util.Locale;
import java.util.Scanner;

public class ConsoleView implements View{
    private ToyController controller;
    private ToyViewCreator creator;
    public ConsoleView(){

    }
    public ConsoleView(ToyController controller, ToyViewCreator creator){
        this.controller = controller;
        this.creator = creator;
    }
    @Override
    public void display() {
        while (true) {
            menu();
            String command = prompt("Введите команду: ");
            Commands com = Commands.valueOf(command.toUpperCase(Locale.ROOT));
            try {
                switch (com) {
                    case ADD:
                        addToy();
                        break;
                    case READ:
                        readToy();
                        break;
                    case ALL:
                        listNotes();
                        break;
                    case UPDATE:
                        updateToy();
                        break;
                    case DELETE:
                        deleteToy();
                        break;
                    case PLAYOUT:
                        playOut();
                        break;
                    case PRIZE:
                        getPrize();
                        break;
                    case EXIT:
                        return;
                    default: throw new Exception("Неверное имя команды");
                }
            } catch( Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void deleteToy() throws Exception{
        System.out.println(controller.delete(creator.parse(readToy())));
    }

    private void updateToy() throws Exception{
        Toy toy = creator.parse(readToy());
        System.out.println("1. name\n2. weight");
        String command = prompt("Введите команду для обновления данных: ").toLowerCase(Locale.ROOT);
        if(command.equals("name"))
        {
            toy=creator.setName();
        }
        else if(command.equals("weight")){
            toy=creator.setWeight();
        }
        System.out.println(controller.update(toy));
        System.out.println(controller.getItem(toy.getId()));
    }

    private void listNotes() {
        System.out.println(controller.list());
    }

    private String readToy() {
        String output = controller.getItem(prompt("Введите ID игрушки: "));
        System.out.println(output);
        return output;
    }

    private void addToy() throws Exception{
        Toy toy = creator.create();
        System.out.println(controller.create(toy));
        controller.getItem(toy.getId());
    }

    private void playOut() throws Exception{
        Toy toy = creator.parse(readToy());
        System.out.println(controller.playOut(toy));
    }

    private void getPrize(){
        System.out.println(controller.getPrize());
    }

    private void menu(){
        int i=1;
        for(Commands command : Commands.values()){
            System.out.println(i++ +". "+command);
        }
    }

    @Override
    public String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
