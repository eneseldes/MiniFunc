
public class IsPalindrome extends Function {

    private Expression expression;
    
    IsPalindrome(Expression expression){
        this.expression = expression;
    }
    
    @Override
    Object getValue() {
        try {
            return execute().getValue();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    Expression execute() {
        // Different from Addition. This class accepts only Number
        if (expression == null) {
            System.out.println("Encountered null expression on " + getClass().getSimpleName() + " operation. Results may be inaccurate!");
            return null;
        } 
        
        if (expression.getValue() instanceof String) {
            StringLiteral sentence = new StringLiteral((String)expression.getValue());
            IntegerVariable left = new IntegerVariable("left", 0);
            IntegerVariable right = new IntegerVariable("right", 
                    (int) new Subtraction(new IntegerLiteral(((String)sentence.getValue()).length()), new IntegerLiteral(1)).getValue());
            
            while((boolean)new ConditionalExpression(left, right, ConditionalOperator.Less).getValue()){
                CharacterLiteral leftLetter = new CharacterLiteral(((String)sentence.getValue()).charAt((int)left.getValue()));
                CharacterLiteral rightLetter = new CharacterLiteral(((String)sentence.getValue()).charAt((int)right.getValue()));
                if (!(boolean)new ConditionalExpression(leftLetter, rightLetter, ConditionalOperator.Equal).getValue()) {
                    return new BooleanLiteral(false);
                }
                left.assign((int)new Addition(left, new IntegerLiteral(1)).getValue());
                right.assign((int)new Subtraction(right, new IntegerLiteral(1)).getValue());
            }
            
            return new BooleanLiteral(true);
        }
        
        if (expression.getValue() instanceof Double) {
            System.out.println("Encountered double expression on " + getClass().getSimpleName() + " operation. Enter an Integer value!");
            return null;
        }
        
        if (expression.getValue() instanceof Integer) {
            IntegerVariable number = new IntegerVariable("number", (Integer)expression.getValue());
            IntegerVariable reversedNumber = new IntegerVariable("reversedNumber", 0);
            IntegerVariable digit = new IntegerVariable("digit", 0);
            
            while((boolean)new ConditionalExpression(number, new IntegerLiteral(0), ConditionalOperator.NotEqual).getValue()){
                digit.assign((Number) new Modulo(number, new IntegerLiteral(10)).getValue());
                reversedNumber.assign((Number) new Addition(new Multipication(reversedNumber, new IntegerLiteral(10)), digit).getValue());
                number.assign((Number) new Division(number, new IntegerLiteral(10)).getValue());
            }
            
            return new ConditionalExpression(expression, reversedNumber, ConditionalOperator.Equal);
        }
        
        System.out.println("Encountered improper expression on " + getClass().getSimpleName() + " operation. Enter an Integer value!"); 
        return null;
    }
    
    @Override
    public String toString(){
        if (expression == null) {
            return "??Inexpressible " + getClass().getSimpleName() + " result due to null value??";
        }

        if (expression.getValue() instanceof String || expression.getValue() instanceof Integer) {
            return "Is " + expression.toString() + " palindrome?";
        }

        return "(Improper Calculation Here...)Is " + expression.toString() + " palindrome?";
    }

}
