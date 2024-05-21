
import java.util.logging.Level;
import java.util.logging.Logger;


public class ExceptionExpression extends Expression {
    Exception e;
    String className;
    
    ExceptionExpression(String className,Exception e){
        this.e=e;
        this.className=className;
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
            System.out.println(className + " " +ex);
        }
        return new StringLiteral("");
    }
    public String toString(){
        return getValue() +" ";
    }

}
