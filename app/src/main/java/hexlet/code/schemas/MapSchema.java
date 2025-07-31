package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public final class MapSchema<T> extends BaseSchema<Map<String, T>> {
    public MapSchema<T> required() {
        addCheck("should not be null", Objects::nonNull);
        return this;
    }

    @Override
    public boolean isValid(Map<String, T> value) {
        return super.isValid(value);
    }

    public MapSchema<T> sizeof(int size) {
        Predicate<Map<String, T>> mapSize = value -> value.size() == size;
        addCheck("size must be equal to specified value", mapSize);
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
