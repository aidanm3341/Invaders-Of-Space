package entities.emitters;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class EctoEmitter extends ParticleEmitter{
	
	public EctoEmitter(float x, float y, Color color, int size)
	{
		this.x = x;
		this.y = y;
		for(int i=0; i<size; i++)
		{
			addParticle(new Ectoplasm(x, y, color));
		}
	}

	public void init(GameContainer gc) { }

	public void update(GameContainer gc, float delta) throws SlickException
	{
		super.update(gc, delta);

		if(particles.isEmpty())
			entityManager.removeEntity(this);
	}

	public void render(GameContainer gc, Graphics g) throws SlickException { }
}
