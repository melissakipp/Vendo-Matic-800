package com.techelevator.controller.utils;

import java.io.IOException;
import java.util.logging.*;

/*
* SEVERE
* WARNING
* INFO
* CONFIG
* FINE
* FINER
* FINEST
*
* logs.log(Level.INFO, "My first log");
* logs.info("My first log.");
*
* */

/*
* Javadoc
* cmd line: javadoc -d doc src/*
* */

/**
 * LogVM - A logger to catch and log activity in the Vending Machine program.
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/logging/Logger.html">...</a>
 * <a href="https://docs.oracle.com/javase/8/docs/api/java/util/logging/FileHandler.html">...</a>
 * @see com.techelevator.VendingMachineCLI
 *
 */
public class LogVM {

    private final static Logger logs = Logger.getLogger(LogVM.class.getName());

    public static void setupLogger() {
        LogManager.getLogManager().reset();
        logs.setLevel(Level.ALL);

        try {
            FileHandler fileHandler = new FileHandler("./logs/logger.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logs.addHandler(fileHandler);

        } catch (IOException e) {
            logs.log(Level.SEVERE, "File logger not working.", e);
        }
    }

}
/*

// Simulate VendingMachineCLI
class LogVMTestDrive {

    private final static Logger logs = Logger.getLogger(LogVM.class.getName());

    public static void main(String[] args) {

        LogVM.setupLogger();

        logs.info("My first log.");
        logs.fine("My second log.");

        AnotherClass.testMethod();

    }
}

// Simulation
class AnotherClass {
    private final static Logger logs = Logger.getLogger(LogVM.class.getName());

    static void testMethod() {
        logs.info("I'm from another class!!!");
    }

}
*/

