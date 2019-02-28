package main.java.Memory;

public class BowlOfFood {
    private int capacity;
    private int balance;

    protected BowlOfFood(int capacity, int balance ) {
        this.capacity = capacity;
        this.balance = balance;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}
