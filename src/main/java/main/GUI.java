package main;

import entities.player.Player;
import org.newdawn.slick.*;
import shop.MyFont;

public class GUI {

    private Image heart;
    private Player player;
    private UnicodeFont guiFont;

    public GUI(Player player){
        this.player = player;
    }

    public void init(GameContainer gc) throws SlickException{
        heart = new Image("heart.png");
        guiFont = new MyFont(20).getUniFont();
    }

    public void render(GameContainer gc, Graphics g){
        g.setFont(guiFont);
        for(int i=0; i<player.getLife()/10; i++){
            g.drawImage(heart, 25 + (i*(heart.getWidth()+5)), 25);
            g.setColor(Color.white);
            g.drawString("Scrap Metal - " + player.getScrapMetal(), gc.getWidth() - 200, 10);
        }
    }
}
