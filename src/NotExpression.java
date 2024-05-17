
public class NotExpression extends Expression {

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
            if (value.getValue() instanceof Boolean) {
                return new BooleanLiteral(!(boolean) value.getValue());
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
        if (value.getValue() instanceof Boolean) {
            return "(" + getValue() + ")";
        }
        return getValue().toString();

    }

}
