package com.example.finalapplication;

public class WaitingListItem {
    private int id;
    private String title;
    private int quantity;
    private double price;
    private String confirmationDate;

    public WaitingListItem() {
    }

    public WaitingListItem(int id, String title, int quantity, String confirmationDate) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.price = 0.0; // Default price if not provided
        this.confirmationDate = confirmationDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(String confirmationDate) {
        this.confirmationDate = confirmationDate;
    }
}
