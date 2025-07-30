package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema<Map<String, String>> {
    private boolean notEmpty = false;
    private boolean shouldBeInSize = false;
    private Map<String, String> data = new HashMap<>();
    private int mapSize = data.size();

    private Map<String, BaseSchema<String>> mapShcemas = new HashMap<>();
    private boolean shouldBeInShape = false;

    public MapSchema required() {
        notEmpty = true;
        return this;
    }

    @Override
    public boolean isValid(Map<String, String> value) {
        if (value == null) {
            return !notEmpty;
        }

        if (shouldBeInSize && value.size() != mapSize) {
            return false;
        }

        if (shouldBeInShape) {
            for (Map.Entry<String, BaseSchema<String>> entry : mapShcemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<String> schema = entry.getValue();

                boolean isValid = value.containsKey(key) && schema.isValid(value.get(key));
                if (!isValid) {
                    return false;
                }
            }
        }

        return true;
    }

    public MapSchema sizeof(int n) {
        this.mapSize = n;
        this.shouldBeInSize = true;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        this.mapShcemas = schemas;
        this.shouldBeInShape = true;
        return this;
    }
}
