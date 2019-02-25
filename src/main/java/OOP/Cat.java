package main.java.OOP;

public class Cat extends Animal {

    protected Cat(String name, float lengthFlee,
               float heightJump) {
        super(name, lengthFlee, heightJump);
    }

    @Override
    protected void swim(float value) {
        System.out.println("Cat " + this.name + " can't swim.");
    }
}
