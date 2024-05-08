
public class BooleanLiteral extends Literal {

    public final boolean value;

    BooleanLiteral(boolean value) {
        this.value = value;
    }

    static Literal create(boolean value) {
        return new BooleanLiteral(value);
    }

    Object getValue() {
        return value;
    }

    @Override
    Expression execute() {
        return this;
    }

    @Override
    public String toString() {
        return "" + value;
    }

}
