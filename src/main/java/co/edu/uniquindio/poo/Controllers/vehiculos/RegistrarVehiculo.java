package co.edu.uniquindio.poo.Controllers.vehiculos;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import co.edu.uniquindio.poo.Controllers.clientes.ListarCliente;
import co.edu.uniquindio.poo.Controllers.empleados.ListarEmpleado;
import co.edu.uniquindio.poo.Models.vehiculos.Vehiculo;
import co.edu.uniquindio.poo.Models.vehiculos.VehiculoModel;
import co.edu.uniquindio.poo.Models.motores.Motor;
import co.edu.uniquindio.poo.Models.motores.MotorModel;
import co.edu.uniquindio.poo.Models.modelos.Modelo;
import co.edu.uniquindio.poo.Models.modelos.ModeloModel;
import co.edu.uniquindio.poo.Models.marcas.Marca;
import co.edu.uniquindio.poo.Models.marcas.MarcaModel;
import co.edu.uniquindio.poo.Models.tipovehiculos.TipoVehiculo;
import co.edu.uniquindio.poo.Models.tipovehiculos.TipoVehiculoModel;
import co.edu.uniquindio.poo.Models.propiedades.Propiedad;
import co.edu.uniquindio.poo.Models.propiedades.PropiedadModel;
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
import javafx.stage.Stage;

public class RegistrarVehiculo {

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
    private ComboBox<String> cbColor;

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
    private ComboBox<Integer> cbTipoTransmision;

    @FXML
    private TextField txCilindraje;

    @FXML
    private TextField txtCambios;

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
    }

    @FXML
    public void crearVehiculo(ActionEvent event) {
        try {
            // Crear un objeto Empleado y llenarlo con los datos de la vista
            Vehiculo nuevoVehiculo = new Vehiculo();
            nuevoVehiculo.setUserType(cbTypeUser.getValue()); 
            nuevoVehiculo.setUsername(txtUsername.getText());
            nuevoVehiculo.setPassword(txtPassword.getText());         
            nuevoVehiculo.setFullName(txtNombre.getText());
            nuevoVehiculo.setIdNumber(txtCedula.getText());
            nuevoVehiculo.setAddress(txtDireccion.getText());
            nuevoVehiculo.setTelephone(txtTelefono.getText());
            nuevoVehiculo.setEmail(txtEmail.getText());
            
            // Intentar guardar el empleado en la base de datos
            boolean creado = vehiculoModel.crearVehiculo(nuevoVehiculo);
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

    public RegistrarVehiculo() {
        vehiculoModel = new VehiculoModel();
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
}
