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

    public static void cameraAction(GameContainer gc, Graphics g){
        cameraActionMultipliedBy(gc, g, 1);
    }

    public static void cameraCut(GameContainer gc, Graphics g){
        cameraCutMultipliedBy(gc, g, 1);
    }

    public static void cameraActionMultipliedBy(GameContainer gc, Graphics g, float scale) {
        x = focus.getX()*scalingFactor;
        y = focus.getY()*scalingFactor;
        width = gc.getWidth();
        height = gc.getHeight();

        if(x < 0 + width/2)
            translationX = 0;
        else if(x > Arena.WIDTH*scalingFactor - width/2)
            translationX = -(Arena.WIDTH*scalingFactor - width);
        else
            translationX = -x + width/2;


        if(y < 0 + height/2)
            translationY = 0;
        else if(y > Arena.HEIGHT*scalingFactor - height/2 )
            translationY = -(Arena.HEIGHT*scalingFactor - height);
        else
            translationY = -y + height/2;


        g.translate(translationX*scale, translationY*scale);
    }

    public static void cameraActionScale(Graphics g){
        g.scale(scalingFactor, scalingFactor);
    }

    public static void cameraCutScale(Graphics g){
        g.scale(1/scalingFactor, 1/scalingFactor);
    }

    public static void cameraCutMultipliedBy(GameContainer gc, Graphics g, float scale) {
        g.translate(-translationX*scale, -translationY*scale);
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
