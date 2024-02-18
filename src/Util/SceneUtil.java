package Util;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SceneUtil {
    private static SceneUtil instance;

    Stage stage;
    Scene scene;
    Parent root;
    FXMLLoader loader;

	public Parent getRoot() {
		return this.root;
	}

	public void setRoot(Parent root) {
		this.root = root;
	}

	public FXMLLoader getLoader() {
		return this.loader;
	}

	public void setLoader(FXMLLoader loader) {
		this.loader = loader;
	}

    private SceneUtil() {}
    
    public static SceneUtil getInstance() {
        if (instance == null) {
            instance = new SceneUtil();
        }
        return instance;
    }

    public void close(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("프로그램 종료");
        alert.setHeaderText("프로그램을 종료하시겠습니까?");
        alert.setContentText("프로그램이 종료됩니다.");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    public Object getController(String fxml)throws IOException{
        loader = new FXMLLoader(getClass().getResource(fxml));
        root = loader.load();
        return loader.getController();
    }

    public void switchScene(ActionEvent event, String fxml) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchScene(MouseEvent event, String fxml, Parent root) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchScene(ActionEvent event, String fxml, Parent root) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}