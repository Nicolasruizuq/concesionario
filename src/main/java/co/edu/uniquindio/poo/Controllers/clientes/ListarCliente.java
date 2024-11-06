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

public class ListarEmpleado{
    @FXML
    private TableColumn<Empleado, String> TCCorreo;

    @FXML
    private TableColumn<Empleado, String> TCDireccion;

    @FXML
    private TableColumn<Empleado, String> TCGenero;

    @FXML
    private TableColumn<Empleado, Integer> TCId;

    @FXML
    private TableColumn<Empleado, Integer> TCIdUser;

    @FXML
    private TableColumn<Empleado, String> TCNombreCompleto;

    @FXML
    private TableColumn<Empleado, String> TCNumeroId;

    @FXML
    private TableColumn<Empleado, String> TCTelefono;

    @FXML
    private TableColumn<Empleado, String> TCUsername;

    @FXML
    private TableView<Empleado> TVEmpleados;

    @FXML
    private MenuBar MBMain;

    @FXML
    private Menu MMiCuenta;

    @FXML
    private Menu MClientes;

    @FXML
    private Menu MEmpleados;

    @FXML
    private Menu MReportes;

    @FXML
    private Menu MUsuarios;

    @FXML
    private Menu MVehiculos;

    private EmpleadoModel empleadoModel;

    public ListarEmpleado() {
        empleadoModel = new EmpleadoModel();
    }

    @FXML
    public void initialize() {
        // Inicializa las columnas del TableView para que se vinculen con las propiedades de Empleado
        TCId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        TCIdUser.setCellValueFactory(cellData -> cellData.getValue().userTypeProperty().asObject());
        TCUsername.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        TCNombreCompleto.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());
        TCNumeroId.setCellValueFactory(cellData -> cellData.getValue().idNumberProperty());
        TCGenero.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        TCCorreo.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        TCDireccion.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        TCTelefono.setCellValueFactory(cellData -> cellData.getValue().telephoneProperty());    
    }

    @FXML
    void OnCloseSesion(ActionEvent event) {
        try {
            // Cargar la pantalla de login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/Views/login.fxml"));
            Parent loginRoot = loader.load();
            
            // Obtener la escena actual y el Stage
            Stage stage = (Stage) MBMain.getScene().getWindow();
            
            // Configurar la nueva escena con la pantalla de login
            Scene loginScene = new Scene(loginRoot);
            stage.setScene(loginScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnLeave(ActionEvent event) {
        Platform.exit();
    }
    
    @FXML
    public void obtenerEmpleados() {
        // Llamamos al modelo para obtener los empleados
        LinkedList<Empleado> empleados = empleadoModel.obtenerEmpleados();

        // Crear un ObservableList a partir de los empleados obtenidos
        if (empleados != null && !empleados.isEmpty()) {
            ObservableList<Empleado> empleadosObservableList = FXCollections.observableArrayList(empleados);

            // Agregar los empleados al TableView
            TVEmpleados.setItems(empleadosObservableList);
        } else {
            // Mostrar una alerta si no se encontraron empleados
            mostrarAlerta("No se encontraron empleados.");
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    @FXML
    void OnRegistrarEmpleados(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("co/edu/uniquindio/poo/Views/empleados/registrarEmpleado.fxml"));
            Parent registerRoot = loader.load();
            
            // Obtener la escena actual y el Stage
            Stage stage = (Stage) MBMain.getScene().getWindow();
            
            // Configurar la nueva escena con la pantalla de login
            Scene registerScene = new Scene(registerRoot);
            stage.setScene(registerScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
