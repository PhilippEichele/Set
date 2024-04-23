package GUI;

import SetGame.Card;
import SetGame.Deck;
import SetGame.SetLogic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.TreeMap;

/*
*   handles GUI displays
 */

public class GUI extends Application {
    private static Scene home;
    public static Scene multiplayerselector;
    public static Scene game;
    private static Scene end;
    private static Stage stage;
    private static int width;
    private static int height;

    public static HashMap<String, Label> labels = new HashMap<>();
    public static HashMap<String, TextField> textFields = new HashMap<>();
    public static ArrayList<Button> buttons = new ArrayList<>();

    public static void initiate(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stageIn) throws Exception {
        final String HOME_SCREEN = "/fxml/StartScreen.fxml";
        final String GAME_SCREEN = "/fxml/GameScreen.fxml";
        final String END_SCREEN = "/fxml/EndScreen.fxml";
        final String MULTIPLAYERSELECTOR = "/fxml/MulitplayerSelector.fxml";

        //sets default height to screen height
        height = (int) Screen.getPrimary().getBounds().getHeight();
        //limits display size to a 16:9 ratio
        width = Math.min((int) (((double) height / 9) * 16), (int) Screen.getPrimary().getBounds().getWidth());

        home = new Scene(FXMLLoader.load(Objects.requireNonNull(GUI.class.getResource(HOME_SCREEN))), width, height);
        multiplayerselector = new Scene(FXMLLoader.load(Objects.requireNonNull(GUI.class.getResource(MULTIPLAYERSELECTOR))), width, height);
        game = new Scene(FXMLLoader.load(Objects.requireNonNull(GUI.class.getResource(GAME_SCREEN))), width, height);
        end = new Scene(FXMLLoader.load(Objects.requireNonNull(GUI.class.getResource(END_SCREEN))), width, height);

        stage = stageIn;
        stage.setResizable(false);
        stage.setMaximized(true);
        stage.setTitle("SET");

        stage.setScene(home);

        stage.show();
    }
    
    public static void multiplayerSelector(){
        stage.setScene(multiplayerselector);
        labels.put("playerLabel", (Label) multiplayerselector.lookup("#playerList"));
        labels.put("playerErrorLabel", (Label) multiplayerselector.lookup("#playerErrorLabel"));
        labels.put("keyErrorLabel", (Label) multiplayerselector.lookup("#keyErrorLabel"));

        labels.get("playerErrorLabel").setText("");
        labels.get("keyErrorLabel").setText("");

        textFields.put("playerField", (TextField) multiplayerselector.lookup("#playerField"));
        textFields.put("keyField", (TextField) multiplayerselector.lookup("#keyField"));
    }

    public static void startGame() {
        buttons.clear();

        labels.put("DeckLabel", (Label) game.lookup("#DeckLabel"));
        labels.put("MainLabel", (Label) game.lookup("#MainLabel"));
        for (int i = 0; i<15; i++){
            buttons.add((Button) game.lookup("#b" + i));
        }
        updateTable();

        stage.setScene(game);
    }

    public static void endGame(){
        labels.put("GameOverLabel", (Label) end.lookup("#GameOver"));
        //get time to clear in seconds
        double ttcSecs = (System.currentTimeMillis() - SetLogic.startTime)/1000;
        //Format time to clear to minutes and seconds
        String timeToClear = (int) (ttcSecs/60) + "." + (int) (ttcSecs%60);
        labels.get("GameOverLabel").setText("Game over :D\nThere are no sets left\nYou found "+ SetLogic.setsFound+" sets in "+timeToClear+" Minutes!");

        stage.setScene(end);
    }

    public static void returnToHome(){
        resetAllButtons();
        stage.setScene(home);
    }

    public static void updateTable() {
        labels.get("DeckLabel").setText(Integer.toString(Deck.deck.size()));
        for (int i = 0; i< SetLogic.cardsOnTable.size(); i++){
            updateButton(buttons.get(i), true, false);
            File file = new File("src\\main\\resources\\images\\cards\\"+ SetLogic.cardsOnTable.get(i).getImg());
            setBtnBackground(buttons.get(i), file.toURI().toString());
        }
        for (int i = 14; i> SetLogic.cardsOnTable.size()-1; i--){
            updateButton(buttons.get(i), false, true);
        }
    }

    public static void submitInputs(){
        labels.get("MainLabel").setText(SetLogic.submitInputs());
        updateTable();
        resetAllButtons();
    }

    public static void updateButton(Button b, boolean visibility, boolean func){
        b.setVisible(visibility);
        b.setDisable(func);
    }

    public static void resetAllButtons(){
        for (Button b : buttons){
            b.setOpacity(1);
        }
        SetLogic.inputs.clear();
    }

    public static void buttonHandler(int button){
        Card currCard = SetLogic.cardsOnTable.get(button);
        if (SetLogic.inputs.contains(currCard)){
            SetLogic.inputs.remove(currCard);
            buttons.get(button).setOpacity(1);
        }else{
            buttons.get(button).setOpacity(0.25);
            SetLogic.inputs.add(currCard);
        }
    }

    public static void setBtnBackground(Button b, String img){
        String background = "-fx-background-image: url('"+img+"')";
        b.setStyle(background);
    }

    public static void setErrorLabel(String label, String error){
        labels.get(label).setText(error);
    }

    public static void setPlayerList(TreeMap<String, String> players){
        String outputString = "Players: \n";
        for (String s : players.keySet()){
            outputString += players.get(s) + "  " + s + "\n";
        }
        labels.get("playerLabel").setText(outputString);
    }

    public static void clearInputFields(){
        textFields.get("playerField").setText("");
        textFields.get("keyField").setText("");
    }
}