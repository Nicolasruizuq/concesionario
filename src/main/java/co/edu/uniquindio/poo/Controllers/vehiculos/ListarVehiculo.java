package co.edu.uniquindio.poo.Controllers.vehiculos;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedList;

import co.edu.uniquindio.poo.Controllers.clientes.EditarCliente;
import co.edu.uniquindio.poo.Controllers.clientes.ListarCliente;
import co.edu.uniquindio.poo.Controllers.empleados.ListarEmpleado;
import co.edu.uniquindio.poo.Models.clientes.Cliente;
import co.edu.uniquindio.poo.Models.empleados.Empleado;
import co.edu.uniquindio.poo.Models.empleados.EmpleadoModel;
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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ListarVehiculo {

    @FXML
    private TableColumn<Vehiculo, Integer> TCId;

    @FXML
    private TableColumn<Vehiculo, String> TCMarca;

    @FXML
    private TableColumn<Vehiculo, String> TCModelo;

    @FXML
    private TableColumn<Vehiculo, String> TCColor;    

    @FXML
    private TableColumn<Vehiculo, String> TCType;

    @FXML
    private TableColumn<Vehiculo, String> TCMotor;

    @FXML
    private TableColumn<Vehiculo, Integer> TCNumeroPuestos;

    @FXML
    TableColumn<Vehiculo, Double> TCValorCompra = new TableColumn<>("Valor de Compra");

    @FXML
    private TableColumn<Vehiculo, Double> TCValorVenta = new TableColumn<>("Valor de venta");

    @FXML
    private TableColumn<Vehiculo, Integer> TCTipoServicio;

    @FXML
    private TableColumn<Vehiculo, Void> TCEditar;

    @FXML
    private TableColumn<Vehiculo, Void> TCEliminar;

    @FXML
    private TableView<Vehiculo> TVVehiculos;

    @FXML
    private MenuBar MBMain;

    @FXML
    private Menu MMiCuenta;

    @FXML
    private Menu MClientes;

    @FXML
    private Menu MEmpleados;

    @FXML
    private Menu MReportes;

    @FXML
    private Menu MUsuarios;

    @FXML
    private Menu MVehiculos;

    private EmpleadoModel empleadoModel;

    private VehiculoModel vehiculoModel;

    private MotorModel motorModel;

    private TipoVehiculoModel tipovehiculoModel;

    private PropiedadModel propiedadModel;

    private MarcaModel marcaModel;

    private ModeloModel modeloModel;

    public ListarVehiculo () {
        empleadoModel = new EmpleadoModel();
        vehiculoModel = new VehiculoModel();
        motorModel = new MotorModel();
        tipovehiculoModel = new TipoVehiculoModel();
        propiedadModel = new PropiedadModel();
        marcaModel = new MarcaModel();
        modeloModel = new ModeloModel();
        
    }

    @FXML
    public void initialize() {
        TCMarca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBrand()));
        // Inicializa las columnas del TableView para que se vinculen con las propiedades del vehículo
        TCId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        TCModelo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));
        TCColor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getColor()));
        TCType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        TCNumeroPuestos.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getnumPassengers()));
        TCMotor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMotor()));
        TCValorCompra.setCellValueFactory(new PropertyValueFactory<>("valorCompra"));

        TCValorCompra.setCellFactory(column -> {
            return new TableCell<Vehiculo, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        // Formatear el número para evitar notación científica
                        DecimalFormat df = new DecimalFormat("#,###.##"); // Formato con coma para miles y hasta dos decimales
                        setText(df.format(item)); // Muestra el número formateado
                    }
                }
            };
        });
        TCValorVenta.setCellValueFactory(new PropertyValueFactory<>("valorVenta"));

        TCValorVenta.setCellFactory(column -> {
            return new TableCell<Vehiculo, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        // Formatear el número para evitar notación científica
                        DecimalFormat df = new DecimalFormat("#,###.##"); // Formato con coma para miles y hasta dos decimales
                        setText(df.format(item)); // Muestra el número formateado
                    }
                }
            };
        });
        TCTipoServicio.setCellValueFactory(cellData -> cellData.getValue().tipoServicioProperty().asObject());
        TCEditar.setCellFactory(param -> new TableCell<Vehiculo, Void>() {
            private final ImageView editIcon = new ImageView(new Image(getClass().getResourceAsStream("/co/edu/uniquindio/poo/Resources/img/editar.png")));
            private final StackPane container = new StackPane(editIcon);
    
            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    editIcon.setFitWidth(30);
                    editIcon.setFitHeight(30);
                    editIcon.setPreserveRatio(true);
                    setAlignment(javafx.geometry.Pos.CENTER);
                    setGraphic(container);
                    editIcon.setOnMouseClicked(event -> {
                        Vehiculo vehiculo = getTableView().getItems().get(getIndex());
                        System.out.println("Clic en editar vehículo: " + vehiculo.getId());
                        if (vehiculo != null) {
                            OnEditarVehiculo(vehiculo);
                        }
                    });
                }
            }
        });
    
        // Configuración de la columna de Eliminar con un icono
        TCEliminar.setCellFactory(param -> new TableCell<Vehiculo, Void>() {
            private final ImageView deleteIcon = new ImageView(new Image(getClass().getResourceAsStream("/co/edu/uniquindio/poo/Resources/img/borrar.png")));
            private final StackPane container = new StackPane(deleteIcon);
        
            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    deleteIcon.setFitWidth(30);
                    deleteIcon.setFitHeight(30); 
                    deleteIcon.setPreserveRatio(true);
                    setAlignment(javafx.geometry.Pos.CENTER);
                    setGraphic(container);
        
                    deleteIcon.setOnMouseClicked(event -> {
                        System.out.println("Evento de clic en ícono de eliminación activado");
                        Vehiculo vehiculo = getTableView().getItems().get(getIndex());
                        if (vehiculo != null) {
                            System.out.println("Vehículo seleccionado para eliminar: " + vehiculo.getId());
                            mostrarConfirmacionEliminar(vehiculo);
                        }
                    });
                }
            }
        });
    }

    private void mostrarConfirmacionEliminar (Vehiculo vehiculo) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación de eliminación");
        alert.setHeaderText("¿Está seguro que desea eliminar este vehículo?");
        alert.setContentText("Esta acción no se puede deshacer.");

        // Mostrar los botones de "Sí" y "No"
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Si el usuario confirma, proceder con la eliminación
                eliminarVehiculo(vehiculo);
            } else {
                // Si el usuario cancela, no hacer nada
                System.out.println("Eliminación cancelada");
            }
        });
    }

    private void eliminarVehiculo (Vehiculo vehiculo) {
        boolean eliminado = vehiculoModel.eliminarVehiculo(vehiculo.getId());
    
        if (eliminado) {
            // Si la eliminación fue exitosa, actualizar la lista de empleados
            obtenerVehiculos();
            
            // Mostrar un mensaje de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Vehiculo Eliminado");
            alert.setHeaderText(null);
            alert.setContentText("El vehículo ha sido marcado como eliminado.");
            alert.showAndWait();
        } else {
            // Si no se pudo eliminar el empleado
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Eliminación");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo eliminar el vehículo.");
            alert.showAndWait();
        }
    }

    private void OnEditarVehiculo (Vehiculo vehiculo) {
        try {
            // Cargar la vista de editar cliente
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/Views/vehiculos/editarvehiculo.fxml"));
            Parent editarRoot = loader.load();
    
            // Obtener el controlador de la vista editarCliente.fxml
            EditarVehiculo controller = loader.getController();
    
            // Pasar el cliente al controlador
            controller.setVehiculo(vehiculo);
    
            // Configurar la nueva escena
            Stage stage = (Stage) MBMain.getScene().getWindow();
            Scene editarScene = new Scene(editarRoot);
            stage.setScene(editarScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } // Esta llave cierra el bloque `catch`
    }

    @FXML
    public void obtenerVehiculos () {
        // Llamamos al modelo para obtener los clientes
        LinkedList<Vehiculo> vehiculos = vehiculoModel.obtenerVehiculos();

        if (vehiculos != null && !vehiculos.isEmpty()) {
            ObservableList<Vehiculo> vehiculosObservableList = FXCollections.observableArrayList(vehiculos);

            // Agregar los clientes al TableView
            TVVehiculos.setItems(vehiculosObservableList);
        } else {
            // Mostrar una alerta si no se encontraron clientes
            mostrarAlerta("No se encontraron vehiculos.");
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

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
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

    private void configurarTabla() {
        // Configurar columna para la marca
        TableColumn<Vehiculo, String> colMarca = new TableColumn<>("Marca");
        colMarca.setCellValueFactory(new PropertyValueFactory<>("brand")); // La propiedad del modelo

        // Configurar otras columnas de tu TableView si es necesario
        TableColumn<Vehiculo, String> colModelo = new TableColumn<>("Modelo");
        colModelo.setCellValueFactory(new PropertyValueFactory<>("idModel")); // Ejemplo

        TableColumn<Vehiculo, Double> colValorVenta = new TableColumn<>("Valor Venta");
        colValorVenta.setCellValueFactory(new PropertyValueFactory<>("valorVenta"));

        // Agregar las columnas al TableView
        TVVehiculos.getColumns().addAll(colMarca, colModelo, colValorVenta);
    }

}

