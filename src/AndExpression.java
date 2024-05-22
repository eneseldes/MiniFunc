
public class AndExpression extends LogicalExpression {

    private Expression x1;
    private Expression x2;

    AndExpression(Expression x1, Expression x2) {
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
            //First, we check the type of expressions here. If it is not BooleanLiteral or BooleanVariable exception is thrown. 
            if (x1 == null || x2 == null) {
                throw new NullPointerException(" An expression is null!! ");
            } else if (x1.getValue() instanceof Boolean && x2.getValue() instanceof Boolean) {
                //Here if it is a Boolean, And gate process is done and returned as a BooleanLiteral.
                return new BooleanLiteral((boolean) x1.getValue() && (boolean) x2.getValue());
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
            return "( " + x1 + " and " + x2 + " )";
        }
        return "**Inexpressible " + getClass().getName() + " result** " + execute();
    }
}
