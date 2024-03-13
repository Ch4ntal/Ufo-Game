package at.sno.newgame;

import at.sno.game.objects.spielobjekt;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;


    public class nyancat extends spielobjekt {
        private Input input;
        private Rectangle shape;
        private float acceleration = 0.1F;

        public nyancat(int x, int y, Image image, Input input) {
            super(x, y, image);
            this.input = input;
            this.shape = new Rectangle((float) x, (float) y, (float) image.getWidth(), (float) image.getHeight());
        }

        public void draw(Graphics g) {
            this.getImage().drawCentered((float) this.getX(), (float) this.getY());
        }


        public void update(int delta) {
            boolean pressed = false;
            if (this.input.isKeyDown(203) && this.getY() - delta - this.getHeight() / 2 > 0) {
                this.setX(this.getX() - (int) this.acceleration);
                pressed = true;
            }

            if (this.input.isKeyDown(205) && this.getY() + delta + this.getHeight() / 2 < 1024) {
                this.setY(this.getY() + (int) this.acceleration);
                pressed = true;
            }

            if (pressed) {
                this.acceleration += (float) delta;
                if (this.acceleration > 50.0F) {
                    this.acceleration = 50.0F;
                }
            } else {
                this.acceleration = 0.1F;
            }

            this.shape.setCenterX((float) this.getX());
            this.shape.setCenterY((float) this.getY());
        }


        @Override
        public Shape getShape() {
            return this.shape;
        }



        public boolean intersects(Shape shape) {
            if (shape != null) {
                return this.getShape().intersects(shape);
            }
            return false;
        }



}