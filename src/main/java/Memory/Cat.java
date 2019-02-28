package main.java.Memory;

public class Cat extends Animal {
    private int appetite;       // аппетит - количество еды, которое кот съедает за один раз
    private boolean isSatiety;  // false - голодный, true - сытый

    protected Cat(String name, float lengthFlee,
                  float heightJump, int appetite, boolean isSatiety) {
        super(name, lengthFlee, heightJump);
        this.appetite = appetite;
        this.isSatiety = isSatiety;
    }

    protected Cat(String name, int appetite, boolean isSatiety) {
        super(name);
        this.appetite = appetite;
        this.isSatiety = isSatiety;
    }

    protected Cat(String name, int appetite) {
        super(name);
        this.appetite = appetite;
    }

    public int getAppetite() {
        return appetite;
    }

    public void setAppetite(int appetite) {
        this.appetite = appetite;
    }

    public boolean isSatiety() {
        return isSatiety;
    }

    public void setSatiety(boolean satiety) {
        isSatiety = satiety;
    }

    public void eat(BowlOfFood bowl) {
        if((bowl.getBalance() >= this.appetite) && !this.isSatiety) {
            bowl.setBalance(bowl.getBalance() - this.appetite);
            this.isSatiety = true;
            System.out.println(this.name + " покушал!");
        } else if(this.isSatiety) {
            System.out.println(this.name + " не хочет кушать, он сытый!");
        } else {
            System.out.println(this.name + " - недостаточно еды!");
        }
    }

    @Override
    protected void swim(float value) {
        System.out.println("Cat " + this.name + " can't swim.");
    }
}
