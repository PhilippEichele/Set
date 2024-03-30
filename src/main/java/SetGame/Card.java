package SetGame;

import Attributes.Colour;
import Attributes.Number;
import Attributes.Shading;
import Attributes.Shape;

public record Card(Shading shading, Shape shape, Number number, Colour colour, String img) {

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

    public String getImg(){
        return img;
    }
}