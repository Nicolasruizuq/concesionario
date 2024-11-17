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
import javafx.util.StringConverter;
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


    private VehiculoModel vehiculoModel;
    private PropiedadModel propiedadModel;

    private final Map<String, Integer> tipoSerVal = new HashMap<>();
    private final Map<String, Integer> asisPermVal = new HashMap<>();
    private final Map<String, Integer> camaraReversaVal = new HashMap<>();
    private final Map<String, String> colorVal = new HashMap<>();
    private final Map<String, Integer> absFrenosVal = new HashMap<>();
    private final Map<String, Integer> aireFrenosVal = new HashMap<>();
    private final Map<String, Integer> sensorColisionVal = new HashMap<>(); 
    private final Map<String, Integer> sensorTraficoVal = new HashMap<>();
    private final Map<String, String> tipoCamionVal = new HashMap<>();
    private final Map<String, Integer> traCuatroXcuatroVal = new HashMap<>();
    private final Map<String, Integer> velCruceroVal = new HashMap<>();
    private final Map<String, Integer> tipoTransVal = new HashMap<>();
    private final Map<String, Integer> aireAcondVal = new HashMap<>();
    
    @FXML
    public void initialize() {
        // Configura los valores del mapa antes de añadir al ComboBox
        tipoSerVal.put("Venta", 1);
        tipoSerVal.put("Renta", 2);
        asisPermVal.put("Si", 1);
        asisPermVal.put("No", 0);
        camaraReversaVal.put("Si", 1);
        camaraReversaVal.put("No", 0);
        colorVal.put("Azul", "Azul");
        colorVal.put("Rojo", "Rojo");
        colorVal.put("Gris", "Gris");
        colorVal.put("Plata", "Plata");
        colorVal.put("Café", "Café");
        colorVal.put("Negro", "Negro");
        colorVal.put("Blanco", "Blanco");
        absFrenosVal.put("Si", 1);
        absFrenosVal.put("No", 0);
        aireFrenosVal.put("Si", 1);
        aireFrenosVal.put("No", 0);        
        sensorColisionVal.put("Si", 1);
        sensorColisionVal.put("No", 0);
        sensorTraficoVal.put("Si", 1);
        sensorTraficoVal.put("No", 0);
        tipoCamionVal.put("Furgón", "Furgón");
        tipoCamionVal.put("Volqueta", "Volqueta");
        traCuatroXcuatroVal.put("Si", 1);
        traCuatroXcuatroVal.put("No", 0);
        aireAcondVal.put("Si", 1);
        aireAcondVal.put("No", 0);
        velCruceroVal.put("Si", 1);
        velCruceroVal.put("No", 0);
        tipoTransVal.put("Automática", 1);
        tipoTransVal.put("Mecánica", 2);

        // Configura los ComboBox con el método genérico
        configurarComboBox(cbTipoServicio, tipoSerVal);
        configurarComboBox(cbAsistentePermanencia, asisPermVal);
        configurarComboBox(cbCamaraReversa, camaraReversaVal);
        //configurarComboBox(cbColor, colorVal);
        configurarComboBox(cbFrenosABS, absFrenosVal);
        configurarComboBox(cbFrenosDeAire, aireFrenosVal);
        configurarComboBox(cbSensorColision, sensorColisionVal);
        configurarComboBox(cbSensorTrafico, sensorTraficoVal);
        //configurarComboBox(cbTipoCamion, tipoCamionVal);
        configurarComboBox(cbTraccionCuatroXcuatro, traCuatroXcuatroVal);
        configurarComboBox(cbVelocidadCrucero, velCruceroVal);
        configurarComboBox(cbTipoTransmision, tipoTransVal);
        configurarComboBox(cbAireAcondicionado, aireAcondVal);                     
        
        // Establecer valores iniciales
        cbTipoServicio.setValue(1);
        cbAsistentePermanencia.setValue(1);
        cbCamaraReversa.setValue(1);
        cbFrenosABS.setValue(1);
        cbFrenosDeAire.setValue(1);
        cbSensorColision.setValue(1);
        cbSensorTrafico.setValue(1);
        cbAireAcondicionado.setValue(1);
        cbTraccionCuatroXcuatro.setValue(1);
        cbVelocidadCrucero.setValue(1);
        cbTipoTransmision.setValue(1);
    
        /*cbCamaraReversa.getItems().addAll(1, 0);    
        cbCamaraReversa.setConverter(new StringConverter<>() {
            @Override
            public String toString(Integer value) {
                // Muestra la etiqueta correspondiente al valor Integer
                return camaraReversaVal.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equals(value))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse("");
            }
    
            @Override
            public Integer fromString(String label) {
                // Convierte la etiqueta de texto a su valor Integer correspondiente
                return camaraReversaVal.get(label);
            }
        });

        cbSensorColision.setValue(1);
        cbCamaraReversa.setValue(1);*/
    }

    @FXML
    public void crearVehiculo(ActionEvent event) {
        try {
            // Crear un objeto Propiedad y llenarlo con los datos de la vista
            Propiedad nuevaPropiedad = new Propiedad();
            nuevaPropiedad.setIdMotorType(cbTipoMotor.getValue()); 
            nuevaPropiedad.setColor(cbColor.getValue());
            nuevaPropiedad.setCilindraje(txCilindraje.getText());         
            nuevaPropiedad.setNumDoors(Integer.parseInt(txtNumeroPuertas.getText()));
            nuevaPropiedad.setnumPassengers(Integer.parseInt(txtNumeroPasajeros.getText()));
            nuevaPropiedad.setCambios(Integer.parseInt(txtCambios.getText()));
            nuevaPropiedad.setNumAirbags(Integer.parseInt(txtNumeroAirBags.getText()));
            nuevaPropiedad.setNumAxles(Integer.parseInt(txtNumeroEjes.getText()));
            nuevaPropiedad.setNumEmergencyExits(Integer.parseInt(txtNumeroSalidasEmergencia.getText()));
            nuevaPropiedad.setHorsePower(txtPotencia.getText());
            nuevaPropiedad.setMaximumSpeed(txtVelocidadMaxima.getText());
            nuevaPropiedad.setTrunkCapacity(Integer.parseInt(txtCapacidadMaletero.getText()));
            nuevaPropiedad.setTime100Km(txtAceleracion.getText());
            nuevaPropiedad.setLoadCapacity(txtCapacidadCajaCarga.getText());
            nuevaPropiedad.setTypeTruck(cbTipoCamion.getText());

            // Intentar guardar el empleado en la base de datos
            boolean propiedadCreado = propiedadModel.crearPropiedad(nuevaPropiedad);
            mostrarAlerta(propiedadCreado ? "Propiedad creado correctamente." : "Error al crear la Propiedad.");
            // Crear un objeto Vehiculo y llenarlo con los datos de la vista
            Vehiculo nuevoVehiculo = new Vehiculo();
            nuevoVehiculo.setIdBrand(cbMarca.getValue()); 
            nuevoVehiculo.setIdModel(cbModelo.getValue());
            nuevoVehiculo.setIdVehicleType(cbTipoVehiculo.getValue());         
            nuevoVehiculo.setIdMotorType(cbTipoMotor.getValue());
            //nuevoVehiculo.setIdProperty(idProperty.getValue()); // OJO ESTE ID VIENE DE INSERTAR LAS PROPIEDADES PRIMERO
            nuevoVehiculo.setValorVenta(Double.parseDouble(txtPrecioVenta.getText()));    
            nuevoVehiculo.setValorCompra(Double.parseDouble(txtPrecioCompra.getText()));
            nuevoVehiculo.setTipoServicio(cbTipoServicio.getValue());

            // Intentar guardar el empleado en la base de datos
            boolean creado = vehiculoModel.crearVehiculo(nuevoVehiculo);
            mostrarAlerta(creado ? "Vehículo creado correctamente." : "Error al crear el Vehículo.");
        } catch (NullPointerException e) {
            mostrarAlerta("Error: Hay campos obligatorios sin completar.");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Ocurrió un error al intentar crear el Vehículo.");
        }
    }
    
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /*public int obtenerValorSeleccionado() {
        return cbTypeUser.getValue(); // Obtiene el valor entero directamente
    }*/
    
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

    private void configurarComboBox(ComboBox<Integer> comboBox, Map<String, Integer> valoresMap) {
        comboBox.getItems().addAll(valoresMap.values());
        comboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Integer value) {
                // Convierte Integer a String basado en el mapa
                return valoresMap.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equals(value))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse("");
            }
    
            @Override
            public Integer fromString(String label) {
                // Convierte String a Integer basado en el mapa
                return valoresMap.get(label);
            }
        });
    }    
}
