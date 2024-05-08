
public class StringLiteral extends Literal {

    public final String value;

    StringLiteral(String value) {
        this.value = value;
    }

    static Literal create(String value) {
        return new StringLiteral(value);
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
        return value;
    }

}
