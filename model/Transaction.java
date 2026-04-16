package model;

import java.time.LocalDate;

public class Transaction {
    public int bookId;
    public LocalDate issueDate;
    public LocalDate returnDate;
    public boolean returned;
    public int fine;

    public Transaction(int bookId, LocalDate issueDate, LocalDate returnDate) {
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.returned = false;
        this.fine = 0;
    }
}