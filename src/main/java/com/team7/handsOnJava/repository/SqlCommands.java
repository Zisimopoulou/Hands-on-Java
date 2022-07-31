package com.team7.handsOnJava.repository;
import java.util.Properties;

public class SqlCommands {
    private static final Properties sqlCommands = new Properties();

    private SqlCommands() {
    }

    public static String get(String commandKey) {
        return sqlCommands.getProperty(commandKey);
    }
}
