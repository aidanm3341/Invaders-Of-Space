package entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Camera{

    private static Entity focus;
    private static float focusWidth, focusHeight;
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
        width = gc.getWidth();
        height = gc.getHeight();
        focusWidth = focusWidth * scalingFactor;
        focusHeight = focusHeight * scalingFactor;

        if(x < 0 + width/2 - focusWidth/4)
            translationX = 0;
        else if(x > Arena.WIDTH*scalingFactor - width/2 - focusWidth/4)
            translationX = -(Arena.WIDTH*scalingFactor - width);
        else
            translationX = -x + width/2 - focusWidth/4;


        if(y < 0 + height/2 - focusHeight/4)
            translationY = 0;
        else if(y > Arena.HEIGHT*scalingFactor - height/2 - focusHeight/4)
            translationY = -(Arena.HEIGHT*scalingFactor - height);
        else
            translationY = -y + height/2 - focusHeight/4;


        g.translate(translationX, translationY);
        g.scale(scalingFactor, scalingFactor);
    }

    public static void cameraCut(GameContainer gc, Graphics g)
    {
        g.scale(1/scalingFactor, 1/scalingFactor);
        g.translate(-translationX, -translationY);
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
