package com.mystudio.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import org.graalvm.compiler.debug.CSVUtil;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.Transition;
import org.mini2Dx.core.screen.transition.FadeInTransition;
import org.mini2Dx.core.screen.transition.FadeOutTransition;

import java.security.Key;

/**
 * The GameScreen generates the screen for playing pong itself and manages it
 * @author bwecke
 */
public class GameScreen extends BasicGameScreen {
    public static int ID = 3;
    Ball ball;
    private Score score;
    private LeftPlatform leftPlatform;
    private RightPlatform rightPlatform;
    private Collision collision;

    //private boolean init;

    /**
     * Generates a new ball, score, a left and right platform and a collision class
     * @param gc The {@link GameContainer} of the game
     */
    @Override
    public void initialise(GameContainer gc) {
        //init = false;
        score = new Score();
        //score.initialise();
        ball = new Ball();
        //ball.initialise();
        leftPlatform = new LeftPlatform();
        //leftPlatform.initialise();
        rightPlatform = new RightPlatform();
        //rightPlatform.initialise();
        collision = new Collision(leftPlatform, rightPlatform, ball, score);
    }

    /**
     * Calls update methods for ball, left and right platform, and for the collision class
     * @param gc The {@link GameContainer} of the game
     * @param screenManager The {@link ScreenManager} of the game
     * @param delta The time in seconds since the last update
     */
    @Override
    public void update(GameContainer gc, ScreenManager<? extends org.mini2Dx.core.screen.GameScreen> screenManager, float delta) {
        /*if (init) {
            this.initialise(gc);
        }*/

        ball.update();
        leftPlatform.update();
        rightPlatform.update();
        collision.checkCollision();
        exitGameScreen(screenManager);
    }

    /**
     * Calls interpolate methods for ball, left and right platform
     * @param gc
     * @param alpha The interpolation alpha value
     */
    @Override
    public void interpolate(GameContainer gc, float alpha) {
        ball.interpolate(alpha);
        leftPlatform.interpolate(alpha);
        rightPlatform.interpolate(alpha);
    }

    /**
     * Renders the game field
     * @param gc The {@link GameContainer} of the game
     * @param g The {@link Graphics} context available for rendering
     */
    @Override
    public void render(GameContainer gc, Graphics g) {
        generateHyphen(g);
        score.render(g);
        ball.render(g);
        leftPlatform.render(g);
        rightPlatform.render(g);
    }

    /**
     * Returns ID form GameScreen
     * @return ID
     */
    @Override
    public int getId() {
        return ID;
    }

    /**
     * Generates the hyphen in the middle of the screen
     * @param g
     */
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

    private void exitGameScreen(ScreenManager screenManager) {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            screenManager.enterGameScreen(MainScreen.ID, new FadeOutTransition(), new FadeInTransition());
            //init = true;
        }
    }

    @Override
    public void preTransitionIn(Transition transitionIn) {
            ball.initialise();
            score.initialise();
            leftPlatform.initialise();
            rightPlatform.initialise();
    }
}
