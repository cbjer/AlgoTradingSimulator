package com.company;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SimpleLogger {
    private String logName;
    private Logger logger;
    private FileHandler fileHandler;
    private SimpleFormatter simpleFormatter;

    private static final String LOG_SUFFIX = ".log";

    SimpleLogger(String logName) {
        this.logName = logName;
        this.logger = Logger.getLogger(logName);
        initialiseLoggerParameters();
    }

    public void log(String logString) {
        this.logger.info(logString);
    }

    private void initialiseLoggerParameters() {
        try {
            this.fileHandler = new FileHandler(this.logName + LOG_SUFFIX);
            this.logger.addHandler(fileHandler);
            this.simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(this.simpleFormatter);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}