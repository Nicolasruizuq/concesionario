package co.edu.uniquindio.poo.Models.motores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import co.edu.uniquindio.poo.Connection.DatabaseConnection;

public class MotorModel {    

    public boolean crearMotor(Motor motor) {
        String sql = "INSERT INTO motor_type (motor, plug_in, status, created_at) " 
                   + "VALUES (?, ?, 1, NOW())";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setString(1, motor.getMotor());

            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public LinkedList<Motor> obtenerMotores() {
        String sql = "SELECT id, motor, plug_in, status FROM motor_type WHERE status = 1 AND deleted_at IS NULL";
        LinkedList<Motor> motores = new LinkedList<>();
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {    
            
            if (!rs.isBeforeFirst()) {
                System.out.println("No se encontraron motores.");
                return motores;
            }    
            
            while (rs.next()) {
                Motor motor = new Motor();
                motor.setId(rs.getInt("id"));               
                motor.setMotor(rs.getString("motor"));
                motor.setPlugIn(rs.getInt("plug_in"));                
                motores.add(motor);    
                
                System.out.println("Tipo de vehículo: " + motor.getId() + ", " + motor.getMotor());
            }
        } catch (SQLException e) {
            e.printStackTrace();            
        }    
        
        System.out.println("Total de motores encontrados: " + motores.size());
        return motores;
    }
    
    

    public Motor obtenerMotorPorId(int id) {
        String sql = "SELECT id, motor, plug_in, status FROM motor_type WHERE id = ? AND status = 1 AND deleted_at IS NULL";
        Motor motor = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    motor = new Motor();
                    motor.setId(rs.getInt("id"));
                    motor.setMotor(rs.getString("motor"));
                    motor.setPlugIn(rs.getInt("plug_in"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return motor;
    }

    public boolean actualizarMotor(Motor motor) {
        String sql = "UPDATE motor_type SET motor = ? WHERE id = ? AND status = 1 AND deleted_at IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, motor.getMotor());

            int filasActualizadas = pstmt.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarMotor(int id) {
        String sql = "UPDATE motor_type SET deleted_at = NOW() WHERE id = ? AND status = 1 AND deleted_at IS NULL";

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
