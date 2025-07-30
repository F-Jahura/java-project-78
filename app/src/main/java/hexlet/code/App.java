package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

import java.util.HashMap;
import java.util.Map;

public class App {
    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        Validator validator = new Validator();
        StringSchema string = validator.string();

        System.out.println(string.isValid("")); // true
        System.out.println(string.isValid(null)); // true

        string.required();

        System.out.println(string.isValid(null)); // false
        System.out.println(string.isValid("")); // false
        System.out.println(string.isValid("how are you")); // true
        System.out.println(string.isValid("hexlet")); // true

        System.out.println(string.contains("wh").isValid("what does the fox say")); // true
        System.out.println(string.contains("what").isValid("what is your name")); // true
        System.out.println(string.contains("whatthe").isValid("very beautiful weather")); // false

        System.out.println(string.isValid("the bird is singing")); //false

        var schema1 = validator.string();
        System.out.println(schema1.minLength(10).minLength(4).isValid("hello")); // true




        System.out.println("\nNumber value");

        NumberSchema number = validator.number();

        System.out.println(number.isValid(5)); // true
        System.out.println(number.isValid(-10)); // true
        System.out.println(number.isValid(null)); // true

        System.out.println(number.positive().isValid(null)); // true
        System.out.println(number.required().isValid(null)); // false


        System.out.println(number.isValid(10)); // true
        System.out.println(number.isValid(-5)); // false
        System.out.println(number.isValid(0)); // false

        System.out.println(number.range(5, 10).isValid(5)); // true
        System.out.println(number.isValid(10)); // true
        System.out.println(number.isValid(4)); // false
        System.out.println(number.isValid(11)); // false



        System.out.println("\nMap value");

        MapSchema map = validator.map();

        System.out.println(map.isValid(null)); // true

        map.required();

        System.out.println(map.isValid(null)); // false
        System.out.println(map.isValid(new HashMap<>())); // true

        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        System.out.println(map.isValid(data)); // true

        map.sizeof(2);

        System.out.println(map.isValid(data));  // false
        data.put("key2", "value2");
        System.out.println(map.isValid(data)); // true


        System.out.println("\ncheck shape");

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));
        map.shape(schemas);


        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        System.out.println(map.isValid(human1)); // true

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        System.out.println(map.isValid(human2)); // false

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        System.out.println(map.isValid(human3)); // false

    }
}
