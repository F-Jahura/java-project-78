package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {
    public StringSchema required() {
        addCheck("empty", s -> s != null && !s.isEmpty());
        return this;
    }

    public StringSchema minLength(int min) {
        Predicate<String> greaterThanMin = value -> value.length() > min;
        addCheck("range", greaterThanMin);
        return this;
    }

    public StringSchema contains(String subString) {
        Predicate<String> contain = text -> text.contains(subString);
        addCheck("contains", contain);
        return this;
    }
}
