package util;

import org.newdawn.slick.geom.Vector2f;

public class Vector2D extends Vector2f {

    public Vector2D(Vector2f v){
        super(v);
    }

    public Vector2D(float x, float y){
        super(x, y);
    }

    public Vector2D(){
        super();
    }

    public Vector2D limit(float limit){
        if(length() > limit){
            return new Vector2D(getNormal().scale(limit));
        }
        return this;
    }

    public void limitLocal(float limit){
        if(lengthSquared() > limit*limit){
            normalise();
            scale(limit);
        }
    }
}
