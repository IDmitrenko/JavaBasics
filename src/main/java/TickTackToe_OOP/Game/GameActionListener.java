package main.java.TickTackToe_OOP.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameActionListener implements ActionListener {
    private int row;
    private int cell;
    private GameButton button;

    public GameActionListener(int row, int cell, GameButton gButton) {
        this.row = row;
        this.cell = cell;
        this.button = gButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameBoard board = button.getBoard();  // ссылка на gameBoard
        // при ходе мы должны проверять можно ли пойти в данную клетку текущему игроку

        if (board.isTurnable(row, cell)) {
            updateByPlayersData(board);

            if (board.isFull()) {    // проверяем не закончились ли места на поле
                board.getGame().showMessage("Ничья!");
                board.emptyField();
            }
            else {    // ходит компьютер
                updateByAiData(board);
            }
        } else {
            board.getGame().showMessage("Некорректный ход!");
        }

    }

    /**
     * Ход человека
     * @param board GameBoard - ссылка на игровое поле
     */
    private void updateByPlayersData(GameBoard board) {
        // обновить матрицу игры
        board.updateGameField(row, cell);  // записываем значение хода человека
        // Обновить содержимое кнопки
        button.setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));
        // toString используется потому что char и String - это разные типы
        if (board.checkWin()) {
            button.getBoard().getGame().showMessage("Вы выиграли!");
            board.emptyField();       // чистим поле
        }
        else {      // передаем ход компьютеру
            board.getGame().passTurn();
        }
    }

    /**
     * Ход компьютера
     * @param board GameBoard - ссылка на игровое поле
     */
    private void updateByAiData(GameBoard board) {
        // генерация координат хода компьютера
        int x, y, buttonIndex, numberOfEmpty;

        // составляем схему своих и свободных клеток
        // и ищем сначала клетку, которая приведет к концу игры
        numberOfEmpty = 1;
        buttonIndex = board.winningCombination(numberOfEmpty);
        if (buttonIndex >= 0) {
            x = buttonIndex / GameBoard.dimension;
            y = buttonIndex % GameBoard.dimension;
        } else {    // ищем есть ли выиграшная комбинация у игрока
            // имитация передачи хода игроку для проверки его комбинаций
            board.getGame().passTurn();
            buttonIndex = board.winningCombination(numberOfEmpty);
            // возврат хода компьютеру
            board.getGame().passTurn();
            if (buttonIndex >= 0) {  // предотвращаем победу игрока
                x = buttonIndex / GameBoard.dimension;
                y = buttonIndex % GameBoard.dimension;
            } else {   // ищем свободную линию, где нужны два наших хода
                numberOfEmpty = 2;
                buttonIndex = board.winningCombination(numberOfEmpty);
                if (buttonIndex >= 0) {
                    x = buttonIndex / GameBoard.dimension;
                    y = buttonIndex % GameBoard.dimension;
                } else {  // ставим в свободную клетку согласно рейтинга
                    buttonIndex = board.freeCellRating();
                    if (buttonIndex >= 0) {
                        x = buttonIndex / GameBoard.dimension;
                        y = buttonIndex % GameBoard.dimension;
                    } else {
                        x = 0;
                        y = 0;
                        board.getGame().showMessage("Ничья!");
                        board.emptyField();
                    }
                }

            }
        }

        // обновить матрицу игры
        board.updateGameField(x, y);

        // обновить содержимое кнопки
        int cellIndex = GameBoard.dimension * x + y;   // получение индекса кнопки
        board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        // проверить победу
        if (board.checkWin()) {
            button.getBoard().getGame().showMessage("Компьютер выиграл!");
            board.emptyField();
        }
        else {
            if (board.isFull()) {    // проверяем не закончились ли места на поле
                board.getGame().showMessage("Ничья!");
                board.emptyField();
            } else
            // передать ход человеку
            board.getGame().passTurn();
        }
    }

}
