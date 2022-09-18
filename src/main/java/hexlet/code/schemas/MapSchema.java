package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {
    private int size;
    private Map<String, BaseSchema> checkShapeMap = new HashMap<>();

    public MapSchema() {
    }

    private final Predicate<Object> isRequired = o -> {
        if (o instanceof Map) {
            return o != null;
        }
        return false;
    };

    private final Predicate<Object> isSizeof = o -> {
        if (o instanceof Map) {
            return ((Map<?, ?>) o).size() == this.size;
        }
        return false;
    };

    private Predicate<Object> isShape = o -> {
        if (o instanceof Map) {
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) o).entrySet()) {
                if (!checkShapeMap.get(entry.getKey()).isValid(entry.getValue())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    };

    @Override
    public final MapSchema required() {
        if (!this.getPredicates().containsKey("required")) {
            this.setPredicates("required", isRequired);
        }
        return this;
    }

    public final MapSchema sizeof(int mapSize) {
        if (!this.getPredicates().containsKey("sizeof")) {
            this.setPredicates("sizeof", isSizeof);
        }
        this.size = mapSize;
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> map) {
        if (!this.getPredicates().containsKey("shape")) {
            this.setPredicates("shape", isShape);
        }
        checkShapeMap = map;
        return this;
    }
}
