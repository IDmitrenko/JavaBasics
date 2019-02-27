package main.java.OOP;

public class PersonExample {
    public static void main(String[] args) {
        Person[] persArray = new Person[5]; // Вначале объявляем массив объектов
        persArray[0] = new Person("Ivanov Ivan", "Engineer", " ivivan@mailbox.com ", "892312312", 30000.00f, 30); // потом для каждой ячейки массива задаем объект
        persArray[1] = new Person("Sergei Emtsov", "Analist", "Svetlov@mail.ru", "89032456791", 50000.00f, 55);
        persArray[2] = new Person("Vitaly Levchenko", "Programmer", "VitLev@inbox.ru", "89265420908", 48700.50f, 42);
        persArray[3] = new Person("Mike Ivanov", "Senior programmer", "Mike74@gmail.com", "89164501213", 90500.00f, 39);
        persArray[4] = new Person("Din Read", "Decorator", "Read45@inbox.ru", "89031278654", 39000.00f, 44);

        for (Person older : persArray) {
            if(older.Age > 40)
                older.outputting();
        }

    }
}
