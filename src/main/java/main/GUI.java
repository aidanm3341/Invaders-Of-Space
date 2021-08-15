package main;

import entities.player.Player;
import org.newdawn.slick.*;
import shop.MyFont;

public class GUI {

    private static final float HEALTHBAR_X = 25;
    private static final float HEALTHBAR_Y = 25;
    private Image healthbarLeft, healthbarRight, healthbarTopAndBottom, healthbarHealth;
    private Player player;
    private UnicodeFont guiFont;

    public GUI(Player player){
        this.player = player;
    }

    public void init(GameContainer gc) throws SlickException{
        healthbarLeft = new Image("healthbar/healthbar_left.png");
        healthbarRight = new Image("healthbar/healthbar_right.png");
        healthbarTopAndBottom = new Image("healthbar/healthbar_top_and_bottom.png");
        healthbarHealth = new Image("healthbar/healthbar_health.png");
        guiFont = new MyFont(20).getUniFont();
    }

    public void render(GameContainer gc, Graphics g){
        g.setFont(guiFont);
        g.drawImage(healthbarLeft, HEALTHBAR_X, HEALTHBAR_Y);

        // repeat top and bottom however many times is needed
        for(int i=0; i<player.getStats().getMaxLife()/10; i++)
            g.drawImage(healthbarTopAndBottom, HEALTHBAR_X + (i*healthbarTopAndBottom.getWidth()), HEALTHBAR_Y);

        // render the actual current health
        for(int i=0; i<player.getStats().getCurrentLife()/10; i++)
            g.drawImage(healthbarHealth, HEALTHBAR_X + (i*healthbarTopAndBottom.getWidth()), HEALTHBAR_Y);

        g.drawImage(healthbarRight,
                HEALTHBAR_X + (healthbarHealth.getWidth() * (player.getStats().getMaxLife()/10)),
                HEALTHBAR_Y);

        g.setColor(Color.white);
        g.drawString("Scrap Metal - " + player.getScrapMetal(), gc.getWidth() - 200, 10);
    }
}
