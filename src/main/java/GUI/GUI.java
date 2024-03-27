package GUI;

import SetGame.Set;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
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
    }

    @Override
    public void start(Stage stageIn) throws Exception {
        final String HOME_SCREEN = "/fxml/StartScreen.fxml";
        final String GAME_SCREEN = "/fxml/GameView.fxml";

        int width = (int) Screen.getPrimary().getBounds().getWidth();
        int height = (int) Screen.getPrimary().getBounds().getHeight();

        home = new Scene(FXMLLoader.load(Objects.requireNonNull(Set.class.getResource(HOME_SCREEN))), width, height);
        game = new Scene(FXMLLoader.load(Objects.requireNonNull(Set.class.getResource(GAME_SCREEN))), width, height);

        stage = stageIn;
        stage.setResizable(false);
        stage.setMaximized(true);
        stage.setTitle("SET");
        stage.setScene(home);

        stage.show();
    }
}