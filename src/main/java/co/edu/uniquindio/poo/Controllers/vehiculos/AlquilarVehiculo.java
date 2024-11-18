package co.edu.uniquindio.poo.Controllers.vehiculos;

import java.io.IOException;
import java.util.LinkedList;

import co.edu.uniquindio.poo.Controllers.clientes.ListarCliente;
import co.edu.uniquindio.poo.Controllers.empleados.ListarEmpleado;
import co.edu.uniquindio.poo.Models.clientes.Cliente;
import co.edu.uniquindio.poo.Models.clientes.ClienteModel;
import co.edu.uniquindio.poo.Models.empleados.Empleado;
import co.edu.uniquindio.poo.Models.empleados.EmpleadoModel;
import co.edu.uniquindio.poo.Models.marcas.MarcaModel;
import co.edu.uniquindio.poo.Models.modelos.ModeloModel;
import co.edu.uniquindio.poo.Models.motores.MotorModel;
import co.edu.uniquindio.poo.Models.propiedades.Propiedad;
import co.edu.uniquindio.poo.Models.propiedades.PropiedadModel;
import co.edu.uniquindio.poo.Models.rentas.Renta;
import co.edu.uniquindio.poo.Models.rentas.RentaModel;
import co.edu.uniquindio.poo.Models.tipovehiculos.TipoVehiculoModel;
import co.edu.uniquindio.poo.Models.vehiculos.Vehiculo;
import co.edu.uniquindio.poo.Models.vehiculos.VehiculoModel;
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
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class AlquilarVehiculo {
    
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
    private Button btnAlquilar;

    @FXML
    private ComboBox<Integer> cbCliente;

    @FXML
    private ComboBox<Integer> cbEmpleado;

    @FXML
    private ComboBox<String> cbVehiculo;

    @FXML
    private TextField txtValorTotal;

    @FXML
    private TextField txtNumDias;

    @FXML
    private TextField txtValorDia;

    private EmpleadoModel empleadoModel;

    private VehiculoModel vehiculoModel;

    private ClienteModel clienteModel;

    private RentaModel rentaModel;

    public AlquilarVehiculo () {
        rentaModel = new RentaModel();
        vehiculoModel = new VehiculoModel();
        empleadoModel = new EmpleadoModel();
        clienteModel = new ClienteModel();
    }

    @FXML
    public void initialize() {       
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

        LinkedList<Vehiculo> vehiculos = vehiculoModel.obtenerVehiculos();
        ObservableList<String> vehiculosList = FXCollections.observableArrayList();

        for (Vehiculo vehiculo : vehiculos) {
            vehiculosList.add(vehiculo.getBrand()+ vehiculo.getModel());
        }
        
        if (vehiculosList != null) {
            // Carga los empleados en el ComboBox
            cbVehiculo.setItems(vehiculosList);

            // Usar StringConverter para mostrar el nombre pero usar el id como valor
            cbVehiculo.setConverter(new StringConverter<String>() {
                @Override
                public String toString(String brand) {
                    // Retornar directamente la marca, ya que es el valor mostrado en el ComboBox
                    return brand;
                }
        
                @Override
                public String fromString(String brand) {
                    // Devuelve la marca tal como está, ya que no estás trabajando con un ID
                    return brand;
                }
            });
        
            // Seleccionar un valor por defecto si es necesario
            cbVehiculo.setValue(vehiculosList.isEmpty() ? null : vehiculosList.get(0));
        }
        LinkedList<Cliente> clientes = clienteModel.obtenerClientes();
        ObservableList<Integer> clientesList = FXCollections.observableArrayList();

        for (Cliente cliente : clientes) {
            clientesList.add(cliente.getId());
        }
        
        if (clientesList != null) {
            // Carga los empleados en el ComboBox
            cbCliente.setItems(clientesList);

            // Usar StringConverter para mostrar el nombre pero usar el id como valor
            cbCliente.setConverter(new StringConverter<Integer>() {
            @Override
            public String toString(Integer idCustomer) {
                // Buscar el empleado por id y mostrar su nombre
                Cliente cliente = clientes.stream()
                        .filter(e -> e.getId() == idCustomer)
                        .findFirst()
                        .orElse(null);
                return cliente != null ? cliente.getFullname() : "";
            }

            @Override
            public Integer fromString(String nombre) {
                // Este método no se usa mucho porque el ComboBox ya está trabajando con ids, pero lo dejamos por si es necesario.
                return clientes.stream()
                        .filter(e -> e.getFullname().equals(nombre))
                        .map(Cliente::getId)
                        .findFirst()
                        .orElse(null);
            }
        });

            // Selecciona un valor por defecto (si es necesario)
            cbCliente.setValue(clientesList.isEmpty() ? null : clientesList.get(0));
        }
    }

    @FXML
    public void crearRenta(ActionEvent event) {
        try {
            // Crear un objeto Empleado y llenarlo con los datos de la vista
            Renta nuevaRenta = new Renta();
            nuevaRenta.setIdCustomer(cbCliente.getValue()); 
            nuevaRenta.setIdEmployee(cbEmpleado.getValue());
            nuevaRenta.setVehicle(cbVehiculo.getValue());         
            try {
                // Convertir los valores del campo de texto a enteros
                int numDays = Integer.parseInt(txtNumDias.getText());
                int valuePerDay = Integer.parseInt(txtValorDia.getText());
                int total = Integer.parseInt(txtValorTotal.getText());

                nuevaRenta.setNumDays(numDays);
                nuevaRenta.setValuePerDay(valuePerDay);
                nuevaRenta.setTotal(total);
            } catch (NumberFormatException e) {
                System.out.println("Error: los valores ingresados no son válidos.");
            }
        
            boolean creado = rentaModel.crearRenta(nuevaRenta);
            mostrarAlerta(creado ? "Renta creado correctamente." : "Error al crear la renta.");
        } catch (NullPointerException e) {
            mostrarAlerta("Error: Hay campos obligatorios sin completar.");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Ocurrió un error al intentar crear la renta.");
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
        return cbCliente.getValue() + cbEmpleado.getValue(); // Obtiene el valor entero directamente
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
