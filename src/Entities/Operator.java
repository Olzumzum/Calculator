package Entities;

public class Operator {
    private char nameOperator;
    private int weight;

    public Operator(char mNameOperator, int mWeight) {
        this.nameOperator = mNameOperator;
        this.weight = mWeight;
    }

    public char getNameOperator() {
        return nameOperator;
    }

    public int getWeight() {
        return weight;
    }
}