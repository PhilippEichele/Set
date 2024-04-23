package SetGame;

import Attributes.Colour;
import Attributes.Number;
import Attributes.Shading;
import Attributes.Shape;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    public static ArrayList<Card> deck = new ArrayList<>();

    public Deck(){
        deck.clear();

        //creates a card for any possible combination of attributes -> Deck of 81 cards
        for(Shape shape : Shape.values()){
            for(Shading shading : Shading.values()){
                for(Number number : Number.values()){
                    for(Colour colour : Colour.values()){
                        deck.add(new Card(shading, shape, number, colour, "s"+number.ordinal()+shading.ordinal()+colour.ordinal()+shape.ordinal()+".png"));
                    }
                }
            }
        }
        Collections.shuffle(deck);
    }

    public ArrayList<Card> draw(int amt){
        /*
        * "draws" a certain amount of cards from the deck and returns them as a list
         */

        //limits amount of cards to draw to the amount of cards on the deck
        amt = Math.min(amt, deck.size());

        //gets cards to draw from the deck and deletes them from the deck
        ArrayList<Card> temp = new ArrayList <> (deck.subList(0,amt));
        deck.subList(0,amt).clear();

        return temp;
    }
}