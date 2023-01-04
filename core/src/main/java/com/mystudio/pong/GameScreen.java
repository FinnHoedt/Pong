package com.mystudio.pong;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;

public class GameScreen extends BasicGameScreen {
    public static int ID = 3;
    Ball ball;
    @Override
    public void initialise(GameContainer gc) {
        ball = new Ball();
        ball.initialise();
    }

    @Override
    public void update(GameContainer gc, ScreenManager<? extends org.mini2Dx.core.screen.GameScreen> screenManager, float delta) {
        ball.update();
    }

    @Override
    public void interpolate(GameContainer gc, float alpha) {
        ball.interpolate( alpha);
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        ball.render(g);
    }

    @Override
    public int getId() {
        return ID;
    }

}
