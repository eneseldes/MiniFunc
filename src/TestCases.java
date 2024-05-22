
public class TestCases {
    
    static void literalTests() {
        System.out.println("literalTests()");
        System.out.println("--------------");
        
        Literal str = StringLiteral.create("CENG114");
        System.out.println(str);

        Literal x = IntegerLiteral.create(17);
        System.out.println(x);

        Literal y = DoubleLiteral.create(3.5);
        System.out.println(y);

        Literal z = BooleanLiteral.create(true);
        System.out.println(z);
        
        Literal u = CharacterLiteral.create('e');
        System.out.println(u);

        System.out.println();
    }

    static void variableTests() {
        System.out.println("variableTests()");
        System.out.println("---------------");
        
        Variable str1 = StringVariable.create("str1", "CENG117");
        Variable str2 = StringVariable.create("str2", "CENG114");
        System.out.println(str1 + " = " + str1.getValue());
        System.out.println(str2 + " = " + str2.getValue());

        str1.assign(str2);
        System.out.println(str1 + " = " + str1.getValue());
        System.out.println(str2 + " = " + str2.getValue());

        Variable i = IntegerVariable.create("i", 4);
        System.out.println(i + " = " + i.getValue());

        Variable d = DoubleVariable.create("d", 10.11);
        System.out.println(d + " = " + d.getValue());

        Variable b = BooleanVariable.create("b", false);
        System.out.println(b + " = " + b.getValue());
        
        Variable char1 = CharacterVariable.create("char1", 'd');
        System.out.println(char1 + " = " + char1.getValue());
        
        Variable char2 = CharacterVariable.create("char2", 'e');
        System.out.println(char2 + " = " + char2.getValue());
        
        System.out.println();
    }

    static void arithmeticExpressioTest() {
        System.out.println("arithmeticExpressioTest()");
        System.out.println("-------------------------");
        
        Expression e1 = new IntegerLiteral(5);
        Expression e2 = new DoubleVariable("e2", 7.5);
        Expression e3 = new IntegerVariable("e3", 4);
        Expression e4 = new StringVariable("e5", " is five");
        Expression e5 = new DoubleLiteral(2.5);
        System.out.println(e2 + " = " + e2.getValue());
        System.out.println(e3 + " = " + e3.getValue());
        System.out.println(e4 + " = " + e4.getValue());
        System.out.println(e1 + " + " + e2 + " = " + new Addition(e1, e2).execute());
        System.out.println(e1 + " + " + e3 + " = " + new Addition(e1, e3).execute());
        System.out.println(e1 + " + " + e4 + " = " + new Addition(e1, e4).execute());
        System.out.println(e2 + " - " + e3 + " = " + new Subtraction(e2, e3).execute());
        System.out.println(e2 + " * " + e3 + " = " + new Multipication(e2, e3).getValue());
        System.out.println(e2 + " / " + e5 + " = " + new Division(e2, e5).execute());
        System.out.println(e1 + " + (" + e2 + " / " + e5 + ") = " + new Addition(e1, new Division(e2, e5)).execute());
        System.out.println("-" + e3 + " = " + new Negation(e3).execute());

        System.out.println();
    }

    static void logicalExpression() {
        System.out.println("logicalExpression()");
        System.out.println("-------------------");
        
        Expression b1 = new BooleanLiteral(true);
        Expression b2 = new BooleanVariable("b2", false);
        System.out.println(b2 + " = " + b2.getValue());
        System.out.println("not(" + b1 + ") = " + new NotExpression(b1).execute());
        System.out.println(b1 + " and " + b2 + " = " + new AndExpression(b1, b2).getValue());
        System.out.println(b1 + " and not(" + b2 + ") = " + new AndExpression(b1, new NotExpression(b2)).getValue());
        System.out.println(b1 + " or " + b2 + " = " + new OrExpression(b1, b2).getValue());
        System.out.println(b1 + " xor " + b2 + " = " + new XorExpression(b1, b2).getValue());
        System.out.println(b1 + " xor not(" + b2 + ") = " + new XorExpression(b1, new NotExpression(b2)).getValue());

        System.out.println();
    }

    static void conditionalExpression() {
        System.out.println("conditionalExpression()");
        System.out.println("-----------------------");
        
        Expression two = new IntegerLiteral(2);
        Expression x1 = new IntegerLiteral(5);
        Expression x2 = new IntegerVariable("x2", 10);
        ConditionalOperator lessOp = ConditionalOperator.Less;
        ConditionalOperator equalOp = ConditionalOperator.Equal;

        System.out.println(x2 + " = " + x2.getValue());
        System.out.println(x1 + " " + lessOp + " " + x2 + " = " + new ConditionalExpression(x1, x2, lessOp).getValue());
        System.out.println(x1 + " " + equalOp + " " + x2 + " = " + new ConditionalExpression(x1, x2, equalOp).getValue());
        System.out.println(two + "*" + x1 + " " + equalOp + " " + x2 + " = " + new ConditionalExpression(new Multipication(two, x1), x2, equalOp).getValue());
    }

    static void ifExpression() {
        System.out.println("ifExpression()");
        System.out.println("--------------");
        
        Expression x1 = new IntegerLiteral(5);
        Expression x2 = new IntegerVariable("x2", 10);
        ConditionalOperator lessOp = ConditionalOperator.Less;
        ConditionalOperator equalOp = ConditionalOperator.Equal;
        ConditionalExpression lop = new ConditionalExpression(x1, x2, lessOp);
        ConditionalExpression nep = new ConditionalExpression(x1, x2, equalOp);

        System.out.println(x2 + " = " + x2.getValue());

        Expression ifExpression1 = new IfExpression(lop, x1, new Negation(x2));
        System.out.println(ifExpression1 + " --> " + ifExpression1.getValue());

        Expression ifExpression2 = new IfExpression(nep, x1, new Negation(x2));
        System.out.println(ifExpression2 + " --> " + ifExpression2.getValue());

        System.out.println();
    }

}
