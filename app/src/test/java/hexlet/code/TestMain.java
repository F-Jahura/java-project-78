package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMain {
    @Test
    void sumTest() {
        int expected = 8;
        int actual = App.sum();
        assertEquals(expected, actual);
    }

    @Test
    void multiplyTest() {
        int expected = 9;
        int actual = App.multiply();
        assertEquals(expected, actual);
    }

    @Test
    void divideTest() {
        int expected = 2;
        int actual = App.divide();
        assertEquals(expected, actual);
    }
}
