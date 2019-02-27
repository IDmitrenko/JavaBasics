package main.java.Memory;

public class Animal {
    protected String name;      // имя животного

    private float lengthFlee; // максимальная длина бега

    private float heightJump; // максимальная высота прыжка

    private float lengthSwim; // максимальная длина заплыва

    protected Animal(String name, float lengthFlee,
                   float heightJump, float lengthSwim) {
        this.name = name;
        this.lengthFlee = lengthFlee;
        this.heightJump = heightJump;
        this.lengthSwim = lengthSwim;
    }

    protected Animal(String name, float lengthFlee,
                  float heightJump) {
        this.name = name;
        this.lengthFlee = lengthFlee;
        this.heightJump = heightJump;
        this.lengthSwim = 1.00f;
    }

    protected Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected void run(float value) {
        System.out.print(this.name + ".flee(" + lengthFlee + "); -> результат: " + value + " : ");
        if(value > lengthFlee) {
            System.out.println("false");
        } else {
            System.out.println("true");
        }

    }

    protected void jump(float value) {
        System.out.print(this.name + ".jump(" + heightJump + "); -> результат: " + value + " : ");
        if(value > heightJump) {
            System.out.println("false");
        } else {
            System.out.println("true");
        }
    }

    protected void swim(float value) {
        System.out.print(this.name + ".swim(" + lengthSwim + "); -> результат: " + value + " : ");
        if(value > lengthSwim) {
            System.out.println("false");
        } else {
            System.out.println("true");
        }
    }
}
