
public class StudentTestCases {

    static void physicsQuestion(){
        System.out.println("Test Case 1 --> A Physics Question");
        System.out.println("----------------------------------");
        
        Expression gravity = new DoubleVariable("gravity", 9.8);
        Expression height = new IntegerVariable("height", 20);
        Expression horizontalVelocity = new IntegerVariable("horizontalVelocity", 5);
        System.out.println("QUESTION: An object with negligible mass is launched from a height of " + height.getValue() + 
                " meters with a horizontal velocity of " + horizontalVelocity.getValue() + "m/s:\n");
        
        System.out.println("1- From the equation of motion in two dimensions, "
                + "we can say 'time = sqrt(2*height/acceleration)'");
        
        Expression time = new Power(new Division(new Multipication(new IntegerLiteral(2), height), gravity), new DoubleLiteral(0.5));
        System.out.println("2- The time the object spends in the air is " + time.getValue() + " seconds from the equation " + time + 
                " since height is " + height.getValue() + " and gravity is " + gravity.getValue() + ".");
        
        Expression range = new Multipication(horizontalVelocity, time);
        System.out.println("3- If we substitute 'time' into equation 'range = horizontalVelocity*time' as " + range 
                + ", \nwe can find the horizontal distance traveled by the object as " + range.getValue() + " meters.");
        
        
        System.out.println();
    }
    
    static void loanCalculation() {
        System.out.println("Test Case 2 --> Loan Calculation");
        System.out.println("----------------------------------");
        
        // basic array structure: client name, loanAmount, isReliable
        Object[] odin = {"Odin", 10000, true};
        Object[] kratos = {"Kratos", 4500, true};
        Object[] loki = {"Loki", 100, false};
        
        System.out.println("AYBU Bank grants loan with some principles: ");
        System.out.println("1- Interest rate is 1.2 up to 2000 Turkish liras, 1.4 up to 5500 liras and 1.6 up to 8000 liras.");
        System.out.println("2- If person is reliable, interest rate is decreased by 0.23, while increasing by 12.4 if one is not reliable.");

        System.out.println();
        System.out.println("AYBU BANK ARCHIVES");
        System.out.println("---+++---+++---+++---+++---+++---+++");
        
        System.out.println(odin[0] + " takes out " + odin[1] + " liras loan.");
        System.out.println("-> Since he is reliable, his refund amount decreased to " + getInterestRate(odin) + ".");
        System.out.printf("---> Total Refund Amount: %.2f TL\n", getRefundAmount(odin));
        
        System.out.println();
        
        System.out.println(kratos[0] + " takes out " + kratos[1] + " liras loan.");
        System.out.println("-> Since he is reliable, his refund amount decreased to " + getInterestRate(kratos) + ".");
        System.out.printf("---> Total Refund Amount: %.2f TL\n", getRefundAmount(kratos));
        
        System.out.println();
        
        System.out.println(loki[0] + " takes out " + loki[1] + " liras loan.");
        System.out.println("-> Since he is NOT reliable, his refund amount increased to " + getInterestRate(loki) + ".");
        System.out.printf("---> Total Refund Amount: %.2f TL\n", getRefundAmount(loki));
        
        System.out.println();
    }

    // method of loanCalculation()
    private static double getInterestRate(Object[] person) {
        int[] breakPoints = {2000, 5500, 8000};
        double[] interestRates = {1.2, 1.4, 1.6};
        
        Expression loanAmount = new DoubleLiteral((int) person[1]);
        Expression isReliable = new BooleanLiteral((boolean) person[2]);

        /*
            Calculate raw interest rate and assign it as Expression since we are 
            going to use this value in another Expression while calculating 
            final interest rate
         */
        Expression rawInterestRate = new If(new Condition(loanAmount, new IntegerLiteral(breakPoints[0]), ConditionalOperator.Less), new DoubleLiteral(interestRates[0]),
                new If(new Condition(loanAmount, new IntegerLiteral(breakPoints[1]), ConditionalOperator.Less), new DoubleLiteral(interestRates[0]),
                        new DoubleLiteral(interestRates[2]))).execute();

        // Find final interest rate based on whether client is reliable or not
        Number finalInterestRate = (Number) new If(new Condition(isReliable, new BooleanLiteral(true), ConditionalOperator.Equal),
                new Subtraction(rawInterestRate, new DoubleLiteral(0.23)),
                new Addition(rawInterestRate, new DoubleLiteral(12.4)))
                .getValue();

        return finalInterestRate.doubleValue();
    }
    
    //method of loanCalculation()
    private static double getRefundAmount(Object[] person){
        Expression loanAmount = new DoubleLiteral((int) person[1]);
        Expression interestRate = new DoubleLiteral(getInterestRate(person));
        
        return ((Number)new Multipication(loanAmount, interestRate).getValue()).doubleValue();
    }
    
    static void arithmeticExpressions(){
        System.out.println("Test Case 3 --> Arithmetic Expressions And Exception Handling");
        System.out.println("-------------------------------------------------------------");
        
        Expression num1 = new IntegerLiteral(25);
        Expression num2 = new DoubleLiteral(0.5);
        Expression num3 = new IntegerLiteral(2);
        Expression bool = new BooleanLiteral(true);
        Expression sentence = new StringLiteral("ceng114");
        Expression nullValue = null;
        
        Expression correctResult1 = new Power(num1, num2);
        System.out.println(correctResult1 + " = " + correctResult1.getValue());
        Expression correctResult2 = new Addition(num1, sentence);
        System.out.println(correctResult2 + " = " + correctResult2.getValue());
        Expression correctResult3 = new Modulo(num1, num3);
        System.out.println(correctResult3 + " = " + correctResult3.getValue());
        Expression correctResult4 = new Absolute(new Subtraction(new Negation(correctResult1), new Power(num3, correctResult3)));
        System.out.println(correctResult4 + " = " + correctResult4.getValue());
        
        System.out.println();
        
        Expression wrongResult1 = new Addition(num1, bool);
        System.out.println(wrongResult1 + " = " + wrongResult1.getValue());
        
        System.out.println();
        
        Expression wrongResult2 = new Power(num1, new Multipication(num1, bool));
        System.out.println(wrongResult2 + " = " + wrongResult2.getValue());
        
        System.out.println();
        
        // Let's make it more complicated
        Expression wrongResult3 = new Power(num1, new Addition(new Multipication(nullValue, num1), bool));
        System.out.println(wrongResult3 + " = " + wrongResult3.getValue());
        
        System.out.println();
    }
    static void logicalExpressions(){
        System.out.println("Test Case 4 --> Logic Expressions");
        System.out.println("---------------------------------");
        
        System.out.println("QUESTION: A=1 B=0, ((AB) + B)' + (A'B + AB')= ? ");
        BooleanLiteral a = new BooleanLiteral(true);
        BooleanLiteral b = new BooleanLiteral(false);
        BooleanLiteral ab = new BooleanLiteral((Boolean)new And(a,b).getValue());
        System.out.println("Firstly, AB means is  A AND B , so A and B is -->" +ab );
        BooleanLiteral abOrB = new BooleanLiteral((Boolean)new Or(ab, b).getValue()); 
        System.out.println("Then AB + B means is AB OR B, AB--> " +ab +", B-->"+b +", AB OR B --> " +abOrB);
        BooleanLiteral abOrBNot = new BooleanLiteral((Boolean) new Not(abOrB).getValue());
        System.out.println("And ' means is NOT gate so (AB + B)' is --> " +abOrBNot);
        BooleanLiteral aXorB = new BooleanLiteral((Boolean) new Xor(a,b).getValue());
        System.out.println("In the second part (A'B + AB') means is A XOR B, so A XOR B --> " +aXorB);
        BooleanLiteral result = new BooleanLiteral((Boolean) new Or(abOrBNot, aXorB).getValue());
        System.out.println("Finally, ((AB) + B)' + (A'B + AB')--> " +result);
        
        System.out.println();
    }
    
    static void functionalExpressions1(){
        System.out.println("Test Case 5 --> Factorial, Prime Number and Fibonacci");
        System.out.println("-----------------------------------------------------");
        
        System.out.println("QUESTION: 132*n! = (n+2)*(n+1)*n*(n-1)! \n a) n=? and (n-3)!=? \n b) What is the nth term of Fibonacci serie \n c) Is n prime? If it is not find the closest prime number to n. ");
        Expression n = new IntegerLiteral(10);
        System.out.println("n*(n-1)! = n! so 132 = (n+2)*(n+1) According to this equation ");
        Expression fac = new Factorial(new Subtraction(n ,new IntegerLiteral(3)));       
        System.out.println("a) n=" +n +" and (n-3)!= " +fac.getValue());
        Expression fib = new Fibonacci(n);
        System.out.println("b) nth term of Fibonacci = " +fib.getValue());
        Expression prime = new IsPrime(n);
        Expression prime2 = new IsPrime(new IntegerLiteral((Integer)new Subtraction(n, new IntegerLiteral(1)).getValue()));
        Expression prime3 = new IsPrime(new IntegerLiteral((Integer)new Addition(n, new IntegerLiteral(1)).getValue()));
        System.out.println("c) " + prime.getValue() +" According to this answer we should find the closest prime number. Firstly we should look at the n+1 and n-1, " +prime2 +" " +prime3);
        
        System.out.println();
    }
    
    static void functionalExpressions2(){
        System.out.println("Test Case 6 --> Min - Max");
        System.out.println("-------------------------");
        
        Expression num1 = new IntegerLiteral(5);
        Expression num2 = new IntegerLiteral(2);
        Expression num3 = new IntegerLiteral(999);
        Expression num4 = new DoubleLiteral(15.7);
        Function min = new Min(num1, num2);
        Function max = new Max(num4, num3);
        Function hybrid = new Max(min, max);
        
        System.out.println(min + " = " + min.getValue());
        System.out.println(max + " = " + max.getValue());
        System.out.println(hybrid + " = " + hybrid.getValue());
        
        System.out.println();
    }
    
    static void functionalExpressions3(){
        System.out.println("Test Case 7 --> Is Palindrome?");
        System.out.println("------------------------------");
        
        StringLiteral s1 = new StringLiteral("ey edip adanada pide ye");
        StringLiteral s2 = new StringLiteral("CENG114");
        IntegerLiteral num1 = new IntegerLiteral(555);
        IntegerLiteral num2 = new IntegerLiteral(249);
        Expression add = new Addition(s1, new Addition(s2, new Addition(num1, num2)));
        Function p1 = new IsPalindrome(s1);
        Function p2 = new IsPalindrome(s2);
        Function p3 = new IsPalindrome(num1);
        Function p4 = new IsPalindrome(num2);
        Function p5 = new IsPalindrome(add);
        
        System.out.println(p1 + " --> " + p1.getValue());
        System.out.println(p2 + " --> " + p2.getValue());
        System.out.println(p3 + " --> " + p3.getValue());
        System.out.println(p4 + " --> " + p4.getValue());
        System.out.println(p5 + " --> " + p5.getValue());
        
        System.out.println();
        
    }
}
