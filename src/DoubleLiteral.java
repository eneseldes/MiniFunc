
public class DoubleLiteral extends Literal {

    public final double value;

    DoubleLiteral(double value) {
        this.value = value;
    }

    static Literal create(double value) {
        return new DoubleLiteral(value);
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
