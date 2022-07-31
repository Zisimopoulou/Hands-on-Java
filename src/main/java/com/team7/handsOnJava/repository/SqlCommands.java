package com.team7.handsOnJava.repository;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import static java.lang.System.exit;
@Slf4j
public class SqlCommands {
    private static final Properties sqlCommands = new Properties();

    static {
        try (InputStream inputStream = SqlCommands.class.getClassLoader().getResourceAsStream(
                "sql.properties")) {
            if (inputStream == null) {
                log.error("Sorry, unable to find sql.properties, exiting application.");
                exit(-1);
            }

            sqlCommands.load(inputStream);
        } catch (IOException ex) {
            log.error("Sorry, unable to parse sql.properties, exiting application.", ex);
            exit(-1);
        }
    }

    private SqlCommands() {
    }

    public static String get(String commandKey) {
        return sqlCommands.getProperty(commandKey);
    }
}
