
public class ExceptionExpression extends Expression {
    
    @Override
    Object getValue() {
        return execute();
    }
    @Override
    Expression execute() {
        throw new IllegalArgumentException(" Invalid type of number entered. Enter a integer!! ");        
    }

}
