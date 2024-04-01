package GUI;

import SetGame.Set;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class MainController {
    public Label MainLabel;
    public Label DeckLabel;

    @FXML
    protected void startGame(){
        Set.initiateGame();
        GUI.startGame();
    }

    @FXML
    protected void home(){
        GUI.returnToHome();
    }

    @FXML
    protected void submit(){
        GUI.submitInputs();
    }

    @FXML
    protected void reset(){
        GUI.resetAllButtons();
    }

    @FXML
    protected void quitGame(){
        System.exit(0);
    }

    @FXML
    protected void startTimedGame(){

    }

    @FXML
    protected void getRules(){

        try{
            if (Desktop.isDesktopSupported()){
                //open rules on windows
                Desktop.getDesktop().browse(new File("src\\main\\resources\\SET INSTRUCTIONS.pdf").toURI());
            }else{
                //open rules on Mac (I could not test this myself sadly, so I am not 100% sure if it works)
                Runtime runtime = Runtime.getRuntime();
                String[] args = {"osascript", "-e", "open location \"" + new File("src\\main\\resources\\SET INSTRUCTIONS.pdf").toURI() + "\""};
                Process process = runtime.exec(args);
            }
        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Operating system not supported for this action");
            alert.showAndWait();
        }
    }

    @FXML
    protected void b0Pressed() {
        GUI.buttonHandler(0);
    }
    @FXML
    protected void b1Pressed() {
        GUI.buttonHandler(1);
    }
    @FXML
    protected void b2Pressed(){
        GUI.buttonHandler(2);
    }
    @FXML
    protected void b3Pressed(){
        GUI.buttonHandler(3);
    }
    @FXML
    protected void b4Pressed(){
        GUI.buttonHandler(4);
    }
    @FXML
    protected void b5Pressed(){
        GUI.buttonHandler(5);
    }
    @FXML
    protected void b6Pressed(){
        GUI.buttonHandler(6);
    }
    @FXML
    protected void b7Pressed(){
        GUI.buttonHandler(7);
    }
    @FXML
    protected void b8Pressed(){
        GUI.buttonHandler(8);
    }
    @FXML
    protected void b9Pressed() {
        GUI.buttonHandler(9);
    }
    @FXML
    protected void b10Pressed(){
        GUI.buttonHandler(10);
    }
    @FXML
    protected void b11Pressed(){
        GUI.buttonHandler(11);
    }
    @FXML
    protected void b12Pressed(){
        GUI.buttonHandler(12);
    }
    @FXML
    protected void b13Pressed(){
        GUI.buttonHandler(13);
    }
    @FXML
    protected void b14Pressed(){
        GUI.buttonHandler(14);
    }
}