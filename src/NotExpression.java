
public class NotExpression extends Expression {

    Expression value;

    NotExpression(Expression value) {
        this.value = value;
    }

    @Override
    Object getValue() {
        if (value.getValue() instanceof Boolean) {
            return !(boolean) value.getValue();
        }
        throw new IllegalArgumentException(" Invalid expression entered!! ");
    }

    @Override
    Expression execute() {
        if (value instanceof BooleanLiteral) {
            BooleanLiteral newValue = new BooleanLiteral(!(boolean) value.getValue());
            return newValue;
        } else if (value instanceof BooleanVariable) {
            BooleanVariable newValue = (BooleanVariable) value;
            newValue.assign((Variable) value);
            return newValue;

        }
        throw new IllegalArgumentException(" Invalid expression entered!! ");

    }

    @Override
    public String toString() {
        return " (" + !(boolean) value.getValue() + ")";
    }

}
