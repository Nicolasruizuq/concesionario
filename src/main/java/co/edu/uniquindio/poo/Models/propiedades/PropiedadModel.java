package co.edu.uniquindio.poo.Models.propiedades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import co.edu.uniquindio.poo.Connection.DatabaseConnection;

public class PropiedadModel {    

    public boolean crearPropiedad(Propiedad propiedad) {
        String sql = "INSERT INTO property (id_motor_type, color, cilindraje, num_doors, num_passengers, cambios, num_airbags, num_axles, num_emergency_exits, horsepower, maximum_speed, trunk_capacity, time_100_km, load_capacity, type_truck, air_conditioning, reverse_camera, cruising_speed, abs_brakes, air_brakes, collision_sensor, cross_traffic_sensor, cuatro_x_cuatro, lane_keeping_assist, transmition_type, status, created_at) " 
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1, NOW())";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setInt(1, propiedad.getIdMotorType());
                pstmt.setString(2, propiedad.getColor());
                pstmt.setString(3, propiedad.getCilindraje());
                pstmt.setInt(4, propiedad.getNumDoors());
                pstmt.setInt(5, propiedad.getnumPassengers());
                pstmt.setInt(6, propiedad.getCambios());
                pstmt.setInt(7, propiedad.getNumAirbags());
                pstmt.setInt(8, propiedad.getNumAxles());
                pstmt.setInt(9, propiedad.getNumEmergencyExits());
                pstmt.setString(10, propiedad.getHorsePower());
                pstmt.setString(11, propiedad.getMaximumSpeed());
                pstmt.setInt(12, propiedad.getTrunkCapacity());
                pstmt.setString(13, propiedad.getTime100Km());
                pstmt.setString(14, propiedad.getLoadCapacity());
                pstmt.setString(15, propiedad.getTypeTruck());
                pstmt.setInt(16, propiedad.getAirConditioning());
                pstmt.setInt(17, propiedad.getReverseCamera());
                pstmt.setInt(18, propiedad.getCruisingSpeed());
                pstmt.setInt(19, propiedad.getAbsBrakes());
                pstmt.setInt(20, propiedad.getAirBrakes());
                pstmt.setInt(21, propiedad.getCollisionSensor());
                pstmt.setInt(22, propiedad.getCrossTrafficSensor());
                pstmt.setInt(23, propiedad.getCuatroXcuatro());
                pstmt.setInt(24, propiedad.getLaneKeepingAssist());
                pstmt.setInt(25, propiedad.getTipoTransmision());

                int filasInsertadas = pstmt.executeUpdate();
                return filasInsertadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public LinkedList<Propiedad> obtenerPropiedades() {
        String sql = "SELECT id, id_motor_type, color, cilindraje, num_doors, num_passengers, cambios, num_airbags, num_axles, num_emergency_exits, horsepower, maximum_speed, trunk_capacity, time_100_km, load_capacity, type_truck, air_conditioning, reverse_camera, cruising_speed, abs_brakes, air_brakes, collision_sensor, cross_traffic_sensor, cuatro_x_cuatro, lane_keeping_assist, transmition_type, status, FROM property WHERE status = 1 AND deleted_at IS NULL";
        LinkedList<Propiedad> propiedades = new LinkedList<>();
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {    
            
            if (!rs.isBeforeFirst()) {
                System.out.println("No se encontraron propiedades.");
                return propiedades;
            }    
            
            while (rs.next()) {
                Propiedad propiedad = new Propiedad();
                propiedad.setId(rs.getInt("id"));               
                propiedad.setIdMotorType(rs.getInt("id_motor_type"));
                propiedad.setCilindraje(rs.getString("cilindraje"));          
                propiedades.add(propiedad);    
                
                System.out.println("Propiedad: " + propiedad.getId() + ", " + propiedad.getCilindraje());
            }
        } catch (SQLException e) {
            e.printStackTrace();            
        }    
        
        System.out.println("Total de propiedades encontrados: " + propiedades.size());
        return propiedades;
    }
    
    

    public Propiedad obtenerPropiedadPorId(int id) {
        String sql = "SELECT id, id_motor_type, color, cilindraje, num_doors, num_passengers, cambios, num_airbags, num_axles, num_emergency_exits, horsepower, maximum_speed, trunk_capacity, time_100_km, load_capacity, type_truck, air_conditioning, reverse_camera, cruising_speed, abs_brakes, air_brakes, collision_sensor, cross_traffic_sensor, cuatro_x_cuatro, lane_keeping_assist, transmition_type, status, FROM property WHERE id = ? AND status = 1 AND deleted_at IS NULL";
        Propiedad propiedad = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    propiedad = new Propiedad();
                    propiedad.setId(rs.getInt("id"));
                    propiedad.setIdMotorType(rs.getInt("id_motor_type"));
                    propiedad.setColor(rs.getString("color"));
                    propiedad.setCilindraje(rs.getString("cilindraje"));
                    propiedad.setNumDoors(rs.getInt("num_doors"));
                    propiedad.setnumPassengers(rs.getInt("num_passengers"));
                    propiedad.setCambios(rs.getInt("cambios"));
                    propiedad.setNumAirbags(rs.getInt("num_airbags"));
                    propiedad.setNumAxles(rs.getInt("num_axles"));
                    propiedad.setNumEmergencyExits(rs.getInt("num_emergency_exits"));
                    propiedad.setHorsePower(rs.getString("horsepower"));
                    propiedad.setMaximumSpeed(rs.getString("maximum_speed"));
                    propiedad.setTrunkCapacity(rs.getInt("trunk_capacity"));
                    propiedad.setTime100Km(rs.getString("time_100_km"));
                    propiedad.setLoadCapacity(rs.getString("load_capacity"));
                    propiedad.setTypeTruck(rs.getString("type_truck"));
                    propiedad.setAirConditioning(rs.getInt("air_conditioning"));
                    propiedad.setReverseCamera(rs.getInt("reverse_camera"));
                    propiedad.setCruisingSpeed(rs.getInt("cruising_speed"));
                    propiedad.setAbsBrakes(rs.getInt("abs_brakes"));
                    propiedad.setAirBrakes(rs.getInt("air_brakes"));
                    propiedad.setCollisionSensor(rs.getInt("collision_sensor"));
                    propiedad.setCrossTrafficSensor(rs.getInt("cross_traffic_sensor"));
                    propiedad.setCuatroXcuatro(rs.getInt("cuatro_x_cuatro"));
                    propiedad.setLaneKeepingAssist(rs.getInt("lane_keeping_assist"));
                    propiedad.setTipoTransmision(rs.getInt("transmition_type"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return propiedad;
    }

    public boolean actualizarPropiedad(Propiedad propiedad) {
        String sql = "UPDATE property SET id_motor_type = ?, color = ?, cilindraje = ?, num_doors = ?, num_passengers = ?, cambios = ?, num_airbags, num_axles = ?, num_emergency_exits = ?, horsepower = ?, maximum_speed = ?, trunk_capacity = ?, time_100_km = ?, load_capacity = ?, type_truck = ?, air_conditioning = ?, reverse_camera = ?, cruising_speed = ?, abs_brakes = ?, air_brakes = ?, collision_sensor = ?, cross_traffic_sensor = ?, cuatro_x_cuatro = ?, lane_keeping_assist = ?, transmition_type = ?, status = ? WHERE id = ? AND status = 1 AND deleted_at IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, propiedad.getIdMotorType());
            pstmt.setString(2, propiedad.getCilindraje());

            int filasActualizadas = pstmt.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarPropiedad(int id) {
        String sql = "UPDATE property SET deleted_at = NOW() WHERE id = ? AND status = 1 AND deleted_at IS NULL";

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
