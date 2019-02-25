package main.java.OOP;

class Person {
    private String FIO;
    private String Position;
    private String Email;
    private String PhoneNumber;
    private float Salary;
    protected int Age;

    protected Person(String f, String ps, String e, String pn, float sl, int a ) {
        this.FIO = f;
        this.Position = ps;
        this.Email = e;
        this.PhoneNumber = pn;
        this.Salary = sl;
        this.Age = a;
    }

    protected void outputting() {
        System.out.println("ФИО : " + this.FIO);
        System.out.println("Должность : " + this.Position);
        System.out.println("Email : " + this.Email);
        System.out.println("Номер телефона : " + this.PhoneNumber);
        System.out.println("Зарплата : " + this.Salary);
        System.out.println("Возраст : " + this.Age);
        System.out.println("--------------------------------------");
    }


}
