package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T extends BaseSchema<T>> {
    private final Class<?> classType;
    private final Map<String, Predicate<Object>> predicates = new LinkedHashMap<>();

    protected BaseSchema(Class<?> type) {
        this.classType = type;
    }

    public final void addPredicates(String str, Predicate<Object> predicate) {
        this.predicates.put(str, predicate);
    }

    public final T required() {
        this.addPredicates("required", o -> classType.isInstance(o) && !o.equals(""));
        return (T)  this;
    }

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
