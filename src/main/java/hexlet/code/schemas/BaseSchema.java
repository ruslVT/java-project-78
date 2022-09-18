package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private Map<String, Predicate<Object>> predicates = new LinkedHashMap<>();

    public final Map<String, Predicate<Object>> getPredicates() {
        return predicates;
    }

    public final void setPredicates(String str, Predicate predicate) {
        this.predicates.put(str, predicate);
    }

    public abstract BaseSchema required();

    public final Boolean isValid(Object obj) {
        if (predicates.isEmpty()) {
            return true;
        }

        for (Map.Entry<String, Predicate<Object>> map : predicates.entrySet()) {
            if (!map.getValue().test(obj)) {
                return false;
            }
        }

        return true;
    }
}
