package com.sio.memory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class FXApplication extends Application {
	
	@Override
	public void start(Stage stage) throws Exception 
	{
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/PlateauView.fxml"));
		
		Image applicationIcon = new Image(getClass().getResourceAsStream("/images/icon.png"));
        stage.getIcons().add(applicationIcon);
		stage.setResizable(false);
		stage.setTitle("Memory");

		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
