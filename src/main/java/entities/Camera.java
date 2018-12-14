package entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Camera{

    private static Entity focus;
    private static float scalingFactor;
    private static float x, y, width, height;
    private static float translationX, translationY;

    public Camera(Entity focus){
        scalingFactor = 1f;
        this.focus = focus;
        x = focus.getX()*scalingFactor;
        y = focus.getY()*scalingFactor;
    }

    public static void cameraAction(GameContainer gc, Graphics g) {
        x = focus.getX()*scalingFactor;
        y = focus.getY()*scalingFactor;
        width = gc.getWidth()*scalingFactor;
        height = gc.getHeight()*scalingFactor;

        if(x < 0 + width - focus.getWidth()/4)
            translationX = 0;
        else if(x > Arena.WIDTH*scalingFactor - width - focus.getWidth()/4)
            translationX = -(Arena.WIDTH*scalingFactor - 2*width);
        else
            translationX = -x + gc.getWidth()/2 - focus.getWidth()/4;


        if(y < 0 + height - focus.getHeight()/4)
            translationY = 0;
        else if(y > Arena.HEIGHT*scalingFactor - height - focus.getHeight()/4)
            translationY = -(Arena.HEIGHT*scalingFactor - 2*height);
        else
            translationY = -y + gc.getHeight()/2 - focus.getHeight()/4;


        g.translate(translationX, translationY);
        g.scale(scalingFactor, scalingFactor);
    }

    public static void setScalingFactor(float newScalingFactor)
    {
        scalingFactor = newScalingFactor;
    }

    public static float getScalingFactor() {
        return scalingFactor;
    }

    public static float getX() {
        return x;
    }
    public static float getY() {
        return y;
    }

    public static float getWidth() {
        return width;
    }
    public static float getHeight() {
        return height;
    }


    public static float convertActualXToGameX(float oldX) {
        return oldX - translationX;
    }

    public static float convertActualYToGameY(float oldY) {
        return oldY - translationY;
    }
}
