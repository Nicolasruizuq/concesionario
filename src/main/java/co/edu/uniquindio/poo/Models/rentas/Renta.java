package co.edu.uniquindio.poo.Models.rentas;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Renta {

    private IntegerProperty id;
    private IntegerProperty idEmployee;
    private IntegerProperty idCustomer;
    private StringProperty vehicle; 
    private IntegerProperty numDays;
    private IntegerProperty valuePerDay;
    private IntegerProperty total;
    private IntegerProperty status;

    public Renta() {
        // Inicialización de las propiedades
        this.id = new SimpleIntegerProperty();
        this.idEmployee = new SimpleIntegerProperty();
        this.idCustomer = new SimpleIntegerProperty();
        this.vehicle = new SimpleStringProperty();
        this.numDays = new SimpleIntegerProperty();
        this.valuePerDay = new SimpleIntegerProperty();
        this.total = new SimpleIntegerProperty();
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
    
    public IntegerProperty idEmployeeProperty() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee.set(idEmployee);
    }

    public int getIdEmployee() {
        return idEmployee.get();
    } 
    
    public IntegerProperty idCustomerProperty() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer.set(idCustomer);
    }

    public int getIdCustomer() {
        return idCustomer.get();
    }

    public StringProperty vehicleProperty() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle.set(vehicle);
    }

    public String getvehicle() {
        return vehicle.get();
    }
        
    public IntegerProperty numDaysProperty() {
        return numDays;
    }

    public void setNumDays(int numDays) {
        this.numDays.set(numDays);
    }

    public int getNumDays() {
        return numDays.get();
    } 

    public IntegerProperty valuePerDayProperty() {
        return valuePerDay;
    }

    public void setValuePerDay(int valuePerDay) {
        this.valuePerDay.set(valuePerDay);
    }

    public int getValuePerDay() {
        return valuePerDay.get();
    }

    public IntegerProperty totalProperty() {
        return total;
    }

    public void setTotal(int total) {
        this.total.set(total);
    }

    public int getTotal() {
        return total.get();
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