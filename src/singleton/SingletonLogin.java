package singleton;

import java.io.IOException;

import controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class SingletonLogin {
	
	private static Pane login;
    
    
    private SingletonLogin() {    
    }
    
    public static Pane getInstance() throws IOException{
        if(login == null) {
        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LoginController.class.getResource("/view/Login.fxml"));
            login = (Pane) loader.load();
        }
        return login;
    }
	
}