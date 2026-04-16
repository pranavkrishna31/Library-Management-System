package model;

public class Membership {
    public int userId;
    public int durationMonths;

    public Membership(int userId, int durationMonths) {
        this.userId = userId;
        this.durationMonths = durationMonths;
    }
}