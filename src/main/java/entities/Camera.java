package entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Camera{

    private Entity focus;
    private float scalingFactor;
    private float x, y, width, height;

    public Camera(Entity focus){
        scalingFactor = 1f;
        this.focus = focus;
        x = focus.getX()*scalingFactor;
        y = focus.getY()*scalingFactor;
    }

    public void cameraAction(GameContainer gc, Graphics g) {
        x = focus.getX()*scalingFactor;
        y = focus.getY()*scalingFactor;
        width = gc.getWidth()*scalingFactor;
        height = gc.getHeight()*scalingFactor;

        g.translate(-x + gc.getWidth()/2 - focus.getWidth()/4,
                -y + gc.getHeight()/2 - focus.getHeight()/4);
        g.scale(scalingFactor, scalingFactor);
    }

    public void setScalingFactor(float scalingFactor)
    {
        this.scalingFactor = scalingFactor;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
