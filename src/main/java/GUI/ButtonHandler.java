package main.java.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class ButtonHandler implements ActionListener {
    private static List<String> numbers = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0");
    private static List<String> command = Arrays.asList("+", "-", "*", "/", "^", "%");
    private final JTextField textField;
    private String temp;
    private String prevCommand = "";
    private String prevInput = "";

    protected ButtonHandler(JTextField field) {
        this.textField = field;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (numbers.contains(e.getActionCommand())) {
            if (((command.contains(prevInput) ||
                    prevInput.equals("="))) ||
                    (prevCommand.isEmpty() && prevInput.isEmpty()) ||
                    (Double.parseDouble(textField.getText().trim()) == 0.00 &&
                            prevInput.equals(".")))    {
                textField.setText(e.getActionCommand());
            } else {
                textField.setText(textField.getText().trim() + e.getActionCommand());
            }
        } else if ("C".equals(e.getActionCommand())) {
            textField.setText("0");
            temp = null;
            prevInput = "";
            prevCommand = "";
        } else if (".".equals(e.getActionCommand()) &&
                textField.getText() != null &&
                !textField.getText().isEmpty() &&
                !textField.getText().contains(".")) {
            textField.setText(textField.getText().trim() + e.getActionCommand());
        } else if ("+/-".equals(e.getActionCommand())) {
            if (Double.parseDouble(textField.getText().trim()) != 0.00)
                textField.setText(String.valueOf(-1 * Double.parseDouble(textField.getText().trim())));
        } else if (command.contains(e.getActionCommand()) &&
                !command.contains(prevInput)) {
            if (temp == null) {
                temp = textField.getText().trim();
            } else {
                operation();
            }
            textField.setText(temp);
        } else if ("=".equals(e.getActionCommand()) &&
                !"=".equals(prevInput)) {
            operation();

            textField.setText(temp);
            temp = null;
        }
        if (command.contains(e.getActionCommand()))
            prevCommand = e.getActionCommand();
        if (!"C".equals(e.getActionCommand()))
            prevInput = e.getActionCommand();
    }

    private String operation() {
        Double result;
        switch (prevCommand) {
            case "+":
                temp = String.valueOf(Double.parseDouble(temp) + Double.parseDouble(textField.getText().trim()));
                break;
            case "-":
                temp = String.valueOf(Double.parseDouble(temp) - Double.parseDouble(textField.getText().trim()));
                break;
            case "*":
                temp = String.valueOf(Double.parseDouble(temp) * Double.parseDouble(textField.getText().trim()));
                break;
            case "/":
                if (Double.parseDouble(textField.getText().trim()) == 0.00) {
                    temp = "ОШИБКА";
                } else {
                    temp = String.valueOf(Double.parseDouble(temp) / Double.parseDouble(textField.getText().trim()));
                }
                break;
            case "^":
                result = Math.pow(Double.parseDouble(temp), Double.parseDouble(textField.getText().trim()));
                temp = String.valueOf(result);
                break;
            case "%":
                temp = String.valueOf(Double.parseDouble(temp) / 100 * Double.parseDouble(textField.getText().trim()));
                break;
        }
        return temp;
    }
}
