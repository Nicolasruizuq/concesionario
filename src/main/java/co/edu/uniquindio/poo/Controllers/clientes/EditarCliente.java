package co.edu.uniquindio.poo.Controllers.clientes;

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
import java.util.LinkedList;
import java.util.Map;
import co.edu.uniquindio.poo.Models.clientes.Cliente;
import co.edu.uniquindio.poo.Models.clientes.ClienteModel;
import co.edu.uniquindio.poo.Models.empleados.Empleado;
import co.edu.uniquindio.poo.Models.empleados.EmpleadoModel;
import co.edu.uniquindio.poo.Controllers.empleados.ListarEmpleado;
import co.edu.uniquindio.poo.Controllers.vehiculos.ListarVehiculo;

public class EditarCliente {

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
    private Button btnGuardar;

    @FXML
    private ComboBox<Integer> cbEmpleados;

    @FXML
    private RadioButton rbFemenino;

    @FXML
    private RadioButton rbMasculino;

    @FXML
    private ToggleGroup sexoGroup;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    private Cliente clienteActual;

    private ClienteModel clienteModel;

    private EmpleadoModel empleadoModel;

    public EditarCliente() {
        clienteModel = new ClienteModel();
        empleadoModel = new EmpleadoModel();
    }

    public void setCliente(Cliente cliente) {
        this.clienteActual = cliente;
        rellenarCampos();
    }

    @FXML
    public void initialize() {
        cargarEmpleados();
    }

    private Map<Integer, Empleado> empleadosMap = new HashMap<>();

    private void cargarEmpleados() {
        LinkedList<Empleado> empleados = empleadoModel.obtenerEmpleados();
        if (empleados != null && !empleados.isEmpty()) {
            // Limpia el mapa y el ComboBox
            empleadosMap.clear();
    
            // Llena el mapa con los empleados y sus IDs
            for (Empleado empleado : empleados) {
                empleadosMap.put(empleado.getId(), empleado); // Suponiendo que el método getId() existe
            }
    
            // Configura los IDs en el ComboBox
            ObservableList<Integer> empleadosIds = FXCollections.observableArrayList(empleadosMap.keySet());
            cbEmpleados.setItems(empleadosIds);
    
            // Formato para mostrar el nombre del empleado en el ComboBox
            cbEmpleados.setConverter(new StringConverter<>() {
                @Override
                public String toString(Integer id) {
                    Empleado empleado = empleadosMap.get(id);
                    return empleado != null ? empleado.getFullName() : "";
                }
    
                @Override
                public Integer fromString(String string) {
                    return null; // No se utiliza
                }
            });
        }
    }

    private void rellenarCampos() {
        if (clienteActual != null) {
            txtNombre.setText(clienteActual.getFullname());
            txtCedula.setText(clienteActual.getIdNumber());
            txtDireccion.setText(clienteActual.getAddress());
            txtTelefono.setText(clienteActual.getTelephone());

            // Seleccionar el género
            if ("Femenino".equals(clienteActual.getGender())) {
                rbFemenino.setSelected(true);
            } else {
                rbMasculino.setSelected(true);
            }

            // Seleccionar el empleado asociado (si aplica)
            cbEmpleados.getSelectionModel().select(clienteActual.getEmpleadoId());
        }
    }

    @FXML
    void editarCliente (ActionEvent event) {
        if (clienteActual != null) {
            clienteActual.setFullname(txtNombre.getText());
            clienteActual.setIdNumber(txtCedula.getText());
            clienteActual.setAddress(txtDireccion.getText());
            clienteActual.setTelephone(txtTelefono.getText());
            clienteActual.setGender(rbFemenino.isSelected() ? "Femenino" : "Masculino");
            clienteActual.setEmpleadoId(cbEmpleados.getValue());

            boolean actualizado = clienteModel.actualizarCliente(clienteActual);
            if (actualizado) {
                mostrarAlerta("Cliente actualizado correctamente.", Alert.AlertType.INFORMATION);
                volverAListarClientes();
            } else {
                mostrarAlerta("Error al actualizar el cliente.", Alert.AlertType.ERROR);
            }
        }
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Resultado");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void volverAListarClientes () {
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
