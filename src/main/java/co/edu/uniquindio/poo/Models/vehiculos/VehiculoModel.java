package co.edu.uniquindio.poo.Models.vehiculos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import co.edu.uniquindio.poo.Connection.DatabaseConnection;
import co.edu.uniquindio.poo.Models.marcas.Marca;
import co.edu.uniquindio.poo.Models.modelos.Modelo;
import co.edu.uniquindio.poo.Models.motores.Motor;
import co.edu.uniquindio.poo.Models.propiedades.Propiedad;
import co.edu.uniquindio.poo.Models.tipovehiculos.TipoVehiculo;

public class VehiculoModel {

    public boolean crearVehiculo(Vehiculo vehiculo) {
        String sql = "INSERT INTO vehicle (id_brand, id_model, id_vehicle_type, id_motor_type, id_property, sale_price, purchase_price, type_of_service, status, created_at) " 
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 1, NOW())";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, vehiculo.getIdBrand());
                pstmt.setInt(2, vehiculo.getIdModel());
                pstmt.setInt(3, vehiculo.getIdVehicleType());
                pstmt.setInt(4, vehiculo.getIdMotorType());
                pstmt.setInt(5, vehiculo.getIdProperty());
                pstmt.setDouble(6, vehiculo.getValorVenta());
                pstmt.setDouble(7, vehiculo.getValorCompra());
                pstmt.setInt(8, vehiculo.getTipoServicio());

            int filasInsertadas = pstmt.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*public LinkedList<Vehiculo> obtenerVehiculos() {        
        String sql = "SELECT ve.id, ve.id_brand, br.brand, ve.id_model, mo.model, ve.id_vehicle_type, vt.type, ve.id_motor_type, mt.motor, mt.plug_in, ve.id_property, ve.sale_price, ve.purchase_price, ve.type_of_service, ve.status "
                   + "FROM vehicle as ve "
                   + "INNER JOIN brand as br ON ve.id_brand = br.id "
                   + "INNER JOIN model as mo ON ve.id_model = mo.id "
                   + "INNER JOIN vehicle_type as vt ON ve.id_vehicle_type = vt.id "
                   + "INNER JOIN motor_type as mt ON ve.id_motor_type = mt.id "
                   + "WHERE ve.deleted_at IS NULL";

        LinkedList<Vehiculo> vehiculos = new LinkedList<>();
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {    
            
            if (!rs.isBeforeFirst()) {
                System.out.println("No se encontraron vehículos.");
                return vehiculos;
            }
                
            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo(null);
                vehiculo.setId(rs.getInt("id"));
                vehiculo.setIdBrand(rs.getInt("id_brand"));
                vehiculo.setIdModel(rs.getInt("id_model"));
                vehiculo.setIdVehicleType(rs.getInt("id_vehicle_type"));
                vehiculo.setIdMotorType(rs.getInt("id_motor_type"));
                vehiculo.setIdProperty(rs.getInt("id_property"));
                vehiculo.setValorVenta(rs.getDouble("sale_price"));
                vehiculo.setValorCompra(rs.getDouble("purchase_price"));
                vehiculo.setTipoServicio(rs.getInt("type_of_service"));
                vehiculos.add(vehiculo);    
                
                System.out.println("Empleado: " + vehiculo.getId() + ", " + vehiculo.getIdBrand());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        System.out.println("Total de vehículos encontrados: " + vehiculos.size());
        return vehiculos;
    }*/
    
    public LinkedList<Vehiculo> obtenerVehiculos() {        
        String sql = "SELECT ve.id, ve.id_brand, br.brand, ve.id_model, mo.model, ve.id_vehicle_type, vt.type, ve.id_motor_type, mt.motor, mt.plug_in, ve.id_property, ve.sale_price, ve.purchase_price, ve.type_of_service, pr.color, pr.num_passengers, ve.status "
                   + "FROM vehicle as ve "
                   + "INNER JOIN brand as br ON ve.id_brand = br.id "
                   + "INNER JOIN model as mo ON ve.id_model = mo.id "
                   + "INNER JOIN vehicle_type as vt ON ve.id_vehicle_type = vt.id "
                   + "INNER JOIN motor_type as mt ON ve.id_motor_type = mt.id "
                   + "INNER JOIN property  as pr ON ve.id_property = pr.id "
                   + "WHERE ve.deleted_at IS NULL";
    
        LinkedList<Vehiculo> vehiculos = new LinkedList<>();
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {    
    
            if (!rs.isBeforeFirst()) {
                System.out.println("No se encontraron vehículos.");
                return vehiculos;
            }
    
            while (rs.next()) { 

                String brandName = rs.getString("brand");
                Marca marca = new Marca(brandName != null ? brandName : "Desconocida");
                String modeloName = rs.getString("model");
                Modelo modelo = new Modelo(modeloName != null ? modeloName : "Desconocida");
                String tipoVehiculoName = rs.getString("type");
                TipoVehiculo tipoVehiculo = new TipoVehiculo(tipoVehiculoName != null ? tipoVehiculoName : "Desconocida");
                String motorName = rs.getString("motor");
                Motor motor = new Motor(motorName != null ? motorName : "Desconocida");
                String colorName = rs.getString("color");
                int numPassengersName = rs.getInt("num_passengers");
                colorName = (colorName != null) ? colorName : "Desconocido";
                numPassengersName = (numPassengersName > 0) ? numPassengersName : 1;
                
                
                
                

                   
                // Crear un nuevo Vehiculo con la marca
                Vehiculo vehiculo = new Vehiculo(marca, modelo, null, null, null);
                vehiculo.setId(rs.getInt("id"));
                vehiculo.setIdBrand(rs.getInt("id_brand"));
                vehiculo.setIdModel(rs.getInt("id_model"));
                vehiculo.setIdVehicleType(rs.getInt("id_vehicle_type"));
                vehiculo.setIdMotorType(rs.getInt("id_motor_type"));
                vehiculo.setIdProperty(rs.getInt("id_property"));
                vehiculo.setValorVenta(rs.getDouble("sale_price"));
                vehiculo.setValorCompra(rs.getDouble("purchase_price"));
                vehiculo.setTipoServicio(rs.getInt("type_of_service"));
                // Asignar otros campos según sea necesario
                vehiculos.add(vehiculo);    
    
                // Mostrar información para depuración
                System.out.println("Vehículo: " + vehiculo.getId() + ", Marca: " + vehiculo.getMarca().getBrand());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        System.out.println("Total de vehículos encontrados: " + vehiculos.size());
        return vehiculos;
    }
    
    public Vehiculo obtenerVehiculoPorId(int id) {
        String sql = "SELECT ve.id, ve.id_brand, br.brand, ve.id_model, mo.model, ve.id_vehicle_type, vt.type, ve.id_motor_type, mt.motor, mt.plug_in, ve.id_property, ve.sale_price, ve.purchase_price, ve.type_of_service, ve.status "
        + "FROM vehicle as ve "
        + "INNER JOIN brand as br ON ve.id_brand = br.id "
        + "INNER JOIN model as mo ON ve.id_model = mo.id "
        + "INNER JOIN vehicle_type as vt ON ve.id_vehicle_type = vt.id "
        + "INNER JOIN motor_type as mt ON ve.id_motor_type = mt.id "
        + "WHERE ve.deleted_at IS NULL";

        Vehiculo vehiculo = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    vehiculo = new Vehiculo(null, null, null, null, null);
                    vehiculo.setId(rs.getInt("id"));
                    vehiculo.setIdBrand(rs.getInt("id_brand"));
                    vehiculo.setIdModel(rs.getInt("id_model"));
                    vehiculo.setIdVehicleType(rs.getInt("id_vehicle_type"));
                    vehiculo.setIdMotorType(rs.getInt("id_motor_type"));
                    vehiculo.setIdProperty(rs.getInt("id_property"));
                    vehiculo.setValorVenta(rs.getDouble("sale_price"));
                    vehiculo.setValorCompra(rs.getDouble("purchase_price"));
                    vehiculo.setTipoServicio(rs.getInt("type_of_service"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehiculo;
    }

    public boolean actualizarVehiculo(Vehiculo vehiculo) {
        String sql = "UPDATE vehicle SET id_brand = ?, id_model = ?, id_vehicle_type = ?, id_motor_type = ?, id_property = ?, sale_price = ?, purchase_price = ?, type_of_service = ? WHERE id = ? AND status = 1 AND deleted_at IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, vehiculo.getIdBrand());
            pstmt.setInt(2, vehiculo.getIdModel());
            pstmt.setInt(3, vehiculo.getIdVehicleType());
            pstmt.setInt(4, vehiculo.getIdMotorType());
            pstmt.setInt(5, vehiculo.getIdProperty());
            pstmt.setDouble(6, vehiculo.getValorVenta());
            pstmt.setDouble(7, vehiculo.getValorCompra());
            pstmt.setInt(8, vehiculo.getTipoServicio());

            int filasActualizadas = pstmt.executeUpdate();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarVehiculo(int id) {
        String sql = "UPDATE vehicle SET deleted_at = NOW() WHERE id = ? AND status = 1 AND deleted_at IS NULL";

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
