package main.java.OOP;

public class MainAnimal {
    public static void main(String[] args) {
        Animal[] actions = new Animal[4];

        actions[0] = new Cat("Cat1", 200.00f, 2.00f);
        actions[1] = new Dog("Dog1",500.00f, 0.50f, 10.00f);
        actions[2] = new Cat("Cat2",250.00f, 2.25f);
        actions[3] = new Dog("Dog2",700.00f, 1.35f, 100.00f);

        for(Animal an : actions) {
            an.flee(220.00f);
            an.jump(1.25f);
            an.swim(25.00f);
        }
    }
}
