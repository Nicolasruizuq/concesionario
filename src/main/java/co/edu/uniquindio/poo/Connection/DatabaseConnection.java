package co.edu.uniquindio.poo.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/concessionaire";
    private static final String USER = "root";
    private static final String PASSWORD = "12345";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void testConnection() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM employee;")) {
             
            System.out.println("La consulta se ejecutó correctamente.");            
            
            while (resultSet.next()) {                
                int id = resultSet.getInt("id"); 
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String cedula = resultSet.getString("id_number");                
                
                System.out.println("ID: " + id + ", Usuario: " + username + ", Contraseña: " + password + " cedula es: " + cedula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en la conexión o en la consulta.");
        }
    }    
}