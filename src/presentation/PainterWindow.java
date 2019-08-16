package presentation;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PainterWindow {

    private static final int windowWidth = 290;
    private static final int windowHeight = 580;
    private static final int editTextHeight = 100;
    private static final int numberCharactersInputField = 30;
    private static final int[] marginEditField = {0, 1, 1, 1};
    private static final int xResult = 0;
    private static final int yREsult = 99;
    private static final int heightResult = 50;

    HashMap<String, JButton> buttonMapMark;
    private JFrame window;
    private JPanel panel;
    private JTextArea editText;
    private JTextField resultText;
    private CollectionButtonCalculator collectionButton;

    public PainterWindow() {
        window = new JFrame("Calculator for FocusStart");
        window.setSize(windowWidth, windowHeight);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new FlowLayout());
        panel.setLayout(null);

        editText = new JTextArea();
        editText.setLineWrap(true);
        editText.setWrapStyleWord(true);
        editText.setSize(windowWidth, editTextHeight);
        editText.setMargin(new Insets(marginEditField[0], marginEditField[1], marginEditField[2], marginEditField[3]));

        resultText = new JTextField(numberCharactersInputField);
        resultText.setBounds(xResult, yREsult, windowWidth, heightResult);
        resultText.setMargin(new Insets(marginEditField[0] + 20, marginEditField[1], marginEditField[2], marginEditField[3]));
        collectionButton = new CollectionButtonCalculator(editText,resultText);
        panel = collectionButton.create();

        panel.add(editText);
        panel.add(resultText);
        panel.add(resultText);

        panel.revalidate();

        window.add(panel);
        window.setContentPane(panel);

        window.setVisible(true);
    }
}
