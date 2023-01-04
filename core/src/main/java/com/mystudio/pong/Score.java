package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.Mini2DxFreeTypeFontGenerator;
import org.mini2Dx.core.font.BitmapFont;
import org.mini2Dx.core.font.GameFont;
import org.mini2Dx.core.font.GameFontCache;
import org.mini2Dx.core.graphics.Graphics;

public class Score implements GameObject {
    private GameFont gameFont;
    private int scoreLeft;
    private int scoreRight;

    @Override
    public void initialise() {
        scoreLeft = 0;
        scoreRight = 0;
    }

    @Override
    public void update() {

    }

    @Override
    public void interpolate(float alpha) {

    }

    @Override
    public void render(Graphics g) {
        drawScore(generateFont(), g);
    }

    public GameFont generateFont() {
        Mini2DxFreeTypeFontGenerator fontGenerator = new Mini2DxFreeTypeFontGenerator(Gdx.files.internal("assets/retro.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 80;
        fontParameter.flip = true;
        Mini2DxFreeTypeFontGenerator.Mini2DxFreeTypeBitmapFontData freeTypeBitmapFontData = fontGenerator.generateFontData(fontParameter);
        return gameFont = new BitmapFont(freeTypeBitmapFontData, freeTypeBitmapFontData.getRegions(), true);
    }

    public void drawScore(GameFont gameFont, Graphics g) {
        g.setFont(gameFont);
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(scoreLeft), (float) Gdx.graphics.getWidth()/2 - 50, 15);
        g.drawString(Integer.toString(scoreRight), (float) Gdx.graphics.getWidth()/2 + 26, 15);
    }

    public void upLeftScore() {
        scoreLeft++;
    }

    public void upRightScore() {
        scoreRight++;
    }
}
