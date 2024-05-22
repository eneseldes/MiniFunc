
public class Condition extends Expression {

    private Expression x1;
    private Expression x2;
    private Number a;
    private Number b;
    private ConditionalOperator op;

    Condition(Expression x1, Expression x2, ConditionalOperator op) {
        this.x1 = x1;
        this.x2 = x2;
        this.op = op;
    }

    @Override
    Object getValue() {
        return execute().getValue();
    }

    @Override
    Expression execute() {
        try {
            //Here the types of expressions were checked. If their expressions types are not same or expressions are not number&string exception is thrown
            if (x1.getValue() instanceof Boolean && x2.getValue() instanceof Boolean && op == ConditionalOperator.Equal)
                return new BooleanLiteral(x1.getValue() == x2.getValue());
            if (x1.getValue() instanceof Character && x2.getValue() instanceof Character && op == ConditionalOperator.Equal)
                return new BooleanLiteral(x1.getValue() == x2.getValue());
            
            if (x1.getValue() instanceof Number) {
                if (x2.getValue() instanceof Number) {
                    //If they are number, Their values were saved as double
                    a = ((Number) x1.getValue()).doubleValue();
                    b = ((Number) x2.getValue()).doubleValue();
                } else {
                    throw new IllegalArgumentException(" Expressions are different type!! ");
                }
            } else if (x1.getValue() instanceof String) {
                if (x2.getValue() instanceof String) {
                    // If they were both strings, the operator was checked to see if it was equal or not equal. 
                    switch (op.name()) {
                        case "Equal":
                            return new BooleanLiteral((x1.getValue().equals(x2.getValue())));
                        case "NotEqual":
                            return new BooleanLiteral((!x1.getValue().equals(x2.getValue())));
                    }
                    throw new IllegalArgumentException(" Invalid operator entered!! ");
                }
                throw new IllegalArgumentException(" Expressions are different type!! ");
            } else {
                throw new IllegalArgumentException(" Invalid variable entered!! ");
            }
        } catch (Exception e) {
            System.out.println(getClass().getSimpleName() + " " + e);
            return new StringLiteral("");
        }
        // If both are numbers, the operator is checked and the answer is returned
        switch (op.name()) {
            case "Equal":
                return new BooleanLiteral(a.doubleValue() == b.doubleValue());
            case "NotEqual":
                return new BooleanLiteral(a.doubleValue() != b.doubleValue());
            case "Less":
                return new BooleanLiteral(a.doubleValue() < b.doubleValue());
            case "LessEqual":
                return new BooleanLiteral(a.doubleValue() <= b.doubleValue());
            case "Greater":
                return new BooleanLiteral(a.doubleValue() > b.doubleValue());
            case "GreaterEqual":
                return new BooleanLiteral(a.doubleValue() >= b.doubleValue());
        }
        return new StringLiteral("");
    }

    @Override
    public String toString() {
        if ((((x1.getValue() instanceof Number && x2.getValue() instanceof Number)
                || (x1.getValue() instanceof String && x2.getValue() instanceof String)))) {
            return "(" + x1 + " " + op + " " + x2 + ") = " + getValue();
        }
        return execute().toString();
    }
}
