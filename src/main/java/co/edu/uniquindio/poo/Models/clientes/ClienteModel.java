package co.edu.uniquindio.poo.Models.clientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import co.edu.uniquindio.poo.Connection.DatabaseConnection;
import co.edu.uniquindio.poo.Models.empleados.Empleado;

public class ClienteModel {
    public boolean crearCliente(Cliente cliente) {
        String sql = "INSERT INTO customer (full_name, id_number, gender, address, telephone, status, created_at) " 
                   + "VALUES (?, ?, ?, ?, ?, 1, NOW())";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, cliente.getFullname());
                pstmt.setString(2, cliente.getIdNumber());
                pstmt.setString(3, cliente.getGender());
                pstmt.setString(4, cliente.getAddress());
                pstmt.setString(5, cliente.getTelephone());

            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public LinkedList<Cliente> obtenerClientes() {
        String sql = "SELECT id, full_name, id_number, gender, address, telephone FROM customer WHERE status = 1 AND deleted_at IS NULL";
        LinkedList<Cliente> clientes = new LinkedList<>();
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
    
            if (!rs.isBeforeFirst()) {
                System.out.println("No se encontraron clientes.");
                return clientes;
            }
    
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setFullname(rs.getString("full_name"));
                cliente.setIdNumber(rs.getString("id_number"));
                cliente.setGender(rs.getString("gender"));
                cliente.setAddress(rs.getString("address"));
                cliente.setTelephone(rs.getString("telephone"));
                clientes.add(cliente);
    
                System.out.println("Cliente: " + cliente.getId() + ", ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        System.out.println("Total de clientes encontrados: " + clientes.size());
        return clientes;
    }
    
    

    public Cliente obtenerClientePorId(int id) {
        String sql = "SELECT id, full_name, id_number, gender, address, telephone FROM customer WHERE id = ? AND status = 1 AND deleted_at IS NULL";
        Cliente cliente = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id); // Establecemos el ID en la consulta

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setId(rs.getInt("id"));
                    cliente.setFullname(rs.getString("full_name"));
                    cliente.setIdNumber(rs.getString("id_number"));
                    cliente.setGender(rs.getString("gender"));
                    cliente.setAddress(rs.getString("address"));
                    cliente.setTelephone(rs.getString("telephone"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepción
        }

        return cliente;
    }

    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE customer SET full_name = ?, id_number = ?, gender = ?, address = ?, telephone = ? WHERE id = ? AND status = 1 AND deleted_at IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getFullname());
            pstmt.setString(2, cliente.getIdNumber());
            pstmt.setString(3, cliente.getGender());
            pstmt.setString(4, cliente.getAddress());
            pstmt.setString(5, cliente.getTelephone());            
            pstmt.setInt(6, cliente.getId());

            int filasActualizadas = pstmt.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarCliente(int id) {
        String sql = "UPDATE customer SET deleted_at = NOW() WHERE id = ? AND status = 1 AND deleted_at IS NULL";

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
