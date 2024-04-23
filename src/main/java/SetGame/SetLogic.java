package SetGame;

import Attributes.Colour;
import Attributes.Number;
import Attributes.Shading;
import Attributes.Shape;
import GUI.GUI;

import java.util.*;
import java.util.stream.Stream;

/*
*   handles game logic
 */

public class SetLogic {
    public static ArrayList<Card> inputs = new ArrayList<>();
    public static ArrayList<Card> cardsOnTable; //List to represent game cards on a "table"
    public static TreeMap<String,String> players = new TreeMap<>();
    static Deck deck;
    public static int setsFound = 0;
    public static double startTime = System.currentTimeMillis();
    public static void main(String[] args){
        GUI.initiate(args);
    }

    public static void initiateSinglePlayerGame(){
        deck = new Deck();
        startTime = System.currentTimeMillis();
        setsFound = 0;

        cardsOnTable = deck.draw(12);
        putCardsOnTable();

        GUI.startGame();
    }

    public static void addPlayer(String player, String key){
        boolean error = false;
        GUI.setErrorLabel("keyErrorLabel", "");
        GUI.setErrorLabel("playerErrorLabel", "");

        if(key.length() != 1){
            GUI.setErrorLabel("keyErrorLabel", "Key MUST be of length 1");
            error = true;
        }
        if(players.containsKey(key)){
            GUI.setErrorLabel("keyErrorLabel", "Key is already taken");
            error = true;
        }
        if(players.containsValue(player)){
            GUI.setErrorLabel("playerErrorLabel", "Player name already exists");
            error = true;
        }
        if(player.isEmpty()){
            GUI.setErrorLabel("playerErrorLabel", "Player name cannot be empty");
            error = true;
        }
        if (error) return;

        GUI.clearInputFields();

        players.put(key, player);
        GUI.setPlayerList(players);
    }

    public static void resetPlayerList(){
        players.clear();
        GUI.setPlayerList(players);
    }

    public static void initiateMultiPlayerGame(){
        deck = new Deck();
    }


    public static String submitInputs(){
        if (inputs.size() != 3){
            return "Please select exactly 3 cards";
        }
        if(checkForSet(inputs.get(0),inputs.get(1),inputs.get(2))){
            cardsOnTable.removeAll(inputs);
            inputs.clear();
            putCardsOnTable();
            setsFound++;
            return "you found a set";
        }
        inputs.clear();
        return "no set";
    }

    public static void playerTurn(){

    }

    public static boolean checkForSet(Card c1, Card c2, Card c3){
        /*
        *   adds the attributes of 3 cards to HashSets.
        *   HashSet has a size of 2 -> the 3 cards cannot be a set -> return false
         */
        HashSet<Colour> colours = new HashSet<>(Arrays.asList(c1.getColour(), c2.getColour(), c3.getColour()));
        if (colours.size() == 2){
            return false;
        }
        HashSet<Shape> shapes = new HashSet<>(Arrays.asList(c1.getShape(), c2.getShape(), c3.getShape()));
        if(shapes.size() == 2){
            return false;
        }
        HashSet<Shading> shadings = new HashSet<>(Arrays.asList(c1.getShading(), c2.getShading(), c3.getShading()));
        if(shadings.size() == 2){
            return false;
        }
        HashSet<Number> numbers = new HashSet<>(Arrays.asList(c1.getNumber(), c2.getNumber(), c3.getNumber()));
        if(numbers.size() == 2){
            return false;
        }
        return true;
    }

    public static void putCardsOnTable(){
        while(!setOnTable()){
            //no set on the "table" and the deck is empty -> no possible set is left to find -> end the game
            if(Deck.deck.isEmpty()){
                GUI.endGame();
                break;
            }
            //if there are less than 12 cards on the "table" fill up to 12 cards, add 3 if there are already 12 cards
            cardsOnTable = cardsOnTable.size() < 12 ?
                    new ArrayList<>(Stream.concat(cardsOnTable.stream(), deck.draw(12 - cardsOnTable.size()).stream()).toList())
                    : new ArrayList<>(Stream.concat(cardsOnTable.stream(), deck.draw(3).stream()).toList());
        }
    }

    public static boolean setOnTable(){
        //checks if any set is on the "table"
        for (int i=0; i<cardsOnTable.size();i++){
            for(int j=1; j<cardsOnTable.size();j++){
                //if any card is compared to itself skip this iteration
                if (j==i){
                    continue;
                }
                for(int k=2; k<cardsOnTable.size();k++){
                    //if any card is compared to itself skip this iteration
                    if (j==k || i==k){
                        continue;
                    }
                    if (checkForSet(cardsOnTable.get(i),cardsOnTable.get(j),cardsOnTable.get(k))){
                        //System.out.println(i+" "+j+" "+k);
                        //^^^^ only for debugging
                        return true;
                    }
                }
            }
        }
        //no set was found
        return false;
    }
}