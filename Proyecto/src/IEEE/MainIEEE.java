package IEEE;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainIEEE extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Cargar el archivo FXML
            Parent root = FXMLLoader.load(getClass().getResource("pantallita.fxml"));

            // Crear la escena
            Scene scene = new Scene(root);

            stage.setTitle("IEEE");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar el archivo FXML.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


