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

public class RegistrarCliente {   
    
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
    private ComboBox<Integer> cbEmpleados;

    @FXML
    private RadioButton rbFemenino;

    @FXML
    private RadioButton rbMasculino;

    @FXML
    private Button btnRegistrarCliente;

    private ClienteModel clienteModel;

    private EmpleadoModel empleadoModel;

    public RegistrarCliente() {
        clienteModel = new ClienteModel();
        empleadoModel = new EmpleadoModel(); // Asegúrate de inicializar el modelo
    }
    

    //private final Map<String, Integer> rolValores = new HashMap<>();
    
    @FXML
    public void initialize() {       
        LinkedList<Empleado> empleados = empleadoModel.obtenerEmpleados();
        ObservableList<Integer> empleadosList = FXCollections.observableArrayList();

        for (Empleado empleado : empleados) {
            empleadosList.add(empleado.getId());
        }
        
        if (empleadosList != null) {
            // Carga los empleados en el ComboBox
            cbEmpleados.setItems(empleadosList);

            // Usar StringConverter para mostrar el nombre pero usar el id como valor
            cbEmpleados.setConverter(new StringConverter<Integer>() {
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
            cbEmpleados.setValue(empleadosList.isEmpty() ? null : empleadosList.get(0));
        }
        
        /*    
        cbTypeUser.setValue(1); // Selecciona "Administrador" por defecto*/    
        // Asigna el grupo de selección de sexo a los RadioButtons
        rbFemenino.setToggleGroup(sexoGroup);
        rbMasculino.setToggleGroup(sexoGroup);
    }

    @FXML
    public void crearCliente(ActionEvent event) {
        try {
            // Crear un objeto Cliente y llenarlo con los datos de la vista
            Cliente nuevoCliente = new Cliente();        
            nuevoCliente.setFullname(txtNombre.getText());
            nuevoCliente.setIdNumber(txtCedula.getText());
            // Obtener el género seleccionado
            String generoSeleccionado = ((RadioButton) sexoGroup.getSelectedToggle()).getText();
            nuevoCliente.setGender(generoSeleccionado);
            nuevoCliente.setAddress(txtDireccion.getText());
            nuevoCliente.setTelephone(txtTelefono.getText());
            nuevoCliente.setEmpleadoId(cbEmpleados.getValue()); 
            
            // Intentar guardar el cliente en la base de datos
            boolean creado = clienteModel.crearCliente(nuevoCliente);
            mostrarAlerta(creado ? "Cliente creado correctamente." : "Error al crear el cliente.");
        } catch (NullPointerException e) {
            mostrarAlerta("Error: Hay campos obligatorios sin completar.");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Ocurrió un error al intentar crear el cliente.");
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
        Integer idEmpleado = cbEmpleados.getValue();
        return idEmpleado != null ? idEmpleado : -1; // Retorna el id del empleado, o -1 si no hay selección
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

    /*public RegistrarCliente() {
        clienteModel = new ClienteModel();
    }*/
    
    
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
    
}
