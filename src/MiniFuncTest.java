
public class MiniFuncTest {

    public static void main(String[] args) {

        /// given test cases
        TestCases.literalTests();
        TestCases.variableTests();
        TestCases.arithmeticExpressioTest();
        TestCases.logicalExpression();
        TestCases.conditionalExpression();
        TestCases.ifExpression();
        
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
