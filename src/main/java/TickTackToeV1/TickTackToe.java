package main.java.TickTackToeV1;

import java.util.Random;
import java.util.Scanner;

public class TickTackToe {

    /*
    блок настроек игры
    */
    private static char[][] map;  // матрица игры
    private static int SIZE = 3;  //размерность поля

    //    private static final char DOT_EMPTY = '\u23FA'; //пустой символ - свободное поле
    private static final char DOT_EMPTY = '°'; //пустой символ - свободное поле
    private static final char DOT_X = 'X';
    private static final char DOT_0 = '0';

    private static final boolean SILLY_MODE = false;  // режим глупого компьютера true

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();      // ход человека
            if(isEndGame(DOT_X)) {
                break;
            }

            computerTurn();  // ход компьютера
            if(isEndGame(DOT_0)) {
                break;
            }

        }

        System.out.println("Игра закончена.");
    }

    /**
     * Метод подготовки игрового поля
     */
    private static void initMap() {
        map = new char[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    /**
     * Метод вывода игрового поля на экран
     */
    private static void printMap() {
        for(int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for(int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for(int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    /**
     * Ход человека
     */
    private static void humanTurn() {
        int x, y;

        do {
            System.out.println("Введите координаты ячейки через пробел");
            y = scanner.nextInt() - 1;
            x = scanner.nextInt() - 1;

        } while (!isCellValid(x, y));

        map[y][x] = DOT_X;

    }

    /**
     * Ход компьютера
     */
    private static void computerTurn() {
        int x = -1;
        int y = -1;
        int numberWin = 0;

        if(SILLY_MODE) {
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!isCellValid(x, y));

        } else {
            // составляем схему своих и свободных клеток
            // и ищем сначала клетку, которая приведет к концу игры
            // проверяем все восемь выиграшных комбинаций
            int priority = 1;
            int[] arrCoordinate = {-1, -1};
            numberWin = winningCombination(DOT_0, priority);
            if(numberWin >= 0) {   // определяем координату в выиграшной комбинации
                arrCoordinate = determineCoordinates(numberWin, arrCoordinate);
                y = arrCoordinate[0];
                x = arrCoordinate[1];
            } else {              // ищем есть ли выиграшная комбинация у игрока
                numberWin = winningCombination(DOT_X, priority);
                if(numberWin >= 0) { // предотвращаем победу игрока
                    arrCoordinate = determineCoordinates(numberWin, arrCoordinate);
                    y = arrCoordinate[0];
                    x = arrCoordinate[1];
                } else {  // ищем свободную линию, где есть наша клетка
                    numberWin = winningCombination(DOT_0, 2);
                    if(numberWin >= 0) {
                        arrCoordinate = determineCoordinates(numberWin, arrCoordinate);
                        y = arrCoordinate[0];
                        x = arrCoordinate[1];
                    } else {  // ставим в свободную клетку согласно рейтинга
                        numberWin = freeCellRating();
                        if(numberWin >= 0) {
                            arrCoordinate = determineCoordinates(numberWin, arrCoordinate);
                            y = arrCoordinate[0];
                            x = arrCoordinate[1];
                        } else {
                            System.out.println("Что-то пошло не так! Не могу определить свободную клетку.");
                        }
                    }
                }
            }

        }

        System.out.println("Компьютер выбрал ячейку " + (y + 1) + " " + (x + 1));
        map[y][x] = DOT_0;

    }

    /**
     * Метод определения позиции свободной клетки по рейтингу
     * @return numberCell - номер клетки
     */
    private static int freeCellRating() {
        int numberCell = -1;
        int[] arrCoordinate = {-1, -1};
        int[] arrCell = {4, 0, 2, 6, 8, 1, 3, 5, 7};  // рейтинг клеток
        for(int i = 0; i < arrCell.length; i++) {
            arrCoordinate = determineCoordinates(arrCell[i], arrCoordinate);
            if(map[arrCoordinate[0]][arrCoordinate[1]] == DOT_EMPTY) {
                numberCell = arrCell[i];
                break;
            }
        }

        return numberCell;
    }

    /**
     * Метод опредения координат по номеру клетки
     * @param numberWin - номер клетки
     * @param arr - массив для возврата координат
     * @return - координата клетки
     */
    private static int[] determineCoordinates(int numberWin, int[] arr) {
        int y = -1;
        int x = -1;
        switch (numberWin) {
            case 0:
                y = 0;
                x = 0;
                break;
            case 1:
                y = 0;
                x = 1;
                break;
            case 2:
                y = 0;
                x = 2;
                break;
            case 3:
                y = 1;
                x = 0;
                break;
            case 4:
                y = 1;
                x = 1;
                break;
            case 5:
                y = 1;
                x = 2;
                break;
            case 6:
                y = 2;
                x = 0;
                break;
            case 7:
                y = 2;
                x = 1;
                break;
            case 8:
                y = 2;
                x = 2;
                break;
        }

        arr[0] = y;
        arr[1] = x;

        return arr;
    }

    /** Метод определения выиграшной комбинации
     * @param playerSymbol - символ, которым играет текущий игрок
     * @param priority - приоритет комбинации 1 - выграшная; 2 - перспективная; 3 - обычная
     * @return int номер выиграшной клетки
     */
    private static int winningCombination(char playerSymbol, int priority) {
        boolean isWinComb = false;  // признак наличия выиграшной комбинации (false - нет )
        int numberWin = 0;          // номер выиграшной позиции ( 0 - 8 )
        int lc = 0;
        int cp = 0;
        outer:
        for(int i = 0; i <=8; i++) {
            switch (i) {
                case 0:  //00-01-02
                    for(int j = 0; j < 3; j++) {
                        cp = counterIts(0, j, playerSymbol);
                        if(cp > 0)
                            lc += cp;
                        if(cp == 1) {
                            numberWin = currentPosition(0, j);
                        }
                    }
                    if(lc == SIZE * 2 - priority) {  // найдена комбинация
                        isWinComb = true;
                        break outer;
                    }
                case 1:  //10-11-12
                    lc = 0;
                    cp = 0;
                    for(int j = 0; j < 3; j++) {
                        cp = counterIts(1, j, playerSymbol);
                        if(cp > 0)
                            lc += cp;
                        if(cp == 1) {
                            numberWin = currentPosition(1, j);
                        }
                    }
                    if(lc == SIZE * 2 - priority) {  // найдена комбинация
                        isWinComb = true;
                        break outer;
                    }
                case 2:  //20-21-22
                    lc = 0;
                    cp = 0;
                    for(int j = 0; j < 3; j++) {
                        cp = counterIts(2, j, playerSymbol);
                        if(cp > 0)
                            lc += cp;
                        if(cp == 1) {
                            numberWin = currentPosition(2, j);
                        }
                    }
                    if(lc == SIZE * 2 - priority) {  // найдена комбинация
                        isWinComb = true;
                        break outer;
                    }

                case 3:  //00-10-20
                    lc = 0;
                    cp = 0;
                    for(int j = 0; j < 3; j++) {
                        cp = counterIts(j, 0, playerSymbol);
                        if(cp > 0)
                            lc += cp;
                        if(cp == 1) {
                            numberWin = currentPosition(j, 0);
                        }
                    }
                    if(lc == SIZE * 2 - priority) {  // найдена комбинация
                        isWinComb = true;
                        break outer;
                    }
                case 4:  //01-11-21
                    lc = 0;
                    cp = 0;
                    for(int j = 0; j < 3; j++) {
                        cp = counterIts(j, 1, playerSymbol);
                        if(cp > 0)
                            lc += cp;
                        if(cp == 1) {
                            numberWin = currentPosition(j, 1);
                        }
                    }
                    if(lc == SIZE * 2 - priority) {  // найдена комбинация
                        isWinComb = true;
                        break outer;
                    }
                case 5:  //02-12-22
                    lc = 0;
                    cp = 0;
                    for(int j = 0; j < 3; j++) {
                        cp = counterIts(j, 2, playerSymbol);
                        if(cp > 0)
                            lc += cp;
                        if(cp == 1) {
                            numberWin = currentPosition(j, 2);
                        }
                    }
                    if(lc == SIZE * 2 - priority) {  // найдена комбинация
                        isWinComb = true;
                        break outer;
                    }

                case 6:  //00-11-22
                    lc = 0;
                    cp = 0;
                    for(int j = 0; j < 3; j++) {
                        cp = counterIts(j, j, playerSymbol);
                        if(cp > 0)
                            lc += cp;
                        if(cp == 1) {
                            numberWin = currentPosition(j, j);
                        }
                    }
                    if(lc == SIZE * 2 - priority) {  // найдена комбинация
                        isWinComb = true;
                        break outer;
                    }

                case 7:  //20-11-02
                    lc = 0;
                    cp = 0;
                    int y = 2;
                    int x = 0;
                    int c = 1;
                    cp = counterIts(y, x, playerSymbol);
                    if(cp > 0)
                        lc += cp;
                    if(cp == 1) {
                        numberWin = currentPosition(y, x);
                    }
                    cp = counterIts(c, c, playerSymbol);
                    if(cp > 0)
                        lc += cp;
                    if(cp == 1) {
                        numberWin = currentPosition(c, c);
                    }
                    cp = counterIts(x, y, playerSymbol);
                    if(cp > 0)
                        lc += cp;
                    if(cp == 1) {
                        numberWin = currentPosition(x, y);
                    }
                    if(lc == SIZE * 2 - priority) {  // найдена комбинация
                        isWinComb = true;
                        break outer;
                    }

            }
        }

        if(!isWinComb) {
            numberWin = -1;   // нет выиграшной позиции
        }
        return numberWin;

    }

    /**
     * Метод определения номера ячейки
     * @param x  -  координата по горизонтали
     * @param y  -  координата по вертикали
     * @return int - номер клетки от  0  до 8
     */
    private static int currentPosition(int y, int x) {
        int cp = 0;
        if(y == 0 && x == 0) {
            cp = 0;
        } else if(y == 0 && x == 1) {
            cp = 1;
        } else if(y == 0 && x == 2) {
            cp = 2;
        } else if(y == 1 && x == 0) {
            cp = 3;
        } else if(y == 1 && x == 1) {
            cp = 4;
        } else if(y == 1 && x == 2) {
            cp = 5;
        } else if(y == 2 && x == 0) {
            cp = 6;
        } else if(y == 2 && x == 1) {
            cp = 7;
        } else if(y == 2 && x == 2) {
            cp = 8;
        }

        return cp;
    }

    /**
     * Метод определения своей ячейки
     * * @param x  -  координата по горизонтали
     * * @param y  -  координата по вертикали
     * * @playerSymbol - символ, которым играет текущий игрок
     * * @return int - 2 - своя клетка, 1 - свободная, 0 - чужая
     */
    private static int counterIts(int y, int x, char playerSymbol) {
        int cp = 0;
        if(map[y][x] == playerSymbol || map[y][x] == DOT_EMPTY) {
            cp += 1;
            if(map[y][x] == playerSymbol)
                cp += 1;
        }

        return cp;
    }

    /**
     * Метод валидации запрашиваемой ячейки на корректность
     * @param x  -  координата по горизонтали
     * @param y  -  координата по вертикали
     * @return boolean - признак валидности
     */
    private static boolean isCellValid(int x, int y) {
        boolean result = true;

        if(x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }

        if(map[y][x] != DOT_EMPTY) {
            result = false;
        }

        return result;
    }

    /**
     * Метод проверки игры на завершение
     * @param playerSymbol - символ, которым играет текущий игрок
     * @return boolean - признак завершения игры
     */
    private static boolean isEndGame(char playerSymbol) {
        boolean result = false;

        printMap();

        // проверяем необходимость следующего хода
        if(chekWin(playerSymbol)) {
            System.out.println("Победили " + playerSymbol);
            result = true;
        }

        // проверяем заполненность поля
        if(isMapFull()) {
            System.out.println("Ничья");
            result = true;
        }

        return result;
    }

    /**
     * Проверка на 100%-ю заполненность поля
     * @return boolean - признак заполненности
     */
    private static boolean isMapFull() {
        boolean result = true;

        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if(map[i][j] == DOT_EMPTY)
                    result = false;
            }
        }

        return result;
    }

    /**
     * Метод проверки выиграша
     * @param playerSymbol - символ, введенный пользователем
     * @return boolean - результат проверки
     */
    private static boolean chekWin(char playerSymbol) {
        boolean result = false;

        if(
                (map[0][0] == playerSymbol && map[0][1] == playerSymbol && map[0][2] == playerSymbol) ||
                        (map[1][0] == playerSymbol && map[1][1] == playerSymbol && map[1][2] == playerSymbol) ||
                        (map[2][0] == playerSymbol && map[2][1] == playerSymbol && map[2][2] == playerSymbol) ||
                        (map[0][0] == playerSymbol && map[1][0] == playerSymbol && map[2][0] == playerSymbol) ||
                        (map[0][1] == playerSymbol && map[1][1] == playerSymbol && map[2][1] == playerSymbol) ||
                        (map[0][2] == playerSymbol && map[1][2] == playerSymbol && map[2][2] == playerSymbol) ||
                        (map[0][0] == playerSymbol && map[1][1] == playerSymbol && map[2][2] == playerSymbol) ||
                        (map[2][0] == playerSymbol && map[1][1] == playerSymbol && map[0][2] == playerSymbol)) {
            result = true;
        }

        return result;
    }


}
