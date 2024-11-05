package co.edu.uniquindio.poo.Controllers.empleados;

import java.io.IOException;
import java.util.LinkedList;

import co.edu.uniquindio.poo.Models.empleados.Empleado;
import co.edu.uniquindio.poo.Models.empleados.EmpleadoModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RegistrarEmpleado {
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtFullName;
    @FXML
    private TextField txtIdNumber;
    @FXML
    private TextField txtGender;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtTelephone;

    private EmpleadoModel empleadoModel;

    public RegistrarEmpleado() {
        empleadoModel = new EmpleadoModel();
    }

    @FXML
    public void crearEmpleado() {
        // Creamos un objeto Empleado y lo llenamos con los datos de la vista
        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setUsername(txtUsername.getText());
        nuevoEmpleado.setFullName(txtFullName.getText());
        nuevoEmpleado.setIdNumber(txtIdNumber.getText());
        nuevoEmpleado.setGender(txtGender.getText());
        nuevoEmpleado.setEmail(txtEmail.getText());
        nuevoEmpleado.setAddress(txtAddress.getText());
        nuevoEmpleado.setTelephone(txtTelephone.getText());

        // Intentamos guardar el empleado en la base de datos
        boolean creado = empleadoModel.crearEmpleado(nuevoEmpleado);
        mostrarAlerta(creado ? "Empleado creado correctamente." : "Error al crear el empleado.");
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }    
    
}
