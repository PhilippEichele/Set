package SetGame;

public class Card {

    private Shading shading;
    private Shape shape;
    private Number number;
    private Colour colour;
    public Card(Shading shading, Shape shape, Number number, Colour colour) {
        this.shading = shading;
        this.shape = shape;
        this.number = number;
        this.colour = colour;
    }

    public Shading getShading() {
        return shading;
    }

    public Shape getShape() {
        return shape;
    }

    public Number getNumber() {
        return number;
    }

    public Colour getColour() {
        return colour;
    }
}