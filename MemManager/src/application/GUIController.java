package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class GUIController {

	@FXML
	private ComboBox<?> algorithmComboBox;

	@FXML
	private Button compactMemBtn;

	@FXML
	private Button addMemBtn;

	@FXML
	private Button removeMemBtn;

	@FXML
	private TextField totalMemTextField;

	@FXML
	private TextField osMemTextField;

	@FXML
	private TextField processSizeTextField;

	@FXML
	private ComboBox<?> pidComboBox;

}
