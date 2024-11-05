package co.edu.uniquindio.poo.Controllers.empleados;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import co.edu.uniquindio.poo.Models.empleados.Empleado;
import co.edu.uniquindio.poo.Models.empleados.EmpleadoModel;

public class RegistrarEmpleado {   
    @FXML
    private MenuBar MBMain;

    @FXML
    private Menu MClientes;

    @FXML
    private Menu MEmpleados;

    @FXML
    private Menu MMiCuenta;

    @FXML
    private Menu MReportes;

    @FXML
    private Menu MUsuarios;

    @FXML
    private Menu MVehiculos;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtUsername;

    @FXML
    private ToggleGroup sexoGroup;

    private EmpleadoModel empleadoModel;
    private final Map<String, Integer> rolValores = new HashMap<>();

    @FXML
    public void initialize() {
        cbTypeUser.getItems().addAll("Administrador", "Empleado");

        rolValores.put("Administrador", 1);
        rolValores.put("Empleado", 2);
        
        // Crea el grupo de selección
        ToggleGroup sexoGroup = new ToggleGroup();

        // Asigna el grupo a ambos RadioButtons
        rbFemenino.setToggleGroup(sexoGroup);
        rbMasculino.setToggleGroup(sexoGroup);

        cbTypeUser.setValue("Administrador"); // Selecciona "Administrador" por defecto
        

    }
    public int obtenerValorSeleccionado() {
        String seleccion = cbTypeUser.getValue();
        return rolValores.getOrDefault(seleccion, -1); // Retorna -1 si no hay selección
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

    public RegistrarEmpleado() {
        empleadoModel = new EmpleadoModel();
    }
    @FXML
    private ComboBox<String> cbTypeUser;

    @FXML
    private RadioButton rbFemenino;

    @FXML
    private RadioButton rbMasculino;

    @FXML
    public void crearEmpleado() {
        // Creamos un objeto Empleado y lo llenamos con los datos de la vista
        Empleado nuevoEmpleado = new Empleado();        
        nuevoEmpleado.setFullName(txtNombre.getText());
        nuevoEmpleado.setIdNumber(txtCedula.getText());
        nuevoEmpleado.setGender(rbFemenino.getText());        
        nuevoEmpleado.setAddress(txtDireccion.getText());
        nuevoEmpleado.setEmail(txtEmail.getText());
        nuevoEmpleado.setTelephone(txtTelefono.getText());
        nuevoEmpleado.setUsername(txtUsername.getText());
        nuevoEmpleado.setPassword(txtPassword.getText());
        nuevoEmpleado.setUserType(cbTypeUser.getValue());

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
