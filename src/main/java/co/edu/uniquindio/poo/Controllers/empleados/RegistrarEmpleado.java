package co.edu.uniquindio.poo.Controllers.empleados;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import co.edu.uniquindio.poo.Controllers.clientes.ListarCliente;
import co.edu.uniquindio.poo.Controllers.vehiculos.ListarVehiculo;
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

    @FXML
    private ComboBox<Integer> cbTypeUser;

    @FXML
    private RadioButton rbFemenino;

    @FXML
    private RadioButton rbMasculino;

    @FXML
    private Button btnRegistrarEmpleado;

    private EmpleadoModel empleadoModel;
    private final Map<String, Integer> rolValores = new HashMap<>();
    
    @FXML
    public void initialize() {
        // Configura los valores del mapa antes de añadir al ComboBox
        rolValores.put("Administrador", 1);
        rolValores.put("Empleado", 2);
    
        cbTypeUser.getItems().addAll(1, 2);
    
        cbTypeUser.setConverter(new StringConverter<>() {
            @Override
            public String toString(Integer value) {
                // Muestra la etiqueta correspondiente al valor Integer
                return rolValores.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equals(value))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse("");
            }
    
            @Override
            public Integer fromString(String label) {
                // Convierte la etiqueta de texto a su valor Integer correspondiente
                return rolValores.get(label);
            }
        });
    
        cbTypeUser.setValue(1); // Selecciona "Administrador" por defecto
    
        // Asigna el grupo de selección de sexo a los RadioButtons
        rbFemenino.setToggleGroup(sexoGroup);
        rbMasculino.setToggleGroup(sexoGroup);
    }

    @FXML
    public void crearEmpleado(ActionEvent event) {
        try {
            // Crear un objeto Empleado y llenarlo con los datos de la vista
            Empleado nuevoEmpleado = new Empleado();
            nuevoEmpleado.setUserType(cbTypeUser.getValue()); 
            nuevoEmpleado.setUsername(txtUsername.getText());
            nuevoEmpleado.setPassword(txtPassword.getText());         
            nuevoEmpleado.setFullName(txtNombre.getText());
            nuevoEmpleado.setIdNumber(txtCedula.getText());
            // Obtener el género seleccionado
            String generoSeleccionado = ((RadioButton) sexoGroup.getSelectedToggle()).getText();
            nuevoEmpleado.setGender(generoSeleccionado);
            nuevoEmpleado.setAddress(txtDireccion.getText());
            nuevoEmpleado.setTelephone(txtTelefono.getText());
            nuevoEmpleado.setEmail(txtEmail.getText());
            
            // Intentar guardar el empleado en la base de datos
            boolean creado = empleadoModel.crearEmpleado(nuevoEmpleado);
            mostrarAlerta(creado ? "Empleado creado correctamente." : "Error al crear el empleado.");
        } catch (NullPointerException e) {
            mostrarAlerta("Error: Hay campos obligatorios sin completar.");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Ocurrió un error al intentar crear el empleado.");
        }
    }
    
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public int obtenerValorSeleccionado() {
        return cbTypeUser.getValue(); // Obtiene el valor entero directamente
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
    void OnRegistrarCliente (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("co/edu/uniquindio/poo/Views/clientes/registrarCliente.fxml"));
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
    
    @FXML
    void OnListarCliente (ActionEvent event) {
        try {       
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("co/edu/uniquindio/poo/Views/clientes/listarCliente.fxml"));
            Parent listRoot = loader.load();

            ListarCliente controller = loader.getController();
        
            // Llamar al método para cargar los empleados
            controller.obtenerClientes();

            // Obtener la escena actual y el Stage
            Stage stage = (Stage) MBMain.getScene().getWindow();

            // Configurar la nueva escena con la pantalla de login
            Scene listScene = new Scene(listRoot);
            stage.setScene(listScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnListarEmpleado (ActionEvent event) {
        try {       
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("co/edu/uniquindio/poo/Views/empleados/listarEmpleado.fxml"));
            Parent listRoot = loader.load();

            ListarEmpleado controller = loader.getController();
        
            // Llamar al método para cargar los empleados
            controller.obtenerEmpleados();

            // Obtener la escena actual y el Stage
            Stage stage = (Stage) MBMain.getScene().getWindow();

            // Configurar la nueva escena con la pantalla de login
            Scene listScene = new Scene(listRoot);
            stage.setScene(listScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnRegistrarEmpleado (ActionEvent event) {
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

    @FXML
    void OnRegistrarVehiculo (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("co/edu/uniquindio/poo/Views/vehiculos/registrarVehiculo.fxml"));
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

    @FXML
    void OnListarVehiculo (ActionEvent event) {
        try {       
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("co/edu/uniquindio/poo/Views/vehiculos/listarVehiculo.fxml"));
            Parent listRoot = loader.load();

            ListarVehiculo controller = loader.getController();
        
            // Llamar al método para cargar los empleados
            controller.obtenerVehiculos();

            // Obtener la escena actual y el Stage
            Stage stage = (Stage) MBMain.getScene().getWindow();

            // Configurar la nueva escena con la pantalla de login
            Scene listScene = new Scene(listRoot);
            stage.setScene(listScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void OnAlquilarVehiculo (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("co/edu/uniquindio/poo/Views/vehiculos/alquilarVehiculo.fxml"));
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
