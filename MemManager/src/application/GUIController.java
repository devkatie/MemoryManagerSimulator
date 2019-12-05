package application;

import java.net.URL;

import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.MemoryList;
import model.OutputString;

/*
 * Written by:
 * 		Katie Porter
 * 		COM 310, Prof. Sister Jane
 * 		Project 2: Memory Manager Simulator
 * 		repo: https://github.com/devkatie/MemoryManagerSimulator
 */
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
	@FXML
	private Text maxMemLabel;
	@FXML
	private TextArea outputTextArea;
	@FXML
	private Button createMemoryButton;

	private MemoryList memoryList;

	public void createMemory() {
		this.memoryList = new MemoryList(Integer.parseInt(totalMemTextField.getText()),
				Integer.parseInt(osMemTextField.getText()));
		outputTextArea.clear();
		outputTextArea.setText(new OutputString(memoryList).getOutputString());

	}

	public void updateMaxMem() {
		/*
		 * updating the max memory text field that is at the bottom of the memory
		 * visualization
		 */

	}

	public void compactMemBtnAction() {
//		pushing button does whatever's here

		memoryList.compact();
		outputTextArea.clear();
		outputTextArea.setText(new OutputString(memoryList).getOutputString());

	
	}

	public void addMemBtnAction() {
//		pushing button does whatever's here

		memoryList.addProcess(pidComboBox.getValue(), Integer.parseInt(processSizeTextField.getText()),
				parseAlgorithmCode(algorithmComboBox.getValue()));

		outputTextArea.clear();
		outputTextArea.setText(new OutputString(memoryList).getOutputString());

	}

	public void removeMemBtnAction() {
//		pushing button does whatever's here

		memoryList.removeProcess(pidComboBox.getValue());
		outputTextArea.clear();
		outputTextArea.setText(new OutputString(memoryList).getOutputString());

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/*
		 * ititialize() is going to do/compile whatever is written here at runtime
		 */

//		ComboBox setups
		algorithmComboBox.getItems().addAll("First Fit", "Best Fit", "Worst Fit");
		pidComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

//		TextField Listeners to ensure only numbers are entered
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
				if (!newValue.matches("\\d{0,4}?")) {
					processSizeTextField.setText(oldValue);
				}
			}
		});

	}

	private int parseAlgorithmCode(String algorithmText) {
		int code = 0;
		switch (algorithmText) {
		case "First Fit":
			code = 0;
			break;
		case "Best Fit":
			code = 1;
			break;
		case "Worst Fit":
			code = 2;
			break;
		}
		return code;
	}

}
