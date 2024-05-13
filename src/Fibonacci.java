
import java.util.*;

public class Fibonacci extends Function {

    List<Integer> fibonacciList = new ArrayList<>();
    Expression term;
    private int termInt;
    private int firstTerm = 1;
    private int secondTerm = 1;

    Fibonacci(Expression term) {
        this.term = term;
        if (term.getValue() instanceof Number) {
            this.termInt = ((Number) term.getValue()).intValue();
            this.fibonacciList.add(this.firstTerm);
            this.fibonacciList.add(this.secondTerm);
        } else {
            throw new IllegalArgumentException(" Invalid type of expression entered. Enter a Number!! ");
        }
    }

    private Fibonacci(int firstTerm, int secondTerm, int termInt, Expression term, List fibonacciList) {
        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
        this.termInt = termInt;
        this.term = term;
        this.fibonacciList = fibonacciList;
        this.fibonacciList.add(secondTerm);
    }

    @Override
    Object getValue() {
        return execute().getValue();
    }

    @Override
    Expression execute() {
        if (termInt - 1 > 1) {
            return new Fibonacci(secondTerm, (firstTerm + secondTerm), (termInt - 1), term, fibonacciList).execute();
        } else {
            return new IntegerLiteral(secondTerm);
        }
    }

    @Override
    public String toString() {
        execute();
        StringBuffer sb = new StringBuffer();
        for (Integer i : fibonacciList) {
            sb.append(i);
            sb.append(" ");
        }

        return sb.toString();
    }

}
