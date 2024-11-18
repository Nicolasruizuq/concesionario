package co.edu.uniquindio.poo.Controllers.clientes;

import java.io.IOException;
import java.util.LinkedList;

import co.edu.uniquindio.poo.Controllers.empleados.ListarEmpleado;
import co.edu.uniquindio.poo.Controllers.vehiculos.ListarVehiculo;
import co.edu.uniquindio.poo.Models.clientes.Cliente;
import co.edu.uniquindio.poo.Models.clientes.ClienteModel;
import co.edu.uniquindio.poo.Models.empleados.Empleado;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ListarCliente{
    
    @FXML
    private TableColumn<Cliente, String> TCDireccion;

    @FXML
    private TableColumn<Cliente, String> TCGenero;

    @FXML
    private TableColumn<Cliente, Integer> TCId;

    @FXML
    private TableColumn<Cliente, Integer> TCIdUser;

    @FXML
    private TableColumn<Cliente, String> TCNombreCompleto;

    @FXML
    private TableColumn<Cliente, String> TCNumeroId;

    @FXML
    private TableColumn<Cliente, String> TCTelefono;

    @FXML
    private TableView<Cliente> TVClientes;

    @FXML
    private TableColumn<Cliente, Void> TCEditar;

    @FXML
    private TableColumn<Cliente, Void> TCEliminar;

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

    private ClienteModel clienteModel;

    public ListarCliente() {
        clienteModel = new ClienteModel();
    }

    @FXML
    public void initialize() {

        // Inicializa las columnas del TableView para que se vinculen con las propiedades de Empleado
        TCId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        TCNombreCompleto.setCellValueFactory(cellData -> cellData.getValue().fullnameProperty());
        TCNumeroId.setCellValueFactory(cellData -> cellData.getValue().idNumberProperty());
        TCGenero.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        TCDireccion.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        TCTelefono.setCellValueFactory(cellData -> cellData.getValue().telephoneProperty());

        TCEditar.setCellFactory(param -> new TableCell<Cliente, Void>() {
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
                        Cliente cliente = getTableView().getItems().get(getIndex());
                        System.out.println("Clic en editar cliente: " + cliente.getFullname());
                        if (cliente != null) {
                            OnEditarCliente(cliente);
                        }
                    });
                }
            }
        });
    
        // Configuración de la columna de Eliminar con un icono
        TCEliminar.setCellFactory(param -> new TableCell<Cliente, Void>() {
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
                        Cliente cliente = getTableView().getItems().get(getIndex());
                        if (cliente != null) {
                            System.out.println("Cliente seleccionado para eliminar: " + cliente.getFullname());
                            mostrarConfirmacionEliminar(cliente);  // Llama a tu función de confirmación
                        }
                    });
                }
            }
        });
    }

    private void mostrarConfirmacionEliminar (Cliente cliente) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación de eliminación");
        alert.setHeaderText("¿Está seguro que desea eliminar este cliente?");
        alert.setContentText("Esta acción no se puede deshacer.");

        // Mostrar los botones de "Sí" y "No"
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Si el usuario confirma, proceder con la eliminación
                eliminarCliente(cliente);
            } else {
                // Si el usuario cancela, no hacer nada
                System.out.println("Eliminación cancelada");
            }
        });
    }

    private void eliminarCliente (Cliente cliente) {
        // Llamar al método eliminarEmpleado del modelo
        boolean eliminado = clienteModel.eliminarCliente(cliente.getId());
    
        if (eliminado) {
            // Si la eliminación fue exitosa, actualizar la lista de empleados
            obtenerClientes();
            
            // Mostrar un mensaje de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cliente Eliminado");
            alert.setHeaderText(null);
            alert.setContentText("El cliente ha sido marcado como eliminado.");
            alert.showAndWait();
        } else {
            // Si no se pudo eliminar el empleado
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Eliminación");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo eliminar al cliente.");
            alert.showAndWait();
        }
    }

    private void OnEditarCliente(Cliente cliente) {
        try {
            // Cargar la vista de editar cliente
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/Views/clientes/editarCliente.fxml"));
            Parent editarRoot = loader.load();
    
            // Obtener el controlador de la vista editarCliente.fxml
            EditarCliente controller = loader.getController();
    
            // Pasar el cliente al controlador
            controller.setCliente(cliente);
    
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
    public void obtenerClientes() {
        // Llamamos al modelo para obtener los clientes
        LinkedList<Cliente> clientes = clienteModel.obtenerClientes();

        if (clientes != null && !clientes.isEmpty()) {
            ObservableList<Cliente> clientesObservableList = FXCollections.observableArrayList(clientes);

            // Agregar los clientes al TableView
            TVClientes.setItems(clientesObservableList);
        } else {
            // Mostrar una alerta si no se encontraron clientes
            mostrarAlerta("No se encontraron clientes.");
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
