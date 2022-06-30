package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import application.MainSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Model;
import model.User;

public class SignUpController {

	@FXML
	private Button backButton;

	@FXML
	private Label createdUserLabel;

	@FXML
	private Label errorLabel;

	@FXML
	private TextField nameInput;

	@FXML
	private PasswordField passwordInput;

	@FXML
	private Button signUpButton;

	@FXML
	private ImageView userImage;

	@FXML
	private TextField usernameInput;

	private Stage window;
	private boolean isDefault = true;
	private File selectedFile;
	private byte[] profileByte;
	private User user;
	private Model model;
	private MainSystem system;

	private void init() {
		model = new Model();
		system = new MainSystem();
	}

	@FXML
	void backToLogin(ActionEvent event) throws IOException {
		Pane login = (Pane) FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
		window = (Stage) backButton.getScene().getWindow();
		window.setTitle("Welcome");
		Scene scene = new Scene(login);
		window.setScene(scene);
		window.show();
	}

	@FXML
	void selectImage(MouseEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters()
				.addAll(new FileChooser.ExtensionFilter("Image Files (png/jpg)", "*.png", "*.jpg"));
		selectedFile = fileChooser.showOpenDialog(window);

		try {
			FileInputStream inputFile = new FileInputStream(selectedFile);

			if (selectedFile != null) {
				userImage.setImage(new Image(inputFile));
				isDefault = false;
			}
		} catch (FileNotFoundException fnfe) {
			errorLabel.setText(fnfe.getMessage());
			errorLabel.setTextFill(Color.RED);
		} catch (NullPointerException npe) {
			errorLabel.setText(npe.getMessage());
			errorLabel.setTextFill(Color.RED);
		}
	}

	@FXML
	void signUpUser(ActionEvent event) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		if (nameInput.getText().isBlank() || !nameInput.getText().isEmpty()) {
			errorLabel.setText("Empty or invalid name");
			errorLabel.setTextFill(Color.RED);
		} else if (usernameInput.getText().isBlank() || !usernameInput.getText().isEmpty()) {
			errorLabel.setText("Empty or invalid username");
			errorLabel.setTextFill(Color.RED);
		} else if (passwordInput.getText().isBlank() || passwordInput.getText().isEmpty()) {
			errorLabel.setText("Empty or invalid password");
			errorLabel.setTextFill(Color.RED);
		} else {
			try {
				if (isDefault == true) {
					InputStream defaultImage = getClass().getResourceAsStream("/resource/default-profile-picture.png");
					profileByte = defaultImage.readAllBytes();
				} else {
					profileByte = Files.readAllBytes(selectedFile.toPath());
				}
			} catch (IOException ioe) {
				errorLabel.setText(ioe.getMessage());
				errorLabel.setTextFill(Color.RED);
			}
			try {
				init();
				if (model.getUserDao().searchUserExist(usernameInput.getText()) == true) {
					errorLabel.setText("User already exists.");
					errorLabel.setTextFill(Color.RED);
				} else {
					user = model.getUserDao().createUser(nameInput.getText(), usernameInput.getText(),
							system.hashPassword(passwordInput.getText()), profileByte);
					errorLabel.setText("Created user " + usernameInput.getText());
					errorLabel.setTextFill(Color.GREEN);
				}
			} catch (SQLException sqle) {
				errorLabel.setText(sqle.getMessage());
				errorLabel.setTextFill(Color.RED);
			}
		}
	}

}