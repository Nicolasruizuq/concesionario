package co.edu.uniquindio.poo.Models.modelos;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Modelo {

    private IntegerProperty id;
    private IntegerProperty idBrand;
    private StringProperty model;

    public Modelo () {
        this.id = new SimpleIntegerProperty();
        this.idBrand = new SimpleIntegerProperty();
        this.model = new SimpleStringProperty();        
    }

    public Modelo (String modeloName) {
        this.id = new SimpleIntegerProperty();
        this.idBrand = new SimpleIntegerProperty();
        this.model = new SimpleStringProperty(modeloName);
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
    
    public StringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public String getModel() {
        return model.get();
    }    
}
