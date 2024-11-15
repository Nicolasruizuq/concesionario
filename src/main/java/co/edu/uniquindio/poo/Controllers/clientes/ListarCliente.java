package co.edu.uniquindio.poo.Controllers.clientes;

import java.io.IOException;
import java.util.LinkedList;
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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    
            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    editIcon.setFitWidth(30);
                    editIcon.setFitHeight(30);
                    setGraphic(editIcon);
                    editIcon.setOnMouseClicked(event -> {
                        Cliente cliente = getTableView().getItems().get(getIndex());
                        // Lógica para editar el cliente
                        System.out.println("Editar cliente: " + cliente.getFullname());
                    });
                }
            }
        });
    
        // Configuración de la columna de Eliminar con un icono
        TCEliminar.setCellFactory(param -> new TableCell<Cliente, Void>() {
            private final ImageView deleteIcon = new ImageView(new Image(getClass().getResourceAsStream("/co/edu/uniquindio/poo/Resources/img/borrar.png")));
    
            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    deleteIcon.setFitWidth(30);
                    deleteIcon.setFitHeight(30); 
                    setGraphic(deleteIcon);
                    deleteIcon.setOnMouseClicked(event -> {
                        Cliente cliente = getTableView().getItems().get(getIndex());
                        // Lógica para eliminar el cliente
                        System.out.println("Eliminar cliente: " + cliente.getFullname());
                    });
                }
            }
        });
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

    

    

    
}
