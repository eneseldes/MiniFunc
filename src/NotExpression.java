
public class NotExpression extends Expression {

    Expression value;

    NotExpression(Expression value) {
        if(value.getValue() instanceof Boolean){
            this.value = value; 
        }
        else{
            throw new IllegalArgumentException(" Invalid type of expression entered. Enter a boolean!! ");
        }
        
    }

    @Override
    Object getValue() {       
            return !(boolean) value.getValue();
    }

    @Override
    Expression execute() {
        return value;  
    }

    @Override
    public String toString() {
        return "(" + getValue() + ")" ;
    }

}
