package Game;

import Game.GameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.Mini2DxFreeTypeFontGenerator;
import org.mini2Dx.core.font.BitmapFont;
import org.mini2Dx.core.font.GameFont;
import org.mini2Dx.core.graphics.Graphics;

/**
 * The score class to save and present the points each player got
 */
public class Score implements GameObject {
    private GameFont gameFont;
    private int scoreLeft;
    private int scoreRight;

    /**
     * Sets the score for both player to 0 and loads a custom font
     */
    @Override
    public void initialise() {
        scoreLeft = 0;
        scoreRight = 0;
        generateFont();
    }

    @Override
    public void update() {}

    @Override
    public void interpolate(float alpha) {}

    /**
     * score is getting rendered
     * @param g
     */
    @Override
    public void render(Graphics g) {
        drawScore(g);
    }

    /**
     * Loads a custom .ttf gameFont from the assets
     */
    public void generateFont() {
        Mini2DxFreeTypeFontGenerator fontGenerator = new Mini2DxFreeTypeFontGenerator(Gdx.files.internal("retro.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 80;
        fontParameter.flip = true;
        Mini2DxFreeTypeFontGenerator.Mini2DxFreeTypeBitmapFontData freeTypeBitmapFontData = fontGenerator.generateFontData(fontParameter);
        gameFont = new BitmapFont(freeTypeBitmapFontData, freeTypeBitmapFontData.getRegions(), true);
    }

    /**
     * Draws the score left and right from the hyphen
     * @param g
     */
    public void drawScore(Graphics g) {
        g.setFont(gameFont);
        g.setColor(Color.WHITE);
        int offset = String.valueOf(scoreLeft).length();
        g.drawString(Integer.toString(scoreLeft), (float) Gdx.graphics.getWidth()/2 - (50 + 18*offset), 15);
        g.drawString(Integer.toString(scoreRight), (float) Gdx.graphics.getWidth()/2 + 26, 15);
    }

    /**
     * Increases left score by 1
     */
    public void upLeftScore() {
        scoreLeft++;
    }

    /**
     * Increases right score by1
     */
    public void upRightScore() {
        scoreRight++;
    }
}
