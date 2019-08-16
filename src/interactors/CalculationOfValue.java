package interactors;

import Entities.CollectionOperator;
import Entities.Operator;

import java.util.*;

public class CalculationOfValue {

    private char[] expressionText;
    private String result;
    private int iStek = 0;

    //получить математическое выражение
    public void giveExpression(String expression) {
        expressionText = expression.toCharArray();
        countingNumbers();
    }

    //вернуть результат
    public String getResult() {
        return result;
    }

    public String countingNumbers() {
        List<String> s = new ConverterToPostfix().conversionPostfix(expressionText);
        String d = "";
        List<Double> stackNumbers = new Stack<>();
        int count = 0;
        while (s.size() != count) {
            char[] str = s.get(count).toCharArray();

            if ((str[0] == '-') && (str.length > 1)) {
                for (int i = 0; i < str.length; i++)
                    d += str[i];
                stackNumbers.add(Double.valueOf(d));
                iStek++;
                d = "";
            } else if ((Character.isDigit(str[0])) || (str[0] == '.')) {

                for (int i = 0; i < str.length; i++)
                    d += str[i];
                stackNumbers.add(Double.valueOf(d));
                iStek++;
                d = "";
            } else {
                if (iStek != 0) {
                    searchOperator(iStek, str[0], stackNumbers);
                }
            }
            count++;
        }
        //????????????????????????????
        if (stackNumbers.size() > 1) {
            int l = s.size();
            char[] dk = s.get(l - 1).toCharArray();
            searchOperator(iStek, dk[0], stackNumbers);
        }

        result = String.valueOf(stackNumbers.get(0));
        System.out.println(result);
        return result;
    }


    private List<Double> searchOperator(int i, char str, List<Double> stekNumbers) {
        MathOperations mathOperations = new MathOperations();
        int j = i - 1;
        double newValue = mathOperations.selectionOperator(str, stekNumbers.get(j - 1), stekNumbers.get(j));
        stekNumbers.set(j - 1, newValue);
        stekNumbers.remove(j);
        i--;
        iStek = i;
        return stekNumbers;
    }
}


