
public class ExceptionExpression extends Expression {

    private Exception e;
    private String className;

    //This turns the class exception into an expression
    ExceptionExpression(String className, Exception e) {
        this.e = e;
        this.className = className;
    }

    @Override
    Object getValue() {
        return execute();
    }

    @Override
    Expression execute() {
        try {
            throw e;
        } catch (Exception ex) {
            System.out.println(className + " " + ex);
        }
        return new StringLiteral("");
    }

    public String toString() {
        return getValue() + " ";
    }

}
