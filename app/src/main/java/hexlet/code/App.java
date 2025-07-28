package hexlet.code;

import hexlet.code.schemas.StringSchema;

public class App {
    public static void main(String[] args) {
        Validator validator = new Validator();
        StringSchema schema = validator.string();

        System.out.println(schema.isValid("")); // true
        System.out.println(schema.isValid(null)); // true

        schema.required();

        System.out.println(schema.isValid(null)); // false
        System.out.println(schema.isValid("")); // false
        System.out.println(schema.isValid("what does the fox say")); // true
        System.out.println(schema.isValid("hexlet")); // true

        System.out.println(schema.contains("wh").isValid("what does the fox say")); // true
        System.out.println(schema.contains("what").isValid("what does the fox say")); // true
        System.out.println(schema.contains("whatthe").isValid("what does the fox say")); // false

        System.out.println(schema.isValid("what does the fox say"));

        var schema1 = validator.string();
        System.out.println(schema1.minLength(10).minLength(4).isValid("Hexlet")); // true
    }
}


      /*System.out.println(sum());

        System.out.println(multiply());

        System.out.println(divide());

        Validate validate =
    }



    public static int sum() {
        return 5 + 3;
    }
    public static int multiply() {
        return 3 * 3;
    }

    public static int divide() {
        return 5 - 3;
    }
}*/
