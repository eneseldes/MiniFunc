
public abstract class Expression {

    String stringValue() {
        return getValue().toString();
    }

    abstract Object getValue();

    abstract Expression execute();

}
