package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema<T> extends BaseSchema<Map<String, T>> {
    public MapSchema<T> required() {
        addCheck("empty", Objects::nonNull);
        return this;
    }

    public MapSchema<T> sizeof(int size) {
        Predicate<Map<String, T>> mapSize = value -> value.size() == size;
        addCheck("size", mapSize);
        return this;
    }


    public MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addCheck("shape", map -> {
            return schemas.entrySet().stream().allMatch(entry -> {
                String key = entry.getKey();
                BaseSchema<T> schema = entry.getValue();
                T s = map.get(key);
                return s != null && schema.isValid(s);
            });
        });
        return this;

    }
}
