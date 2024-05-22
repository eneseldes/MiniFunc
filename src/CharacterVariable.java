
public class CharacterVariable extends Variable {

    public char value;

    CharacterVariable(String name) {
        super(name);
        this.value = ' ';
    }

    CharacterVariable(String name, char value) {
        super(name);
        this.value = value;
    }

    static Variable create(String name, char value) {
        return new CharacterVariable(name, value);
    }

    @Override
    Object getValue() {
        return value;
    }

    @Override
    Expression execute() {
        return new CharacterVariable(name);
    }

    @Override
    void assign(Variable var) {
        CharacterVariable strVar = (CharacterVariable) var;
        value = strVar.value;
    }
    
    void assign(char letter){
        value = letter;
    }

    @Override
    public String toString() {
        return name;
    }
}
