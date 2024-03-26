package SetGame;

public record Card(Shading shading, Shape shape, Number number, Colour colour) {

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