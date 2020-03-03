package jestx;

import java.io.*;
import java.util.*;
import org.apache.ibatis.session.*;

/**
 * 
 */
public abstract class RDB {
    private static HashMap<String, SqlSessionFactory> factories = new HashMap<>();

    /**
     * 获取工厂
     * 
     * @param name 工厂名
     * @return 工厂单例
     */
    public static SqlSessionFactory getSessionFactory(String name) {
        if (!factories.containsKey(name)) {
            String path = name.replace(".", "/") + ".xml";
            InputStream input = RDB.class.getClassLoader().getResourceAsStream(path);
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(input);
            factory.getConfiguration().setMapUnderscoreToCamelCase(true);
            factories.put(name, factory);
        }
        return factories.get(name);
    }

    /**
     * 打开会话
     * 
     * @param name 工厂名
     * @return
     */
    public static Step<SqlSession> open(String name) {
        SqlSessionFactory factory = getSessionFactory(name);
        SqlSession session = factory.openSession();
        return new Step<>(session);
    }
}