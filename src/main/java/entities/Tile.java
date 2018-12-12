package entities;

import messaging.Message;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

import java.util.ArrayList;

public class Tile extends Entity{
	
	private float x, y;
	private Image image;
	
	public Tile(Image image, float x, float y)
	{
		this.image = image;
		this.x = x;
		this.y = y;
	}

	public void init(GameContainer gc) throws SlickException {

	}

	public void update(GameContainer gc, float delta) throws SlickException
	{

	}

	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		if(x + image.getWidth() < -300)
			x += image.getWidth()*8;
		if(y + image.getHeight() < -300)
			y += image.getHeight()*7;
		
		g.drawImage(image, x, y);
	}

	public void onMessage(Message msg) {

	}
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getWidth(){
		return image.getWidth();
	}
	
	public float getHeight() {
		return image.getHeight();
	}
}
