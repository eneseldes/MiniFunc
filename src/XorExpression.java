public class XorExpression extends Expression{
    Expression x1;
    Expression x2;
    XorExpression(Expression x1, Expression x2){
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
            if(x1.getValue()==x2.getValue()){
                return new BooleanLiteral(false);
            }
            return new BooleanLiteral(true);
        }
        throw new IllegalArgumentException(" Invalid expression entered!! ");
    }
    @Override
    public String toString(){
        return "(" +x1 +" xor " + x2 +")";
    }
    

}
