package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<MapSchema> {

    public MapSchema() {
        super(Map.class);
    }

    public final MapSchema sizeof(int mapSize) {
        this.addPredicates("sizeof", o -> o instanceof Map && ((Map<?, ?>) o).size() == mapSize);
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> map) {
        this.addPredicates("shape", o -> {
            if (o instanceof Map) {
                for (Map.Entry<?, ?> entry : ((Map<?, ?>) o).entrySet()) {
                    if (!map.get(entry.getKey()).isValid(entry.getValue())) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        });
        return this;
    }
}
