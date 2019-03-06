package main.java.GUI;

import javax.swing.*;
import java.awt.*;

public class CalcExample {
    public static void main(String[] args) {
        // Создаем окно
        JFrame frame = new JFrame("Калькулятор");
        // Определяем размеры
        frame.setSize(590, 540);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font = new Font("Arial", Font.PLAIN, 40);
        // Создаем панель с менеджером вертикального расположения компонентов
        JPanel contents = new JPanel(new VerticalLayout());
        // Добавим текстовое поле ввода аргументов и вывода результата в панель
        JTextField field = new JTextField(16);
        field.setHorizontalAlignment(SwingConstants.RIGHT);
        field.setSize(340, 40);
        field.setFont(font);
        field.setText("0");
        field.setCaretPosition(field.getCaretPosition() + 1);
        contents.add(field);

        JPanel buttons = new JPanel(new VerticalLayout());
        GridLayout gl = new GridLayout(5, 4, 20, 20);
        buttons.setLayout(gl);
        ButtonHandler bh = new ButtonHandler(field);
        Dimension ps = new Dimension(120, 60);
        // Добавим кнопки в панель
        String namesButtons[] = {"C", "^", "%", "/", "7", "8", "9", "*",
                "4", "5", "6", "-", "1", "2", "3", "+", "0", ".","+/-","="};
        JButton btn[] = new JButton[20];

        for (int i = 0; i < 20; i++) {
            btn[i] = new JButton(namesButtons[i]);
            buttons.add(namesButtons[i], btn[i]);
            btn[i].addActionListener(bh);
            btn[i].setPreferredSize(ps);
            btn[i].setFont(font);
        }
        contents.add(buttons);

        // Размещаем панель в контейнере
        frame.setContentPane(contents);

        // Открываем окно
        frame.setVisible(true);
    }
}
