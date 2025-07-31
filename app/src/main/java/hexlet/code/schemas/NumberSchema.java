package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema required() {
        addCheck("should not be null", Objects::nonNull);
        return this;
    }

    @SuppressWarnings("java:S1126")
    @Override
    public boolean isValid(Integer value) {
        return super.isValid(value);
    }

    public NumberSchema positive() {
        Predicate<Integer> notNull = Objects::isNull;
        Predicate<Integer> greaterThanZero = value -> value > 0;
        addCheck("should be positive", notNull.or(greaterThanZero));
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck("range", value -> value >= min && value <= max);
        return this;
    }
}
