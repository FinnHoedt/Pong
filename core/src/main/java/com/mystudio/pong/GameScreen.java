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
    private LeftPlatform leftPlatform;
    private RightPlatform rightPlatform;
    private Collision collision;
    private Flash flash; private SplitBall split; private BiggerPlatform grow;
    //private PowerUp[] powerUp = {flash,split,grow};

    @Override
    public void initialise(GameContainer gc) {
        score = new Score();
        score.initialise();
        ball = new Ball();
        ball.initialise();
        leftPlatform = new LeftPlatform();
        leftPlatform.initialise();
        rightPlatform = new RightPlatform();
        rightPlatform.initialise();

        flash = new Flash();
        flash.initialise();
        split = new SplitBall();
        split.initialise();
        grow = new BiggerPlatform();
        grow.initialise();
        collision = new Collision(leftPlatform, rightPlatform, ball, score, flash, split, grow);
    }

    @Override
    public void update(GameContainer gc, ScreenManager<? extends org.mini2Dx.core.screen.GameScreen> screenManager, float delta) {
        ball.update();
        leftPlatform.update();
        rightPlatform.update();
        collision.checkCollision();
        flash.update();
        split.update();
        grow.update();
    }

    @Override
    public void interpolate(GameContainer gc, float alpha) {
        ball.interpolate(alpha);
        leftPlatform.interpolate(alpha);
        rightPlatform.interpolate(alpha);
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        generateHyphen(g);
        score.render(g);
        ball.render(g);
        leftPlatform.render(g);
        rightPlatform.render(g);
        flash.render(g);
        split.render(g);
        grow.render(g);
    }

    @Override
    public int getId() {
        return ID;
    }

    private void generateHyphen(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        float width = 8;
        float count = 12;
        float gap = 10;
        float height = (Gdx.graphics.getHeight() /count - gap);
        float x = (Gdx.graphics.getWidth()/2) - (width/2);
        float y;
        for (int i = 1; i <= count; i++) {
            y = (height+gap)*i - (height+gap/2);
            g.fillRect(x,y,width,height);
        }
    }
}
