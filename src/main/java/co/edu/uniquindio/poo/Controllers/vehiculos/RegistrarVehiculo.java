package co.edu.uniquindio.poo.Controllers.vehiculos;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import co.edu.uniquindio.poo.Controllers.clientes.ListarCliente;
import co.edu.uniquindio.poo.Controllers.empleados.ListarEmpleado;
import co.edu.uniquindio.poo.Models.vehiculos.Vehiculo;
import co.edu.uniquindio.poo.Models.vehiculos.VehiculoModel;
import co.edu.uniquindio.poo.Models.motores.Motor;
import co.edu.uniquindio.poo.Models.motores.MotorModel;
import co.edu.uniquindio.poo.Models.modelos.Modelo;
import co.edu.uniquindio.poo.Models.modelos.ModeloModel;
import co.edu.uniquindio.poo.Models.clientes.Cliente;
import co.edu.uniquindio.poo.Models.clientes.ClienteModel;
import co.edu.uniquindio.poo.Models.empleados.Empleado;
import co.edu.uniquindio.poo.Models.empleados.EmpleadoModel;
import co.edu.uniquindio.poo.Models.marcas.Marca;
import co.edu.uniquindio.poo.Models.marcas.MarcaModel;
import co.edu.uniquindio.poo.Models.tipovehiculos.TipoVehiculo;
import co.edu.uniquindio.poo.Models.tipovehiculos.TipoVehiculoModel;
import co.edu.uniquindio.poo.Models.propiedades.Propiedad;
import co.edu.uniquindio.poo.Models.propiedades.PropiedadModel;
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
    private ComboBox<Integer> cbEmpleado;

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
    private TextField txtTiempoCarga;

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
    private MarcaModel marcaModel;
    private MotorModel motorModel;
    private TipoVehiculoModel tipoVehiculoModel;
    private ModeloModel modeloModel;
    private EmpleadoModel empleadoModel;

    private final Map<String, Integer> tipoSerVal = new HashMap<>();
    private final Map<String, Integer> asisPermVal = new HashMap<>();
    private final Map<String, Integer> camaraReversaVal = new HashMap<>();
    private final Map<String, String> colorVal = new HashMap<>();
    private final Map<String, Integer> absFrenosVal = new HashMap<>();
    private final Map<String, Integer> aireFrenosVal = new HashMap<>();
    private final Map<String, Integer> sensorColisionVal = new HashMap<>(); 
    private final Map<String, Integer> sensorTraficoVal = new HashMap<>();
    private final Map<String, Integer> tipoCamionVal = new HashMap<>();
    private final Map<String, Integer> traCuatroXcuatroVal = new HashMap<>();
    private final Map<String, Integer> velCruceroVal = new HashMap<>();
    private final Map<String, Integer> tipoTransVal = new HashMap<>();
    private final Map<String, Integer> aireAcondVal = new HashMap<>();
    
    public RegistrarVehiculo() {        
        //empleadoModel = new EmpleadoModel();
        vehiculoModel = new VehiculoModel();
        propiedadModel = new PropiedadModel();
        marcaModel = new MarcaModel();
        modeloModel = new ModeloModel();
        motorModel = new MotorModel();
        tipoVehiculoModel = new TipoVehiculoModel();
        empleadoModel = new EmpleadoModel();
    }

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
        tipoCamionVal.put("No Aplica", 1);
        tipoCamionVal.put("Furgón", 2);
        tipoCamionVal.put("Volqueta", 3);
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
        configurarComboBox(cbTipoCamion, tipoCamionVal);
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
        cbTipoCamion.setValue(1);

        // Lista las marcas
        LinkedList<Marca> marcas = marcaModel.obtenerMarcas();
        ObservableList<Integer> marcasList = FXCollections.observableArrayList();

        for (Marca marca : marcas) {
            marcasList.add(marca.getId());
        }
        
        if (marcasList != null) {            
            cbMarca.setItems(marcasList);            
            cbMarca.setConverter(new StringConverter<Integer>() {
                @Override
                public String toString(Integer id) {
                    Marca marca = marcas.stream()
                            .filter(e -> e.getId() == id)
                            .findFirst()
                            .orElse(null);
                    return marca != null ? marca.getBrand() : "";
                }

                @Override
            public Integer fromString(String nombre) {                
                return marcas.stream()
                        .filter(e -> e.getBrand().equals(nombre))
                        .map(Marca::getId)
                        .findFirst()
                        .orElse(null);
            }
        });            
            cbMarca.setValue(marcasList.isEmpty() ? null : marcasList.get(0));
        }
        
        // Lista los modelos
        LinkedList<Modelo> modelos = modeloModel.obtenerModelos();
        ObservableList<Integer> modeloList = FXCollections.observableArrayList();

        for (Modelo modelo : modelos) {
            modeloList.add(modelo.getId());
        }
        
        if (modeloList != null) {            
            cbModelo.setItems(modeloList);            
            cbModelo.setConverter(new StringConverter<Integer>() {
                @Override
                public String toString(Integer id) {
                    Modelo modelo = modelos.stream()
                            .filter(e -> e.getId() == id)
                            .findFirst()
                            .orElse(null);
                    return modelo != null ? modelo.getModel() : "";
                }

                @Override
            public Integer fromString(String nombre) {                
                return modelos.stream()
                        .filter(e -> e.getModel().equals(nombre))
                        .map(Modelo::getId)
                        .findFirst()
                        .orElse(null);
            }
        });            
            cbModelo.setValue(modeloList.isEmpty() ? null : modeloList.get(0));
        }

        // Lista los tipos de Motor
        LinkedList<Motor> motores = motorModel.obtenerMotores();
        ObservableList<Integer> motoresList = FXCollections.observableArrayList();

        for (Motor motor : motores) {
            motoresList.add(motor.getId());
        }
        
        if (motoresList != null) {            
            cbTipoMotor.setItems(motoresList);            
            cbTipoMotor.setConverter(new StringConverter<Integer>() {
                @Override
                public String toString(Integer id) {
                    Motor motor = motores.stream()
                            .filter(e -> e.getId() == id)
                            .findFirst()
                            .orElse(null);
                    return motor != null ? motor.getMotor() : "";
                }

                @Override
                public Integer fromString(String nombre) {                
                    return motores.stream()
                            .filter(e -> e.getMotor().equals(nombre))
                            .map(Motor::getId)
                            .findFirst()
                            .orElse(null);
                }
            });            
            cbTipoMotor.setValue(motoresList.isEmpty() ? null : motoresList.get(0));
        }
    
        // Lista tipo de vehículo
        LinkedList<TipoVehiculo> tiposvehiculos = tipoVehiculoModel.obtenerTiposDeVehiculos();
        ObservableList<Integer> tipovehiculosList = FXCollections.observableArrayList();

        for (TipoVehiculo tiposvehiculo : tiposvehiculos) {
            tipovehiculosList.add(tiposvehiculo.getId());
        }
        
        if (tipovehiculosList != null) {            
            cbTipoVehiculo.setItems(tipovehiculosList);            
            cbTipoVehiculo.setConverter(new StringConverter<Integer>() {
                @Override
                public String toString(Integer id) {
                    TipoVehiculo tiposvehiculo = tiposvehiculos.stream()
                            .filter(e -> e.getId() == id)
                            .findFirst()
                            .orElse(null);
                    return tiposvehiculo != null ? tiposvehiculo.getType() : "";
                }

                @Override
            public Integer fromString(String nombre) {                
                return tiposvehiculos.stream()
                        .filter(e -> e.getType().equals(nombre))
                        .map(TipoVehiculo::getId)
                        .findFirst()
                        .orElse(null);
            }
        });            
        cbTipoVehiculo.setValue(tipovehiculosList.isEmpty() ? null : tipovehiculosList.get(0));
        }

        // Lista los colores
        ObservableList<String> coloresList = FXCollections.observableArrayList(colorVal.values());
        cbColor.setItems(coloresList);
        cbColor.setConverter(new StringConverter<>() {
            @Override
            public String toString(String key) {
                return colorVal.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equals(key))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse("");
            }

            @Override
            public String fromString(String value) {
                return colorVal.get(value);
            }
        });        
        cbColor.setValue(coloresList.isEmpty() ? null : coloresList.get(0));
        
        // Lista los empleados
        LinkedList<Empleado> empleados = empleadoModel.obtenerEmpleados();
        ObservableList<Integer> empleadosList = FXCollections.observableArrayList();

        for (Empleado empleado : empleados) {
            empleadosList.add(empleado.getId());
        }
        
        if (empleadosList != null) {
            // Carga los empleados en el ComboBox
            cbEmpleado.setItems(empleadosList);

            // Usar StringConverter para mostrar el nombre pero usar el id como valor
            cbEmpleado.setConverter(new StringConverter<Integer>() {
            @Override
            public String toString(Integer idEmpleado) {
                // Buscar el empleado por id y mostrar su nombre
                Empleado empleado = empleados.stream()
                        .filter(e -> e.getId() == idEmpleado)
                        .findFirst()
                        .orElse(null);
                return empleado != null ? empleado.getFullName() : "";
            }

            @Override
            public Integer fromString(String nombre) {
                // Este método no se usa mucho porque el ComboBox ya está trabajando con ids, pero lo dejamos por si es necesario.
                return empleados.stream()
                        .filter(e -> e.getFullName().equals(nombre))
                        .map(Empleado::getId)
                        .findFirst()
                        .orElse(null);
            }
        });

            // Selecciona un valor por defecto (si es necesario)
            cbEmpleado.setValue(empleadosList.isEmpty() ? null : empleadosList.get(0));
        }
    }

    @FXML
    public void crearVehiculo (ActionEvent event) {
        try {            
            // Crear un objeto Propiedad y llenarlo con los datos de la vista
            Propiedad nuevaPropiedad = new Propiedad();  
            nuevaPropiedad.setIdMotorType(cbTipoMotor.getValue()); 
            nuevaPropiedad.setColor(cbColor.getValue());
            nuevaPropiedad.setCilindraje(txCilindraje.getText());         
            nuevaPropiedad.setNumDoors(Integer.parseInt(txtNumeroPuertas.getText()));
            nuevaPropiedad.setNumPassengers(Integer.parseInt(txtNumeroPasajeros.getText()));
            nuevaPropiedad.setCambios(Integer.parseInt(txtCambios.getText()));
            nuevaPropiedad.setNumAirbags(Integer.parseInt(txtNumeroAirBags.getText()));
            nuevaPropiedad.setNumAxles(Integer.parseInt(txtNumeroEjes.getText()));
            nuevaPropiedad.setNumEmergencyExits(Integer.parseInt(txtNumeroSalidasEmergencia.getText()));
            nuevaPropiedad.setHorsePower(txtPotencia.getText());
            nuevaPropiedad.setMaximumSpeed(txtVelocidadMaxima.getText());
            nuevaPropiedad.setTrunkCapacity(Integer.parseInt(txtCapacidadMaletero.getText()));
            nuevaPropiedad.setTime100Km(txtAceleracion.getText());
            nuevaPropiedad.setLoadCapacity(txtCapacidadCajaCarga.getText());
            nuevaPropiedad.setTypeTruck(cbTipoCamion.getValue());
            nuevaPropiedad.setAirConditioning(cbAireAcondicionado.getValue());
            nuevaPropiedad.setReverseCamera(cbCamaraReversa.getValue());
            nuevaPropiedad.setCruisingSpeed(cbVelocidadCrucero.getValue());
            nuevaPropiedad.setAbsBrakes(cbFrenosABS.getValue());
            nuevaPropiedad.setAirBrakes(cbFrenosDeAire.getValue());
            nuevaPropiedad.setCollisionSensor(cbSensorColision.getValue());
            nuevaPropiedad.setCrossTrafficSensor(cbSensorTrafico.getValue());
            nuevaPropiedad.setCuatroXcuatro(cbTraccionCuatroXcuatro.getValue());
            nuevaPropiedad.setLaneKeepingAssist(cbAsistentePermanencia.getValue());
            nuevaPropiedad.setTipoTransmision(cbTipoTransmision.getValue());

            if (!validarCampos()) {
                return;
            }
            // Guarda las propiedades del en la base de datos
            int propiedadCreado = propiedadModel.crearPropiedad(nuevaPropiedad);
            mostrarAlerta(propiedadCreado > 0 ? "Propiedad creado correctamente." : "Error al crear la Propiedad.");
          
            Vehiculo nuevoVehiculo = new Vehiculo(null, null, null, null, null);  
            nuevoVehiculo.setIdBrand(cbMarca.getValue()); 
            nuevoVehiculo.setIdModel(cbModelo.getValue());
            nuevoVehiculo.setIdVehicleType(cbTipoVehiculo.getValue());         
            nuevoVehiculo.setIdMotorType(cbTipoMotor.getValue());
            nuevoVehiculo.setIdProperty(propiedadCreado);
            nuevoVehiculo.setIdEmployee(cbEmpleado.getValue());
            nuevoVehiculo.setValorVenta(Double.parseDouble(txtPrecioVenta.getText()));    
            nuevoVehiculo.setValorCompra(Double.parseDouble(txtPrecioCompra.getText()));
            nuevoVehiculo.setTipoServicio(cbTipoServicio.getValue());

            // Guarda el vehiculo del en la base de datos
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

    private boolean validarCampos() {
        if (cbTipoServicio.getValue() == null || 
            cbMarca.getValue() == null || 
            cbModelo.getValue() == null || 
            cbTipoMotor.getValue() == null) {
            mostrarAlerta("Error: Hay campos obligatorios sin completar.");
            return false;
        }
        return true;
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
