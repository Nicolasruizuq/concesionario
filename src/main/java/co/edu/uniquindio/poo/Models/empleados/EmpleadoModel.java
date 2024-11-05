package co.edu.uniquindio.poo.Models.empleados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import co.edu.uniquindio.poo.Connection.DatabaseConnection;

public class EmpleadoModel {

    public boolean verificarCredenciales(String username, String password) {
        String sql = "SELECT * FROM employee WHERE username = ? AND password = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean crearEmpleado(Empleado empleado) {
        String sql = "INSERT INTO employee (username, full_name, id_number, gender, email, address, telephone, created_at) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, empleado.getUsername());
            pstmt.setString(2, empleado.getFullName());
            pstmt.setString(3, empleado.getIdNumber());
            pstmt.setString(4, empleado.getGender());
            pstmt.setString(5, empleado.getEmail());
            pstmt.setString(6, empleado.getAddress());
            pstmt.setString(7, empleado.getTelephone());

            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public LinkedList<Empleado> obtenerEmpleados() {
        String sql = "SELECT id, id_user, username, full_name, id_number, gender, email, address, telephone FROM employee WHERE status = 1 AND deleted_at IS NULL";
        LinkedList<Empleado> empleados = new LinkedList<>();
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
    
            // Verificar si hay resultados
            if (!rs.isBeforeFirst()) {
                System.out.println("No se encontraron empleados.");
                return empleados; // Si no hay resultados, retornamos una lista vacía
            }
    
            // Iterar por los resultados
            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("id"));
                empleado.setIdUser(rs.getInt("id_user")); // Asegúrate de tener esta columna en la consulta SQL
                empleado.setUsername(rs.getString("username"));
                empleado.setFullName(rs.getString("full_name"));
                empleado.setIdNumber(rs.getString("id_number"));
                empleado.setGender(rs.getString("gender"));
                empleado.setEmail(rs.getString("email"));
                empleado.setAddress(rs.getString("address"));
                empleado.setTelephone(rs.getString("telephone"));
                empleados.add(empleado);
    
                // Verifica que se recuperaron los datos
                System.out.println("Empleado: " + empleado.getId() + ", " + empleado.getUsername());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepción
        }
    
        // Verifica si la lista contiene elementos
        System.out.println("Total de empleados encontrados: " + empleados.size());
        return empleados;
    }
    
    

    public Empleado obtenerEmpleadoPorId(int id) {
        String sql = "SELECT id, username, full_name, id_number, gender, email, address, telephone FROM employee WHERE id = ? AND status = 1 AND deleted_at IS NULL";
        Empleado empleado = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id); // Establecemos el ID en la consulta

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    empleado = new Empleado();
                    empleado.setId(rs.getInt("id"));
                    empleado.setUsername(rs.getString("username"));
                    empleado.setFullName(rs.getString("full_name"));
                    empleado.setIdNumber(rs.getString("id_number"));
                    empleado.setGender(rs.getString("gender"));
                    empleado.setEmail(rs.getString("email"));
                    empleado.setAddress(rs.getString("address")); // Agrega address
                    empleado.setTelephone(rs.getString("telephone")); // Agrega telephone
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepción
        }

        return empleado;
    }

    public boolean actualizarEmpleado(Empleado empleado) {
        String sql = "UPDATE employee SET username = ?, full_name = ?, id_number = ?, gender = ?, address = ?, telephone = ?, email = ? WHERE id = ? AND status = 1 AND deleted_at IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, empleado.getUsername());
            pstmt.setString(2, empleado.getFullName());
            pstmt.setString(3, empleado.getIdNumber());
            pstmt.setString(4, empleado.getGender());
            pstmt.setString(5, empleado.getAddress());
            pstmt.setString(6, empleado.getTelephone());
            pstmt.setString(7, empleado.getEmail());
            pstmt.setInt(8, empleado.getId()); // Asegúrate de que tengas un método getId en Empleado

            int filasActualizadas = pstmt.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarEmpleado(int id) {
        String sql = "UPDATE employee SET deleted_at = NOW() WHERE id = ? AND status = 1 AND deleted_at IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int filasActualizadas = pstmt.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }  
}
