package main.java.Memory;

public class MainCat {
    public static void main(String[] args) {

        System.out.println("------  Коты кушают из своих мисок  --------------");

        Cat cat1 = new Cat("Барсик", 10);

        Cat cat2 = new Cat("Мурзик", 20);

        Cat cat3 = new Cat("Маша", 12);

        BowlOfFood bowl1 = new BowlOfFood(12, 12);

        BowlOfFood bowl2 = new BowlOfFood(20, 15);

        BowlOfFood bowl3 = new BowlOfFood(12, 8);

        cat1.eat(bowl1);

        cat2.eat(bowl2);

        cat3.eat(bowl3);

        System.out.println("------- Коты кушают из одной миски по очереди  --------");

        Cat[] actions = new Cat[6];
        actions[0] = new Cat("Барбара", 9);
        actions[1] = new Cat("Вилли", 21);
        actions[2] = new Cat("Грета", 13);
        actions[3] = new Cat("Джипси", 80);
        actions[4] = new Cat("Жуан", 160);
        actions[5] = new Cat("Васька", 30);

        BowlOfFood bowl = new BowlOfFood(100, 40);

        for(Cat anb : actions) {
            if(anb.getAppetite() > bowl.getCapacity()) {
                System.out.println(anb.getName() + " - аппетит (" + anb.getAppetite() + ")превышает объем миски (" + bowl.getCapacity()+ "). Такого прожору нам не накормить!");
            } else {
                if(bowl.getBalance() < anb.getAppetite()) {
                    System.out.println("Для " + anb.getName() + " не хватает еды.");
                    System.out.println("В миске " + bowl.getBalance() + " единиц еды. Нужно " + anb.getAppetite());
                    if(anb.getAppetite() > 40 + bowl.getBalance()) {
                        System.out.println("Добавляем " + (anb.getAppetite() - bowl.getBalance()) + " единиц еды.");
                        bowl.setBalance(anb.getAppetite());
                    } else {
                        System.out.println("Добавляем 40 единиц еды.");
                        bowl.setBalance(40 + bowl.getBalance());
                    }
                }
                anb.eat(bowl);
            }
        }

        cat1.eat(bowl);
    }
}
