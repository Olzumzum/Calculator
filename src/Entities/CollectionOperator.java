package Entities;

import java.util.ArrayList;
import java.util.List;

public class CollectionOperator {
    ArrayList<Operator> markOperation;

    public CollectionOperator() {
        markOperation = new ArrayList<>();
        markOperation.add(new Operator('(', 0));
        markOperation.add(new Operator(')', 1));
        markOperation.add(new Operator('+', 2));
        markOperation.add(new Operator('-', 2));
        markOperation.add(new Operator('*', 3));
        markOperation.add(new Operator('/', 3));
        markOperation.add(new Operator('<', 4));
        markOperation.add(new Operator('>', 4));
    }

    public ArrayList<Operator> getMarkOperation(){
        return markOperation;
    }
}
