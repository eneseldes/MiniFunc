
public class IfExpression extends Expression {

    // Result of the expression
    Expression value;

    // Condition and expressions that are going to be returned according to condition
    ConditionalExpression conditionalExpression;
    Expression ifStep;
    Expression elseStep;

    IfExpression(ConditionalExpression conditionalExpression, Expression ifStep, Expression elseStep) {
        this.conditionalExpression = conditionalExpression;
        this.ifStep = ifStep;
        this.elseStep = elseStep;
    }

    // Getting value
    @Override
    Object getValue() {
        execute();
        return value.getValue();
    }

    // Assigning and calculating
    @Override
    Expression execute() {
        // Assign the field 'value' to the expression that are going to be returned
        if ((boolean) conditionalExpression.getValue()) {
            value = ifStep;
            return ifStep.execute();
        } else {
            value = elseStep;
            return elseStep.execute();
        }
    }

    @Override
    public String toString() {
        return "if(" + conditionalExpression.toString() + ") { " + ifStep.toString() + " } else { " + elseStep.toString() + " }";
    }
}
