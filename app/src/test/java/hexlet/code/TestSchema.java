package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestSchema {
    @Test
    void testNullEmptyTrue() {
        StringSchema schema = new StringSchema();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
    }

    @Test
    void testNullEmptyFalse() {
        StringSchema schema = new StringSchema();
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
    }

    @Test
    void testLength() {
        StringSchema schema = new StringSchema();
        schema.required();
        assertTrue(schema.minLength(4).isValid("Hexlet"));
        assertFalse(schema.minLength(10).isValid("validator"));
    }

    @Test
    void testContains() {
        StringSchema schema = new StringSchema();
        schema.required();
        assertTrue(schema.contains("what").isValid("what is this"));
        assertFalse(schema.contains("hello").isValid("this is very easy test"));
    }
}
