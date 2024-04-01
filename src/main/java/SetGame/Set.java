package SetGame;

import Attributes.Colour;
import Attributes.Number;
import Attributes.Shading;
import Attributes.Shape;
import GUI.GUI;

import java.util.*;
import java.util.stream.Stream;

public class Set{
    public static ArrayList<Card> inputs = new ArrayList<>();
    public static ArrayList<Card> cardsOnTable;
    static Deck deck;
    public static int setsFound;
    public static double startTime;
    public static void main(String[] args){
        GUI.initiate(args);
    }

    public static void addInput(int inpt){
        inputs.add(cardsOnTable.get(inpt));
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
            return "SET";
        }
        inputs.clear();
        return "no set";
    }

    public static void initiateGame(){
        deck = new Deck();
        deck.shuffle();
        startTime = System.currentTimeMillis();
        setsFound = 0;

        cardsOnTable = deck.draw(12);
        putCardsOnTable();
    }

    public static boolean checkForSet(Card c1, Card c2, Card c3){
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
            if(Deck.deck.isEmpty()){
                GUI.endGame();
                break;
            }
            cardsOnTable = cardsOnTable.size() < 12 ?
                    new ArrayList<>(Stream.concat(cardsOnTable.stream(), deck.draw(12 - cardsOnTable.size()).stream()).toList())
                    : new ArrayList<>(Stream.concat(cardsOnTable.stream(), deck.draw(3).stream()).toList());
        }
    }

    public static boolean setOnTable(){
        for (int i=0; i<cardsOnTable.size();i++){
            for(int j=1; j<cardsOnTable.size();j++){
                if (j==i){
                    continue;
                }
                for(int k=2; k<cardsOnTable.size();k++){
                    if (j==k || i==k){
                        continue;
                    }
                    if (checkForSet(cardsOnTable.get(i),cardsOnTable.get(j),cardsOnTable.get(k))){
                        System.out.println(i+" "+j+" "+k);
                        //^^^^ only for debugging
                        return true;
                    }
                }
            }
        }
        return false;
    }
}