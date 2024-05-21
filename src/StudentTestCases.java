
public class StudentTestCases {

    static void testCase1(){
        System.out.println("Test Case 1");
        System.out.println("--------------");
        
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
    
    static void testCase2() {
        System.out.println("Test Case 2");
        System.out.println("--------------");
        
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
    }

    // method of testCase2()
    static double getInterestRate(Object[] person) {
        int[] breakPoints = {2000, 5500, 8000};
        double[] interestRates = {1.2, 1.4, 1.6};
        
        Expression loanAmount = new DoubleLiteral((int) person[1]);
        Expression isReliable = new BooleanLiteral((boolean) person[2]);

        /*
            Calculate raw interest rate and assign it as Expression since we are 
            going to use this value in another Expression while calculating 
            final interest rate
         */
        Expression rawInterestRate = new IfExpression(new ConditionalExpression(loanAmount, new IntegerLiteral(breakPoints[0]), ConditionalOperator.Less), new DoubleLiteral(interestRates[0]),
                new IfExpression(new ConditionalExpression(loanAmount, new IntegerLiteral(breakPoints[1]), ConditionalOperator.Less), new DoubleLiteral(interestRates[0]),
                        new DoubleLiteral(interestRates[2]))).execute();

        // Find final interest rate based on whether client is reliable or not
        Number finalInterestRate = (Number) new IfExpression(new ConditionalExpression(isReliable, new BooleanLiteral(true), ConditionalOperator.Equal),
                new Substraction(rawInterestRate, new DoubleLiteral(0.23)),
                new Addition(rawInterestRate, new DoubleLiteral(12.4)))
                .getValue();

        return finalInterestRate.doubleValue();
    }
    
    //method of testCase2()
    static double getRefundAmount(Object[] person){
        Expression loanAmount = new DoubleLiteral((int) person[1]);
        Expression interestRate = new DoubleLiteral(getInterestRate(person));
        
        return ((Number)new Multipication(loanAmount, interestRate).getValue()).doubleValue();
    }
}
