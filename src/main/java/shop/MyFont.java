package shop;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import java.awt.*;
import java.io.IOException;

public class MyFont {

    private float size;
    private UnicodeFont uniFont;

    public MyFont(float size) {
        this.size = size;
        Font newFont = null;
        try {
            newFont = Font.createFont(Font.TRUETYPE_FONT, org.newdawn.slick.util.ResourceLoader.getResourceAsStream("a-astro-space-font/AstroSpace-0Wl3o.otf"));
            Font javaFont = newFont.deriveFont(size);

            uniFont = new UnicodeFont(javaFont);
            uniFont.addAsciiGlyphs();

            ColorEffect a = new ColorEffect();
            a.setColor(Color.white);

            uniFont.getEffects().add(a);
            uniFont.addAsciiGlyphs();
            uniFont.loadGlyphs();
        } catch (FontFormatException | IOException | SlickException e) {
            e.printStackTrace();
        }
    }

    public UnicodeFont getUniFont() {
        return uniFont;
    }
}
