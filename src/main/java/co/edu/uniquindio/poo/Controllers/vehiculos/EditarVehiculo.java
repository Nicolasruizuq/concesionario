package co.edu.uniquindio.poo.Controllers.vehiculos;

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
import java.util.LinkedList;
import java.util.Map;
import co.edu.uniquindio.poo.Models.clientes.Cliente;
import co.edu.uniquindio.poo.Models.clientes.ClienteModel;
import co.edu.uniquindio.poo.Models.empleados.Empleado;
import co.edu.uniquindio.poo.Models.empleados.EmpleadoModel;
import co.edu.uniquindio.poo.Models.vehiculos.Vehiculo;
import co.edu.uniquindio.poo.Models.vehiculos.VehiculoModel;
import co.edu.uniquindio.poo.Controllers.clientes.ListarCliente;
import co.edu.uniquindio.poo.Controllers.empleados.ListarEmpleado;
import co.edu.uniquindio.poo.Controllers.vehiculos.ListarVehiculo;

public class EditarVehiculo {
    
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
    private Button btnRegistrarVehículo;

    @FXML
    private ComboBox<Integer> cbAireAcondicionado;

    @FXML
    private ComboBox<Integer> cbTipoServicio;

    @FXML
    private ComboBox<Integer> cbAsistentePermanencia;

    @FXML
    private ComboBox<Integer> cbCamaraReversa;

    @FXML
    private ComboBox<Integer> cbFrenosABS;

    @FXML
    private ComboBox<Integer> cbFrenosDeAire;

    @FXML
    private ComboBox<Integer> cbMarca;

    @FXML
    private ComboBox<Integer> cbModelo;

    @FXML
    private ComboBox<Integer> cbSensorColision;

    @FXML
    private ComboBox<Integer> cbSensorTrafico;

    @FXML
    private ComboBox<Integer> cbTipoCamion;

    @FXML
    private ComboBox<Integer> cbTipoMotor;

    @FXML
    private ComboBox<Integer> cbTipoVehiculo;

    @FXML
    private ComboBox<Integer> cbTraccion4X4;

    @FXML
    private ComboBox<Integer> cbVelocidadCrucero;

    @FXML
    private TextField txCilindraje;

    @FXML
    private TextField txtAceleracion;

    @FXML
    private TextField txtCapacidadCajaCarga;

    @FXML
    private TextField txtCapacidadMaletero;

    @FXML
    private TextField txtNumeroAirBags;

    @FXML
    private TextField txtNumeroEjes;

    @FXML
    private TextField txtNumeroPasajeros;

    @FXML
    private TextField txtNumeroPuertas;

    @FXML
    private TextField txtNumeroSalidasEmergencia;

    @FXML
    private TextField txtPotencia;

    @FXML
    private TextField txtVelocidadMaxima;

    @FXML
    private TextField txtPrecioCompra;

    @FXML
    private TextField txtPrecioVenta;

    private VehiculoModel vehiculoModel;

    private Vehiculo vehiculoActual;

    private final Map<String, Integer> rolValores = new HashMap<>();

    public EditarVehiculo () {
        vehiculoModel = new VehiculoModel();
    }

    public void setVehiculo (Vehiculo vehiculo) {
        this.vehiculoActual = vehiculo;
        rellenarCampos();
    }

    private void rellenarCampos() {
        if (vehiculoActual != null) {
            cbTipoVehiculo.setValue(vehiculoActual.getClass());
            txtEmail.setText(vehiculoActual.getId());
            cbMarca.setValue(vehiculoActual.getIdBrand());
            cbModelo.setValue(vehiculoActual.getIdModel());
            cbTipoMotor.setValue(vehiculoActual.getIdMotorType());
            cbTipoVehiculo.setValue(vehiculoActual.getIdVehicleType());
        }
    }

    @FXML
    void EditarVehiculo (ActionEvent event) {
        if (vehiculoActual != null) {
            vehiculoActual.setClass(txtEmail.getText());
            vehiculoActual.setPassword(txtPassword.getText());
            vehiculoActual.setUsername(txtUsername.getText());
            vehiculoActual.setUserType(cbTypeUser.getValue());
            vehiculoActual.setFullName(txtNombre.getText());
            vehiculoActual.setIdNumber(txtCedula.getText());
            vehiculoActual.setAddress(txtDireccion.getText());
            vehiculoActual.setTelephone(txtTelefono.getText());

            boolean actualizado = vehiculoModel.actualizarVehiculo(vehiculoActual);
            if (actualizado) {
                mostrarAlerta("Vehiculo actualizado correctamente.", Alert.AlertType.INFORMATION);
                volverAListarEmpleados();
            } else {
                mostrarAlerta("Error al actualizar el vehiculo.", Alert.AlertType.ERROR);
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

    private void volverAListarVehiculos () {
        try {       
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("co/edu/uniquindio/poo/Views/vehiculos/listarVehiculo.fxml"));
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

}
