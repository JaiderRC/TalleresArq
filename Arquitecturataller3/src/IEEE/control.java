package IEEE;

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
    private Button btnCalcular32;

    @FXML
    private Button btnCalcular64;

    @FXML
    private Button btnLimpiar;

    @FXML
    private TextField txtDecimal;

    @FXML
    private TextField txtExponente;

    @FXML
    private TextField txtIEEE;

    @FXML
    private TextField txtMantisa;

    @FXML
    private TextField txtSigno;

    @FXML
    void initialize() {
        assert btnCalcular32 != null : "fx:id=\"btnCalcular32\" was not injected: check your FXML file 'pantallita.fxml'.";
        assert btnCalcular64 != null : "fx:id=\"btnCalcular64\" was not injected: check your FXML file 'pantallita.fxml'.";
        assert btnLimpiar != null : "fx:id=\"btnLimpiar\" was not injected: check your FXML file 'pantallita.fxml'.";
        assert txtDecimal != null : "fx:id=\"txtDecimal\" was not injected: check your FXML file 'pantallita.fxml'.";
        assert txtExponente != null : "fx:id=\"txtExponente\" was not injected: check your FXML file 'pantallita.fxml'.";
        assert txtIEEE != null : "fx:id=\"txtIEEE\" was not injected: check your FXML file 'pantallita.fxml'.";
        assert txtMantisa != null : "fx:id=\"txtMantisa\" was not injected: check your FXML file 'pantallita.fxml'.";
        assert txtSigno != null : "fx:id=\"txtSigno\" was not injected: check your FXML file 'pantallita.fxml'.";

        btnCalcular32.setOnAction(e -> calcularIEEE(32));
        btnCalcular64.setOnAction(e -> calcularIEEE(64));
        btnLimpiar.setOnAction(e -> limpiarCampos());
    }

    @FXML
    void calcularIEEE(int bits) {
        try {
            String ieee;
            int longitudEsperada = (bits == 32) ? 32 : 64;

            // Verificar si el usuario ingresó un decimal o un binario IEEE
            if (!txtDecimal.getText().isEmpty() && txtIEEE.getText().isEmpty()) {
                double numero = Double.parseDouble(txtDecimal.getText());
                ieee = modelo.convertirDecimalAIEEE(numero, bits);
            } else if (!txtIEEE.getText().isEmpty() && txtDecimal.getText().isEmpty()) {
                ieee = txtIEEE.getText();
                if (ieee.length() != longitudEsperada) {
                	 Alert alert = new Alert(Alert.AlertType.ERROR);
                     alert.setHeaderText(null);
                     alert.setTitle("Error");
                     alert.setContentText("El IEEE " + bits + " debe tener exactamente " + longitudEsperada + " bits.");
                     Optional<ButtonType> action = alert.showAndWait();
                    throw new IllegalArgumentException("El IEEE " + bits + " debe tener exactamente " + longitudEsperada + " bits.");
                }

                // Convertir IEEE a Decimal y actualizar el campo txtDecimal
                if (bits == 32) {
                    float numeroDecimal = modelo.ieeeADecimal(ieee);
                    txtDecimal.setText(String.valueOf(numeroDecimal));
                } else {
                    double numeroDecimal = modelo.ieeeADecimal64(ieee);
                    txtDecimal.setText(String.valueOf(numeroDecimal));
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Debe ingresar solo el campo que desea calcular, limpie antes de una nueva entrada (El cero tambien es un valor decimal válido)");
                Optional<ButtonType> action = alert.showAndWait();
                throw new IllegalArgumentException("Debe ingresar solo un campo (Decimal o IEEE).");
            }

            // Mostrar el IEEE en el campo de texto
            txtIEEE.setText(ieee);

            // Dividir en Signo, Exponente y Mantisa
            String[] partes = modelo.dividirIEEE(ieee);
            txtSigno.setText(partes[0]);
            txtExponente.setText(partes[1]);
            txtMantisa.setText(partes[2]);

        } catch (NumberFormatException e) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error en la entrada limpiar");
            Optional<ButtonType> action = alert.showAndWait();
            txtIEEE.clear();
        } catch (IllegalArgumentException e) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error en la entrada limpiar");
            Optional<ButtonType> action = alert.showAndWait();
            txtIEEE.clear();
        }
    }


    private void limpiarCampos() {
        txtDecimal.clear();
        txtIEEE.clear();
        txtSigno.clear();
        txtExponente.clear();
        txtMantisa.clear();
    }
}
