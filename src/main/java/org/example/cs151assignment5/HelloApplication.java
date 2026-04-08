package org.example.cs151assignment5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage primaryStage;
    

@Override
public void start(Stage stage) throws IOException {
    primaryStage = stage;
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 600, 440); 
    scene.getStylesheets().add(
        HelloApplication.class.getResource("style.css").toExternalForm()
    );
    stage.setTitle("Rock Paper Scissors");
    stage.setResizable(false);
    stage.setScene(scene);
    stage.show();
}

public static void switchScene(String fxmlFile) throws IOException {
    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
    Scene scene = new Scene(loader.load(), 600, 440); 
    scene.getStylesheets().add(
        HelloApplication.class.getResource("style.css").toExternalForm()
    );
    primaryStage.setScene(scene);
}


}
