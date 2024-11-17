package co.edu.uniquindio.poo.Models.tipovehiculos;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class TipoVehiculo {

    private IntegerProperty id;
    private StringProperty type;
    private IntegerProperty status;

    public TipoVehiculo() {
        this.id = new SimpleIntegerProperty();
        this.type = new SimpleStringProperty();
        this.status = new SimpleIntegerProperty();
    }

    public TipoVehiculo (String tipoVehiculoName) {
        this.id = new SimpleIntegerProperty();
        this.type = new SimpleStringProperty(tipoVehiculoName);
        this.status = new SimpleIntegerProperty();
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
    
    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getType() {
        return type.get();
    }
    
    public IntegerProperty statusProperty() {
        return status;
    }

    public void setStatus(int status) {
        this.status.set(status);
    }

    public int getStatus() {
        return status.get();
    }
}
