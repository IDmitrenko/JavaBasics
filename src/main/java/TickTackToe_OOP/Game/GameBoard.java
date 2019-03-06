package main.java.TickTackToe_OOP.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JFrame {
    static int dimension = 4;     // размерность
    static int cellSize = 150;    // размер одной клетки
    private char [][] gameField;  // матрица игры
    private GameButton[] gameButtons;  // массив кнопок

    private Game game;            // ссылка на игру

    static char nullSymbol = '\u0000';  // null символ

    public GameBoard(Game currentGame) {
        this.game = currentGame;
        initField();
    }

    /**
     * Метод инициации и отрисовки игрового поля
     */
    private void initField() {
        // задаем основные настройки окна игры
        setBounds(cellSize * dimension, cellSize * dimension, 400, 400);
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        Font font = new Font("Arial", Font.PLAIN, cellSize / 3);

        JPanel controlPanel = new JPanel();   // панель управления игрой
        JButton newGameButton = new JButton("Новая игра");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyField();
            }
        });

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.add(newGameButton);
        controlPanel.setSize(cellSize * dimension, 150);

        JPanel gameFieldPanel = new JPanel();   // панель самой игры
        gameFieldPanel.setLayout(new GridLayout(dimension, dimension));
        gameFieldPanel.setSize(cellSize * dimension, cellSize * dimension);

        gameField = new char[dimension][dimension];   // инициализируем матрицу игры
        gameButtons = new GameButton[dimension * dimension];  // массив кнопок игры (по номерам кнопок)

        // заполняем кнопками игровое поле
        for (int i = 0; i < (dimension * dimension); i++) {
            GameButton fieldButton = new GameButton(i, this); // индекс кнопки и текущее поле
            gameFieldPanel.add(fieldButton);  // добавляем кнопку на игровое поле
            gameButtons[i] = fieldButton;     // ссылка на кнопку в игре
            fieldButton.setFont(font);
        }

        // добавляем эти две панели на основное окно
        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(gameFieldPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Метод очистки поля и матрицы игры
     */
    void emptyField() {
        for (int i = 0; i < (dimension * dimension); i++) {
            gameButtons[i].setText("");

            int x = i / GameBoard.dimension;
            int y = i % GameBoard.dimension;

            gameField[y][x] = nullSymbol;
        }
    }

    Game getGame() {
        return game;
    }

    /**
     * Метод проверки доступности клетки для хода
     * @param x - по горизонтали
     * @param y - по вертикали
     * @return boolean
     */
    boolean isTurnable(int x, int y) {
        boolean result = false;

        if (gameField[y][x] == nullSymbol)
            result = true;

        return result;
    }

    /**
     * Обновление матрицы игры после хода
     * @param x - по горизонтали
     * @param y - по вертикали
     */
    void updateGameField(int x, int y) {
        gameField[y][x] = game.getCurrentPlayer().getPlayerSign();
    }

    /**
     * Проверка победы по столбцам и линиям
     * @return флаг победы - Красное знамя
     */
    boolean checkWin() {
        boolean result = false;

        char playerSymbol = getGame().getCurrentPlayer().getPlayerSign();

        if (checkWinDiagonals(playerSymbol) || checkWinLines(playerSymbol)) {
            result = true;
        }

        return result;
    }

    /**
     * Метод проверки заполненности поля
     * @return boolean
     */
    boolean isFull() {
        boolean result = true;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (gameField[i][j] == nullSymbol)
                    result = false;
            }
        }

        return result;
    }

    /**
     * Проверка победы по столбцам и линиям
     * @return флаг победы
     */
    private boolean checkWinLines(char playerSymbol) {
        boolean cols, rows, result;

        result = false;

        for (int col = 0; col < dimension; col++) {
            cols = true;
            rows = true;

            for (int row = 0; row < dimension; row++) {
                cols &= (gameField[col][row] == playerSymbol);
                rows &= (gameField[row][col] == playerSymbol);
            }

            // Это условие после каждой проверки колонки и столбца
            // позволяет остановить дальнейшее выполнение, без проверки
            // всех остальных столбцов и строк.
            if (cols || rows) {
                result = true;
            }

            if (result)
                break;

        }

        return result;

    }

    /**
     * Поиск выиграшной комбинации
     * @param numberOfEmpty - количество пустых ячеек
     * @return buttonIndex - позиция выиграшной клетки
     */
    int winningCombination(int numberOfEmpty) {
        int buttonIndex = -1;

        char playerSymbol = getGame().getCurrentPlayer().getPlayerSign();

        buttonIndex = winningCombinationLines(playerSymbol, numberOfEmpty);
        if (buttonIndex < 0) {
            buttonIndex = winningCombinationDiagonals(playerSymbol, numberOfEmpty);
        }

        return buttonIndex;
    }

    /**
     * Поиск выиграшной комбинации по столбцам и линиям
     * @param playerSymbol - символ для комбинации
     * @param numberOfEmpty - количество пустых ячеек
     * @return buttonIndex - позиция выиграшной клетки
     */
    private int winningCombinationLines(char playerSymbol, int numberOfEmpty) {
        int buttonIndex = -1;
        boolean cols, rows;
        int numberOfEmptyX, numberOfEmptyY;
        int buttonIndexX, buttonIndexY;

        for (int col = 0; col < dimension; col++) {
            cols = true;
            rows = true;
            numberOfEmptyX = numberOfEmpty;
            numberOfEmptyY = numberOfEmpty;
            buttonIndexX = -1;
            buttonIndexY = -1;

            for (int row = 0; row < dimension; row++) {

                if (gameField[col][row] == nullSymbol) {
                    numberOfEmptyX--;
                    buttonIndexX = (row * dimension) + col;
                }

                if (gameField[row][col] == nullSymbol) {
                    numberOfEmptyY--;
                    buttonIndexY = (col * dimension) + row;
                }

                cols &= (gameField[col][row] == playerSymbol ||
                         gameField[col][row] == nullSymbol) &&
                        numberOfEmptyX >= 0;
                rows &= (gameField[row][col] == playerSymbol ||
                         gameField[row][col] == nullSymbol) &&
                        numberOfEmptyY >= 0;
            }

            if (cols || rows) {
                if (cols) {
                    buttonIndex = buttonIndexX;
                } else
                    buttonIndex = buttonIndexY;

                break;
            }
        }

        return buttonIndex;
    }


    /**
     * Проверка выиграшных комбинаций на диагоналях
     * @return флаг победы
     */
    private boolean checkWinDiagonals(char playerSymbol) {
        boolean diagonalL, diagonalR, result;
        int beginning = dimension - 1;

        result = false;

        diagonalL = true;
        diagonalR = true;
        for (int i = 0; i < dimension; i++) {
            diagonalL &= (gameField[i][i] == playerSymbol);
            diagonalR &= (gameField[i][beginning--] == playerSymbol);
        }

        if (diagonalL || diagonalR)
            result = true;

        return result;
    }

    /**
     * Поиск выиграшной комбинации по диагоналям
     * @param playerSymbol - символ для комбинации
     * @param numberOfEmpty - количество пустых ячеек
     * @return buttonIndex - позиция выиграшной клетки
     */
    private int winningCombinationDiagonals(char playerSymbol, int numberOfEmpty) {
        int buttonIndex = -1;
        int beginning = dimension -1;

        boolean diagonalL = true;
        boolean diagonalR = true;
        int numberOfEmptyL = numberOfEmpty;
        int numberOfEmptyR = numberOfEmpty;
        int buttonIndexL = buttonIndex;
        int buttonIndexR = buttonIndex;
        for (int i = 0; i < dimension; i++) {

            if (gameField[i][i] == nullSymbol) {
                numberOfEmptyL--;
                buttonIndexL = (i * dimension) + i;
            }

            if (gameField[i][beginning] == nullSymbol) {
                numberOfEmptyR--;
                buttonIndexR = (beginning * dimension) + i;
            }

            diagonalL &= (gameField[i][i] == playerSymbol ||
                          gameField[i][i] == nullSymbol) &&
                         numberOfEmptyL >= 0;
            diagonalR &= (gameField[i][beginning] == playerSymbol ||
                          gameField[i][beginning] == nullSymbol) &&
                         numberOfEmptyR >= 0;
            beginning--;
        }

        if (diagonalL || diagonalR) {
            if (diagonalL) {
                buttonIndex = buttonIndexL;
            } else
                buttonIndex = buttonIndexR;
        }

        return buttonIndex;
    }

    /**
     * Поиск свободной клетки согласно рейтинга
     * @return  buttonIndex - позиция рейтинговой свободной клетки
     */
    int freeCellRating() {
        int buttonIndex = -1;

        buttonIndex = freeCellRatingDiagonals();
        if (buttonIndex < 0) {
            buttonIndex = freeCell();
        }

        return buttonIndex;
    }

    /**
     * Поиск свободной клетки по диагонали
     * @return buttonIndex - позиция рейтинговой свободной клетки
     */
    int freeCellRatingDiagonals() {
        int buttonIndex = -1;
        int beginning = dimension - 1;

        for (int i = 0; i < dimension; i++) {
            if (gameField[i][i] == nullSymbol) {
                buttonIndex = (i * dimension) + i;
                break;
            }

            if (gameField[i][beginning] == nullSymbol) {
                buttonIndex = (beginning * dimension) + i;
                break;
            }
            beginning--;
        }

        return buttonIndex;
    }

    /**
     * Поиск свободной клетки
     * @return buttonIndex - позиция свободной клетки
     */
    int freeCell() {
        int buttonIndex = -1;

        out:
        for (int col = 0; col < dimension; col++) {
            for (int row = 0; row < dimension; row++) {
                if (gameField[col][row] == nullSymbol) {
                    buttonIndex = (row * dimension) + col;
                    break out;
                }

                if (gameField[row][col] == nullSymbol) {
                    buttonIndex = (col * dimension) + row;
                    break out;
                }
            }
        }

        return buttonIndex;
    }

    public GameButton getButton(int buttonIndex) {
        return gameButtons[buttonIndex];  // возвращаем ссылку из массива
    }
}
