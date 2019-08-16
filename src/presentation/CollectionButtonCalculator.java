package presentation;

import interactors.CalculationOfValue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/*
Идея данного класса упростить работу с калькулятором в случае добавления знаков операций
(чтобы не требовалось рассчитывать координаты для новых появившихся кнопочек
 */


public class CollectionButtonCalculator {
    private static final int windowWidth = 400;
    private static final int windowHeight = 700;
    private static final int sizeButton = 50;
    private static final int sizeHorizontal = 4;
    private static final int sizeVertical = 6;
    private static final int margeButton = 12;

    private static final String[] mark = {"+", "-", "*", "/", ".", "=", "(", ")", "<", ">", "C"};
    private static final String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    private ArrayList<Integer> columns;
    private ArrayList<Integer> lines;
    HashMap<String, JButton> buttonMapMark;
    JButton buttonOperation;
    JPanel panel;
    JTextArea textField;
    String resultText;
    JTextField resultTextField;

    public CollectionButtonCalculator(JTextArea text, JTextField textres) {
        resultTextField = textres;
        textField = text;
    }

    //создание панели кнопок
    public JPanel create() {
        getCoordinates();
        buttonMapMark = new HashMap<>();
        panel = new JPanel();
        panel.setLayout(null);
        int line = 0;

        int k = 0;
        int l = 0;

        //заполняем все строчки
        while (line < lines.size()) {
            //заполняем все колонки
            int column = 0;
            while (column < columns.size()) {
                //цикл задания имен и создния кнопок
                if (k < numbers.length) {
                    if (column == sizeHorizontal - 1) {
                        buttonOperation = new JButton(mark[l]);
                        buttonOperation.setBounds(columns.get(column++), lines.get(line), sizeButton, sizeButton);
                        buttonMapMark.put(mark[l], buttonOperation);
                        panel.add(buttonOperation);

                        clicked(buttonOperation, mark[l]);

                        l++;
                    } else {
                        buttonOperation = new JButton(numbers[k]);
                        buttonOperation.setBounds(columns.get(column++), lines.get(line), sizeButton, sizeButton);
                        buttonMapMark.put(numbers[k], buttonOperation);
                        panel.add(buttonOperation);

                        clicked(buttonOperation, numbers[k]);

                        k++;
                    }
                } else {
                    //если цифры кончились, а знаки нет, то выставляем оставшиеся
                    if (l < mark.length) {
                        buttonOperation = new JButton(mark[l]);
                        buttonOperation.setBounds(columns.get(column++), lines.get(line), sizeButton, sizeButton);
                        buttonMapMark.put(mark[l], buttonOperation);
                        panel.add(buttonOperation);

                        clicked(buttonOperation, mark[l]);
                        l++;
                    } else {
                        break;
                    }
                }
            }
            line++;
        }

        return panel;
    }

    // получение координат для выстраивания столбцов
    private void getCoordinates() {
        columns = new ArrayList<>();
        lines = new ArrayList<>();

        for (int i = sizeHorizontal; i > 0; i--) {
            columns.add(windowWidth - 140 - i * (sizeButton + margeButton) + margeButton); // формула для столбцов
        }

        for (int i = 0; i < sizeVertical; i++) {
            lines.add(windowHeight - 130 - (i * sizeButton + (8 + i) * margeButton));             //формула для строк
        }
    }

    //ввод символов в текстовое поле
    public void clicked(JButton button, String singButton) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (singButton == "=") {
                    CalculationOfValue calculationOfValue = new CalculationOfValue();
                    calculationOfValue.giveExpression(textField.getText());
                    resultText = calculationOfValue.getResult();
                    resultTextField.setText(resultText);
                    textField.setText("");

                } else if (singButton == "C") {
                    resultTextField.setText("");
                    textField.setText("");

                } else
                    textField.setText(textField.getText() + singButton);
            }
        });
    }

    public String returnResult() {
        return resultText;
    }

}
