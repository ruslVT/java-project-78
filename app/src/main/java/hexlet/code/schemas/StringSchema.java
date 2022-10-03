package hexlet.code.schemas;

public class StringSchema extends BaseSchema<StringSchema> {
    private int minLength;
    private String subString;

    public StringSchema() {
        super(String.class);
    }

    public final StringSchema minLength(int length) {
        this.addPredicates("minLength", o -> o instanceof String && ((String) o).length() >= minLength);
        this.minLength = length;
        return this;
    }

    public final StringSchema contains(String str) {
        this.addPredicates("contains", o -> o instanceof String && ((String) o).contains(subString));
        this.subString = str;
        return this;
    }
}
