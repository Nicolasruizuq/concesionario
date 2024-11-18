package co.edu.uniquindio.poo.Models.vehiculos;

import co.edu.uniquindio.poo.Models.empleados.Empleado;
import co.edu.uniquindio.poo.Models.marcas.Marca;
import co.edu.uniquindio.poo.Models.modelos.Modelo;
import co.edu.uniquindio.poo.Models.motores.Motor;
import co.edu.uniquindio.poo.Models.propiedades.Propiedad;
import co.edu.uniquindio.poo.Models.tipovehiculos.TipoVehiculo;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Vehiculo {

    private IntegerProperty id;
    private IntegerProperty idBrand;
    private IntegerProperty idModel;
    private IntegerProperty idVehicleType;
    private IntegerProperty idMotorType;
    private IntegerProperty idProperty;
    private IntegerProperty idEmployee;
    private DoubleProperty valorVenta;
    private DoubleProperty valorCompra;
    private IntegerProperty tipoServicio;
    private Marca marca;
    private Propiedad propiedad;
    private Modelo modelo;
    private Motor motor;
    private TipoVehiculo tipoVehiculo;
    private Empleado empleado;


    public Vehiculo (Marca marca, Modelo modelo, Propiedad propiedad, Motor motor, TipoVehiculo tipoVehiculo) {
        this.id = new SimpleIntegerProperty();
        this.idBrand = new SimpleIntegerProperty();
        this.idModel = new SimpleIntegerProperty();
        this.idVehicleType = new SimpleIntegerProperty();
        this.idMotorType = new SimpleIntegerProperty();
        this.idProperty = new SimpleIntegerProperty();
        this.idEmployee = new SimpleIntegerProperty();
        this.valorVenta = new SimpleDoubleProperty();
        this.valorCompra = new SimpleDoubleProperty();
        this.tipoServicio = new SimpleIntegerProperty();
        this.marca = marca;
        this.propiedad = propiedad;
        this.motor = motor;
        this.modelo = modelo;
        this.tipoVehiculo = tipoVehiculo;
        this.empleado = empleado;
        
    }

    // Getters y Setters para las propiedades
    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idBrandProperty() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand.set(idBrand);
    }

    public int getIdBrand() {
        return idBrand.get();
    }   
    
    public IntegerProperty idModelProperty() {
        return idModel;
    }

    public void setIdModel(int idModel) {
        this.idModel.set(idModel);
    }

    public int getIdModel() {
        return idModel.get();
    }

    public IntegerProperty idVehicleTypeProperty() {
        return idVehicleType;
    }

    public void setIdVehicleType(int idVehicleType) {
        this.idVehicleType.set(idVehicleType);
    }

    public int getIdVehicleType() {
        return idVehicleType.get();
    }
 
    public IntegerProperty idMotorTypeProperty() {
        return idMotorType;
    }

    public void setIdMotorType(int idMotorType) {
        this.idMotorType.set(idMotorType);
    }

    public int getIdMotorType() {
        return idMotorType.get();
    }

    public IntegerProperty idPropertyProperty() {
        return idProperty;
    }

    public void setIdProperty(int idProperty) {
        this.idProperty.set(idProperty);
    }

    public int getIdProperty() {
        return idProperty.get();
    }

    public IntegerProperty idEmployeeProperty() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee.set(idEmployee);
    }

    public int getIdEmployee() {
        return idEmployee.get();
    }

    public DoubleProperty valorVentaProperty() {
        return valorVenta;
    }

    public void setValorVenta (Double valorVenta) {
        this.valorVenta.set(valorVenta);
    }

    public Double getValorVenta() {
        return valorVenta.get();
    }

    public DoubleProperty valorCompraProperty() {
        return valorCompra;
    }

    public void setValorCompra (Double valorCompra) {
        this.valorCompra.set(valorCompra);
    }

    public Double getValorCompra() {
        return valorCompra.get();
    }

    public IntegerProperty tipoServicioProperty() {
        return tipoServicio;
    }

    public void setTipoServicio(int tipoServicio) {
        this.tipoServicio.set(tipoServicio);
    }

    public int getTipoServicio() {
        return tipoServicio.get();
    }

    public Marca getMarca() {
        return marca;
    }

    public String getBrand() {
        return marca != null ? marca.getBrand() : "Desconocida";
    }

    public Modelo getModelo() {
        return modelo;
    }

    public String getModel() {
        return modelo != null ? modelo.getModel() : "Desconocida";
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public String getType() {
        return tipoVehiculo != null ? tipoVehiculo.getType() : "Desconocida";
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public String getColor() {
        return propiedad != null ? propiedad.getColor() : "Desconocida";
    }

    public int getnumPassengers() {
        return propiedad != null ? propiedad.getnumPassengers() : 0;
    }

    public Motor getMMotor() {
        return motor;
    }

    public String getMotor() {
        return motor != null ? motor.getMotor() : "Desconocida";
    }

    
}
