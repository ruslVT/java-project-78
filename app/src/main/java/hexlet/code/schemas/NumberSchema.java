package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<NumberSchema> {

    public NumberSchema() {
        super(Number.class);
    }

    public final NumberSchema positive() {
        this.addPredicates("positive", o -> (o instanceof Integer && (Integer) o > 0) || o == null);
        return this;
    }

    public final NumberSchema range(int min, int max) {
        this.addPredicates("range", o -> (o instanceof Integer
                && (Integer) o >= min && (Integer) o <= max)
                || o == null);

        return this;
    }
}
