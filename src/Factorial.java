public class Factorial extends ArithmeticUnaryExpression{

    public Factorial(Expression expression) {//Tam sayı alma olayını yap
        super(expression);
    }
    

    @Override
    Object getValue() {
        return execute().getValue();
    }

    @Override
    Expression execute() {
        if(expression.getValue() instanceof Integer){
            if((int)expression.getValue()>1){
               return new Multipication(expression, new Factorial(new IntegerLiteral((int)expression.getValue()-1)).execute());
            }
            else{
                return new IntegerLiteral(1);
            }
        }
        else if(expression.getValue() instanceof Double){
            if((double)expression.getValue()>1){
               return new Multipication(expression,new Factorial(new DoubleLiteral((double)expression.getValue()-1)).execute());
            }
            else{
                return new DoubleLiteral(1.0);
            }
        }   
        return null;//Hata fırlat
    }

}
