package GUI;

import SetGame.Card;
import SetGame.Deck;
import SetGame.Set;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class GUI extends Application {
    private static Scene home;
    private static Scene game;
    private static Scene end;
    private static Stage stage;
    private static int width;
    private static int height;

    public static ArrayList<Label> labels = new ArrayList<>();
    public static ArrayList<Button> buttons = new ArrayList<>();

    public static void initiate(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stageIn) throws Exception {
        final String HOME_SCREEN = "/fxml/StartScreen.fxml";
        final String GAME_SCREEN = "/fxml/GameView.fxml";
        final String END_SCREEN = "/fxml/EndScreen.fxml";

        width = (int) Screen.getPrimary().getBounds().getWidth();
        height = (int) Screen.getPrimary().getBounds().getHeight();

        home = new Scene(FXMLLoader.load(Objects.requireNonNull(GUI.class.getResource(HOME_SCREEN))), width, height);
        game = new Scene(FXMLLoader.load(Objects.requireNonNull(GUI.class.getResource(GAME_SCREEN))), width, height);
        end = new Scene(FXMLLoader.load(Objects.requireNonNull(GUI.class.getResource(END_SCREEN))), width, height);

        stage = stageIn;
        stage.setResizable(false);
        stage.setMaximized(true);
        stage.setTitle("SET");
        stage.setScene(home);

        stage.show();
    }

    public static void startGame() {
        stage.setScene(game);

        labels.add((Label) game.lookup("#DeckLabel"));
        labels.add((Label) game.lookup("#MainLabel"));

        for (int i = 0; i<15; i++){
            buttons.add((Button) game.lookup("#b" + i));
        }

        updateTable();
    }

    public static void endGame(){
        stage.setScene(end);
    }

    public static void updateTable(){
        labels.get(0).setText(Integer.toString(Deck.deck.size()));
        for (int i = 0; i< Set.cardsOnTable.size(); i++){
            try {
                updateButton(buttons.get(i), true, false);
                File file = new File("src\\main\\resources\\images\\"+Set.cardsOnTable.get(i).getImg());
                setBtnBackground(buttons.get(i), file.toURI().toString());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        for (int i = 14; i>Set.cardsOnTable.size()-1;i--){
            updateButton(buttons.get(i), false, true);
        }
    }

    public static void submitInputs(){
        String s = Set.submitInputs();
        labels.get(1).setText(s);

        if(s.equals("no set")){
            resetAllButtons();
        }else{
            resetAllButtons();
            updateTable();
        }
    }

    public static void updateButton(Button tb, boolean visibility, boolean func){
        tb.setVisible(visibility);
        tb.setDisable(func);
    }

    public static void resetAllButtons(){
        for (Button b : buttons){
            b.setOpacity(1);
        }
        Set.inputs.clear();
    }

    public static void buttonHandler(int button){
        Card currCard = Set.cardsOnTable.get(button);
        if (Set.inputs.contains(currCard)){
            Set.inputs.remove(currCard);
            buttons.get(button).setOpacity(1);
        }else{
            buttons.get(button).setOpacity(0.75);
            Set.addInput(button);
        }
    }

    public static void setBtnBackground(Button tb, String img){
        tb.setBackground(new Background(new BackgroundImage(new Image(img), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(0.75, 0.75, true, true, false, false))));
    }
}