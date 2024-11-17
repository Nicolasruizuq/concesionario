package co.edu.uniquindio.poo.Models.motores;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Motor {

    private IntegerProperty id;
    private StringProperty motor;
    private IntegerProperty plug_in;
    private IntegerProperty status;

    public Motor() {
        this.id = new SimpleIntegerProperty();
        this.motor = new SimpleStringProperty();
        this.plug_in = new SimpleIntegerProperty();
        this.status = new SimpleIntegerProperty();
    }

    public Motor (String motorName) {
        this.id = new SimpleIntegerProperty();
        this.motor = new SimpleStringProperty(motorName);
        this.plug_in = new SimpleIntegerProperty();
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
    
    public StringProperty motorProperty() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor.set(motor);
    }

    public String getMotor() {
        return motor.get();
    }

    public IntegerProperty plugInProperty() {
        return plug_in;
    }

    public void setPlugIn(int plug_in) {
        this.plug_in.set(plug_in);
    }

    public int getPlugIn() {
        return plug_in.get();
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
