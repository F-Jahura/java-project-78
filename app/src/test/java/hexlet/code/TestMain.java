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
}
