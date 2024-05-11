
public class MiniFuncTest {

    public static void main(String[] args) {

        /// given test cases
        /*TestCases.literalTests();
        TestCases.variableTests();
        TestCases.arithmeticExpressioTest();
        TestCases.logicalExpression();
        TestCases.conditionalExpression();
        TestCases.ifExpression();*/
        System.out.println((new Root(new DoubleLiteral(4.0), new DoubleLiteral(2.0))).getValue());
        System.out.println(new Power(new DoubleLiteral(4), new DoubleLiteral(0.5)).getValue());
        /// student test cases
        //StudentTestCases.testCase1();
        //StudentTestCases.testCase2();
        //StudentTestCases.testCase3();
    }

}
