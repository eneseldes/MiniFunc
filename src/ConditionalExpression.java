
public class ConditionalExpression extends Expression {

    Expression x1;
    Expression x2;
    private Number a;
    private Number b;  
    ConditionalOperator op;

    ConditionalExpression(Expression x1, Expression x2, ConditionalOperator op) {
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
        if (x1.getValue() instanceof Number) {
            a = ((Number) x1.getValue());
            if (x2.getValue() instanceof Number) {
                b = ((Number) x2.getValue());
            } else {
                throw new IllegalArgumentException(" Invalid type of expression entered. Enter a boolean!! ");
            }
        } else if (x1.getValue() instanceof String) {
            if (x2.getValue() instanceof String) {
                switch (op.name()) {
                    case "Equal":
                        return new BooleanLiteral(x1.getValue().equals(x1.getValue()));
                    case "NotEqual":
                        return new BooleanLiteral(!x1.getValue().equals(x1.getValue()));
                }
                throw new IllegalArgumentException(" Invalid operator entered!! ");
            }
        } else {
            throw new IllegalArgumentException(" Invalid variable entered!! ");
        }

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
        return null;
    }

    @Override
    public String toString() {
        return " (" + x1 + " " + op + " " + x2 + ") = " + getValue();
    }
}
