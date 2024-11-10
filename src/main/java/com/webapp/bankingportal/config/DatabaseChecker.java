package com.webapp.bankingportal.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseChecker {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isDatabaseConnected() {
        try {
            jdbcTemplate.execute("SELECT 1");
            return true;
        } catch (Exception e) {
            System.err.println("Database connection failed: " + e.getMessage());
            return false;
        }
    }
}
