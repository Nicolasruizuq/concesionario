package co.edu.uniquindio.poo.Models.ventas;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Venta {

    private IntegerProperty id;
    private IntegerProperty idEmployee;
    private IntegerProperty idCustomer;
    private StringProperty telephoneCustomer;
    private StringProperty product;
    private IntegerProperty amount;
    private IntegerProperty salesPrice;
    private IntegerProperty total;
    private IntegerProperty status;

    public Venta() {
        this.id = new SimpleIntegerProperty();        
        this.idEmployee = new SimpleIntegerProperty();
        this.idCustomer = new SimpleIntegerProperty();          
        this.telephoneCustomer = new SimpleStringProperty();
        this.product = new SimpleStringProperty();
        this.amount = new SimpleIntegerProperty();
        this.salesPrice = new SimpleIntegerProperty();
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

    public StringProperty telephoneCustomerProperty() {
        return telephoneCustomer;
    }

    public void setTelephoneCustomer(String telephoneCustomer) {
        this.telephoneCustomer.set(telephoneCustomer);
    }

    public String getTelephoneCustomer() {
        return telephoneCustomer.get();
    }

    public StringProperty productProperty() {
        return product;
    }

    public void setProduct(String product) {
        this.product.set(product);
    }

    public String getProduct() {
        return product.get();
    }

    
    public IntegerProperty amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public int getAmount() {
        return amount.get();
    }

    public IntegerProperty salesPriceProperty() {
        return salesPrice;
    }

    public void setSalesPrice(int salesPrice) {
        this.salesPrice.set(salesPrice);
    }

    public int getSalesPrice() {
        return salesPrice.get();
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
