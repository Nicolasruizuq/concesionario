package co.edu.uniquindio.poo.Controllers.reportes;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import co.edu.uniquindio.poo.Controllers.clientes.ListarCliente;
import co.edu.uniquindio.poo.Controllers.empleados.ListarEmpleado;
import co.edu.uniquindio.poo.Controllers.vehiculos.ListarVehiculo;
import co.edu.uniquindio.poo.Models.clientes.ClienteModel;
import co.edu.uniquindio.poo.Models.empleados.Empleado;
import co.edu.uniquindio.poo.Models.empleados.EmpleadoModel;
import co.edu.uniquindio.poo.Models.rentas.Renta;
import co.edu.uniquindio.poo.Models.rentas.RentaModel;
import co.edu.uniquindio.poo.Models.vehiculos.VehiculoModel;
import co.edu.uniquindio.poo.Models.ventas.Venta;
import co.edu.uniquindio.poo.Models.ventas.VentaModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import co.edu.uniquindio.poo.Models.rentas.Renta;
import co.edu.uniquindio.poo.Models.rentas.RentaModel;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ReportesVentas {

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
    private TableColumn<?, ?> TCCantidad;

    @FXML
    private TableColumn<?, ?> TCColor;

    @FXML
    private TableColumn<?, ?> TCMarca;

    @FXML
    private TableColumn<?, ?> TCModelo;

    @FXML
    private TableView<Venta> TVReportes;

    @FXML
    private Button btnGenerarReporte;

    @FXML
    private ComboBox<Integer> cbEmpleado;

    @FXML
    private ComboBox<Integer> cbTipoReporte;

    private EmpleadoModel empleadoModel;

    private VentaModel ventaModel;

    private RentaModel rentaModel;

    public ReportesVentas () {
        empleadoModel = new EmpleadoModel();
        rentaModel = new RentaModel();
        
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
    }

    @FXML
    void generarReporteVentas(ActionEvent event) {
        // Obtener el ID del empleado seleccionado en el ComboBox
        Integer idEmpleado = cbEmpleado.getValue();
        
        // Si no hay un empleado seleccionado, mostrar un mensaje y salir
        if (idEmpleado == null) {
            // Mostrar un mensaje de error o advertencia, si es necesario
            System.out.println("Por favor seleccione un empleado.");
            return;
        }

        // Obtener las ventas del empleado seleccionado
        LinkedList<Venta> ventas = ventaModel.obtenerVentaPorEmpleado(idEmpleado);
        
        // Verificar si se encontraron ventas
        if (ventas.isEmpty()) {
            System.out.println("No se encontraron ventas para el empleado seleccionado.");
            return;
        }
        
        // Convertir la lista de ventas a ObservableList
        ObservableList<Venta> observableVentas = FXCollections.observableArrayList(ventas);
        
        // Establecer las ventas en el TableView
        TVReportes.setItems(observableVentas);

        // Configuración de las columnas del TableView (adaptar a las propiedades de Venta)
        TCCantidad.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
        TCColor.setCellValueFactory(cellData -> cellData.getValue().colorProperty());  // Adaptar según la propiedad real de color
        TCMarca.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());  // Adaptar según la propiedad real de marca
        TCModelo.setCellValueFactory(cellData -> cellData.getValue().modeloProperty());  
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

    @FXML
    void OnVenderVehiculo (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("co/edu/uniquindio/poo/Views/vehiculos/venderVehiculo.fxml"));
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
    void OnReportes (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("co/edu/uniquindio/poo/Views/reportes/reportes.fxml"));
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