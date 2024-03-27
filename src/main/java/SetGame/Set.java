package SetGame;

import Attributes.Colour;
import Attributes.Number;
import Attributes.Shading;
import Attributes.Shape;
import GUI.GUI;

import java.util.*;
import java.util.stream.Stream;

public class Set{
    static ArrayList<Card> cardsOnTable;
    static Deck deck;
    static Scanner s;
    public static void main(String[] args){
        GUI.startGame(args);
        gameloop();
    }

    public static void gameloop(){
        deck = new Deck();
        s = new Scanner(System.in);
        deck.shuffle();
        cardsOnTable = deck.draw(12);

        while(deck.decksize() > 0 || cardsOnTable.size() > 0){
            System.out.println("Cards left in deck: "+deck.decksize());
            while(!setOnTable()){
                if (deck.decksize() == 0){
                    System.out.println("out of deck cards and no set left");
                    System.exit(0);
                }
                cardsOnTable = cardsOnTable.size() < 12 ?
                        new ArrayList<>(Stream.concat(cardsOnTable.stream(), deck.draw(12 - cardsOnTable.size()).stream()).toList())
                        : new ArrayList<>(Stream.concat(cardsOnTable.stream(), deck.draw(3).stream()).toList());
            }

            for(int i = 0;i< cardsOnTable.size();i++){
                System.out.print(i+": ");
                printCard(cardsOnTable.get(i));
            }
            try{
                Card[] choices = new Card[]{cardsOnTable.get(s.nextInt()), cardsOnTable.get(s.nextInt()), cardsOnTable.get(s.nextInt())};

                if(checkForSet(choices[0],choices[1],choices[2])){
                    System.out.println("SET");
                    cardsOnTable.removeAll(Arrays.asList(choices));
                }else{
                    System.out.println("no set");
                }
            }catch(InputMismatchException IME){
                System.out.println("inputs must be Integers\n");
                s.next();
            }catch(IndexOutOfBoundsException IOOBE){
                System.out.println("number not on table\n");
                s.next();
            }

        }
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

    public static void printCard(Card c){
        System.out.println(c.getShading()+" "+c.getShape()+" "+c.getColour()+" "+c.getNumber());
    }
}