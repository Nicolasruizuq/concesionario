package co.edu.uniquindio.poo.Controllers.empleados;

import java.io.IOException;
import java.util.LinkedList;

import co.edu.uniquindio.poo.Controllers.clientes.EditarCliente;
import co.edu.uniquindio.poo.Controllers.clientes.ListarCliente;
import co.edu.uniquindio.poo.Controllers.vehiculos.ListarVehiculo;
import co.edu.uniquindio.poo.Models.clientes.Cliente;
import co.edu.uniquindio.poo.Models.empleados.Empleado;
import co.edu.uniquindio.poo.Models.empleados.EmpleadoModel;
import javafx.application.Platform;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ListarEmpleado{
    @FXML
    private TableColumn<Empleado, String> TCCorreo;

    @FXML
    private TableColumn<Empleado, String> TCDireccion;

    @FXML
    private TableColumn<Empleado, String> TCGenero;

    @FXML
    private TableColumn<Empleado, Integer> TCId;

    @FXML
    private TableColumn<Empleado, Integer> TCIdUser;

    @FXML
    private TableColumn<Empleado, String> TCNombreCompleto;

    @FXML
    private TableColumn<Empleado, String> TCNumeroId;

    @FXML
    private TableColumn<Empleado, String> TCTelefono;

    @FXML
    private TableColumn<Empleado, String> TCUsername;

    @FXML
    private TableColumn<Empleado, Void> TCEditar;

    @FXML
    private TableColumn<Empleado, Void> TCEliminar;

    @FXML
    private TableView<Empleado> TVEmpleados;

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

    public ListarEmpleado() {
        empleadoModel = new EmpleadoModel();
    }

    @FXML
    public void initialize() {
        // Inicializa las columnas del TableView para que se vinculen con las propiedades de Empleado
        TCId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        TCIdUser.setCellValueFactory(cellData -> cellData.getValue().userTypeProperty().asObject());
        TCUsername.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        TCNombreCompleto.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());
        TCNumeroId.setCellValueFactory(cellData -> cellData.getValue().idNumberProperty());
        TCGenero.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        TCCorreo.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        TCDireccion.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        TCTelefono.setCellValueFactory(cellData -> cellData.getValue().telephoneProperty()); 
        
        TCEditar.setCellFactory(param -> new TableCell<Empleado, Void>() {
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
                        Empleado empleado = getTableView().getItems().get(getIndex());
                        System.out.println("Clic en editar empleado: " + empleado.getFullName());
                        if (empleado != null) {
                            OnEditarEmpleado(empleado);
                        }
                    });
                }
            }
        });
    
        // Configuración de la columna de Eliminar con un icono
        TCEliminar.setCellFactory(param -> new TableCell<Empleado, Void>() {
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
                        Empleado empleado = getTableView().getItems().get(getIndex());
                        if (empleado != null) {
                            System.out.println("Empleado seleccionado para eliminar: " + empleado.getFullName());
                            mostrarConfirmacionEliminar(empleado);  // Llama a tu función de confirmación
                        }
                    });
                }
            }
        });
    }

    private void mostrarConfirmacionEliminar (Empleado empleado) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación de eliminación");
        alert.setHeaderText("¿Está seguro que desea eliminar este empleado?");
        alert.setContentText("Esta acción no se puede deshacer.");

        // Mostrar los botones de "Sí" y "No"
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Si el usuario confirma, proceder con la eliminación
                eliminarEmpleado(empleado);
            } else {
                // Si el usuario cancela, no hacer nada
                System.out.println("Eliminación cancelada");
            }
        });
    }

    private void eliminarEmpleado (Empleado empleado) {
        // Llamar al método eliminarEmpleado del modelo
        boolean eliminado = empleadoModel.eliminarEmpleado(empleado.getId());
    
        if (eliminado) {
            // Si la eliminación fue exitosa, actualizar la lista de empleados
            obtenerEmpleados();
            
            // Mostrar un mensaje de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empleado Eliminado");
            alert.setHeaderText(null);
            alert.setContentText("El empleado ha sido marcado como eliminado.");
            alert.showAndWait();
        } else {
            // Si no se pudo eliminar el empleado
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Eliminación");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo eliminar al empleado.");
            alert.showAndWait();
        }
    }

    private void OnEditarEmpleado (Empleado empleado) {
        try {
            // Cargar la vista de editar cliente
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/Views/empleados/editarEmpleado.fxml"));
            Parent editarRoot = loader.load();
    
            // Obtener el controlador de la vista editarCliente.fxml
            EditarEmpleado controller = loader.getController();
    
            // Pasar el cliente al controlador
            controller.setEmpleado(empleado);
    
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
public void obtenerEmpleados() {
    LinkedList<Empleado> empleados = empleadoModel.obtenerEmpleados();

    if (empleados != null && !empleados.isEmpty()) {
        ObservableList<Empleado> empleadosObservableList = FXCollections.observableArrayList(empleados);
        TVEmpleados.setItems(empleadosObservableList);
        System.out.println("Empleados cargados en el TableView");
    } else {
        mostrarAlerta("No se encontraron empleados.");
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
}

