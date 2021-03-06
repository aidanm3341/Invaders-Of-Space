package entities.emitters;

import entities.Entity;
import entities.EntityType;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

public abstract class Particle extends Entity {

	protected float x, y, velX, velY, angle, angularVelocity, accel;
	protected int life;

	public Particle(float x, float y, int life)
	{
		this.x = x;
		this.y = y;
		this.life = life;
	}
	
	public abstract void update(GameContainer gc, float delta) throws SlickException;
	
	public abstract void render(GameContainer gc, Graphics g);
	
	public void setVertices(float[] vertices)
	{
		body = new Polygon(vertices);
	}
	
	public int getLife()
	{
		return life;
	}
	
	public Polygon getPolygon()
	{
		return body;
	}

	@Override
	public EntityType getType() {
		return EntityType.NON_COLLIDING;
	}
}
