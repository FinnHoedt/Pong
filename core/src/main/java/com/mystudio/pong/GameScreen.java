package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;

public class GameScreen extends BasicGameScreen {
    public static int ID = 3;
    Ball ball;
    private Score score;
    @Override
    public void initialise(GameContainer gc) {
        score = new Score();
        score.initialise();
        ball = new Ball();
        ball.initialise();
    }

    @Override
    public void update(GameContainer gc, ScreenManager<? extends org.mini2Dx.core.screen.GameScreen> screenManager, float delta) {
        ball.update();
        
    }

    @Override
    public void interpolate(GameContainer gc, float alpha) {
        ball.interpolate(alpha);
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        generateHyphen(g);
        score.render(g);
        ball.render(g);
    }

    @Override
    public int getId() {
        return ID;
    }

    private void generateHyphen(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        int width = 8;
        int count = 12;
        int gap = 10;
        int height = (Gdx.graphics.getHeight() /count - gap);
        int x = (Gdx.graphics.getWidth()/2) - (width/2);
        int y;
        for (int i = 1; i <= count; i++) {
            y = (height+gap)*i - (height+gap/2);
            g.fillRect(x,y,width,height);
        }
    }
}
