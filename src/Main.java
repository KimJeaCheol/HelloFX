import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{
	

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		Image icon = new Image("icon.png");
		stage.getIcons().add(icon);
		stage.setTitle("게시판");
		stage.setResizable(false);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
