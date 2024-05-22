
public class NotExpression extends LogicalExpression {

    private Expression value;

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
            //Here the types of expression is checked.If expression's type is not a boolean, exception is thrown
            if (value.getValue() instanceof Boolean) {
                //If expression's type is a boolean, we return by taking the opposite of the boolean
                return new BooleanLiteral(!(Boolean) value.getValue());
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
        return "**Inexpressible " + getClass().getName() + " result** " + execute();
    }
}
