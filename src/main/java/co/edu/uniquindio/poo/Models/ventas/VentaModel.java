package co.edu.uniquindio.poo.Models.ventas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import co.edu.uniquindio.poo.Connection.DatabaseConnection;

public class VentaModel {    

    public boolean crearVenta(Venta venta) {
        String sql = "INSERT INTO sale (id_employee, id_customer, telephone_customer, product, amount, sales_price, total, status, created_at) " 
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, 1, NOW())";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setInt(1, venta.getIdEmployee());                
                pstmt.setInt(2, venta.getIdCustomer());
                pstmt.setString(3, venta.getTelephoneCustomer());
                pstmt.setString(4, venta.getProduct());
                pstmt.setInt(5, venta.getAmount());
                pstmt.setInt(6, venta.getSalesPrice());
                pstmt.setInt(7, venta.getTotal());

            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public LinkedList<Venta> obtenerVentas() {
        String sql = "SELECT id, id_employee, id_customer, telephone_customer, product, amount, sales_price, total, status, created_at FROM sale WHERE status = 1 AND deleted_at IS NULL";
        LinkedList<Venta> ventas = new LinkedList<>();
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {    
            
            if (!rs.isBeforeFirst()) {
                System.out.println("No se encontraron ventas.");
                return ventas;
            }    
            
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setId(rs.getInt("id")); 
                venta.setIdEmployee(rs.getInt("id_employee"));
                venta.setIdCustomer(rs.getInt("id_customer"));
                venta.setTelephoneCustomer(rs.getString("telephone_customer"));
                venta.setProduct(rs.getString("product"));
                venta.setAmount(rs.getInt("amount"));
                venta.setSalesPrice(rs.getInt("sales_price"));
                venta.setTotal(rs.getInt("total"));
                venta.setStatus(rs.getInt("status"));

                ventas.add(venta);    
                
                System.out.println("Venta: " + venta.getId() + ", " + venta.getIdCustomer());
            }
        } catch (SQLException e) {
            e.printStackTrace();            
        }    
        
        System.out.println("Total de ventas encontradas: " + ventas.size());
        return ventas;
    }
       
    public Venta obtenerVentaPorId(int id) {
        String sql = "SELECT id, id_employee, id_customer, telephone_customer, product, amount, sales_price, total, status, created_at FROM sale WHERE id = ? AND status = 1 AND deleted_at IS NULL";
        Venta venta = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    venta = new Venta();
                    venta.setId(rs.getInt("id"));
                    venta.setProduct(rs.getString("product"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return venta;
    }    

    public boolean eliminarVenta(int id) {
        String sql = "UPDATE sale SET deleted_at = NOW() WHERE id = ? AND status = 1 AND deleted_at IS NULL";

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
