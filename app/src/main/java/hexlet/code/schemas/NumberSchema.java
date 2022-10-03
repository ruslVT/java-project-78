package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<NumberSchema> {
    private int rangeMin;
    private int rangeMax;

    public NumberSchema() {
        super(Number.class);
    }

    public final NumberSchema positive() {
        this.addPredicates("positive", o -> (o instanceof Integer && (Integer) o > 0) || o == null);
        return this;
    }

    public final NumberSchema range(int min, int max) {
        this.addPredicates("range", o -> (o instanceof Integer
                && (Integer) o >= rangeMin && (Integer) o <= rangeMax)
                || o == null);
        this.rangeMin = min;
        this.rangeMax = max;

        return this;
    }
}
