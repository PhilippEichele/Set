package SetGame;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<>();

    public Deck(){
        for(Shape shape : Shape.values()){
            for(Shading shading : Shading.values()){
                for(Number number : Number.values()){
                    for(Colour colour : Colour.values()){
                        deck.add(new Card(shading, shape, number, colour));
                    }
                }
            }
        }
    }

    public ArrayList<Card> getDeck(){
        return deck;
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public ArrayList<Card> draw(int amt){
        amt = Math.min(amt, deck.size());
        ArrayList<Card> temp = new ArrayList <Card> (deck.subList(0,amt));
        deck.subList(0,amt).clear();
        return temp;
    }

    public int decksize(){
        return deck.size();
    }
}