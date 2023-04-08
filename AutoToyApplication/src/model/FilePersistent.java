package model;

import format.Format;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilePersistent<T> implements Persistable<T> {
    private String filePath;
    private final Format<T> format;

    public FilePersistent(Format<T> format) {
        this.format = format;
    }

    public FilePersistent(Format<T> format, String filename) {
        this(format);
        filePath = filename + "." + format.getExtension();
    }

    public String read(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder result = new StringBuilder();
            while (reader.ready()) {
                result.append(reader.readLine()).append("\n");
            }
            return result.toString();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не создан!");
            e.printStackTrace();
            write(file, "");
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла!");
            e.printStackTrace();
        }
        return null;
    }

    public void write(File file, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(data);
        } catch (FileNotFoundException e) {
            throw new NullPointerException();
        } catch (IOException e) {
            System.out.println("Ошибка записи файла!");
            e.printStackTrace();
        }
    }

    public List<T> getData() {
        try {
            File file = new File(filePath);
            return new ArrayList<>(format.allFromFormat(read(file)));
        } catch (NullPointerException e) {
            System.out.println("Неверно указан путь к файлу!");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public void save(List<T> data) {
        try {
            File file = new File(filePath);
            List<T> items = new ArrayList<>(data);
            String result = format.allToFormat(items);
            write(file, result);
        } catch (NullPointerException e) {
            System.out.println("Неверно указан путь к файлу!");
            e.printStackTrace();
        }

    }
}
