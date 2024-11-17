package co.edu.uniquindio.poo.Models.vehiculos;

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
    private DoubleProperty valorVenta;
    private DoubleProperty valorCompra;
    private IntegerProperty tipoServicio;


    public Vehiculo () {
        this.id = new SimpleIntegerProperty();
        this.idBrand = new SimpleIntegerProperty();
        this.idModel = new SimpleIntegerProperty();
        this.idVehicleType = new SimpleIntegerProperty();
        this.idMotorType = new SimpleIntegerProperty();
        this.idProperty = new SimpleIntegerProperty();
        this.valorVenta = new SimpleDoubleProperty();
        this.valorCompra = new SimpleDoubleProperty();
        this.tipoServicio = new SimpleIntegerProperty();
        
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

    public DoubleProperty valorVenta() {
        return valorVenta;
    }

    public void setValorVenta (Double valorVenta) {
        this.valorVenta.set(valorVenta);
    }

    public Double getValorVenta() {
        return valorVenta.get();
    }

    public DoubleProperty valorCompra() {
        return valorCompra;
    }

    public void setValorCompra (Double valorCompra) {
        this.valorCompra.set(valorCompra);
    }

    public Double getValorCompra() {
        return valorCompra.get();
    }

    public IntegerProperty tipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(int tipoServicio) {
        this.tipoServicio.set(tipoServicio);
    }

    public int getTipoServicio() {
        return tipoServicio.get();
    }

    
}
