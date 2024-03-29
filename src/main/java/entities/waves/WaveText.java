package entities.waves;

import entities.Entity;
import entities.EntityType;
import org.newdawn.slick.*;

public class WaveText extends Entity{

    private SpriteSheet numberSheet;
    private Image waveText;
    private int sheetX, sheetY;
    private float alpha;
    private int counter, counterMax;

    public WaveText(int number) {
        try {
            numberSheet = new SpriteSheet("waveNumbers.png", 130, 226);
            waveText = new Image("waveText.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
        sheetX = number % 5;
        sheetY = number < 5 ? 0 : 1;

        alpha = 0.8f;
        counter = 0;
        counterMax = 500;
    }

    public void init(GameContainer gc){

    }

    public void update(GameContainer gc, float delta) {
        if(counter >= counterMax) {
            if (alpha > 0)
                alpha -= 0.002f;
        }
        counter++;
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        waveText.draw(140, 238, new Color(1, 1, 1, alpha));
        numberSheet.getSprite(sheetX, sheetY).draw(840, 238, new Color(1, 1, 1, alpha));
    }

    @Override
    public EntityType getType() {
        return EntityType.NON_COLLIDING;
    }
}
