
// Check Addition.Java for comments
public class Power extends ArithmeticBinaryExpression {

    Power(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
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
        if (leftExpression == null || rightExpression == null) {
            System.out.println("Encountered null expression on " + getClass().getSimpleName() + " operation. Results may be inaccurate!");
            return null;
        } else if (!(leftExpression.getValue() instanceof Number) || !(rightExpression.getValue() instanceof Number)) {
            System.out.println("Encountered non-number expression on " + getClass().getSimpleName() + " operation. Results may be inaccurate!");
            return null;
        }

        Double base = ((Number) leftExpression.getValue()).doubleValue();
        Double exponent = ((Number) rightExpression.getValue()).doubleValue();

        // Hold numerator and denominator of the exponent value
        int[] exponentAsIntegerValues = getNumeratorAndDenominator(exponent);
        int exponentNumerator = exponentAsIntegerValues[0];
        int exponentDenominator = exponentAsIntegerValues[1];

        /*
            Example: 4^(3/2) means: 2nd root of 3rd exponent of 4
        
            The assigning value of 'resultValue' below is the code representation
            of the arithmetic expression above
         */
        Double exponentialResult = ((Number) pow(base, exponentNumerator).getValue()).doubleValue();
        Number resultValue = root(exponentialResult, exponentDenominator);

        return resultValue.doubleValue() % 1 == 0
                ? IntegerLiteral.create(resultValue.intValue()) : DoubleLiteral.create(resultValue.doubleValue());
    }

    // Takes nth order exponent recursively
    static Expression pow(Double base, Integer exponent) {
        if (exponent == 0) {
            return new IntegerLiteral(1);
        }
        // if exponent is negative, invert the number
        if (exponent < 0) {
            return new Division(new IntegerLiteral(1), 
                    new Power(new DoubleLiteral(base), 
                            new IntegerLiteral(-exponent)))
                                .execute();
        }

        return new Multipication(new DoubleLiteral(base), 
                new Power(new DoubleLiteral(base), 
                        new IntegerLiteral(exponent - 1)))
                            .execute();

    }

    // Finds greates common divisor
    static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }

    // Takes nth order root
    static Double root(Double base, Integer exponent) {
        return Math.pow(base, 1.0 / exponent);
    }

    // Returns the denominator and numerator part of a decimal number as array
    static int[] getNumeratorAndDenominator(double exponent) {
        // Typecast the decimal number and take the length of the decimal part
        String decimalString = Double.toString(exponent);
        int decimalPlaces = decimalString.substring(decimalString.indexOf('.') + 1).length();

        // Main logic of this method is as follows:
        // 0.75 --> 75 / 100 --> (using gcd) 3 / 4
        int denominator = (int) Math.pow(10, decimalPlaces);
        int numerator = (int) new Multipication(new DoubleLiteral(exponent), new IntegerLiteral(denominator)).getValue();
        int gcd = gcd(numerator, denominator);

        // Simplification
        numerator = (int) new Division(new IntegerLiteral(numerator), new IntegerLiteral(gcd)).getValue();
        denominator = (int) new Division(new IntegerLiteral(denominator), new IntegerLiteral(gcd)).getValue();

        int[] exponentAsDecimal = {numerator, denominator};
        return exponentAsDecimal;
    }
    
}
