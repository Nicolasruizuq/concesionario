package co.edu.uniquindio.poo.Models.rentas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import co.edu.uniquindio.poo.Connection.DatabaseConnection;

public class RentaModel {
    public boolean crearRenta(Renta renta) {
        String sql = "INSERT INTO rented (id_employee, id_customer, vehicle, num_days, value_per_day, total, status, created_at) " 
                   + "VALUES (?, ?, ?, ?, ?, ?,  1, NOW())";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, renta.getIdEmployee());
                pstmt.setInt(2, renta.getIdCustomer());
                pstmt.setString(3, renta.getvehicle());
                pstmt.setInt(4, renta.getNumDays());
                pstmt.setInt(5, renta.getValuePerDay());
                pstmt.setInt(6, renta.getTotal());

            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public LinkedList<Renta> obtenerRentas() {
        String sql = "SELECT id, id_employee, id_customer, vehicle, num_days, value_per_day, total, status, created_at FROM rented WHERE status = 1 AND deleted_at IS NULL";
        LinkedList<Renta> rentas = new LinkedList<>();
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
    
            if (!rs.isBeforeFirst()) {
                System.out.println("No se encontraron vehiculos rentados.");
                return rentas;
            }
    
            while (rs.next()) {
                Renta renta = new Renta();
                renta.setId(rs.getInt("id"));
                renta.setIdEmployee(rs.getInt("id_employee"));
                renta.setIdCustomer(rs.getInt("id_customer"));
                renta.setVehicle(rs.getString("vehicle"));
                renta.setNumDays(rs.getInt("num_days"));
                renta.setValuePerDay(rs.getInt("value_per_day"));
                renta.setTotal(rs.getInt("total"));
                renta.setStatus(rs.getInt("status"));
                rentas.add(renta);
    
                System.out.println("Renta: " + renta.getId() + ", " + ", " + renta.getvehicle());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        System.out.println("Total de rentas de vehículos encontrados: " + rentas.size());
        return rentas;
    }   

    public Renta obtenerRentaPorId(int id) {
        String sql = "SELECT id, id, id_employee, id_customer, vehicle, num_days, value_per_day, total, status, created_at FROM rented WHERE id = ? AND status = 1 AND deleted_at IS NULL";
        Renta renta = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id); // Establecemos el ID en la consulta

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    renta = new Renta();
                    renta.setId(rs.getInt("id"));
                    renta.setIdEmployee(rs.getInt("id_employee"));
                    renta.setIdCustomer(rs.getInt("id_customer"));
                    renta.setNumDays(rs.getInt("vehicle"));
                    renta.setNumDays(rs.getInt("num_days"));
                    renta.setValuePerDay(rs.getInt("value_per_day"));
                    renta.setTotal(rs.getInt("total"));
                    renta.setStatus(rs.getInt("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return renta;
    }

    public LinkedList<Renta> obtenerRentasPorEmpleado() {
        String sql = "SELECT id, id_employee, id_customer, vehicle, num_days, value_per_day, total, status, created_at FROM rented WHERE status = 1 AND deleted_at IS NULL";
        LinkedList<Renta> rentas = new LinkedList<>();
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
    
            if (!rs.isBeforeFirst()) {
                System.out.println("No se encontraron vehiculos rentados.");
                return rentas;
            }
    
            while (rs.next()) {
                Renta renta = new Renta();
                renta.setId(rs.getInt("id"));
                renta.setIdEmployee(rs.getInt("id_employee"));
                renta.setIdCustomer(rs.getInt("id_customer"));
                renta.setVehicle(rs.getString("vehicle"));
                renta.setNumDays(rs.getInt("num_days"));
                renta.setValuePerDay(rs.getInt("value_per_day"));
                renta.setTotal(rs.getInt("total"));
                renta.setStatus(rs.getInt("status"));
                rentas.add(renta);
    
                System.out.println("Renta: " + renta.getId() + ", " + ", " + renta.getvehicle());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        System.out.println("Total de rentas de vehículos encontrados: " + rentas.size());
        return rentas;
    }

    public boolean actualizarRenta(Renta renta) {
        String sql = "UPDATE rented SET id_employee = ?, id_customer = ?, vehicle = ?, num_days = ?, value_per_day = ?, total = ? WHERE id = ? AND status = 1 AND deleted_at IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, renta.getIdEmployee());
            pstmt.setInt(2, renta.getIdCustomer());
            pstmt.setString(4, renta.getvehicle());
            pstmt.setInt(5, renta.getNumDays());
            pstmt.setInt(6, renta.getValuePerDay());
            pstmt.setInt(7, renta.getTotal());

            int filasActualizadas = pstmt.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarRenta(int id) {
        String sql = "UPDATE rented SET deleted_at = NOW() WHERE id = ? AND status = 1 AND deleted_at IS NULL";

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
