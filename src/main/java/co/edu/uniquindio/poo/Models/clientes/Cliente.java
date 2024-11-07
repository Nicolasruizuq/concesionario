package co.edu.uniquindio.poo.Models.clientes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Cliente {

    private IntegerProperty id;
    private StringProperty fullname;
    private StringProperty idNumber;
    private StringProperty gender;
    private StringProperty address;
    private StringProperty telephone;
    private IntegerProperty id_employee;

    public Cliente() {
        // Inicialización de las propiedades
        this.id = new SimpleIntegerProperty();
        this.fullname = new SimpleStringProperty();
        this.idNumber = new SimpleStringProperty();
        this.gender = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.telephone = new SimpleStringProperty();
        this.id_employee = new SimpleIntegerProperty();
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
   
    public StringProperty fullnameProperty() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname.set(fullname);
    }

    public String getFullname() {
        return fullname.get();
    }
        
    public StringProperty idNumberProperty() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber.set(idNumber);
    }

    public String getIdNumber() {
        return idNumber.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getGender() {
        return gender.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty telephoneProperty() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }

    public String getTelephone() {
        return telephone.get();
    }

    public IntegerProperty userTypeProperty() {
        return id_employee;
    }

    public void setUserType(int id_employee) {
        this.id_employee.set(id_employee);
    }

    public int getUserType() {
        return id_employee.get();
    }
}