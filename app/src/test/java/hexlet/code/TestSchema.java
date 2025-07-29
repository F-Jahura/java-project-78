package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestSchema {
    @Test
    void testStringDefaultIsValid() {
        StringSchema schema = new StringSchema();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid("how are you"));
    }

    @Test
    void testStringRequired() {
        StringSchema schema = new StringSchema().required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    void testStringMinLength() {
        StringSchema schema = new StringSchema().minLength(4);
        assertTrue(schema.isValid("hello"));
        assertFalse(schema.isValid("abc"));
    }

    @Test
    void testStringContains() {
        StringSchema schema = new StringSchema().contains("what");
        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid("the bird is singing"));
        assertFalse(schema.isValid("very beautiful weather"));
    }

    @Test
    void testStringCombinedRules() {
        StringSchema schema = new StringSchema().required().minLength(4).contains("hex");
        assertFalse(schema.isValid("hex"));
        assertFalse(schema.isValid("not containing words"));
        assertTrue(schema.isValid("hexlet"));
        assertTrue(schema.isValid("longer hex text"));
    }

    @Test
    void testNumberDefaultRules() {
        NumberSchema schema = new NumberSchema();
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(-10));
        assertTrue(schema.isValid(null));
    }

    @Test
    void testNumberPositiveRules() {
        NumberSchema schema = new NumberSchema().positive();
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-5));
        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(0));
    }

    @Test
    void testRequiredRules() {
        NumberSchema schema = new NumberSchema().required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
        assertTrue(schema.isValid(-5));
    }

    @Test
    void testRangeRules() {
        NumberSchema schema = new NumberSchema().range(5, 10);
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }
}
