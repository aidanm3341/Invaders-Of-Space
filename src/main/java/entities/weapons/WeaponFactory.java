package entities.weapons;

import entities.emitters.BulletEmitter;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;

public class WeaponFactory {
    public Weapon basicWeapon(Point origin){
        return new Weapon(origin) {
            @Override
            protected Image getImage() throws SlickException {
                return new Image("particles/laser.png");
            }

            @Override
            protected BulletEmitter getBulletEmitter() throws SlickException {
                return new BulletEmitter(100, 10, new Image("particles/bullet4.png"));
            }
        };
    }

    public Weapon advancedWeapon(Point origin){
        return new Weapon(origin) {
            @Override
            protected Image getImage() throws SlickException {
                return new Image("particles/laser.png");
            }

            @Override
            protected BulletEmitter getBulletEmitter() throws SlickException {
                return new BulletEmitter(50, 7, new Image("particles/bullet.png"));
            }
        };
    }
}
