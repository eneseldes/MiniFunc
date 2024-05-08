
public class IntegerLiteral extends Literal {

    public final int value;

    IntegerLiteral(int value) {
        this.value = value;
    }

    static Literal create(int value) {
        return new IntegerLiteral(value);
    }

    @Override
    Expression execute() {
        return this;
    }

    Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

}
