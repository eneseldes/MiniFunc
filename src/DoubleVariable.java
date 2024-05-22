
public class DoubleVariable extends Variable {

    public double value;

    DoubleVariable(String name) {
        super(name);
        this.value = 0.0;
    }

    DoubleVariable(String name, double value) {
        super(name);
        this.value = value;
    }

    static Variable create(String name, double value) {
        return new DoubleVariable(name, value);
    }

    @Override
    Object getValue() {
        return value;
    }

    @Override
    Expression execute() {
        return new DoubleLiteral(value);
    }

    @Override
    void assign(Variable var) {
        DoubleVariable doubleVar = (DoubleVariable) var;
        value = doubleVar.value;
    }
    
    void assign(Number number){
        value = number.doubleValue();
    }

    @Override
    public String toString() {
        return name;
    }

}
