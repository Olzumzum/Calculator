package interactors;

import Entities.CollectionOperator;
import Entities.Operator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ConverterToPostfix {
    private List<String> linePostfix;
    private int iLinePost;
    private Operator operatorInLine;
    private ArrayList<Operator> collectionOperator;
    private Stack<Operator> stack;

    public ConverterToPostfix() {
        collectionOperator = new CollectionOperator().getMarkOperation();
        stack = new Stack<>();
    }

    public List<String> conversionPostfix(char[] expressionText) {
        int count = 0;
        String numbersFromExpr = "";        //строка для записи чисел
        linePostfix = new ArrayList<>();
        int weightStr = -1;
        iLinePost = 0;

        if (!mathExpressionTest(expressionText))
            return linePostfix = null;

        while (count < expressionText.length) {


            if (expressionText[count] != ' ') {
                //если цифра - занести в строчку
                if ((Character.isDigit(expressionText[count])) || (expressionText[count] == '.')) {
                    numbersFromExpr += String.valueOf(expressionText[count]);
                }

                //если оператор выполнить
                else {
                    if (!numbersFromExpr.isEmpty()) {
                        if ((expressionText[count] == '-') && (!Character.isDigit(expressionText[count - 1])))
                            numbersFromExpr += String.valueOf(expressionText[count]);
                        else {
                            linePostfix.add(iLinePost, numbersFromExpr);//если оператор то
                            iLinePost++;
                            numbersFromExpr = "";
                        }
                    } else if (expressionText[count] == '-')
                        numbersFromExpr += String.valueOf(expressionText[count]);


                    if (!numbersFromExpr.equals("-")) {
                        //найти введенный оператор среди существующих в калькуляторе
                        for (int i = 0; i < collectionOperator.size(); i++) {
                            //если оператор используется в калькуляторе
                            if (collectionOperator.get(i).getNameOperator() == expressionText[count]) {
                                //присвоить вес
                                operatorInLine = collectionOperator.get(i);
                            }
                        }

                        //выбор действий относительно оператора
                        destributionWeight(operatorInLine);
                    }
                }

                //если цифра последняя - добавить в префиксную форму
                if ((count == expressionText.length - 1) && (Character.isDigit(expressionText[count]))) {
                    linePostfix.add(iLinePost, numbersFromExpr);
                    iLinePost++;
                }
            }

            count++;
            weightStr = -1;
        }


        while (!stack.empty()) {
            if (stack.peek().getWeight() > 0) {
                linePostfix.add(String.valueOf(stack.peek().getNameOperator()));
                iLinePost++;
            }
            stack.pop();
        }

        System.out.println(linePostfix);

        return linePostfix;

    }

    private void choiceOfOperators(Operator op, List<String> linePostfix) {
        //если в стеке есть операторы
        if (stack.size() != 0) {

            //если оператор с таким же весом
            if (op.getWeight() == stack.peek().getWeight()) {
                linePostfix.add(iLinePost, String.valueOf(stack.peek().getNameOperator()));
                iLinePost++;
                stack.pop();
                Operator operator = new Operator(op.getNameOperator(), op.getWeight());
                stack.push(operator);
            } else {

                //если вес больше, чем у предыдущего
                if (op.getWeight() > stack.peek().getWeight()) {
                    Operator operator = new Operator(op.getNameOperator(), op.getWeight());
                    stack.push(operator);
                }

                //если вес меньше, чем у предыдущего
                if (op.getWeight() < stack.peek().getWeight()) {
                    while (!stack.empty() && (op.getWeight() <= stack.peek().getWeight())) {
                        linePostfix.add(iLinePost, String.valueOf(stack.peek().getNameOperator()));
                        iLinePost++;
                        stack.pop();
                    }
                    Operator operator = new Operator(op.getNameOperator(), op.getWeight());
                    stack.push(operator);
                }
            }
        }

        //если в стеке ничего
        else {
            Operator operator = new Operator(op.getNameOperator(), op.getWeight());
            stack.push(operator);
        }
    }

    private void destributionWeight(Operator operatorInLine) {
        // если скобки
        if (operatorInLine.getWeight() == 0) {
            Operator op = new Operator(operatorInLine.getNameOperator(), operatorInLine.getWeight());
            stack.push(op);
        }

        if (operatorInLine.getWeight() == 1)
            workWithClosingBracket();

        //если +, -, *, / ... и т.д.
        if (operatorInLine.getWeight() > 1)
            choiceOfOperators(operatorInLine, linePostfix);

    }

    //действия, если полученный оператор - ")"
    private void workWithClosingBracket() {
        while (stack.peek().getWeight() > 0) {
            linePostfix.add(iLinePost, String.valueOf(stack.peek().getNameOperator()));
            iLinePost++;
            stack.pop();
        }

        if (stack.peek().getWeight() <= 1) {
            stack.pop();
        }
    }

    //проверка введеных значений
    private boolean mathExpressionTest(char[] expression) {
        boolean flag = true;
        for (int i = 0; i < expression.length; i++) {
            if (!Character.isDigit(expression[i])) {
                for (int j = 0; j < collectionOperator.size(); j++) {
                    if (expression[i] == collectionOperator.get(j).getNameOperator()) {
                        flag = true;
                        break;
                    } else
                        flag = false;
                }
            }
            if (flag == false)
                break;
        }
        return flag;

    }


}
