package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestSchema {
    private final Validator validator = new Validator();

    @Test
    public void testMain() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        App.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("true"));
        assertTrue(output.contains("false"));

        System.setOut(originalOut);
    }
    @Test
    void testStringDefaultIsValid() {
        StringSchema schema = validator.string();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid("how are you"));
    }

    @Test
    void testStringRequired() {
        StringSchema schema = validator.string().required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    void testStringMinLength() {
        StringSchema schema = validator.string().minLength(4);
        assertTrue(schema.isValid("hello"));
        assertFalse(schema.isValid("abc"));
    }

    @Test
    void testStringContains() {
        StringSchema schema = validator.string().contains("what");
        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid("the bird is singing"));
        assertFalse(schema.isValid("very beautiful weather"));
    }

    @Test
    void testStringCombinedRules() {
        StringSchema schema = validator.string().required().minLength(4).contains("hex");
        assertFalse(schema.isValid("hex"));
        assertFalse(schema.isValid("not containing words"));
        assertTrue(schema.isValid("hexlet"));
        assertTrue(schema.isValid("longer hex text"));
    }

    @Test
    void testNumberDefaultRules() {
        NumberSchema schema = validator.number();
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(-10));
        assertTrue(schema.isValid(null));
    }

    @Test
    void testNumberPositiveRules() {
        NumberSchema schema = validator.number().positive();
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-5));
        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(0));
    }

    @Test
    void testRequiredRules() {
        NumberSchema schema = validator.number().required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
        assertTrue(schema.isValid(-5));
    }

    @Test
    void testRangeRules() {
        NumberSchema schema = validator.number().range(5, 10);
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testMapDefaultRules() {
        MapSchema schema = validator.map();
        assertTrue(schema.isValid(null));
    }

    @Test
    void testMapRequiredRules() {
        MapSchema schema = validator.map().required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    void testMapSizeRules() {
        MapSchema schema = validator.map();
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));
        schema.sizeof(2);
        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    void testMapShapeRules() {
        MapSchema schema = validator.map().required().sizeof(2);

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("lastName", validator.string().required().minLength(2));
        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));


        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3));
    }
}
