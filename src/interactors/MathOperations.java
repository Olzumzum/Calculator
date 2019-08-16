package interactors;

public class MathOperations {

    //выбор оператора
    public double selectionOperator(char operator, double a, double b) {
        if (operator == '+')
            return addition(a, b);

        else if (operator == '-')
            return subtraction(a, b);

        else if (operator == '*')
            return multiplication(a, b);

        else if (operator == '/')
            return division(a, b);

        else if (operator == '>')
            return ternary(a, b);

        else if (operator == '<')
            return ternaryV(a, b);
        else
            return 0;
    }

    //сложение
    public double addition(double a, double b) {
        return a + b;
    }

    //вычитание
    public double subtraction(double a, double b) {
        return a - b;
    }

    //умножение
    public double multiplication(double a, double b) {
        return a * b;
    }

    //умножение
    public double division(double a, double b) {
        return a / b;
    }

    //тернарный оператор
    public double ternary(double a, double b) {
        return a > b ? a : b;    //тернарный оператор
    }

    public double ternaryV(double a, double b) {
        return a < b ? a : b;
    }
}
