package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class GUIController implements Initializable {

	@FXML
	private ComboBox<String> algorithmComboBox;
	@FXML
	private ComboBox<Integer> pidComboBox;
	@FXML
	private TextField totalMemTextField;
	@FXML
	private TextField osMemTextField;
	@FXML
	private TextField processSizeTextField;
	@FXML
	private Button compactMemBtn;
	@FXML
	private Button addMemBtn;
	@FXML
	private Button removeMemBtn;

	public void compactMemBtnAction() {
		// pushing button does whatever's here
	}
	
	public void addMemBtnAction() {
		// pushing button does whatever's here
	}
	
	public void removeMemBtnAction() {
		// pushing button does whatever's here
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		algorithmComboBox.getItems().addAll("First Fit", "Best Fit", "Worst Fit");
		pidComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		totalMemTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,4}?")) {
					totalMemTextField.setText(oldValue);
				}
			}
		});
		osMemTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,4}?")) {
					osMemTextField.setText(oldValue);
				}
			}
		});
		processSizeTextField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("regex")) {
					processSizeTextField.setText(oldValue);
				}
			}
		});

	}

}
