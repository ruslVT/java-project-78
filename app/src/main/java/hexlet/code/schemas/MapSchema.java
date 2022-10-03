package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema<MapSchema> {
    private int size;
    private Map<String, BaseSchema> checkShapeMap = new HashMap<>();

    public MapSchema() {
        super(Map.class);
    }

    public final MapSchema sizeof(int mapSize) {
        this.addPredicates("sizeof", o -> o instanceof Map && ((Map<?, ?>) o).size() == this.size);
        this.size = mapSize;
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> map) {
        this.addPredicates("shape", o -> {
            if (o instanceof Map) {
                for (Map.Entry<?, ?> entry : ((Map<?, ?>) o).entrySet()) {
                    if (!checkShapeMap.get(entry.getKey()).isValid(entry.getValue())) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        });

        checkShapeMap = map;
        return this;
    }
}
