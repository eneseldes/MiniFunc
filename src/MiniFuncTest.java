
public class MiniFuncTest {

    public static void main(String[] args) {

        /// given test cases
        /*TestCases.literalTests();
        TestCases.variableTests();
        TestCases.arithmeticExpressioTest();
        TestCases.logicalExpression();
        TestCases.conditionalExpression();
        TestCases.ifExpression();*/
        BooleanVariable a = new BooleanVariable("a", false);
        BooleanLiteral b = new BooleanLiteral(true);
        System.out.println(new XorExpression(a, b));
        /// student test cases
        //StudentTestCases.testCase1();
        //StudentTestCases.testCase2();
        //StudentTestCases.testCase3();
    }

}
