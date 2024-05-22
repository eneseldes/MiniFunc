
import java.util.*;

public class Fibonacci extends Function {

    private List<Integer> fibonacciList = new ArrayList<>();
    private Expression term;
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
        //This constructor is being used to do recursion
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
            //Here the types of expression is checked.If expression's type is not a number, exception is thrown
            if (term.getValue() instanceof Number) {
                if (((Number) term.getValue()).doubleValue() % 1 != 0) {
                    //If expression's  value is a double number,like 4.2 ,exception is thrown
                    throw new IllegalArgumentException(" Invalid type of expression entered. Enter a Integer!! ");
                } //We checked is termInt < 1?. If it is true exception is thrown
                else if ((Boolean) new ConditionalExpression(new IntegerLiteral(termInt), new IntegerLiteral(1), ConditionalOperator.Less).getValue()) {
                    throw new IllegalArgumentException(" Enter a positive number!!");
                }
                //We checked is termInt - 1 < 1?. If it is true new Fibonacci is returned. We counts term until term -1 = 1 
                if ((Boolean) new ConditionalExpression(new Substraction(new IntegerLiteral(termInt), new IntegerLiteral(1)), new IntegerLiteral(1), ConditionalOperator.Greater).getValue()) {
                    return new Fibonacci(secondTerm, (Integer) new Addition(new IntegerLiteral(firstTerm),
                            new IntegerLiteral(secondTerm)).getValue(), (Integer) new Substraction(new IntegerLiteral(termInt), new IntegerLiteral(1)).getValue(),
                            term, fibonacciList).execute();
                } //Here if term -1 = 1, secondTerm(here last term of fibonacci) is returned
                else {
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
            if (((Number) term.getValue()).doubleValue() == 1) {
                return "" + fibonacciList.get(0);
            } else if (((Number) term.getValue()).doubleValue() % 1 == 0 && ((Number) term.getValue()).doubleValue() >= 1) {
                execute();
                StringBuffer sb = new StringBuffer();
                for (Integer i : fibonacciList) {
                    sb.append(i);
                    sb.append(" ");
                }
                return sb.toString();
            }
        }
        return "**Inexpressible " + getClass().getName() + " result** " + execute();
    }
}
