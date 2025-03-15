package ejemplo;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
public class control {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCalcular;

    @FXML
    private Button btnLimpiar;

    @FXML
    private TextField txtByte;

    @FXML
    private TextField txtGB;

    @FXML
    private TextField txtGiB;

    @FXML
    private TextField txtKB;

    @FXML
    private TextField txtKiB;

    @FXML
    private TextField txtMB;

    @FXML
    private TextField txtMiB;

    @FXML
    private TextField txtTB;

    @FXML
    private TextField txtTiB;
    private void limpiar() {
        txtByte.clear();
        txtGB.clear();
        txtGiB.clear();
        txtKB.clear();
        txtKiB.clear();
        txtMB.clear();
        txtMiB.clear();
        txtTB.clear();
        txtTiB.clear();
    }
    private void calcular() {
        TextField[] fields = { txtByte, txtKB, txtKiB, txtMB, txtMiB, txtGB, txtGiB, txtTB, txtTiB };
        String[] units = { "B", "KB", "KiB", "MB", "MiB", "GB", "GiB", "TB", "TiB" };

        int filledCount = 0;
        TextField filledField = null;
        String filledUnit = "";
        double filledValue = 0;

        for (int i = 0; i < fields.length; i++) {
            if (!fields[i].getText().isEmpty()) {
                filledCount++;
                if (filledCount > 1) {
                	Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("Solo se debe ingresar un valor para la conversión");
                    Optional<ButtonType> action = alert.showAndWait();
                	return;
                    
                }
                try {
                    filledValue = Double.parseDouble(fields[i].getText());
                    filledField = fields[i];
                    filledUnit = units[i];
                } catch (NumberFormatException e) {
                    fields[i].setText("Intente de nuevo");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("Solo numeros");
                    Optional<ButtonType> action = alert.showAndWait();
                    return;
                }
            }
        }
        if (filledCount == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Debe ingresar un valor para la conversión.");
            alert.showAndWait();
            return;
        }
        if (filledField != null) {
            actualizarValores(filledValue, filledUnit);
        }
    }
    private void actualizarValores(double value, String fromUnit) {
        txtByte.setText(String.valueOf(Model.convert(value, fromUnit, "B")));
        txtKB.setText(String.valueOf(Model.convert(value, fromUnit, "KB")));
        txtKiB.setText(String.valueOf(Model.convert(value, fromUnit, "KiB")));
        txtMB.setText(String.valueOf(Model.convert(value, fromUnit, "MB")));
        txtMiB.setText(String.valueOf(Model.convert(value, fromUnit, "MiB")));
        txtGB.setText(String.valueOf(Model.convert(value, fromUnit, "GB")));
        txtGiB.setText(String.valueOf(Model.convert(value, fromUnit, "GiB")));
        txtTB.setText(String.valueOf(Model.convert(value, fromUnit, "TB")));
        txtTiB.setText(String.valueOf(Model.convert(value, fromUnit, "TiB")));
    }
    @FXML
    void initialize() {
        assert btnCalcular != null : "fx:id=\"btnCalcular\" was not injected: check your FXML file 'Untitled'.";
        assert btnLimpiar != null : "fx:id=\"btnLimpiar\" was not injected: check your FXML file 'Untitled'.";
        assert txtByte != null : "fx:id=\"txtByte\" was not injected: check your FXML file 'Untitled'.";
        assert txtGB != null : "fx:id=\"txtGB\" was not injected: check your FXML file 'Untitled'.";
        assert txtGiB != null : "fx:id=\"txtGiB\" was not injected: check your FXML file 'Untitled'.";
        assert txtKB != null : "fx:id=\"txtKB\" was not injected: check your FXML file 'Untitled'.";
        assert txtKiB != null : "fx:id=\"txtKiB\" was not injected: check your FXML file 'Untitled'.";
        assert txtMB != null : "fx:id=\"txtMB\" was not injected: check your FXML file 'Untitled'.";
        assert txtMiB != null : "fx:id=\"txtMiB\" was not injected: check your FXML file 'Untitled'.";
        assert txtTB != null : "fx:id=\"txtTB\" was not injected: check your FXML file 'Untitled'.";
        assert txtTiB != null : "fx:id=\"txtTiB\" was not injected: check your FXML file 'Untitled'.";
        btnCalcular.setOnAction(event -> calcular());
        btnLimpiar.setOnAction(event -> limpiar());
    }

}
