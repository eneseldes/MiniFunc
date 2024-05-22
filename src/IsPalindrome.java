
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
            // Get value as string and initialize 0 and sentence.length() - 1 indexes
            StringLiteral sentence = new StringLiteral((String)expression.getValue());
            IntegerVariable leftIndex = new IntegerVariable("left", 0);
            IntegerVariable rightIndex = new IntegerVariable("right", 
                    (int) new Subtraction(new IntegerLiteral(((String)sentence.getValue()).length()), new IntegerLiteral(1)).getValue());
            
            while((boolean)new Condition(leftIndex, rightIndex, ConditionalOperator.Less).getValue()){
                // Get letters at indexes leftIndex and rightIndex
                CharacterLiteral leftLetter = new CharacterLiteral(((String)sentence.getValue()).charAt((int)leftIndex.getValue()));
                CharacterLiteral rightLetter = new CharacterLiteral(((String)sentence.getValue()).charAt((int)rightIndex.getValue()));
                
                // If letters on the left and right are not equal, return false
                if ((boolean)new Condition(leftLetter, rightLetter, ConditionalOperator.NotEqual).getValue()) {
                    return new BooleanLiteral(false);
                }
                
                // Increase leftIndex, decrease rightIndex
                leftIndex.assign((int)new Addition(leftIndex, new IntegerLiteral(1)).getValue());
                rightIndex.assign((int)new Subtraction(rightIndex, new IntegerLiteral(1)).getValue());
            }
            
            return new BooleanLiteral(true);
        }
        
        // Input can not be a double value
        if (expression.getValue() instanceof Double) {
            System.out.println("Encountered double expression on " + getClass().getSimpleName() + " operation. Enter an Integer value!");
            return null;
        }
        
        if (expression.getValue() instanceof Integer) {
            IntegerVariable number = new IntegerVariable("number", (Integer)expression.getValue());
            IntegerVariable reversedNumber = new IntegerVariable("reversedNumber", 0);
            IntegerVariable digit = new IntegerVariable("digit", 0);

            while((boolean)new Condition(number, new IntegerLiteral(0), ConditionalOperator.NotEqual).getValue()){
                // Get last digit of the 'number'
                digit.assign((Number) new Modulo(number, new IntegerLiteral(10)).getValue());
                // Add digit at the end of 'reversedNumber'. (by multiplying reversedNumber by 10 in every iteration as below))
                reversedNumber.assign((Number) new Addition(new Multipication(reversedNumber, new IntegerLiteral(10)), digit).getValue());
                // Erase the last digit of the 'number' (by dividing number by 10 in every iteration as below))
                number.assign((Number) new Division(number, new IntegerLiteral(10)).getValue());
            }
            
            // If the original number is equal to reversedNumber, return true, else false
            return new Condition(expression, reversedNumber, ConditionalOperator.Equal);
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
