package entities.emitters;

import entities.EntityType;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;

public class Bullet extends Particle {

	private static final float BULLET_SPEED = 1f;
	private Image image;
	private float damage;
	
	public Bullet(float x, float y, float angle, int life, float damage, Image image){
		super(x, y, life);
		this.angle = angle;
		this.damage = damage;

		this.image = image.copy();

		this.isCollidable = true;

		float[] vertices = new float[]{
				0,0,
				24,0,
				24,24,
				0,24
		};

		body = new Polygon(vertices);

		body = (Polygon) body.transform(Transform.createRotateTransform(angle));
		this.image.setRotation((float) Math.toDegrees(angle));

		velX = (float) Math.cos(angle-Math.toRadians(90)) * BULLET_SPEED;
		velY = (float) Math.sin(angle-Math.toRadians(90)) * BULLET_SPEED;
	}

	public void init(GameContainer gc) {

	}

	public void update(GameContainer gc, float delta) throws SlickException {
		delta *= 0.8f;

		body.setCenterX(x+(float) (100*Math.cos(angle-Math.toRadians(90))));
		body.setCenterY(y+(float) (100*Math.sin(angle-Math.toRadians(90))));
		
		x += velX*delta;
		y += velY*delta;
		life-= delta;
	}
	
	public void render(GameContainer gc, Graphics g) {
		g.drawImage(image, body.getCenterX()-image.getWidth()/2, body.getCenterY()-image.getHeight()/2);
	}

	public void destroy(){
		life = 0;
	}

	public float getDamage(){
	    return damage;
    }

	@Override
	public EntityType getType() {
		return EntityType.BULLET;
	}
}
