
import java.util.*;

public class Fibonacci extends Function {

    List<Integer> fibonacciList = new ArrayList<>();
    Expression term;
    private Integer termInt;
    private Integer firstTerm = 1;
    private Integer secondTerm = 1;

    Fibonacci(Expression term) {
        this.term = term;
        if (term.getValue() instanceof Number) {
            termInt = ((Number) term.getValue()).intValue();
            fibonacciList.add(firstTerm);
            fibonacciList.add(secondTerm);
        }
    }

    private Fibonacci(Integer firstTerm, Integer secondTerm, Integer termInt, Expression term, List fibonacciList) {
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
        try {
            if (term.getValue() instanceof Number) {
                if (((Number) term.getValue()).doubleValue() % 1 != 0) {
                    throw new IllegalArgumentException(" Invalid type of expression entered. Enter a Integer!! ");
                }
                else if(termInt<1){
                    throw new IllegalArgumentException(" Enter a positive number!!");
                }
                if (termInt - 1 > 1) {
                    return new Fibonacci(secondTerm, (Integer) new Addition(new IntegerLiteral(firstTerm),
                            new IntegerLiteral(secondTerm)).getValue(),(Integer) new Substraction(new IntegerLiteral(termInt), new IntegerLiteral(1)).getValue(),
                            term, fibonacciList).execute();
                } else {
                    return new IntegerLiteral(secondTerm);
                }
            } else {
                throw new IllegalArgumentException(" Invalid type of expression entered. Enter a Integer!! ");
            }
        } catch (Exception e) {
            System.out.println(getClass().getSimpleName() + " " + e);
        }
        return new StringLiteral("");

    }

    @Override
    public String toString() {

        if (term.getValue() instanceof Number) {
            if (((Number) term.getValue()).doubleValue() % 1 == 0 && ((Number)term.getValue()).doubleValue()>=1) {
                execute();
                StringBuffer sb = new StringBuffer();
                for (Integer i : fibonacciList) {
                    sb.append(i);
                    sb.append(" ");
                }
                return sb.toString();
            }
        }
        return "**Inexpressible " + getClass().getName() + " result** " +execute();

    }
}
