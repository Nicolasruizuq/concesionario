package co.edu.uniquindio.poo.Models.tipovehiculos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import co.edu.uniquindio.poo.Connection.DatabaseConnection;

public class TipoVehiculoModel {    

    public boolean crearTipoVehiculo(TipoVehiculo tipovehiculo) {
        String sql = "INSERT INTO vehicle_type (type, status, created_at) " 
                   + "VALUES (?, 1, NOW())";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setString(1, tipovehiculo.getType());

            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public LinkedList<TipoVehiculo> obtenerTiposDeVehiculos() {
        String sql = "SELECT id, type, status FROM vehicle_type WHERE status = 1 AND deleted_at IS NULL";
        LinkedList<TipoVehiculo> tiposvehiculos = new LinkedList<>();
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {    
            
            if (!rs.isBeforeFirst()) {
                System.out.println("No se encontraron marcas.");
                return tiposvehiculos;
            }    
            
            while (rs.next()) {
                TipoVehiculo tipovehiculo = new TipoVehiculo();
                tipovehiculo.setId(rs.getInt("id"));               
                tipovehiculo.setType(rs.getString("type"));                
                tiposvehiculos.add(tipovehiculo);    
                
                System.out.println("Tipo de Vehiculo: " + tipovehiculo.getId() + ", " + tipovehiculo.getType());
            }
        } catch (SQLException e) {
            e.printStackTrace();            
        }    
        
        System.out.println("Total de tipos de vehículos encontrados: " + tiposvehiculos.size());
        return tiposvehiculos;
    }
    
    

    public TipoVehiculo obtenerTipoVehiculoPorId(int id) {
        String sql = "SELECT id, type, status FROM vehicle_type WHERE id = ? AND status = 1 AND deleted_at IS NULL";
        TipoVehiculo tipovehiculo = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    tipovehiculo = new TipoVehiculo();
                    tipovehiculo.setId(rs.getInt("id"));
                    tipovehiculo.setType(rs.getString("type"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tipovehiculo;
    }

    public boolean actualizarTipoVehiculo(TipoVehiculo tipovehiculo) {
        String sql = "UPDATE vehicle_type SET type = ? WHERE id = ? AND status = 1 AND deleted_at IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tipovehiculo.getType());

            int filasActualizadas = pstmt.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarTipoVehiculo(int id) {
        String sql = "UPDATE vehicle_type SET deleted_at = NOW() WHERE id = ? AND status = 1 AND deleted_at IS NULL";

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
