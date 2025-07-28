package hexlet.code;

import hexlet.code.schemas.StringSchema;

public class App {
    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        Validator validator = new Validator();
        StringSchema schema = validator.string();

        System.out.println(schema.isValid("")); // true
        System.out.println(schema.isValid(null)); // true

        schema.required();

        System.out.println(schema.isValid(null)); // false
        System.out.println(schema.isValid("")); // false
        System.out.println(schema.isValid("how are you")); // true
        System.out.println(schema.isValid("hexlet")); // true

        System.out.println(schema.contains("wh").isValid("what does the fox say")); // true
        System.out.println(schema.contains("what").isValid("what is your name")); // true
        System.out.println(schema.contains("whatthe").isValid("very beautiful weather")); // false

        System.out.println(schema.isValid("the bird is singing"));

        var schema1 = validator.string();
        System.out.println(schema1.minLength(10).minLength(4).isValid("hello")); // true
    }
}
