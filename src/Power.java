
// Check Addition.Java for comments
public class Power extends ArithmeticBinaryExpression {

    Literal value;

    Power(Expression base, Expression exponent) {
        super(base, exponent);
    }

    @Override
    Object getValue() {
            execute();
            return value.getValue();

    }

    @Override
    Expression execute() {
        // Different from Addition. This class accepts only Number
        if (!(leftExpression.getValue() instanceof Number) && !(rightExpression.getValue() instanceof Number)) {
            System.out.println("Encountered improper value in " + getClass().getName() + ". Results may be inaccurate!");
            return null;
        }

        Double base = ((Number) leftExpression.getValue()).doubleValue();
        Double exponent = ((Number) rightExpression.getValue()).doubleValue();

        // Hold numerator and denominator of the exponent value
        int[] exponentAsIntegerValues = getNumeratorAndDenominator(exponent);
        int exponentNumerator = exponentAsIntegerValues[0];
        int exponentDenominator = exponentAsIntegerValues[1];

        // The the nth-numerator- order exponent
        Double resultValue = pow(base, exponentNumerator) * root(base, exponentDenominator);
        // Take the nth-denominator- order root
        resultValue = root(resultValue, exponentDenominator);

        value = resultValue % 1 == 0
                ? IntegerLiteral.create(resultValue.intValue()) : DoubleLiteral.create(resultValue);

        return value.execute();
    }

    // Takes nth order exponent recursively
    Double pow(Double base, Integer exponent) {
        if (exponent == 0) {
            return 1.0;
        }
        // if exponent is negative, invert the number
        if (exponent < 0) {
            return 1 / pow(base, -exponent);
        }
        
        return base * pow(base, exponent - 1);
    }

    // Finds greates common divisor
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        
        return gcd(b, a % b);
    }

    // Takes nth order root
    Double root(Double base, Integer exponent) {
        return Math.pow(base, 1.0 / exponent);
    }

    // Returns the denominator and numerator part of a decimal number
    public static int[] getNumeratorAndDenominator(double exponent) {
        // Typecast the decimal number and take the length of the decimal part
        String decimalString = Double.toString(exponent);
        int decimalPlaces = decimalString.substring(decimalString.indexOf('.') + 1).length();

        // Main logic of this method is as follows:
        // 0.75 --> 75 / 100 --> (using gcd) 3 / 4
        
        int denominator = (int) Math.pow(10, decimalPlaces);
        int numerator = (int) (exponent * denominator);
        int gcd = gcd(numerator, denominator);
        
        // Simplification
        numerator /= gcd;
        denominator /= gcd;
        
        int[] exponentAsDecimal = {numerator, denominator};
        return exponentAsDecimal;
    }

    @Override
    public String toString() {
        try{
            return "power(" + leftExpression.toString() + "," + rightExpression.toString() + ")";
        }
        catch(Exception e){
            return "**Inexpressible " + getClass().getName() + " result**";
        }
    }
    
}
