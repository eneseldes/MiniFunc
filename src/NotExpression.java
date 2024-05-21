
public class NotExpression extends LogicalExpression {
    Expression value;

    NotExpression(Expression value) {
        this.value = value;
    }

    @Override
    Object getValue() {
        return execute().getValue();
    }

    @Override
    Expression execute() {
        try {
            return new IfExpression(new ConditionalExpression(value, new StringLiteral("BooleanLiteral"), ConditionalOperator.InstanceOf),
            new BooleanLiteral(!(boolean) value.getValue()), new ExceptionExpression("NotExpression", new IllegalArgumentException(" Invalid type of expression entered. Enter a boolean!! ")));
        } catch (Exception e) {
            System.out.println(getClass().getSimpleName() + " " + e);
        }
        return new StringLiteral("");
    }

    @Override
    public String toString() {
        if (value.getValue() instanceof Boolean) {
            return "(" + getValue() + ")";
        }
        return "**Inexpressible " + getClass().getName() + " result** " +execute();

    }

}
