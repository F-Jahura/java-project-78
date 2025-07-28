package hexlet.code.schemas;

public class StringSchema extends Schema {
    private boolean notEmpty = false;
    private int min;
    private String text;
    public StringSchema required() {
        notEmpty = true;
        return this;
    }

    @Override
    public boolean isValid(String value) {
        if (value == null) {
            return !notEmpty;
        }

        if (notEmpty && value.isEmpty()) {
            return false;
        }

        if (value.length() < min) {
            return false;
        }

        if (text != null && !value.contains(text)) {
            return false;
        }
        return true;
    }

    public StringSchema minLength(int n) {
        this.min = n;
        return this;
    }

    public StringSchema contains(String subString) {
        this.text = subString;
        return this;
    }
}
