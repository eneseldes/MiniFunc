
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
        try {
            execute();
            return value.getValue();
        }
        catch(Exception e){
            System.out.println("Null condition statement in " + getClass().getName() +  ", results may be incorrect!");
            return null;
        }
    }

    // Assigning and calculating
    @Override
    Expression execute() {
        // Assign the field 'value' to the expression that are going to be returned
        if ((boolean) conditionalExpression.getValue()) {
            value = ifStep;
        } else {
            value = elseStep;
        }

        return value.execute();
    }

    @Override
    public String toString() {
        try{
            return "if(" + conditionalExpression.toString() + ") { " + ifStep.toString() + " } else { " + elseStep.toString() + " }";
        }
        catch(Exception e){
            return "**Inexpressible " + getClass().getName() + " result**";
        }
    }
}
