
public class StringVariable extends Variable {

    public String value;

    StringVariable(String name) {
        super(name);
        this.value = "";
    }

    StringVariable(String name, String value) {
        super(name);
        this.value = value;
    }

    static Variable create(String name, String value) {
        return new StringVariable(name, value);
    }

    @Override
    Object getValue() {
        return value;
    }

    @Override
    Expression execute() {
        return new StringLiteral(value);
    }

    @Override
    void assign(Variable var) {
        StringVariable strVar = (StringVariable) var;
        value = new String(strVar.value);
    }
    
    void assign(String sentence){
        value = sentence;
    }

    @Override
    public String toString() {
        return name;
    }

}
