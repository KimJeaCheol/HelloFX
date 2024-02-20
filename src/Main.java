import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application{
	
	double x, y = 0;

	@Override
	public void start(Stage stage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/UI/Dashboard.fxml"));
		stage.initStyle(StageStyle.UNDECORATED);//타이틀바 삭제
		
		root.setOnMousePressed(event -> {
			x = event.getSceneX();
			y = event.getScreenY();
		});

		root.setOnMouseDragged(event -> {
			stage.setX(event.getScreenX() - x);
			stage.setY(event.getScreenY() - y);
		});

		Scene scene = new Scene(root,800,500);
		
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
