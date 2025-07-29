package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class App {
    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        Validator stringValidator = new Validator();
        StringSchema string = stringValidator.string();

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

        var schema1 = stringValidator.string();
        System.out.println(schema1.minLength(10).minLength(4).isValid("hello")); // true




        System.out.println("\nNumber value");

        Validator numberValidator = new Validator();
        NumberSchema number = numberValidator.number();

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

    }
}
