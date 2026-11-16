package com.example.megasoft;

import org.h2.tools.RunScript;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

public class DatabaseInitService {
    public static void main(String[] args) {
        Path file = Path.of("sql", "init_db.sql");

        try (Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            RunScript.execute(Database.getInstance().getConnection(), reader);
            System.out.println("Database tables created successfully.");
        } catch (IOException | SQLException e) {
            throw new DatabaseException("Failed to initialize database", e);
        }
    }
}
