package service;

import model.*;
import util.Validation;

import java.time.LocalDate;
import java.util.*;

public class LibraryService {

    Scanner sc = new Scanner(System.in);

    List<Book> books = new ArrayList<>();
    List<User> users = new ArrayList<>();
    List<Transaction> transactions = new ArrayList<>();
    List<Membership> memberships = new ArrayList<>();

    // ---------------- CONSTRUCTOR (PRELOAD DATA) ----------------

    public LibraryService() {

        // Preload Books (15)
        books.add(new Book(1, "HarryPotter", "Rowling", 5));
        books.add(new Book(2, "TheAlchemist", "PauloCoelho", 4));
        books.add(new Book(3, "WingsOfFire", "APJAbdulKalam", 3));
        books.add(new Book(4, "RichDadPoorDad", "RobertKiyosaki", 6));
        books.add(new Book(5, "ThinkAndGrowRich", "NapoleonHill", 2));
        books.add(new Book(6, "AtomicHabits", "JamesClear", 5));
        books.add(new Book(7, "DeepWork", "CalNewport", 3));
        books.add(new Book(8, "Ikigai", "HectorGarcia", 4));
        books.add(new Book(9, "ThePowerOfNow", "EckhartTolle", 2));
        books.add(new Book(10, "Sapiens", "YuvalNoahHarari", 3));
        books.add(new Book(11, "StartWithWhy", "SimonSinek", 2));
        books.add(new Book(12, "ZeroToOne", "PeterThiel", 3));
        books.add(new Book(13, "CleanCode", "RobertMartin", 4));
        books.add(new Book(14, "DesignPatterns", "GoF", 2));
        books.add(new Book(15, "CrackingCodingInterview", "GayleMcDowell", 5));

        // Preload Users
        users.add(new User(1, "John"));
        users.add(new User(2, "Alice"));
        users.add(new User(3, "Bob"));

        // Preload Memberships
        memberships.add(new Membership(1, 6));
        memberships.add(new Membership(2, 12));
        memberships.add(new Membership(3, 24));

        System.out.println("System initialized with sample data");
    }

    // ---------------- BOOK ----------------

    public void addBook() {
        System.out.print("Enter id, name, author, quantity: ");
        int id = sc.nextInt();
        String name = sc.next();
        String author = sc.next();
        int qty = sc.nextInt();

        if (Validation.isEmpty(name) || Validation.isEmpty(author)) {
            Validation.showError("Name/Author cannot be empty");
            return;
        }

        if (!Validation.isPositive(qty)) {
            Validation.showError("Quantity must be positive");
            return;
        }

        books.add(new Book(id, name, author, qty));
        System.out.println("Book added");
    }

    public void updateBook() {
        System.out.print("Enter book id: ");
        int id = sc.nextInt();

        if (!Validation.isPositive(id)) {
            Validation.showError("Invalid Book ID");
            return;
        }

        for (Book b : books) {
            if (b.id == id) {
                System.out.print("Enter new quantity: ");
                int qty = sc.nextInt();

                if (!Validation.isPositive(qty)) {
                    Validation.showError("Quantity must be positive");
                    return;
                }

                b.quantity = qty;
                System.out.println("Updated");
                return;
            }
        }

        System.out.println("Book not found");
    }

    public void viewBooks() {
        for (Book b : books) {
            System.out.println(b.id + " " + b.name + " " + b.author + " Qty:" + b.quantity);
        }
    }

    public Book findBook(int id) {
        for (Book b : books) {
            if (b.id == id) return b;
        }
        return null;
    }

    // ---------------- SEARCH ----------------

    public void searchBook() {
        System.out.print("Enter book name: ");
        String name = sc.next();

        if (Validation.isEmpty(name)) {
            Validation.showError("Book name required");
            return;
        }

        boolean found = false;

        for (Book b : books) {
            if (b.name.equalsIgnoreCase(name)) {
                System.out.println("Found: " + b.name + " Available: " + (b.quantity > 0));
                found = true;
            }
        }

        if (!found) {
            System.out.println("Book not found");
        }
    }

    // ---------------- ISSUE ----------------

    public void issueBook() {
        System.out.print("Enter book id: ");
        int id = sc.nextInt();

        if (!Validation.isPositive(id)) {
            Validation.showError("Invalid Book ID");
            return;
        }

        Book b = findBook(id);

        if (b == null || b.quantity == 0) {
            Validation.showError("Book not available");
            return;
        }

        b.quantity--;

        LocalDate issue = LocalDate.now();
        LocalDate ret = issue.plusDays(15);

        transactions.add(new Transaction(id, issue, ret));

        System.out.println("Issued successfully. Return before: " + ret);
    }

    // ---------------- RETURN ----------------

    public void returnBook() {
        System.out.print("Enter book id: ");
        int id = sc.nextInt();

        if (!Validation.isPositive(id)) {
            Validation.showError("Invalid Book ID");
            return;
        }

        for (Transaction t : transactions) {
            if (t.bookId == id && !t.returned) {

                LocalDate today = LocalDate.now();
                t.returned = true;

                if (today.isAfter(t.returnDate)) {
                    int daysLate = today.getDayOfYear() - t.returnDate.getDayOfYear();
                    t.fine = daysLate * 10;
                }

                Book b = findBook(id);
                if (b != null) b.quantity++;

                System.out.println("Returned. Fine: " + t.fine);
                return;
            }
        }

        System.out.println("No active transaction");
    }

    // ---------------- FINE ----------------

    public void payFine() {
        System.out.print("Enter book id: ");
        int id = sc.nextInt();

        for (Transaction t : transactions) {
            if (t.bookId == id && t.fine > 0) {
                System.out.println("Fine paid: " + t.fine);
                t.fine = 0;
                return;
            }
        }

        System.out.println("No fine pending");
    }

    // ---------------- USER ----------------

    public void addUser() {
        System.out.print("Enter user id and name: ");
        int id = sc.nextInt();
        String name = sc.next();

        if (Validation.isEmpty(name)) {
            Validation.showError("User name required");
            return;
        }

        users.add(new User(id, name));
        System.out.println("User added");
    }

    // ---------------- MEMBERSHIP ----------------

    public void addMembership() {
        System.out.print("Enter user id and duration (6/12/24): ");
        int id = sc.nextInt();
        int duration = sc.nextInt();

        if (duration != 6 && duration != 12 && duration != 24) {
            Validation.showError("Invalid duration");
            return;
        }

        memberships.add(new Membership(id, duration));
        System.out.println("Membership added");
    }

    public void updateMembership() {
        System.out.print("Enter user id: ");
        int id = sc.nextInt();

        for (Membership m : memberships) {
            if (m.userId == id) {
                System.out.print("Enter new duration: ");
                int duration = sc.nextInt();

                if (duration != 6 && duration != 12 && duration != 24) {
                    Validation.showError("Invalid duration");
                    return;
                }

                m.durationMonths = duration;
                System.out.println("Updated");
                return;
            }
        }

        System.out.println("Membership not found");
    }
}