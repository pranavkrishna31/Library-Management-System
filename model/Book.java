package model;

public class Book {
    public int id;
    public String name;
    public String author;
    public int quantity;

    public Book(int id, String name, String author, int quantity) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.quantity = quantity;
    }
}