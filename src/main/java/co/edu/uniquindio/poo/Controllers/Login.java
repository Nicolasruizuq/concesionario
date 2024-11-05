package co.edu.uniquindio.poo.Controllers;

import co.edu.uniquindio.poo.Models.empleados.EmpleadoModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import java.io.IOException;
import javafx.stage.Stage;

public class Login {
    @FXML
    private Button btnIniciarSesion;

    @FXML
    private TextField txtUsuario; // Para capturar el nombre de usuario

    @FXML
    private TextField txtContraseña; // Para capturar la contraseña

    @FXML
    private Hyperlink HLOlvidoContraseña;

    private EmpleadoModel empleadoModel;

    public Login() {
        empleadoModel = new EmpleadoModel(); // Inicializar el modelo
    }

    @FXML
    void OnRecuperarContraseña(ActionEvent event) {

    }
    
    @FXML    
    public void handleLogin() {
        String usuario = txtUsuario.getText();
        String contraseña = txtContraseña.getText();
        
        if (empleadoModel.verificarCredenciales(usuario, contraseña)) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/Views/Main.fxml")); 
                Stage stage = (Stage) btnIniciarSesion.getScene().getWindow();
                stage.setTitle("Tu Carro UQ");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Usuario o contraseña incorrecta");
        }        
    }
   
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de Inicio de Sesión");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }     
}
