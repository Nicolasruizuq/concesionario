package co.edu.uniquindio.poo.Models.marcas;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Marca {

    private IntegerProperty id;
    private StringProperty brand;
    private IntegerProperty status;

    public Marca() {
        this.id = new SimpleIntegerProperty();
        this.brand = new SimpleStringProperty();
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
    
    public StringProperty brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public String getBrand() {
        return brand.get();
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
