
public class IntegerVariable extends Variable {

    public int value;

    IntegerVariable(String name) {
        super(name);
        this.value = 0;
    }

    IntegerVariable(String name, int value) {
        super(name);
        this.value = value;
    }

    static Variable create(String name, int value) {
        return new IntegerVariable(name, value);
    }

    @Override
    Object getValue() {
        return value;
    }

    @Override
    Expression execute() {
        return new IntegerLiteral(value);
    }

    @Override
    void assign(Variable var) {
        IntegerVariable intVar = (IntegerVariable) var;
        value = intVar.value;
    }

    @Override
    public String toString() {
        return name;
    }

}
