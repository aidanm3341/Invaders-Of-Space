package entities.weapons;

import entities.emitters.BulletEmitter;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;

public class WeaponFactory {
    public Weapon basicWeapon(Vector2f origin){
        return new Weapon(origin) {
            @Override
            protected Image getImage() throws SlickException {
                return new Image("particles/laser.png");
            }

            @Override
            protected BulletEmitter createBulletEmitter() throws SlickException {
                return new BulletEmitter(120, 10, new Image("particles/bullet4.png"));
            }
        };
    }

    public Weapon advancedWeapon(Vector2f origin){
        return new Weapon(origin) {
            @Override
            protected Image getImage() throws SlickException {
                return new Image("particles/laser.png");
            }

            @Override
            protected BulletEmitter createBulletEmitter() throws SlickException {
                return new BulletEmitter(50, 6, new Image("particles/bullet.png"));
            }
        };
    }
}
