
public class Xor extends LogicalExpression {

    private Expression x1;
    private Expression x2;

    Xor(Expression x1, Expression x2) {
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
            //Here the types of expressions are checked.If expressions' types are not a boolean, exception is thrown
            if (x1.getValue() instanceof Boolean && x2.getValue() instanceof Boolean) {
                //If their values are same false is returned
                if (x1.getValue() == x2.getValue()) {
                    return new BooleanLiteral(false);
                }
                //If it is not true is returned
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
        return "**Inexpressible " + getClass().getName() + " result** " + execute();
    }
}
