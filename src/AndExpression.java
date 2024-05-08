
public class AndExpression extends Expression{
    Expression x1;
    Expression x2;
    AndExpression(Expression x1, Expression x2){
        this.x1=x1;
        this.x2=x2;
    }

    @Override
    Object getValue() {
        return execute().getValue();       
    }

    @Override
    Expression execute() {
        if(x1.getValue() instanceof Boolean && x2.getValue() instanceof Boolean){
           return new BooleanLiteral((boolean)x1.getValue() && (boolean) x2.getValue());
        }
        throw new IllegalArgumentException("Invalid type! ");

    }
    @Override
    public String toString(){
        return " (" +x1 +" and " + x2 +") ";
    }

}
