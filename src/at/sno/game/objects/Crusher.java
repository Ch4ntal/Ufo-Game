package at.sno.game.objects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Crusher extends spielobjekt{
    private Input input;
    private Rectangle shape;

    private float acceleration=0.1f;

    public Crusher(int x, int y, Image image, Input input) {
        super(x, y, image);
        this.input = input;
        shape = new Rectangle(x,y,image.getWidth(),image.getHeight());
    }

    @Override
    public void draw(Graphics g) {
        this.getImage().drawCentered(this.getX(),this.getY());
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    @Override
    public void update(int delta) {
        boolean pressed =false;

        if (input.isKeyDown(Input.KEY_LEFT) && ((this.getX()-delta-this.getWidth()/2) >0)){
            this.setX(this.getX()- (int)this.acceleration);
            pressed = true;
        }
        if (input.isKeyDown(Input.KEY_RIGHT) &&((this.getX()+delta+this.getWidth()/2)<1024 )){
            this.setX(this.getX()+(int)this.acceleration);
            pressed = true;
        }

        if (pressed) {
            acceleration+= delta;
            if (acceleration > 15) acceleration=15;
        }else {
            acceleration = 0.1f;
        }
        shape.setCenterX(this.getX());
        shape.setCenterY(this.getY());


    }

}
