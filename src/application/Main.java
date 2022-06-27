package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import singleton.SingletonLogin;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
//            BorderPane root = new BorderPane();
//            Scene scene = new Scene(root,400,400);
//            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        try {
            Pane root = SingletonLogin.getInstance();
            primaryStage.setTitle("Welcome");

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}