package co.edu.uniquindio.poo.Models.marcas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import co.edu.uniquindio.poo.Connection.DatabaseConnection;

public class MarcaModel {    

    public boolean crearMarca(Marca marca) {
        String sql = "INSERT INTO brand (brand, status, created_at) " 
                   + "VALUES (?, 1, NOW())";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setString(1, marca.getBrand());

            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public LinkedList<Marca> obtenerMarcas() {
        String sql = "SELECT id, brand FROM brand WHERE status = 1 AND deleted_at IS NULL";
        LinkedList<Marca> marcas = new LinkedList<>();
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {    
            
            if (!rs.isBeforeFirst()) {
                System.out.println("No se encontraron marcas.");
                return marcas;
            }    
            
            while (rs.next()) {
                Marca marca = new Marca();
                marca.setId(rs.getInt("id"));               
                marca.setBrand(rs.getString("brand"));                
                marcas.add(marca);    
                
                System.out.println("Marca: " + marca.getId() + ", " + marca.getBrand());
            }
        } catch (SQLException e) {
            e.printStackTrace();            
        }    
        
        System.out.println("Total de marcas encontrados: " + marcas.size());
        return marcas;
    }
    
    

    public Marca obtenerMarcaPorId(int id) {
        String sql = "SELECT id, brand FROM brand WHERE id = ? AND status = 1 AND deleted_at IS NULL";
        Marca marca = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    marca = new Marca();
                    marca.setId(rs.getInt("id"));
                    marca.setBrand(rs.getString("brand"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return marca;
    }

    public boolean actualizarMarca(Marca marca) {
        String sql = "UPDATE brand SET brand = ? WHERE id = ? AND status = 1 AND deleted_at IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, marca.getBrand());

            int filasActualizadas = pstmt.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarMarca(int id) {
        String sql = "UPDATE brand SET deleted_at = NOW() WHERE id = ? AND status = 1 AND deleted_at IS NULL";

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
