package co.edu.uniquindio.poo.Models.modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import co.edu.uniquindio.poo.Connection.DatabaseConnection;

public class ModeloModel {

    public boolean crearEmpleado(Modelo modelo) {
        String sql = "INSERT INTO model (id_brand, model, created_at) " 
                   + "VALUES (?, ?, NOW())";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, modelo.getIdBrand());
                pstmt.setString(2, modelo.getModel());
                

            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public LinkedList<Modelo> obtenerModelos() {
        String sql = "SELECT id, id_brand, model FROM model WHERE deleted_at IS NULL";
        LinkedList<Modelo> modelos = new LinkedList<>();
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
    
            // Verificar si hay resultados
            if (!rs.isBeforeFirst()) {
                System.out.println("No se encontraron modelos.");
                return modelos;
            }
    
            // Iterar por los resultados
            while (rs.next()) {
                Modelo modelo = new Modelo();
                modelo.setId(rs.getInt("id"));
                modelo.setIdBrand(rs.getInt("id_brand"));
                modelo.setModel(rs.getString("model"));          
                modelos.add(modelo);    
                
                System.out.println("Modelo: " + modelo.getId() + ", " + modelo.getModel());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            
        System.out.println("Total de modelos encontrados: " + modelos.size());
        return modelos;
    }
    
    public Modelo obtenerModeloPorId(int id) {
        String sql = "SELECT id_brand, model FROM model WHERE id = ? AND deleted_at IS NULL";
        Modelo modelo = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    modelo = new Modelo();
                    modelo.setId(rs.getInt("id"));
                    modelo.setIdBrand(rs.getInt("id_brand"));
                    modelo.setModel(rs.getString("model"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelo;
    }

    public Modelo obtenerModeloPorIdMarca(int id) {
        String sql = "SELECT id, id_brand, model FROM model WHERE id_brand = ? AND deleted_at IS NULL";
        Modelo modelo = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    modelo = new Modelo();
                    modelo.setId(rs.getInt("id"));
                    modelo.setIdBrand(rs.getInt("id_brand"));
                    modelo.setModel(rs.getString("model"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelo;
    }

    public boolean actualizarModelo(Modelo modelo) {
        String sql = "UPDATE model SET id_brand = ?, model = ? WHERE id = ? AND deleted_at IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {            
                
            pstmt.setString(1, modelo.getModel());
            pstmt.setInt(2, modelo.getIdBrand());            

            int filasActualizadas = pstmt.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarModelo(int id) {
        String sql = "UPDATE model SET deleted_at = NOW() WHERE id = ? AND deleted_at IS NULL";

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
