
public class MiniFuncTest {

    public static void main(String[] args) {

        /// given test cases
        GivenTestCases.literalTests();
        GivenTestCases.variableTests();
        GivenTestCases.arithmeticExpressioTest();
        GivenTestCases.logicalExpression();
        GivenTestCases.conditionalExpression();
        GivenTestCases.ifExpression();
        
        //Student test cases
        StudentTestCases.physicsQuestion();
        StudentTestCases.loanCalculation();
        StudentTestCases.arithmeticExpressions();
        StudentTestCases.logicalExpressions();
        
        System.out.println("***************************FUNCTION TEST CASES***************************");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println();
        StudentTestCases.functionalExpressions1();
        StudentTestCases.functionalExpressions2();
        StudentTestCases.functionalExpressions3();
        
    }

}
