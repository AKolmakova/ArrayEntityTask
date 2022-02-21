package com.kolmakova.array.config;

import org.apache.log4j.*;

public class ApplicationConfig {
    private static final String LOGGER_CONSOLE_PATTERN = "%d [%p|%c] %m%n";
    private static final String LOGGER_FILE_PATTERN = "%d %-5p [%c{1}] %m%n";
    private static final String LOGGER_FILE_LOCATION = "./logs/app.log";

    public static void initLogger() {
        ConsoleAppender console = new ConsoleAppender();
        console.setLayout(new PatternLayout(LOGGER_CONSOLE_PATTERN));
        console.setThreshold(Level.INFO);
        console.activateOptions();

        FileAppender fileAppender = new FileAppender();
        fileAppender.setName("FileLogger");
        fileAppender.setFile(LOGGER_FILE_LOCATION);
        fileAppender.setLayout(new PatternLayout(LOGGER_FILE_PATTERN));
        fileAppender.setThreshold(Level.DEBUG);
        fileAppender.setAppend(true);
        fileAppender.activateOptions();

        Logger.getRootLogger().addAppender(console);
        Logger.getRootLogger().addAppender(fileAppender);
    }
}
