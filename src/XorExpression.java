
public class XorExpression extends LogicalExpression {
    Expression x1;
    Expression x2;

    XorExpression(Expression x1, Expression x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    @Override
    Object getValue() {
        return execute().getValue();
    }

    @Override
    Expression execute() {
        try {
            if (x1.getValue() instanceof Boolean && x2.getValue() instanceof Boolean) {
                if (x1.getValue() == x2.getValue()) {
                    return new BooleanLiteral(false);
                }
                return new BooleanLiteral(true);
            } else {
                throw new IllegalArgumentException(" Invalid type of expression entered. Enter a boolean!! ");
            }
        } catch (Exception e) {
            System.out.println(getClass().getSimpleName() + " " + e);
        }
        return new StringLiteral("");
    }

    @Override
    public String toString() {
        if (x1.getValue() instanceof Boolean && x2.getValue() instanceof Boolean) {
            return "( " + x1 + " xor " + x2 + " )";
        }
        return "**Inexpressible " + getClass().getName() + " result** " +execute();
    }

}
