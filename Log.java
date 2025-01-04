package controller;

import java.io.FileWriter;
import java.io.IOException;

public class Log {
    private static Log instance; // Singleton instance
    private StringBuilder logBuffer = new StringBuilder(); // Stores log events

    // Private constructor
    private Log() {}

    // Method to get the Singleton instance
    public static synchronized Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    // Method to log events
    public void logEvent(String event) {
        logBuffer.append(event).append("\n");
    }

    // Method to write log to a file
    public void writeToFile(String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(logBuffer.toString());
        }
    }
}
