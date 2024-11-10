package com.webapp.bankingportal.config;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCheckRunner implements ApplicationRunner {

    private final DatabaseChecker databaseChecker;
    private static final int MAX_RETRIES = 5;  // Adjust retry limit as needed
    private static final int RETRY_DELAY_MS = 2000;  // Delay between retries in milliseconds

    public DatabaseCheckRunner(DatabaseChecker databaseChecker) {
        this.databaseChecker = databaseChecker;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        int attempts = 0;
        while (attempts < MAX_RETRIES) {
            if (databaseChecker.isDatabaseConnected()) {
                System.out.println("Database connection successful. Starting the application...");
                return; // Proceed with application startup
            }
            System.out.println("Database not connected. Retrying...");
            attempts++;
            Thread.sleep(RETRY_DELAY_MS);
        }
        throw new IllegalStateException("Failed to connect to the database after " + MAX_RETRIES + " attempts.");
    }
}
