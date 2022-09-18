package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema {
    private int minLength;
    private String subString;

    public StringSchema() {
    }

    private final Predicate<Object> isRequired = o -> {
        if (o instanceof String) {
            return o != null && !((String) o).isEmpty();
        }
        return false;
    };

    private final Predicate<Object> isMinLength = o -> {
        if (o instanceof String) {
            return ((String) o).length() >= minLength;
        }
        return false;
    };

    private final Predicate<Object> isContains = o -> {
        if (o instanceof String) {
            return ((String) o).contains(subString);
        }
        return false;
    };

    @Override
    public final StringSchema required() {
        if (!this.getPredicates().containsKey("required")) {
            this.setPredicates("required", isRequired);
        }
        return this;
    }

    public final StringSchema minLength(int length) {
        if (!this.getPredicates().containsKey("minLength")) {
            this.setPredicates("minLength", isMinLength);
        }
        this.minLength = length;
        return this;
    }

    public final StringSchema contains(String str) {
        if (!this.getPredicates().containsKey("contains")) {
            this.setPredicates("contains", isContains);
        }
        this.subString = str;
        return this;
    }
}
