package main.java.introducnionToJava;

public class introduction {
//1
    public static void main(String[] args) {
//2 Создать переменные всех пройденных типов данных, и инициализировать их значения
        byte b = 70;
        short s = 3404;
        int i = 125678;
        long l = 45500L;
        float f = 67820.0f;
        double d = 758.72775;
        boolean bool = true;
        char c = 'Д';

        System.out.println(b);
        System.out.println(s);
        System.out.println(i);
        System.out.println(l);
        System.out.println(f);
        System.out.println(d);
        System.out.println(bool);
        System.out.println(c);
//3 Написать метод, вычисляющий выражение a * (b + (c / d)) и возвращающий результат,
// где a, b, c, d – входные параметры этого метода
        double a1 = 1200;
        double b1 = 32.25;
        double c1 = 99;
        double d1 = 16.5;
        double r1 = evaluatedExpression(a1, b1, c1, d1);
        System.out.println(r1);
//4 Написать метод, принимающий на вход два числа, и проверяющий, что их сумма лежит в пределах от 10 до 20 (включительно),
// если да – вернуть true, в противном случае – false
        double a2 = 34.56;
        double b2 = -16.89;
        if(amountInRange(a2, b2)) {
            System.out.println("Сумма чисел " + a2 + " и " + b2 + " лежит в пределах от 10 до 20(включительно).");
        } else {
            System.out.println("Сумма чисел " + a2 + " и " + b2 + " не лежит в пределах от 10 до 20(включительно).");
        }
//5 Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль
// положительное число передали или отрицательное (Замечание: ноль считаем положительным числом.)
        int a3 = -5;
        analysisNumber(a3);
//6 Написать метод, которому в качестве параметра передается целое число, метод должен вернуть true,
// если число отрицательное
        int a4 = 8;
        if(negativeNumber(a4)) {
            System.out.println("Число " + a4 + " является отрицательным.");
        } else {
            System.out.println("Число " + a4 + " является положительным.");
        }
//7 Написать метод, которому в качестве параметра передается строка, обозначающая имя, метод должен
// вывести в консоль сообщение «Привет, указанное_имя!»
        String name = "Игорь";
        hello(name);


//8 *Написать метод, который определяет, является ли год високосным, и выводит сообщение в консоль.
// Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
        int year = 2019;
        leapYear(year);
        year = 2020;
        leapYear(year);

    }

    private static double evaluatedExpression(double a, double b, double c, double d) {
        return a * (b + (c / d));
    }

    private static boolean amountInRange(double a, double b) {
        return (a + b) >= 10 && (a + b) <= 20;
    }

    private static void analysisNumber(int a) {
        if(a >= 0) {
            System.out.println("Число " + a + " является положительным.");
        } else {
            System.out.println("Число " + a + " является отрицательным.");
        }
    }

    private static boolean negativeNumber(int a) {
        return (a < 0);
    }

    private static void hello(String name) {
        System.out.println("Привет, " + name + "!");
    }

    private static void leapYear(int year) {
        if(((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0)) {
            System.out.println(year + " год является високосным.");
        } else {
            System.out.println(year + " год не является високосным.");
        }
    }

}
