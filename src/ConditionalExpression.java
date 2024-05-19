
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
        try {
            if (x1.getValue() instanceof Number) {
                a = ((Number) x1.getValue()).doubleValue();
                if (x2.getValue() instanceof Number) {
                    b = ((Number) x2.getValue()).doubleValue();
                } else {
                    throw new IllegalArgumentException(" Expressions are different type!! ");
                }
            } else if (x1.getValue() instanceof String) {
                if (x2.getValue() instanceof String) {
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
        if((((x1.getValue() instanceof Number && x2.getValue() instanceof Number) || 
                (x1.getValue() instanceof String && x2.getValue() instanceof String)))){
             return "(" + x1 + " " + op + " " + x2 + ") = " + getValue();
        }
       return execute().toString();
    }
}
