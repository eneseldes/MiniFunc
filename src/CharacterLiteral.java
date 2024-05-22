
public class CharacterLiteral extends Literal {

    public final char value;

    CharacterLiteral(char value) {
        this.value = value;
    }

    static Literal create(char value) {
        return new CharacterLiteral(value);
    }

    @Override
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
