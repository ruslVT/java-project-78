package hexlet.code.schemas;

public class StringSchema extends BaseSchema<StringSchema> {

    public StringSchema() {
        super(String.class);
    }

    public final StringSchema minLength(int length) {
        this.addPredicates("minLength", o -> o instanceof String && ((String) o).length() >= length);
        return this;
    }

    public final StringSchema contains(String str) {
        this.addPredicates("contains", o -> o instanceof String && ((String) o).contains(str));
        return this;
    }
}
