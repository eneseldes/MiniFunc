
public class ConditionalExpression extends Expression {

    Expression x1;
    Expression x2;
    private Number a;
    private Number b;
    private String str1;
    private String str2;
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
        if (x1.getValue() instanceof IntegerVariable || x1.getValue() instanceof IntegerLiteral || x1.getValue() instanceof Integer) {
            a = (Integer) x1.getValue();
        } else if (x1.getValue() instanceof DoubleVariable || x1.getValue() instanceof DoubleLiteral || x1.getValue() instanceof Double) {
            a = (Double) x1.getValue();
        } else if (x1.getValue() instanceof String) {
            str1 = (String) x1.getValue();

        } else {
            throw new IllegalArgumentException(" Invalid expression entered!! ");
        }
        if (x2.getValue() instanceof IntegerVariable || x2.getValue() instanceof IntegerLiteral || x2.getValue() instanceof Integer) {
            b = (Integer) x2.getValue();
        } else if (x2.getValue() instanceof DoubleVariable || x2.getValue() instanceof DoubleLiteral || x2.getValue() instanceof Double) {
            b = (Double) x2.getValue();
        } else if (x2.getValue() instanceof String) {
            str2 = (String) x2.getValue();

        } else {
            throw new IllegalArgumentException(" Invalid expression entered!! ");
        }

        if (str1 != null && str2 != null) {
            switch (op.name()) {
                case "Equal":
                    return new BooleanLiteral(str1.equals(str2));
                case "NotEqual":
                    return new BooleanLiteral(!str1.equals(str2));
            }
            throw new IllegalArgumentException(" Invalid operator entered!! ");
        } else if ((str1 == null && str2 != null) || (str1 != null && str2 == null)) {
            throw new IllegalArgumentException(" String and Expression cannot be compared!!  ");
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
        return " (" + x1 + " " + op + " " + x2 + ") ";
    }
}
