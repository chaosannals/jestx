package jestx;

import org.junit.Test;
import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

public class HashTest {
    @Test
    public void testAsSha256() throws NoSuchAlgorithmException {
        String a = Hash.asSha256("aaaa");
        String b = Hash.asSha256("bbbb");
        assertEquals(a, Hash.asSha256("aaaa"));
        assertEquals(b, Hash.asSha256("bbbb"));
        assertNotEquals(a, b);
    }
}