public class NotExpression extends Expression {
    Expression value;
    NotExpression(Expression value){
        this.value=value;
    }

    @Override
    Object getValue() {
        if(value.getValue() instanceof Boolean){
            return !(boolean)value.getValue();
        }
        return null;//Burada hata throwla
    }

    @Override
    Expression execute() {
        if(value instanceof BooleanLiteral){
            BooleanLiteral newValue= new BooleanLiteral(!(boolean)value.getValue());
            return newValue;
        }
        else if(value instanceof BooleanVariable){
            BooleanVariable newValue=(BooleanVariable)value;
            newValue.assign((Variable)value);
            return newValue;
           
        }
        return null;//Burada hata throwla

    }
    @Override
    public String toString(){
       return " (" + !(boolean)value.getValue() +")"; 
    }

}
