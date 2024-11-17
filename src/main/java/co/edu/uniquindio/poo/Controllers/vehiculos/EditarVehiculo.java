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
import co.edu.uniquindio.poo.Models.propiedades.Propiedad;
import co.edu.uniquindio.poo.Models.propiedades.PropiedadModel;
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
    private Button btnGuardarVehiculo;

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
    private ComboBox<Integer> cbTraccionCuatroXcuatro;

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

    private Vehiculo vehiculoActual;

    private Propiedad propiedadActual;


    private VehiculoModel vehiculoModel;
    private PropiedadModel propiedadModel;

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
            cbAireAcondicionado.setValue(propiedadActual.getAirConditioning());
            cbTipoServicio.setValue(vehiculoActual.getTipoServicio());
            cbAsistentePermanencia.setValue(propiedadActual.getLaneKeepingAssist());
            cbCamaraReversa.setValue(propiedadActual.getReverseCamera());
            cbColor.setValue(propiedadActual.getColor());
            cbFrenosABS.setValue(propiedadActual.getAbsBrakes());
            cbFrenosDeAire.setValue(propiedadActual.getAirBrakes());
            cbMarca.setValue(vehiculoActual.getIdBrand());
            cbModelo.setValue(vehiculoActual.getIdModel());
            cbSensorColision.setValue(propiedadActual.getCollisionSensor());
            cbSensorTrafico.setValue(propiedadActual.getCrossTrafficSensor());
            cbTipoMotor.setValue(vehiculoActual.getIdMotorType());
            cbTipoVehiculo.setValue(vehiculoActual.getIdVehicleType());
            cbTraccionCuatroXcuatro.setValue(propiedadActual.getCuatroXcuatro());
            cbVelocidadCrucero.setValue(propiedadActual.getCruisingSpeed());
            cbTipoTransmision.setValue(propiedadActual.getTipoTransmision());
            txCilindraje.setText(propiedadActual.getCilindraje());
            txtCambios.setText(String.valueOf(propiedadActual.getCambios()));
            txtAceleracion.setText(String.valueOf(propiedadActual.getTime100Km()));
            txtCapacidadCajaCarga.setText(String.valueOf(propiedadActual.getLoadCapacity()));
            txtCapacidadMaletero.setText(String.valueOf(propiedadActual.getTrunkCapacity()));
            txtNumeroAirBags.setText(String.valueOf(propiedadActual.getNumAirbags()));
            txtNumeroEjes.setText(String.valueOf(propiedadActual.getNumAxles()));
            txtNumeroPasajeros.setText(String.valueOf(propiedadActual.getnumPassengers()));
            txtNumeroPuertas.setText(String.valueOf(propiedadActual.getNumDoors()));
            txtNumeroSalidasEmergencia.setText(String.valueOf(propiedadActual.getNumEmergencyExits()));
            txtPotencia.setText(String.valueOf(propiedadActual.getHorsePower()));
            txtVelocidadMaxima.setText(String.valueOf(propiedadActual.getMaximumSpeed()));
            txtPrecioCompra.setText(String.valueOf(vehiculoActual.getValorCompra()));
            txtPrecioVenta.setText(String.valueOf(vehiculoActual.getValorVenta()));
            
        }
    }

    @FXML
    void EditarVehiculo (ActionEvent event) {
        if (vehiculoActual != null) {           
            
            propiedadActual.setIdMotorType(cbTipoMotor.getValue()); 
            propiedadActual.setColor(cbColor.getValue());
            propiedadActual.setCilindraje(txCilindraje.getText());         
            propiedadActual.setNumDoors(Integer.parseInt(txtNumeroPuertas.getText()));
            propiedadActual.setNumPassengers(Integer.parseInt(txtNumeroPasajeros.getText()));
            propiedadActual.setCambios(Integer.parseInt(txtCambios.getText()));
            propiedadActual.setNumAirbags(Integer.parseInt(txtNumeroAirBags.getText()));
            propiedadActual.setNumAxles(Integer.parseInt(txtNumeroEjes.getText()));
            propiedadActual.setNumEmergencyExits(Integer.parseInt(txtNumeroSalidasEmergencia.getText()));
            propiedadActual.setHorsePower(txtPotencia.getText());
            propiedadActual.setMaximumSpeed(txtVelocidadMaxima.getText());
            propiedadActual.setTrunkCapacity(Integer.parseInt(txtCapacidadMaletero.getText()));
            propiedadActual.setTime100Km(txtAceleracion.getText());
            propiedadActual.setLoadCapacity(txtCapacidadCajaCarga.getText());
            propiedadActual.setTypeTruck(cbTipoCamion.getValue());
            propiedadActual.setAirConditioning(cbAireAcondicionado.getValue());
            propiedadActual.setReverseCamera(cbCamaraReversa.getValue());
            propiedadActual.setCruisingSpeed(cbVelocidadCrucero.getValue());
            propiedadActual.setAbsBrakes(cbFrenosABS.getValue());
            propiedadActual.setAirBrakes(cbFrenosDeAire.getValue());
            propiedadActual.setCollisionSensor(cbSensorColision.getValue());
            propiedadActual.setCrossTrafficSensor(cbSensorTrafico.getValue());
            propiedadActual.setCuatroXcuatro(cbTraccionCuatroXcuatro.getValue());
            propiedadActual.setLaneKeepingAssist(cbAsistentePermanencia.getValue());
            propiedadActual.setTipoTransmision(cbTipoTransmision.getValue());
            propiedadActual.setTypeTruck(cbTipoCamion.getValue());

            // Intentar guardar el empleado en la base de datos
            boolean propiedadActualizado = propiedadModel.actualizarPropiedad(propiedadActual);
            mostrarAlerta(propiedadActualizado ? "Propiedad actualizada correctamente." : "Error al actualizar la Propiedad.");
            // Crear un objeto Vehiculo y llenarlo con los datos de la vista
            vehiculoActual.setIdBrand(cbMarca.getValue()); 
            vehiculoActual.setIdModel(cbModelo.getValue());
            vehiculoActual.setIdVehicleType(cbTipoVehiculo.getValue());         
            vehiculoActual.setIdMotorType(cbTipoMotor.getValue());
            //nuevoVehiculo.setIdProperty(idProperty.getValue()); // OJO ESTE ID VIENE DE INSERTAR LAS PROPIEDADES PRIMERO
            vehiculoActual.setValorVenta(Double.parseDouble(txtPrecioVenta.getText()));    
            vehiculoActual.setValorCompra(Double.parseDouble(txtPrecioCompra.getText()));
            vehiculoActual.setTipoServicio(cbTipoServicio.getValue());

            // Intentar guardar el empleado en la base de datos
            boolean vehiculoActualizado = vehiculoModel.actualizarVehiculo(vehiculoActual);
            mostrarAlerta(vehiculoActualizado ? "Vehículo actualizada correctamente." : "Error al actualizar el vehículo.");
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
