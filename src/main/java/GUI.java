import entities.Player;
import org.newdawn.slick.*;

public class GUI {

    private Image heart;
    private Player player;

    public GUI(Player player){
        this.player = player;
    }

    public void init(GameContainer gc) throws SlickException{
        heart = new Image("heart.png");
    }

    public void render(GameContainer gc, Graphics g){
        for(int i=0; i<player.getLife()/10; i++){
            g.drawImage(heart, 25 + (i*(heart.getWidth()+5)), 25);
            g.setColor(Color.white);
            g.drawString("Scrap Metal - " + player.getScrapMetal(), gc.getWidth() - 200, 10);
        }
    }
}
