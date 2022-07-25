package com.team7.handsOnJava.repository;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.lang.System.exit;

@Slf4j
public class SqlCommandRepository {
    private static final Properties sqlCommands = new Properties();

    // we only want the following to happen once at the beginning
    static {
        try (InputStream inputStream = SqlCommandRepository.class.getClassLoader().getResourceAsStream(
                "sql.properties")) {
            if (inputStream == null) {
                log.error("Sorry, unable to find sql.properties, exiting application.");
                // Abnormal exit
                exit(-1);
            }

            //load a properties file from class path, inside static method
            sqlCommands.load(inputStream);
        } catch (IOException ex) {
            log.error("Sorry, unable to parse sql.properties, exiting application.", ex);
            // Abnormal exit
            exit(-1);
        }
    }

    private SqlCommandRepository() {
    }

    public static String get(String commandKey) {
        return sqlCommands.getProperty(commandKey);
    }
}
