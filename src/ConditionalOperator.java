
enum ConditionalOperator {
    Equal,
    NotEqual,
    Less,
    LessEqual,
    Greater,
    GreaterEqual,
    InstanceOf;

    @Override
    public String toString() {
        switch (this) {
            case Equal:
                return "==";
            case NotEqual:
                return "!=";
            case Less:
                return "<";
            case LessEqual:
                return "<=";
            case Greater:
                return ">";
            case GreaterEqual:
                return ">=";
            case InstanceOf:
                return "InstanceOf";
        }

        return super.toString();
    }
}
