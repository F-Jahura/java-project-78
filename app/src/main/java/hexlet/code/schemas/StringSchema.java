package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {
    public StringSchema required() {
        addCheck("should not be null", s -> s != null && !s.isEmpty());
        return this;
    }

    @SuppressWarnings("java:S1126")
    @Override
    public boolean isValid(String value) {
        return super.isValid(value);
    }

    public StringSchema minLength(int min) {
        Predicate<String> greaterThanMin = value -> value.length() > min;
        addCheck("range", greaterThanMin);
        return this;
    }

    public StringSchema contains(String subString) {
        Predicate<String> contain = text -> text.contains(subString);
        addCheck("shouldContainsText", contain);
        return this;
    }
}
