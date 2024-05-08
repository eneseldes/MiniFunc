
public class ConditionalExpression extends Expression{
    Expression x1;
    Expression x2;
    private Number a;
    private Number b;
    ConditionalOperator op;
    ConditionalExpression(Expression x1, Expression x2, ConditionalOperator op){
        this.x1=x1;
        this.x2=x2;
        this.op=op;
    }

    @Override
    Object getValue() {
        return execute().getValue();
    }

    @Override
    Expression execute() {
        if(x1.getValue() instanceof IntegerVariable || x1.getValue() instanceof IntegerLiteral || x1.getValue() instanceof Integer){
            a = (Integer) x1.getValue();
        }
        else if(x1.getValue() instanceof DoubleVariable || x1.getValue() instanceof DoubleLiteral || x1.getValue() instanceof Double){
            a = (Double) x1.getValue();
        }
        else{
            //hata throwla
        }
        if(x2.getValue() instanceof IntegerVariable || x2.getValue() instanceof IntegerLiteral || x2.getValue() instanceof Integer){
            b = (Integer) x2.getValue();
        }
        else if(x2.getValue() instanceof DoubleVariable || x2.getValue() instanceof DoubleLiteral || x2.getValue() instanceof Double){
            b = (Double) x2.getValue();
        }
        else{
            //hata throwla
        }
        
        switch(op.name()){
                case "Equal":return new BooleanLiteral(a.doubleValue() == b.doubleValue());
		case "NotEqual":return new BooleanLiteral(a.doubleValue()!=b.doubleValue()); 
		case "Less": return new BooleanLiteral(a.doubleValue()<b.doubleValue());
		case "LessEqual": return new BooleanLiteral(a.doubleValue()<=b.doubleValue());
		case "Greater": return new BooleanLiteral(a.doubleValue()>b.doubleValue());
		case "GreaterEqual": return new BooleanLiteral(a.doubleValue()>=b.doubleValue());
            }
        return null;       
    }
}
