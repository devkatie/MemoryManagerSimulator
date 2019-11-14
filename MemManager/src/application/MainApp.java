package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/*
 * Written by:
 * 		Katie Porter
 * 		COM 310, Prof. Sister Jane
 * 		Project 2: Memory Manager Simulator
 * 		repo: https://github.com/devkatie/MemoryManagerSimulator
 */
public class MainApp extends Application {

	private Stage primaryStage;
	private VBox rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Memory Manager");
		this.primaryStage.getIcons()
				.add(new Image("https://img7.androidappsapk.co/300/a/f/7/com.smartprojects.RAMOptimization.png"));

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("GUI.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Show the scene
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
