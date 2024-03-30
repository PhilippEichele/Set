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
        for(Shape shape : Shape.values()){
            for(Shading shading : Shading.values()){
                for(Number number : Number.values()){
                    for(Colour colour : Colour.values()){
                        deck.add(new Card(shading, shape, number, colour, (shading.name()+shape.name()+number.name()+colour.name()).toLowerCase()+".JPG"));
                    }
                }
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public ArrayList<Card> draw(int amt){
        amt = Math.min(amt, deck.size());
        ArrayList<Card> temp = new ArrayList <> (deck.subList(0,amt));
        deck.subList(0,amt).clear();
        return temp;
    }
}