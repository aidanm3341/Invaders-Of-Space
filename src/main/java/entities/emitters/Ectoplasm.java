package entities.emitters;

import java.util.Random;

import messaging.Message;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

public class Ectoplasm extends Particle
{
	private Image image;
	private Random rand;
	private float alphaValue = 0.7f;
	private Color color;
	
	public Ectoplasm(float x, float y, Color color)
	{
		super(x, y, 0);
		rand = new Random();
		life = 100 + rand.nextInt(150);
		
		this.x = x + rand.nextInt(50)-25;
		this.y = y + rand.nextInt(50)-25;
		
		this.angle = rand.nextInt(360);
		angularVelocity = 0.1f;
		
		if(rand.nextInt(3) == 1)
			this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), alphaValue);
		else
			this.color = new Color(106, 0, 0, alphaValue);
		
		try {
			image = new Image("blankParticle.png");
		}catch(SlickException e){e.printStackTrace();}

		image = image.getScaledCopy(0.5f);
		image.setImageColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		
		life = 100 + rand.nextInt(150);
		
		velX = (rand.nextFloat() + rand.nextFloat() - 1)/6;
		velY = (rand.nextFloat() + rand.nextFloat() - 1)/6;
		
		accel = 0.0001f;
	}

	public void init(GameContainer gc) throws SlickException {

	}

	public void update(GameContainer gc, float delta)
	{	
		if(velX < 0){
			velX += accel*delta;
		}
		else if(velX > 0){
			velX -= accel*delta;
		}
		if(velY < 0){
			velY += accel*delta;
		}
		else if(velY > 0){
			velY -= accel*delta;
		}
		
		x += velX*delta;
		y += velY*delta;
		
		angle += angularVelocity;
		life--;
		
		if(alphaValue > 0.05f){
			alphaValue -= 0.003f;
			image.setImageColor(color.getRed(), color.getGreen(), color.getBlue(), alphaValue);
		}
	}

	public void render(GameContainer gc, Graphics g) 
	{
		g.drawImage(image, x, y);
	}

	public void onMessage(Message msg) {

	}

}
