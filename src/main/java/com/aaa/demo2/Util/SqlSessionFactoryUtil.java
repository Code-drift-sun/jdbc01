package com.aaa.demo2.Util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class SqlSessionFactoryUtil {
    private static final SqlSessionFactory SQL_SESSION_FACTORY = buildSqlSessionFactory();

    private SqlSessionFactoryUtil() {
    }

    private static SqlSessionFactory buildSqlSessionFactory() {
        try (Reader reader = Resources.getResourceAsReader("config.xml")) {
            return new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return SQL_SESSION_FACTORY;
    }
}
