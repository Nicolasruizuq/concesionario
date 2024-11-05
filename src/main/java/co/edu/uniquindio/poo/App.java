package co.edu.uniquindio.poo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/co/edu/uniquindio/poo/Views/Login.fxml"));    
        primaryStage.setTitle("Iniciar Sesión");
        Image icon = new Image(getClass().getResourceAsStream("/co/edu/uniquindio/poo/Resources/img/carro-deportivo.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }    
}


