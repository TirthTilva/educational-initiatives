package com.edtech.classroom.core;

import java.util.Date;
import java.util.logging.*;

public class Log {
    private static final Logger logger = Logger.getLogger("VirtualClassroomLogger");
    static {
        logger.setUseParentHandlers(false);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setFormatter(new SimpleFormatter() {
            private static final String FMT = "[%1$tF %1$tT] %2$s %n";
            @Override
            public synchronized String format(LogRecord lr) {
                return String.format(FMT, new Date(lr.getMillis()), lr.getMessage());
            }
        });
        logger.addHandler(ch);
        logger.setLevel(Level.INFO);
    }
    public static void info(String msg) { logger.info(msg); }
    public static void warn(String msg) { logger.warning(msg); }
    public static void err(String msg) { logger.severe(msg); }
}