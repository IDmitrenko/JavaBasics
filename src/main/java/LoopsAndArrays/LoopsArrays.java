package main.java.LoopsAndArrays;

public class LoopsArrays {
    public static void main(String[] args) {
//1.Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
// С помощью цикла и условия заменить 0 на 1, 1 на 0;
        System.out.println("Задача 1:");
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.print("Входной массив:  ");
        printArr(arr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                arr[i] = 0;
            } else {
                arr[i] = 1;
            }
        }

        System.out.print("Выходной массив: ");
        printArr(arr);

//2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
        System.out.println("Задача 2:");
        int[] arr2 = new int[8];
        int a2 = 0;
        int increment = 3;
        for(int i = 0; i < arr2.length; i++) {
            arr2[i] = a2;
            a2 += increment;
            System.out.print(arr2[i] + "\t");
        }
        System.out.println();

//3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ], пройти по нему циклом, и числа, меньшие 6, умножить на 2;

        int[] arr3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("Задача 3:");
        System.out.print("Входной массив:  ");
        printArr(arr3);
        for(int i = 0; i < arr3.length; i++) {
            if(arr3[i] < 6)
                arr3[i] *= 2;
        }

        System.out.print("Выходной массив: ");
        printArr(arr3);

//4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
// и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
        System.out.println("Задача 4:");
        int x = 9;
        int[][] arr4 = new int[x][x];
        for(int i = 0; i < arr4.length; i++) {
            for(int j = 0; j < arr4[i].length; j++) {
                if((i == j) || ((i + j) == (x - 1))) {
                    arr4[i][j] = 1;
                } else {
                    arr4[i][j] = 0;
                }
            }
        }

        printArr2(arr4);

//5. Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
        System.out.println("Задача 5:");
        int[] arr5 = {35, 67, 2, 89, 7, 66, 12, 44, -8, 99, 64};
        int minValue = arr5[0];
        int maxValue = arr5[0];
        int numberMin = 0;
        int numberMax = 0;
        System.out.print("Исходный массив:  ");
        printArr(arr5);
        for(int i = 0; i < arr5.length; i++) {
            if(arr5[i] > maxValue) {
                maxValue = arr5[i];
                numberMax = i;
            } else if(arr5[i] < minValue) {
                minValue = arr5[i];
                numberMin = i;
            }
        }
        System.out.println("Минимальный элемент - " + numberMin + ". Его значение равно " + minValue);
        System.out.println("Максимальный элемент - " + numberMax + ". Его значение равно " + maxValue);

//6. Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
// если в массиве есть место, в котором сумма левой и правой части массива равны.
// Примеры: checkBalance([1, 1, 1, || 2, 1]) → true, checkBalance ([2, 1, 1, 2, 1]) → false,
// checkBalance ([10, || 10]) → true, граница показана символами ||, эти символы в массив не входят;
        System.out.println("Задача 6:");
        int[] arr61 = {23, 1, 5, 16, 13};
        int[] arr62 = {12, 4, 6, 8, 9, 4};

        if(checkBalance(arr61)){
            System.out.println("Сумма левой и правой части массива равны.");
        } else {
            System.out.println("Сумма левой и правой части массива не равны.");
        }

        if(checkBalance(arr62)){
            System.out.println("Сумма левой и правой части массива равны.");
        } else {
            System.out.println("Сумма левой и правой части массива не равны.");
        }


//7. *Написать метод, которому на вход подается одномерный массив и число n
// (может быть положительным или отрицательным),
// при этом метод должен сместить все элементы массива на n позиций.
// Нельзя пользоваться вспомогательными массивами.
        System.out.println("Задача 7:");
        int arr7[] = {12, 45, 36, 23, 89, 43, 44, 67};
        int offset = 3;
        System.out.print("Исходный массив:  ");
        printArr(arr7);
        System.out.println("Смещение  " + offset);
        offsetArr(arr7, offset);
        System.out.print("Выходной массив:  ");
        printArr(arr7);
        int arr8[] = {12, 45, 36, 23, 89, 43, 44, 67};
        offset = -2;
        System.out.print("Исходный массив:  ");
        printArr(arr8);
        System.out.println("Смещение  " + offset);
        offsetArr(arr8, offset);
        System.out.print("Выходной массив:  ");
        printArr(arr8);

    }


    private static void printArr2(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private static void printArr(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }


    private static boolean checkBalance(int[] arr) {
        System.out.print("Исходный массив:  ");
        printArr(arr);
        int half = arr.length / 2;
        int part = arr.length % 2;
        int sumL1 = 0;
        int sumR1 = 0;
        int sumL2 = 0;
        int sumR2 =0;
        for(int i = 0; i < arr.length; i++) {
            switch (part) {
                case 1:
                    if (i < half) {
                        sumL1 += arr[i];
                        sumL2 = sumL1;
                    } else if (i == half) {
                        sumL2 += arr[i];
                        sumR1 += arr[i];
                    } else {
                        sumR1 += arr[i];
                        sumR2 += arr[i];
                    }
                    break;

                case 0:
                    if (i < half) {
                        sumL1 += arr[i];
                    } else {
                        sumR1 += arr[i];
                    }
            }
        }
        switch (part) {
            case 1:
                if (sumL1 == sumR1) {
                    System.out.print("Сумма элементов ");
                    for (int j = 0; j < half; j++) {
                        System.out.print(arr[j] + "\t");
                    }
                    System.out.print(" равна сумме элементов ");
                    for (int j = half; j < arr.length; j++) {
                        System.out.print(arr[j] + "\t");
                    }
                    System.out.println();
                    return true;
                }

                if (sumL2 == sumR2) {
                    System.out.print("Сумма элементов ");
                    for (int j = 0; j <= half; j++) {
                        System.out.print(arr[j] + "\t");
                    }
                    System.out.print(" равна сумме элементов ");
                    for (int j = half + 1; j < arr.length; j++) {
                        System.out.print(arr[j] + "\t");
                    }
                    System.out.println();
                    return true;
                }

            case 0:
                if (sumL1 == sumR1) {
                    System.out.print("Сумма элементов ");
                    for (int j = 0; j < half; j++) {
                        System.out.print(arr[j] + "\t");
                    }
                    System.out.print(" равна сумме элементов ");
                    for (int j = half; j < arr.length; j++) {
                        System.out.print(arr[j] + "\t");
                    }
                    System.out.println();
                    return true;
                }
        }
        return false;
    }

    private static void offsetArr(int[] arr, int offset) {
        if(offset < 0) {
            offset = -offset;
            for(int i = arr.length; i > offset; i--) {
                int temp = arr[arr.length - 1];
                for(int j = arr.length - 1; j > 0; j--) {
                    arr[j] = arr[j - 1];
                }
                arr[0] = temp;
            }
        } else if(offset > 0) {
            for(int i = 0; i < offset; i++) {
                int temp = arr[arr.length - 1];
                for(int j = arr.length - 1; j > 0; j--) {
                    arr[j] = arr[j-1];
                }
                arr[0] = temp;
            }
        }
    }

}
