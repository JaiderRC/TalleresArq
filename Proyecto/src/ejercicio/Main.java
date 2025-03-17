package ejercicio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application{
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));
			Scene scene = new Scene(root);

			stage.setTitle("Sistema Numerico");
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
