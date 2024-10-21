package co.edu.uniquindio.poo.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.edu.uniquindio.poo.Connection.DatabaseConnection;

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
    public void handleLogin() {
        String usuario = txtUsuario.getText();
        String contraseña = txtContraseña.getText();
        
        if (verificarCredenciales(usuario, contraseña)) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/Views/Main.fxml")); 
                Stage stage = (Stage) btnIniciarSesion.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Usuario o contraseña incorrecta");
        }
    }

    private boolean verificarCredenciales(String usuario, String contraseña) {
        String sql = "SELECT * FROM employee WHERE username = ? AND password = ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, usuario);
            pstmt.setString(2, contraseña);
            
            ResultSet rs = pstmt.executeQuery();
            
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
