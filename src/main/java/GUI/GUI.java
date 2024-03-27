package GUI;

import SetGame.Set;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class GUI extends Application {

    private final String HOME_SCREEN = "/fxml/StartScreen.fxml";
    private final String GAME_SCREEN = "/fxml/GameView.fxml";
    //private  Map<SceneName, FxmlInfo> scenes = new HashMap<>();

    public static void startGame(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Set.class.getResource("/fxml/GameView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setMaximized(true);
        stage.setTitle("SET");
        stage.setScene(scene);

        stage.show();
    }
}