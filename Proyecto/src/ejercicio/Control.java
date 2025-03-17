package ejercicio;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;

public class Control {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnLimpiar;

	@FXML
	private ComboBox<String> cBoxType;

	@FXML
	private ComboBox<String> cBoxUnit1;

	@FXML
	private ComboBox<String> cBoxUnit2;
	
	@FXML
	private Label newSpace;

	@FXML
	private TextField txtNewSpace;

	@FXML
	private TextField txtBinario;

	@FXML
	private TextField txtDecimal;

	@FXML
	private TextField txtHexadecimal;

	@FXML
	private TextField txtOctal;

	@FXML
	private TextField txtResult;
	
	@FXML
    private ComboBox<String> cBoxFormat;

	private HashMap<String, Boolean> box = new HashMap<>();
	
	String val = "";
	
	@FXML
	void update(KeyEvent event) {
		TextField source = (TextField) event.getSource();
		try {			
			if(source == txtDecimal) {
				if(!box.get("Decimal")) {
					if(txtDecimal.getText().isEmpty()) limpiar(null);
					else val = txtDecimal.getText();
				} else return;
			}
			if(source == txtBinario) {
				if(!box.get("Binario")) {
					if(txtBinario.getText().isEmpty()) limpiar(null);
					else val = Model.binaryToDecimal(txtBinario.getText());
				} else return;
				
			}
			if(source == txtHexadecimal) {
				if(!box.get("Hexadecimal")) {
					if(txtHexadecimal.getText().isEmpty()) limpiar(null);
					else val = Model.hexToDecimal(txtHexadecimal.getText());			
				} else return;
			}
			if(source==txtOctal) {
				if(!box.get("Octal")) {
					if(txtOctal.getText().isEmpty()) limpiar(null);
					else val = Model.octalToDecimal(txtOctal.getText());
				} else return;
			}	
			if(!box.get("Decimal") && source!=txtDecimal) txtDecimal.setText(val);
			if(!box.get("Binario") && source!=txtBinario) txtBinario.setText(Model.decimalToBinary(val));
			if(!box.get("Octal") && source!=txtOctal) txtOctal.setText(Model.decimalToOctal(val));
			if(!box.get("Hexadecimal") && source!=txtHexadecimal) txtHexadecimal.setText(Model.decimalToHex(val));
			
			
		} catch (Exception e) {
			limpiar(null);
			
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(e.getMessage());
			a.show();
		}

	}


	void nuevoCuadro(String operacion) {
		newSpace.setText(operacion);
		newSpace.setVisible(true);
		txtNewSpace.setVisible(true);
	}

	@FXML
	void select(ActionEvent event) {
		
		String i1 = cBoxUnit1.getSelectionModel().getSelectedItem();
		String i2 = cBoxUnit2.getSelectionModel().getSelectedItem();
		
		box.forEach((k,v) -> {
			box.replace(k, false);
		});
		
		if(i1 != null) box.replace(i1, true);
		
		if(i2 != null) box.replace(i2, true);
		
		if(i1 != null && i2 != null && i1.equals(i2)) {
			nuevoCuadro(i1);
		} else {
			quitarCuadro();
		}
		
		activarColores();
	}
	
	

	@FXML
	void doMath(ActionEvent event) {
		try {
			int t1 = cBoxUnit1.getSelectionModel().getSelectedIndex();
			int t2 = cBoxUnit2.getSelectionModel().getSelectedIndex();
			if(cBoxUnit1.getSelectionModel().getSelectedItem()==null || cBoxUnit2.getSelectionModel().getSelectedItem()==null) throw new Exception("Debes asignar una unidad");
			
			String v1 = "";
			String v2 = "";
			
			if(t1==1)  {
				v1 = Model.binaryToDecimal(txtBinario.getText());
				
				if(!txtNewSpace.getText().isEmpty()) v2 = Model.binaryToDecimal(txtNewSpace.getText());
			}
			if(t1==2) {
				v1 = Model.hexToDecimal(txtHexadecimal.getText());
				if(!txtNewSpace.getText().isEmpty()) v2 = Model.hexToDecimal(txtNewSpace.getText());
			}
			if(t1==3) {
				v1 = Model.octalToDecimal(txtOctal.getText());
				if(!txtNewSpace.getText().isEmpty()) v2 = Model.octalToDecimal(txtNewSpace.getText());
			}
			if(t1==4) {
				v1 = txtDecimal.getText();
				if(!txtNewSpace.getText().isEmpty()) v2 = txtNewSpace.getText();
			}
			
			if(t1 != t2) {			
				if(t2==1) v2 = Model.binaryToDecimal(txtBinario.getText());
				if(t2==2) v2 = Model.hexToDecimal(txtHexadecimal.getText());
				if(t2==3) v2 = Model.octalToDecimal(txtOctal.getText());
				if(t2==4) v2 = txtDecimal.getText();
			}
						
			int type = cBoxType.getSelectionModel().getSelectedIndex();
			String result = "";
			if(type == 0) result = Model.sumStrings(v1, v2);
			if(type == 1) result = Model.subtractStrings(v1, v2);
			if(type == 2) result = Model.multiplyStrings(v1, v2);
			if(type == 3) result = Model.divideStrings(v1, v2);
			
			int format = cBoxFormat.getSelectionModel().getSelectedIndex();
			if(format == 0) txtResult.setText(Model.decimalToBinary(result));
			else if(format == 1) txtResult.setText(Model.decimalToHex(result));
			else if(format == 2) txtResult.setText(Model.decimalToOctal(result));
			else if(format == 3) txtResult.setText(result);
			
		} catch(Exception e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(e.getMessage());
			a.show();
		}
	}

	@FXML
	void limpiar(ActionEvent event) {
		txtHexadecimal.setText("");
		txtBinario.setText("");
		txtDecimal.setText("");
		txtOctal.setText("");
		txtNewSpace.setText("");
		txtResult.setText("");
	}

	void basics() {
		String units[] = { "None", "Binario", "Hexadecimal", "Octal", "Decimal" };
		cBoxUnit1.getItems().addAll(Arrays.asList(units));
		cBoxUnit2.getItems().addAll(Arrays.asList(units));
		cBoxFormat.getItems().addAll(Arrays.asList("Binario", "Hexadecimal", "Octal", "Decimal"));
		cBoxType.getItems().addAll(Arrays.asList("Suma", "Resta", "Multiplicación", "División"));
		
		cBoxType.getSelectionModel().select(0);
		cBoxFormat.getSelectionModel().select(0);
		txtResult.setStyle("-fx-text-fill: black; -fx-opacity: 1;");
		
		box.put(cBoxUnit1.getItems().get(1), false);
		box.put(cBoxUnit1.getItems().get(2), false);
		box.put(cBoxUnit1.getItems().get(3), false);	
		box.put(cBoxUnit1.getItems().get(4), false);
	}

	void quitarCuadro() {
		newSpace.setVisible(false);
		txtNewSpace.setText("");
		txtNewSpace.setVisible(false);
	}

	void activarColores() {

		txtDecimal.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: grey;");
		txtHexadecimal.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: grey;");
		txtOctal.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: grey;");
		txtBinario.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: grey;");

		if(box.get("Binario")) txtBinario.setStyle("-fx-background-color: #FFCCCC; -fx-border-color: grey;");
		
		if(box.get("Decimal")) txtDecimal.setStyle("-fx-background-color: #FFCCCC; -fx-border-color: grey;");			
		
		if(box.get("Hexadecimal")) txtHexadecimal.setStyle("-fx-background-color: #FFCCCC; -fx-border-color: grey;");

		if(box.get("Octal")) txtOctal.setStyle("-fx-background-color: #FFCCCC; -fx-border-color: grey;");
	}
	
	@FXML
	void initialize() {
		assert btnLimpiar != null : "fx:id=\"btnLimpiar\" was not injected: check your FXML file 'Untitled'.";
		assert cBoxType != null : "fx:id=\"cBoxType\" was not injected: check your FXML file 'Untitled'.";
		assert cBoxUnit1 != null : "fx:id=\"cBoxUnit1\" was not injected: check your FXML file 'Untitled'.";
		assert cBoxUnit2 != null : "fx:id=\"cBoxUnit2\" was not injected: check your FXML file 'Untitled'.";
		assert txtBinario != null : "fx:id=\"txtBinario\" was not injected: check your FXML file 'Untitled'.";
		assert txtDecimal != null : "fx:id=\"txtDecimal\" was not injected: check your FXML file 'Untitled'.";
		assert txtHexadecimal != null : "fx:id=\"txtHexadecimal\" was not injected: check your FXML file 'Untitled'.";
		assert txtOctal != null : "fx:id=\"txtOctal\" was not injected: check your FXML file 'Untitled'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Untitled'.";

		basics();
		quitarCuadro();
	}

}
