package jestx;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class StepTest {
    @Test
    public void testThen() {
        Random r = new Random();
        int p1 = r.nextInt(10000);
        int p2 = r.nextInt(10000);
        int p3 = r.nextInt(10000);
        int result = new Step<>(p1).then(i -> i + p2).then(i -> i * p3).peek();
        assertEquals(result, (p1 + p2) * p3);
    }
}