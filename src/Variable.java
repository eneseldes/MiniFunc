
public abstract class Variable extends Expression {

    public final String name;

    Variable(String name) {
        this.name = name;
    }

    abstract void assign(Variable var);

}
