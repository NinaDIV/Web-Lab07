/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author dev
 */
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/lab07";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "MySQL JDBC driver not found", e);
            throw new IllegalStateException("MySQL JDBC driver not found", e);
        }
    }
 public static Connection getConnection() throws SQLException {
    try {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    } catch (SQLException e) {
        // Loguea el error en el logger
        logger.log(Level.SEVERE, "Failed to establish a database connection", e);
        throw e; // Vuelve a lanzar la excepción
    }
}


    public static void insertProduct(String name, double price) {
        String query = "INSERT INTO products (name, price) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.executeUpdate();
            logger.log(Level.INFO, "Product inserted successfully: {0}", name);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error inserting product", e);
        }
    }
}
