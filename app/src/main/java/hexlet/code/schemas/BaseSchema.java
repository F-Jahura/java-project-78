package hexlet.code.schemas;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();

    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

    public boolean isValid(T value) {
        Collection<Predicate<T>> values = checks.values();
        return values.stream().allMatch(p -> p.test(value));
    }
}
