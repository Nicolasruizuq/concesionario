package co.edu.uniquindio.poo.Models.empleados;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Empleado {

    private IntegerProperty id;
    private IntegerProperty id_user;
    private StringProperty username;
    private StringProperty fullName;
    private StringProperty idNumber;
    private StringProperty gender;
    private StringProperty email;
    private StringProperty address;
    private StringProperty telephone;

    // Constructor vacío
    public Empleado() {
        // Inicialización de las propiedades
        this.id = new SimpleIntegerProperty();
        this.id_user = new SimpleIntegerProperty();
        this.username = new SimpleStringProperty();
        this.fullName = new SimpleStringProperty();
        this.idNumber = new SimpleStringProperty();
        this.gender = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.telephone = new SimpleStringProperty();
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

    public IntegerProperty idUserProperty() {
        return id_user;
    }

    public void setIdUser(int id_user) {
        this.id_user.set(id_user);
    }

    public int getIdUser() {
        return id_user.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public String getFullName() {
        return fullName.get();
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

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getEmail() {
        return email.get();
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
}


/*package co.edu.uniquindio.poo.Models.empleados;

public class Empleado {
    private int id;
    private int id_user;
    private String username;
    private String fullName;
    private String idNumber;
    private String gender;
    private String email;
    private String address;
    private String telephone;

    // Constructor vacío
    
    public Empleado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return id_user;
    }

    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
*/