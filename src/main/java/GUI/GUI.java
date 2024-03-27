package GUI;

import SetGame.Set;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class GUI extends Application {
    private static Scene home;
    private static Scene game;
    private static Stage stage;

    public static void initiate(String[] args){
        launch(args);
    }

    public static void startGame(){
        stage.setScene(game);
        stage.setFullScreen(true);
    }

    @Override
    public void start(Stage stageIn) throws Exception {
        stage = stageIn;
        String HOME_SCREEN = "/fxml/StartScreen.fxml";
        home = new Scene(FXMLLoader.load(Objects.requireNonNull(Set.class.getResource(HOME_SCREEN))));
        String GAME_SCREEN = "/fxml/GameView.fxml";
        game = new Scene(FXMLLoader.load(Objects.requireNonNull(Set.class.getResource(GAME_SCREEN))));

        stage.setFullScreen(true);
        stage.setTitle("SET");
        stage.setScene(home);

        stage.show();
    }
}