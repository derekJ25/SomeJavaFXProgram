package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private Label errorMessage;

	@FXML
	private Button exitButton;

	@FXML
	private Button loginButton;

	@FXML
	private PasswordField passwordInput;

	@FXML
	private Button signUpButton;

	@FXML
	private TextField usernameInput;

	private Stage window;

	@FXML
	void exit(ActionEvent event) {
		window = (Stage) exitButton.getScene().getWindow();
		window.close();
	}

	@FXML
	void login(ActionEvent event) {
		errorMessage.setWrapText(true);
		if (!usernameInput.getText().isEmpty() || !usernameInput.getText().isBlank()) {
			if (!passwordInput.getText().isEmpty() || !passwordInput.getText().isBlank()) {
				
			} else {
				errorMessage.setText("Password is empty or blank.");
				errorMessage.setTextFill(Color.RED);
			}
		} else {
			errorMessage.setText("Username is empty or blank.");
			errorMessage.setTextFill(Color.RED);
		}
	}

	@FXML
	void signUp(ActionEvent event) {
		try {
			Pane signUpPane = (Pane) FXMLLoader.load(getClass().getResource("/view/SignUp.fxml"));
			window = (Stage) signUpButton.getScene().getWindow();
			window.setTitle("Sign up");
			Scene scene = new Scene(signUpPane);
			window.setScene(scene);
			window.show();
		} catch (IOException ioe) {
			errorMessage.setText(ioe.getMessage());
			errorMessage.setTextFill(Color.RED);
		} catch (Exception e) {
			errorMessage.setText(e.getMessage());
			errorMessage.setTextFill(Color.RED);
		}
	}

}