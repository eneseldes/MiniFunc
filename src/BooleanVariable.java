
public class BooleanVariable extends Variable {

    public boolean value;

    BooleanVariable(String name) {
        super(name);
        this.value = false;
    }

    BooleanVariable(String name, boolean value) {
        super(name);
        this.value = value;
    }

    static Variable create(String name, boolean value) {
        return new BooleanVariable(name, value);
    }

    Object getValue() {
        return value;
    }

    @Override
    Expression execute() {
        return new BooleanLiteral(value);
    }

    @Override
    void assign(Variable var) {
        BooleanVariable booleanVar = (BooleanVariable) var;
        value = booleanVar.value;
    }

    @Override
    public String toString() {
        return name;
    }

}
