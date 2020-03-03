package jestx;

import java.util.*;

/**
 * 密码生成
 * 
 */
public abstract class Password {
    private final static char[] CHAR_SET = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9', '!', '~', '^', '_' };

    /**
     * 随机密码
     * 
     * @param length 密码长度
     * @return 密码
     */
    public static String make(int length) {
        char[] buffer = new char[length];
        Random random = new Random();
        for (int i = 0; i < length; ++i) {
            int index = random.nextInt(CHAR_SET.length);
            buffer[i] = CHAR_SET[index];
        }
        return new String(buffer);
    }
}