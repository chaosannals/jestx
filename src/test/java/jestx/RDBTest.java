package jestx;

import org.junit.Test;

import jestx.rdb.mapper.*;

import static org.junit.Assert.*;

public class RDBTest {
    @Test
    public void testQuery() {
        RDB.open("rdb.jestx").then(session -> {
            assertNotNull(session);
            TesterMapper mapper = session.getMapper(TesterMapper.class);
            mapper.create();
            session.close();
        });
    }

    @Test
    public void testDrop() {
        RDB.open("rdb.jestx").then(session -> {
            assertNotNull(session);
            TesterMapper mapper = session.getMapper(TesterMapper.class);
            mapper.drop();
            session.close();
        });
    }
}