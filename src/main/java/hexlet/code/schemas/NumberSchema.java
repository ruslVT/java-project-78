package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {
    private int rangeMin;
    private int rangeMax;

    public NumberSchema() {
    }

    private final Predicate<Object> isRequired = o -> {
        if (o instanceof Integer) {
            return o != null;
        }
        return false;
    };

    private final Predicate<Object> isPositive = o -> {
        if (o == null) {
            return true;
        } else if (o instanceof Integer) {
            return (Integer) o > 0;
        }
        return false;
    };
    private final Predicate<Object> isRange = o -> {
        if (o instanceof Integer) {
            return (Integer) o >= rangeMin && (Integer) o <= rangeMax;
        }
        return false;
    };

    @Override
    public final NumberSchema required() {
        if (!this.getPredicates().containsKey("required")) {
            this.setPredicates("required", isRequired);
        }
        return this;
    }

    public final NumberSchema positive() {
        if (!this.getPredicates().containsKey("positive")) {
            this.setPredicates("positive", isPositive);
        }
        return this;
    }

    public final NumberSchema range(int min, int max) {
        if (!this.getPredicates().containsKey("range")) {
            this.setPredicates("range", isRange);
        }
        this.rangeMin = min;
        this.rangeMax = max;
        return this;
    }
}
